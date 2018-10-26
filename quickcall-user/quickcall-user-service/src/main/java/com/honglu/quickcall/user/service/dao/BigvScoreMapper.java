package com.honglu.quickcall.user.service.dao;

import com.honglu.quickcall.user.facade.entity.BigvScore;
import com.honglu.quickcall.user.facade.entity.example.BigvScoreExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BigvScoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_score
     *
     * @mbggenerated Thu Oct 25 14:43:38 CST 2018
     */
    int countByExample(BigvScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_score
     *
     * @mbggenerated Thu Oct 25 14:43:38 CST 2018
     */
    int insert(BigvScore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_score
     *
     * @mbggenerated Thu Oct 25 14:43:38 CST 2018
     */
    List<BigvScore> selectByExample(BigvScoreExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigv_score
     *
     * @mbggenerated Thu Oct 25 14:43:38 CST 2018
     */
    BigvScore selectByPrimaryKey(Integer id);

    /**
     * 更新大V评分到大V排名表
     * @param customerId
     * @param valueScore
     * @return
     */
    int updateBigvScore(@Param("customerId") Long customerId, @Param("valueScore") BigDecimal valueScore);
}