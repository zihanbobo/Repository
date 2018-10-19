package com.honglu.quickcall.user.facade.entity;

import java.util.Date;

public class SkillItem {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.id
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.name
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.skill_describe
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private String skillDescribe;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.image_url
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private String imageUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.sort
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private Short sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.price_step
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private Short priceStep;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.skill_status
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private Byte skillStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.create_time
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.modify_time
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.create_man
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private String createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.modify_man
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private String modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column skill_item.remark
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.id
     *
     * @return the value of skill_item.id
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.id
     *
     * @param id the value for skill_item.id
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.name
     *
     * @return the value of skill_item.name
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.name
     *
     * @param name the value for skill_item.name
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.skill_describe
     *
     * @return the value of skill_item.skill_describe
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public String getSkillDescribe() {
        return skillDescribe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.skill_describe
     *
     * @param skillDescribe the value for skill_item.skill_describe
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe == null ? null : skillDescribe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.image_url
     *
     * @return the value of skill_item.image_url
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.image_url
     *
     * @param imageUrl the value for skill_item.image_url
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.sort
     *
     * @return the value of skill_item.sort
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public Short getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.sort
     *
     * @param sort the value for skill_item.sort
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setSort(Short sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.price_step
     *
     * @return the value of skill_item.price_step
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public Short getPriceStep() {
        return priceStep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.price_step
     *
     * @param priceStep the value for skill_item.price_step
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setPriceStep(Short priceStep) {
        this.priceStep = priceStep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.skill_status
     *
     * @return the value of skill_item.skill_status
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public Byte getSkillStatus() {
        return skillStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.skill_status
     *
     * @param skillStatus the value for skill_item.skill_status
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setSkillStatus(Byte skillStatus) {
        this.skillStatus = skillStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.create_time
     *
     * @return the value of skill_item.create_time
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.create_time
     *
     * @param createTime the value for skill_item.create_time
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.modify_time
     *
     * @return the value of skill_item.modify_time
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.modify_time
     *
     * @param modifyTime the value for skill_item.modify_time
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.create_man
     *
     * @return the value of skill_item.create_man
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.create_man
     *
     * @param createMan the value for skill_item.create_man
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.modify_man
     *
     * @return the value of skill_item.modify_man
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.modify_man
     *
     * @param modifyMan the value for skill_item.modify_man
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column skill_item.remark
     *
     * @return the value of skill_item.remark
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column skill_item.remark
     *
     * @param remark the value for skill_item.remark
     *
     * @mbggenerated Fri Oct 19 10:46:10 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}