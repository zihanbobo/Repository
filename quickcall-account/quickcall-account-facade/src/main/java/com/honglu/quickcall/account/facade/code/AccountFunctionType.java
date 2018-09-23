package com.honglu.quickcall.account.facade.code;


/**
 * Created by len.song on 2017-12-07.
 * 只用来存放rpc功能点code
 * 组成：项目编码(MyServiceCode.Account.code()) + "变量"(从 001 - 500 范围)
 */
public interface AccountFunctionType {

    // 从 "002001" 开始， 002保持不变  从 001 开始递增;
    //支付相关业务使用002001 ~ 002100
	//支付宝充值
    String AlipayRecharge = "002001";
    //支付宝提现
    String AlipayWhithdraw = "002002";
    //支付宝支付回调
    String AlipayNotify = "002003";
    //微信支付回调
    String WechatNotify = "002004";
    //绑定支付宝
    String BindAliaccount= "002005";
    
   

    //账户相关操作从 002200 - 002299
    //创建账户
    String CreateUserAccount = "002200";
    //入账
    
    
    //查询账户
    String QueryAccount = "002201";
    

    //订单相关操作 从 002300 - 002399
    //创建订单 传产品
    
    //支付成功mq 测试
    String PaySuccessMqTest = "002304";
}
