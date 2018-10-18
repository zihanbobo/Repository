package com.honglu.quickcall.task.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Skill {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.id
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.name
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.skill_describe
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String skillDescribe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.min_price
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private BigDecimal minPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.max_price
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private BigDecimal maxPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.image_url
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String imageUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.title_url
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String titleUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.sort
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private Short sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.price_step
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private Short priceStep;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.skill_status
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private Byte skillStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.create_time
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.modify_time
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.create_man
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.modify_man
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill.remark
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.id
     *
     * @return the value of skill.id
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.id
     *
     * @param id the value for skill.id
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.name
     *
     * @return the value of skill.name
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.name
     *
     * @param name the value for skill.name
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.skill_describe
     *
     * @return the value of skill.skill_describe
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getSkillDescribe() {
        return skillDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.skill_describe
     *
     * @param skillDescribe the value for skill.skill_describe
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe == null ? null : skillDescribe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.min_price
     *
     * @return the value of skill.min_price
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.min_price
     *
     * @param minPrice the value for skill.min_price
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.max_price
     *
     * @return the value of skill.max_price
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.max_price
     *
     * @param maxPrice the value for skill.max_price
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.image_url
     *
     * @return the value of skill.image_url
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.image_url
     *
     * @param imageUrl the value for skill.image_url
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.title_url
     *
     * @return the value of skill.title_url
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getTitleUrl() {
        return titleUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.title_url
     *
     * @param titleUrl the value for skill.title_url
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl == null ? null : titleUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.sort
     *
     * @return the value of skill.sort
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public Short getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.sort
     *
     * @param sort the value for skill.sort
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setSort(Short sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.price_step
     *
     * @return the value of skill.price_step
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public Short getPriceStep() {
        return priceStep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.price_step
     *
     * @param priceStep the value for skill.price_step
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setPriceStep(Short priceStep) {
        this.priceStep = priceStep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.skill_status
     *
     * @return the value of skill.skill_status
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public Byte getSkillStatus() {
        return skillStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.skill_status
     *
     * @param skillStatus the value for skill.skill_status
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setSkillStatus(Byte skillStatus) {
        this.skillStatus = skillStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.create_time
     *
     * @return the value of skill.create_time
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.create_time
     *
     * @param createTime the value for skill.create_time
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.modify_time
     *
     * @return the value of skill.modify_time
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.modify_time
     *
     * @param modifyTime the value for skill.modify_time
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.create_man
     *
     * @return the value of skill.create_man
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.create_man
     *
     * @param createMan the value for skill.create_man
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.modify_man
     *
     * @return the value of skill.modify_man
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.modify_man
     *
     * @param modifyMan the value for skill.modify_man
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill.remark
     *
     * @return the value of skill.remark
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill.remark
     *
     * @param remark the value for skill.remark
     *
     * @mbggenerated Thu Oct 18 16:23:07 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}