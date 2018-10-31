package com.honglu.quickcall.account.service.bussService.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.entity.Advertisement;
import com.honglu.quickcall.account.facade.exchange.request.BarrageMessageRequest;
import com.honglu.quickcall.account.facade.exchange.request.FirstBarrageRequest;
import com.honglu.quickcall.account.facade.vo.BarrageMessageVO;
import com.honglu.quickcall.account.facade.vo.OrderDetailVO;
import com.honglu.quickcall.account.facade.vo.PopWindowVO;
import com.honglu.quickcall.account.service.bussService.BarrageMessageService;
import com.honglu.quickcall.account.service.dao.AdvertisementMapper;
import com.honglu.quickcall.account.service.dao.OrderMapper;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 弹幕消息接口实现类
 *
 * @author duanjun
 * @date 2018-10-18 9:13
 */
@Service
public class BarrageMessageServiceImpl implements BarrageMessageService {

	private final static Logger logger = LoggerFactory.getLogger(BarrageMessageServiceImpl.class);

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private AdvertisementMapper advertisementMapper;

	/**
	 * 弹幕消息队列redis key
	 */
	private static final String BARRAGE_MESSAGE_QUEUE_REDIS_KEY = "message:barrage_queue";
	/**
	 * 弹幕消息列表redis key
	 */
	private static final String BARRAGE_MESSAGE_LIST_REDIS_KEY = "message:barrage_list";
	/**
	 * 弹幕消息分布式锁redis key
	 */
	private static final String BARRAGE_MESSAGE_DISTRIBUTED_LOCK_REDIS_KEY = "message:barrage_distributed_lock";
	/**
	 * 弹幕消息最大队列树
	 */
	private static final Long MAX_QUEUE_NUM = 1000L;

	/**
	 * 每天每个用户只弹窗一次
	 */
	private static final String FirstPopWindowOnceKey = "everyday:PopWindow:once:";

	@Override
	public void lpushMessage(Long orderId) {
		try {
			logger.info("下单成功 --- 发送弹幕消息：{}", orderId);
			// 查询订单信息
			OrderDetailVO orderDetailVO = orderMapper.queryBarrageOrderInfo(orderId);

			// 封装弹幕消息
			BarrageMessageVO barrageMessageVO = new BarrageMessageVO();
			barrageMessageVO.setNickName("*" + orderDetailVO.getNickName().substring(1));
			barrageMessageVO.setHeadPortraitUrl(orderDetailVO.getHeadPortraitUrl());
			barrageMessageVO.setSkillId(orderDetailVO.getSkillItemId());
			barrageMessageVO.setProductName(orderDetailVO.getSkillName());
			barrageMessageVO.setOrderAmounts(orderDetailVO.getOrderAmount());
			barrageMessageVO.setOrderTime(orderDetailVO.getOrderTime());

			Jedis jedis = null;
			JedisPool db2_pool = null;
			try {
				db2_pool = JedisUtil.getJedisPoolDB2();
				jedis = db2_pool.getResource();
				Long total = jedis.lpush(BARRAGE_MESSAGE_QUEUE_REDIS_KEY, JSON.toJSONString(barrageMessageVO));
				if (total > MAX_QUEUE_NUM) {
					logger.info("弹幕消息队列数量大于最大配置值：{} ,弹出一条最久的消息弃用：", MAX_QUEUE_NUM,
							jedis.rpop(BARRAGE_MESSAGE_QUEUE_REDIS_KEY));
				}
			} catch (Exception e) {
				logger.error("订单Id：{}，入队弹幕消息异常：", orderId, e);
				db2_pool.returnBrokenResource(jedis);
			} finally {
				db2_pool.returnResource(jedis);
			}
		} catch (Exception e) {
			logger.error("订单Id：{}，下单成功后入队弹幕消息异常：", orderId, e);
		}
	}

	@Override
	public CommonResponse rpopMessage(BarrageMessageRequest request) {
		Jedis jedis = null;
		JedisPool db2_pool = null;
		try {
			db2_pool = JedisUtil.getJedisPoolDB2();
			jedis = db2_pool.getResource();

			// 选从缓存列表中取值
			String cacheList = jedis.get(BARRAGE_MESSAGE_LIST_REDIS_KEY);
			if (StringUtils.isNotBlank(cacheList)) {
				return ResultUtils.resultSuccess(JSON.parseArray(cacheList, BarrageMessageVO.class));
			}

			List<BarrageMessageVO> list = new ArrayList<>();
			// 从缓存队列里面取数据
			try {
				if (jedis.setnx(BARRAGE_MESSAGE_DISTRIBUTED_LOCK_REDIS_KEY, "1") == 1) {
					// 从队列里获取5个消息
					for (int i = 0; i < 5; i++) {
						String message = jedis.rpop(BARRAGE_MESSAGE_QUEUE_REDIS_KEY);
						if (message == null) {
							break;
						}
						BarrageMessageVO bean = JSON.parseObject(message, BarrageMessageVO.class);
						list.add(bean);
					}
				}
			} finally {
				// 删除锁
				jedis.del(BARRAGE_MESSAGE_DISTRIBUTED_LOCK_REDIS_KEY);
			}

			// 获取到数据后，先存入缓存，再返回
			if (list.size() > 0) {
				jedis.set(BARRAGE_MESSAGE_LIST_REDIS_KEY, JSON.toJSONString(list));
				// 过期时间设为 29秒过期
				jedis.expire(BARRAGE_MESSAGE_LIST_REDIS_KEY, 29);
				return ResultUtils.resultSuccess(list);
			}

			// 若获取不到数据，等待200毫秒再从redis缓存列表中获取
			Thread.sleep(200);
			cacheList = jedis.get(BARRAGE_MESSAGE_LIST_REDIS_KEY);
			if (StringUtils.isNotBlank(cacheList)) {
				return ResultUtils.resultSuccess(JSON.parseArray(cacheList, BarrageMessageVO.class));
			}
		} catch (Exception e) {
			logger.error("出队弹幕消息异常：", e);
			if(db2_pool != null){
				db2_pool.returnBrokenResource(jedis);
			}
		} finally {
			if(db2_pool != null) {
				db2_pool.returnResource(jedis);
			}
		}

		// 最终获取不到数据后 -- 返回空集合
		return ResultUtils.resultSuccess(Collections.emptyList());
	}

	@Override
	public CommonResponse popWindowOnce(FirstBarrageRequest request) {
		PopWindowVO vo = new PopWindowVO();
		String key = FirstPopWindowOnceKey + request.getDeviceId();
		String value = JedisUtil.get(key);
		if (StringUtils.isNotBlank(value)) {
			vo.setShowWindow(false);
		} else {
			vo.setShowWindow(true);
			JedisUtil.set(key, String.valueOf(request.getDeviceId()), getRemainSecondsOneDay(new Date()));
		}
		Advertisement advertisement = advertisementMapper.selectAdvertisement();
		if (advertisement != null) {
			vo.setHeadPortraitUrl(advertisement.getImageUrl());
			vo.setSourceUrl(advertisement.getUrl());
		} else {
			vo.setShowWindow(false);
		}
		return ResultUtils.resultSuccess(vo);
	}

	/**
	 * 当期时间离今天结束还有多少秒
	 * 
	 * @param currentDate
	 * @return
	 */
	private static Integer getRemainSecondsOneDay(Date currentDate) {
		LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).plusDays(1)
				.withHour(0).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
		return (int) seconds;
	}
}
