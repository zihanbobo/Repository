package com.honglu.quickcall.account.web.service;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.WebResponseModel;

public interface PaymentService {
    WebResponseModel execute(AbstractRequest request);
}
