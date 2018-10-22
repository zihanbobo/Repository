package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 个人中心返回数据 -- 页面对象
 *
 * @author duanjun
 * @date 2018-10-20 11:37
 */
public class CustomerCenterVO implements Serializable {

    private static final long serialVersionUID = -1209835663261243685L;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 用户ID
     */
    private String customerAppId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headPortraitUrl;
    /**
     * 用户性别：0=女,1=男
     */
    private Integer sex;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户等级
     */
    private Integer customerLevel;
    /**
     * 用户签名
     */
    private String signName;
    /**
     * 用户是否实名认证：0=未认证,1=待审核,2=已通过,3=拒绝
     */
    private Integer identityStatus;
    /**
     * 大V认证状态：0=未认证,1=待审核,2=已通过,3=拒绝
     */
    private Integer vStatus;
    /**
     * 关注数
     */
    private Integer attentionNum;
    /**
     * 用户粉丝数
     */
    private Integer fansNum;
    /**
     * 充值金额
     */
    private BigDecimal rechargeAmounts;
    /**
     * 提现金额
     */
    private BigDecimal withdrawAmounts;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAppId() {
        return customerAppId;
    }

    public void setCustomerAppId(String customerAppId) {
        this.customerAppId = customerAppId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }

    public Integer getvStatus() {
        return vStatus;
    }

    public void setvStatus(Integer vStatus) {
        this.vStatus = vStatus;
    }

    public Integer getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(Integer attentionNum) {
        this.attentionNum = attentionNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public BigDecimal getRechargeAmounts() {
        return rechargeAmounts;
    }

    public void setRechargeAmounts(BigDecimal rechargeAmounts) {
        this.rechargeAmounts = rechargeAmounts;
    }

    public BigDecimal getWithdrawAmounts() {
        return withdrawAmounts;
    }

    public void setWithdrawAmounts(BigDecimal withdrawAmounts) {
        this.withdrawAmounts = withdrawAmounts;
    }

    @Override
    public String toString() {
        return "CustomerCenterVO{" +
                "customerId=" + customerId +
                ", customerAppId='" + customerAppId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", customerLevel=" + customerLevel +
                ", signName='" + signName + '\'' +
                ", identityStatus=" + identityStatus +
                ", vStatus=" + vStatus +
                ", attentionNum=" + attentionNum +
                ", fansNum=" + fansNum +
                ", rechargeAmounts=" + rechargeAmounts +
                ", withdrawAmounts=" + withdrawAmounts +
                '}';
    }
}