package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.entity.example.BigvSkillScoreExample;
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
    int countByExample(BigvSkillScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int deleteByExample(BigvSkillScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int insert(BigvSkillScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int insertSelective(BigvSkillScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    List<BigvSkillScore> selectByExample(BigvSkillScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    BigvSkillScore selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int updateByExampleSelective(@Param("record") BigvSkillScore record, @Param("example") BigvSkillScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int updateByExample(@Param("record") BigvSkillScore record, @Param("example") BigvSkillScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int updateByPrimaryKeySelective(BigvSkillScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_skill_score
     *
     * @mbggenerated Thu Oct 25 14:43:15 CST 2018
     */
    int updateByPrimaryKey(BigvSkillScore record);

    /**
     * 查询大V技能评分数据
     *
     * @param customerId
     * @param skillItemId
     * @return
     */
    BigvSkillScore selectBigvSkillScore(@Param("customerId") Long customerId, @Param("skillItemId") Long skillItemId);

    /**
     * 查询大V技能的订单笔数
     *
     * @param customerId
     * @param skillItemId
     * @return
     */
    Integer selectBigvSkillOrderTotal(@Param("customerId") Long customerId, @Param("skillItemId") Long skillItemId);

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
     * @param customerId
     * @param skillItemId
     * @param valueScore
     * @return
     */
    int updateBigvSkillScore(@Param("customerId") Long customerId, @Param("skillItemId") Long skillItemId, @Param("valueScore") BigDecimal valueScore);
}