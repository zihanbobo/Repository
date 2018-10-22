package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.OrderRequestType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * 订单评价提交 - 请求对象
 *
 * @author duanjun
 * @date 2018-10-22 13:49
 */
public class OrderEvaluationSubmitRequest extends AbstractRequest {

    private static final long serialVersionUID = 6541742576228485039L;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 评价用户ID
     */
    private Long customerId;
    /**
     * 评价星级
     */
    private Integer evaluateStart;
    /**
     * 评价标签ID
     */
    private Integer[] labelIds;

    @Override
    public String getBizCode() {
        return OrderRequestType.ORDER_EVALUATION_SUBMIT;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getEvaluateStart() {
        return evaluateStart;
    }

    public void setEvaluateStart(Integer evaluateStart) {
        this.evaluateStart = evaluateStart;
    }

    public Integer[] getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(Integer[] labelIds) {
        this.labelIds = labelIds;
    }
}
