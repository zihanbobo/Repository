package com.honglu.quickcall.activity.facade.entity;

import java.util.Date;

/**
 * 券可以使用的品类
 */
public class CouponSkill {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.coupon_id
     *
     * @mbggenerated
     */
    private Long couponId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.skill_item_id
     *
     * @mbggenerated
     */
    private Long skillItemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.create_man
     *
     * @mbggenerated
     */
    private String createMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.modify_man
     *
     * @mbggenerated
     */
    private String modifyMan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coupon_skill.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.id
     *
     * @return the value of coupon_skill.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.id
     *
     * @param id the value for coupon_skill.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.coupon_id
     *
     * @return the value of coupon_skill.coupon_id
     *
     * @mbggenerated
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.coupon_id
     *
     * @param couponId the value for coupon_skill.coupon_id
     *
     * @mbggenerated
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.skill_item_id
     *
     * @return the value of coupon_skill.skill_item_id
     *
     * @mbggenerated
     */
    public Long getSkillItemId() {
        return skillItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.skill_item_id
     *
     * @param skillItemId the value for coupon_skill.skill_item_id
     *
     * @mbggenerated
     */
    public void setSkillItemId(Long skillItemId) {
        this.skillItemId = skillItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.status
     *
     * @return the value of coupon_skill.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.status
     *
     * @param status the value for coupon_skill.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.create_time
     *
     * @return the value of coupon_skill.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.create_time
     *
     * @param createTime the value for coupon_skill.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.modify_time
     *
     * @return the value of coupon_skill.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.modify_time
     *
     * @param modifyTime the value for coupon_skill.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.create_man
     *
     * @return the value of coupon_skill.create_man
     *
     * @mbggenerated
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.create_man
     *
     * @param createMan the value for coupon_skill.create_man
     *
     * @mbggenerated
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.modify_man
     *
     * @return the value of coupon_skill.modify_man
     *
     * @mbggenerated
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.modify_man
     *
     * @param modifyMan the value for coupon_skill.modify_man
     *
     * @mbggenerated
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coupon_skill.remark
     *
     * @return the value of coupon_skill.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coupon_skill.remark
     *
     * @param remark the value for coupon_skill.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}