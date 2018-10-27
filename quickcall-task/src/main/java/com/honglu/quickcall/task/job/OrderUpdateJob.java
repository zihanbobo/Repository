package com.honglu.quickcall.task.job;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.TransferTypeEnum;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.task.dao.AccountMapper;
import com.honglu.quickcall.task.dao.TaskOrderMapper;
import com.honglu.quickcall.task.dao.TradeDetailMapper;
import com.honglu.quickcall.task.entity.TaskOrder;
import com.honglu.quickcall.task.entity.TradeDetail;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：订单状态更新
 * @Package: com.calf.job 
 * @author: chenliuguang   
 * @date: 2018年9月29日 下午2:35:31
 */
@Component
public class OrderUpdateJob {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderUpdateJob.class);


    @Autowired
    private TaskOrderMapper    taskOrderMapper;
    
    /**默认超时小时数*/
    private final static  Integer   END_OVER_TIME_HOUR = -12;
    /**默认超时分钟数*/
    private final static  Integer   RECEIVE_OVER_TIME_MINUTES = -15;
    /**立即服务超时分钟数*/
    private final static  Integer   START_OVER_TIME_MINUTES = -5;
    
    
    
    
    private  final static Integer  CANCEL_ONE =  1 ;
    private  final static Integer  CANCEL_TWO =  2 ;
    private  final static Integer  CANCEL_THREE =  3 ;
    private  final static Integer  CANCEL_FOUR =  4 ;

    
    //接单设置
    
    @Scheduled(cron = "0 * * * * ?")
    public void updateOrderStatus() {
    	LOGGER.info("=============修改订单状态自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.MINUTE, RECEIVE_OVER_TIME_MINUTES);
    		Date  endTime =  cal.getTime();
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_YES; 
    		//获取接单设置超时
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_RECEIVE;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_SYSTEM_NOT_RECEIVE;
			
    		
    		//大V未接单返回给账户金额  先退款，再更新状态
    		List<TaskOrder>  orderList = taskOrderMapper.queryReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		refundToCustomer(orderList,CANCEL_ONE);
    		
    		
    		taskOrderMapper.waittingReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		
			
			
			
			
			//大V未发起立即服务超时
    		cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
    		endTime =  cal.getTime();
    		queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START;
    		updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_NOT_START;
			
    		//大V未发起立即服务超时给账户金额
    		orderList = taskOrderMapper.queryReceiveOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
    		refundToCustomer(orderList,CANCEL_TWO);
    		
    		taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			
			
			
			
			
			//用户未接立即服务超时
			cal = Calendar.getInstance();
			cal.setTime(currTime);
			cal.add(Calendar.MINUTE, START_OVER_TIME_MINUTES);
			endTime =  cal.getTime();
			queryStatus = OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE;
			updateStatus = OrderSkillConstants.ORDER_STATUS_CANCEL_USER_NOT_ACCEPCT;
			
			
			//大V未发起立即服务超时给账户金额
			orderList = taskOrderMapper.queryStartOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			refundToCustomer(orderList,CANCEL_THREE);
			taskOrderMapper.startOrderOverTime(currTime, endTime, queryStatus, updateStatus, skillType);
			
			
			
			
			//叫醒自动转换为进行中状态
			//用户未接立即服务超时
			queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_WAITING_START;
			updateStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
			skillType = OrderSkillConstants.SKILL_TYPE_NO;
			taskOrderMapper.appointOrderGoing(currTime,endTime, queryStatus, updateStatus, skillType);
			//TODO  要给大V推送消息
			
//			cal = Calendar.getInstance();
//			
//			cal.add(Calendar.MINUTE, ORDER_END_TIME_MINUTES);
//			//叫醒自动转换为进行中状态
//			//用户未接立即服务超时
//			queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
//			updateStatus = OrderSkillConstants.ORDER_STATUS_FINISHED_USER_ACCEPCT;
//			taskOrderMapper.appointOrderFinish(currTime,endTime, queryStatus, updateStatus, skillType);
			
    	} catch (Exception e) {
    		LOGGER.error("job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============修改订单状态自动任务结束=================");
    }
    
    
    @Scheduled(cron = "30 0/30 * * * ?")
    public void updateOrderStatusAfter12Hour() {
    	LOGGER.info("=============修改订单状态自动任务开始=================");
    	try {
    		Date  currTime = new Date();
    		Calendar  cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.HOUR_OF_DAY, END_OVER_TIME_HOUR);
    		Date  endTime =  cal.getTime();
    		Integer skillType = OrderSkillConstants.SKILL_TYPE_YES; 
    		//获取接单设置超时
    		Integer  queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH;
    		Integer  updateStatus = OrderSkillConstants.ORDER_STATUS_FINISH_DV_FINISH;
    		
    		
    		//服务完成，大V金额冻结
    		List<TaskOrder>  orderList = taskOrderMapper.queryOrderStatusAfter12HourCust(currTime, endTime, queryStatus, updateStatus, skillType);
    		freezeToService(orderList);
    		taskOrderMapper.updateOrderStatusAfter12HourCust(currTime, endTime, queryStatus, updateStatus, skillType);
    		sendMsgByOrderList(orderList, CANCEL_FOUR);
    		
    		
    		cal = Calendar.getInstance();
    		cal.setTime(currTime);
    		cal.add(Calendar.HOUR_OF_DAY, END_OVER_TIME_HOUR);
    		endTime =  cal.getTime();
    		skillType = OrderSkillConstants.SKILL_TYPE_NO; 
    		//获取接单设置超时
    		queryStatus = OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT;
    		updateStatus = OrderSkillConstants.ORDER_STATUS_FINISH_BOTH_NO_OPERATE;
    		
    		
    		//服务完成，大V金额冻结
    		orderList = taskOrderMapper.queryOrderStatusAfter12HourBoth(currTime, endTime, queryStatus, updateStatus, skillType);
    		freezeToService(orderList);
    		taskOrderMapper.updateOrderStatusAfter12HourBoth(currTime, endTime, queryStatus, updateStatus, skillType);

    		sendMsgByOrderList(orderList, CANCEL_FOUR);
    		
    	} catch (Exception e) {
    		LOGGER.error("job执行发生异常，异常信息：", e);
    	}
    	LOGGER.info("=============修改订单状态自动任务结束=================");
    }
    
    
    
    
//    public  void  updateOrderStatusByOrderList(List<TaskOrder>  orderList,Integer  updateOrderStatus){
//    	
//    	if(!CollectionUtils.isEmpty(orderList)){
//    		List<Long>  orderIdList =  new ArrayList<Long>();
//    		for (TaskOrder order : orderList) {
//    			orderIdList.add(order.getOrderId());
//    		}
//    		taskOrderMapper.updateOrderStatus(updateOrderStatus, orderIdList);
//    	}
//    }
    
    
    /**
     * 订单结束推送消息
     * @param orderList
     */
    public   void   sendMsgByOrderList(List<TaskOrder>  orderList,Integer  cancelType){
    	if(!CollectionUtils.isEmpty(orderList)){
			try {
				for (TaskOrder order : orderList) {
					Long  customerId =  order.getCustomerId();
					sendOrderMessage(customerId, cancelType, false);
					sendOrderMessage(order.getServiceId(), cancelType, true);
				}
			} catch (Exception e) {
				LOGGER.error("用户退款发生异常，异常信息",e);
			}
		}
    }
    /**
     * 订单取消，退款给用户
     * @param orderList
     */
    public   void   refundToCustomer(List<TaskOrder>  orderList,Integer  cancelType){
    	if(!CollectionUtils.isEmpty(orderList)){
    		try {
    			for (TaskOrder order : orderList) {
    				Long  customerId =  order.getCustomerId();
    				BigDecimal  payAmount =  order.getOrderAmounts();
    				//调用接口退款给用户
    				LOGGER.info("用户信息："+order.toString());
    				inAccount(customerId, payAmount,TransferTypeEnum.RECHARGE,AccountBusinessTypeEnum.OrderRefund);
    				LOGGER.info("用户ID："+customerId +"订单超时，系统自动退款给用户"+payAmount);
    				
    				sendOrderMessage(customerId, cancelType, false);
    				sendOrderMessage(order.getServiceId(), cancelType, true);
    			}
    		} catch (Exception e) {
    			LOGGER.error("用户退款发生异常，异常信息",e);
    		}
    	}
    }
    
    
    
    /***
     * 消息推送
     * @param customerId
     * @param serviceId
     * @param cancelType 1:15分钟未接受订单   2:大V5分钟未发起立即服务   3：用户未接受大V立即服务   4：订单自动完成
     * @param msgContent
     */
    public void   sendOrderMessage(Long  customerId,Integer  cancelType,boolean  dvFlag){
    	
    	String  content =  null ;
    	if(dvFlag){
    		if(cancelType == CANCEL_ONE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_15MINUTE_TIMEOUT ;
    		}else if (cancelType == CANCEL_TWO){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_5MINUTE_START_TIMEOUT ;
    		}else if (cancelType == CANCEL_THREE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_5MINUTE_CONFIRM_TIMEOUT ;
    		}else if(cancelType == CANCEL_FOUR){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_FINISH ;
    		}
    	}else{
    		if(cancelType == CANCEL_ONE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_DV_15MINUTE_TIMEOUT ;
    		}else if (cancelType == CANCEL_TWO){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_DV_5MINUTE_START_TIMEOUT ;
    		}else if (cancelType == CANCEL_THREE){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_DV_5MINUTE_CONFIRM_TIMEOUT ;
    		}else if(cancelType == CANCEL_FOUR){
    			content =  OrderSkillConstants.IM_MSG_CONTENT_CANCEL_CUST_FINISH ;
    		}
    	}
    	if(StringUtils.isNotBlank(content)){
    		content = OrderSkillConstants.IM_MSG_CONTENT_DEFAULT ;
    	}
    	//下单成功后推送IM消息
		RongYunUtil.sendOrderMessage(customerId, content);
    }
    
    
    /**
     * 订单结束，大V金额冻结
     * @param orderList
     */
    public   void   freezeToService(List<TaskOrder>  orderList){
    	if(!CollectionUtils.isEmpty(orderList)){
    		try {
				for (TaskOrder order : orderList) {
					Long  serviceId =  order.getServiceId();
					BigDecimal   payAmount =  order.getOrderAmounts();
					//大V冻结
					inAccount(order.getServiceId(), order.getOrderAmounts(), TransferTypeEnum.FROZEN, AccountBusinessTypeEnum.FroZen);
					LOGGER.info("主播用户ID："+serviceId +"订单超时，系统自动退款给用户"+payAmount);
				}
			} catch (Exception e) {
				LOGGER.error("大V账户冻结发生异常，异常信息",e);
			}
    	}
    }
    
    
    
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private TradeDetailMapper tradeDetailMapper;

	private final static String froZenTime = ResourceBundle.getBundle("thirdconfig").getString("froZenTime");
    
    public void inAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {

		// 入账
		accountMapper.inAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setInPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

		if (transferType == TransferTypeEnum.FROZEN) {
			String userFrozenkey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + customerId;
			String steamFrozenKey = RedisKeyConstants.ACCOUNT_USERFROZEN_USER + tradeDetail.getTradeId();
			String frozenTimeKey = RedisKeyConstants.ACCOUNT_USERFROZEN_Time + tradeDetail.getTradeId();
			String userFrozenValue = JedisUtil.get(userFrozenkey);
			if (StringUtils.isNotBlank(userFrozenValue)) {
				userFrozenValue = userFrozenValue + "," + tradeDetail.getTradeId();
			} else {
				JedisUtil.set(userFrozenkey, tradeDetail.getTradeId() + "");

			}
			JedisUtil.set(steamFrozenKey, amount + "");
			// 缓存24小时
			JedisUtil.set(frozenTimeKey, "1", Integer.parseInt(froZenTime));

		}

	}
    
    
	public void outAccount(Long customerId, BigDecimal amount, TransferTypeEnum transferType,
			AccountBusinessTypeEnum accountBusinessType) {
		// 入账
		accountMapper.outAccount(customerId, amount, transferType.getType());
		// 记录流水
		TradeDetail tradeDetail = new TradeDetail();
		tradeDetail.setTradeId(UUIDUtils.getId());
		tradeDetail.setCustomerId(customerId);
		tradeDetail.setCreateTime(new Date());
		tradeDetail.setType(accountBusinessType.getType());
		tradeDetail.setOutPrice(amount);
		tradeDetailMapper.insertSelective(tradeDetail);

	}
    
    
    
    

}
