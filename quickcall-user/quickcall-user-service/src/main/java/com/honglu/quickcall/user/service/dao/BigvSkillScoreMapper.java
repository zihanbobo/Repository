package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BigvSkillScoreMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int insert(BigvSkillScore record);

    /**
     * 查询大V技能的订单笔数
     *
     * @param customerSkillId
     * @return
     */
    Integer selectBigvSkillOrderTotal(@Param("customerSkillId") Long customerSkillId);

    /**
     * 根据默认评价得到到订单表
     *
     * @param orderId
     * @param valueScore
     * @return
     */
    int updateValueScoreToOrder(@Param("orderId") Long orderId, @Param("valueScore") BigDecimal valueScore);

    /**
     * 更新大V单项技能评分
     *
     * @param customerSkillId
     * @param valueScore
     * @param addOrderTotal
     * @return
     */
    int updateBigvSkillScore(@Param("customerSkillId") Long customerSkillId,
                             @Param("valueScore") BigDecimal valueScore,
                             @Param("addOrderTotal") Integer addOrderTotal);

    /**
     * 更新大V单项技能评分
     *
     * @param customerSkillId
     * @param valueScore
     * @param orderTotal
     * @return
     */
    int updateBigvSkillScore2(@Param("customerSkillId") Long customerSkillId,
                             @Param("valueScore") BigDecimal valueScore,
                             @Param("orderTotal") Integer orderTotal);

    /**
     * 根据创主见升序，查询一条数据
     * @return
     */
    BigvSkillScore selectOneData(@Param("lastId") Long lastId);

    /**
     * 根据客户ID -- 物理删除数据
     * @param customerId
     * @return
     */
    int deleteDataByCustomerId(@Param("customerId") Long customerId);

    /**
     * 删除不在大V列表中的数据
     * @return
     */
    int deleteNotBigvData();

    /**
     * 查询大V的技能不在技能排名表中的数据
     * @return
     */
    List<BigvSkillScore> selectNeedInsertBigvData();
}