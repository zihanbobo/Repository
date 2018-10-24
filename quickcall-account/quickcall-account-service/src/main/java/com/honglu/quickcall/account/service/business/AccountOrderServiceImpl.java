package com.honglu.quickcall.account.service.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.service.dao.OrderMapper;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：对外订单查询接口
 * @Package: com.honglu.quickcall.account.service.business 
 * @author: chenliuguang   
 * @date: 2018年10月24日 下午1:54:25
 */
@Service("Account.AccountOrderService")
public class AccountOrderServiceImpl implements IAccountOrderService {

	@Autowired
	private   OrderMapper  orderMapper;
	
	
	
	@Override
	public Integer checkReceiveOrderByCustomerSkillId(Long customerSkillId) {
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);//待开始
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);//大V发起开始服务
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);//进行中
		statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);//进行中（大V发起完成服务）
		statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);//待开始
		Integer  resultCount = orderMapper.selectGongIngOrderListByCustomerSkillId(customerSkillId, OrderSkillConstants.SKILL_TYPE_YES, statusList);
		if(resultCount == null ){
			return  1;
		}else{
			if(resultCount == 0){
				return  1;
			}else{
				return 0 ;
			}
		}
	}

	
}