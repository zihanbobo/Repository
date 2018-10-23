package com.honglu.quickcall.task.dao;

import org.apache.ibatis.annotations.Mapper;

import com.honglu.quickcall.account.facade.entity.TradeDetail;

@Mapper
public interface TradeDetailMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table trade_detail
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Long tradeId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table trade_detail
	 *
	 * @mbggenerated
	 */
	int insert(TradeDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table trade_detail
	 *
	 * @mbggenerated
	 */
	int insertSelective(TradeDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table trade_detail
	 *
	 * @mbggenerated
	 */
	TradeDetail selectByPrimaryKey(Long tradeId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table trade_detail
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(TradeDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table trade_detail
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(TradeDetail record);
}