package com.js.driver.model.bean;

/**
 * Created by huyg on 2019-06-03.
 */
public class PayInfo {


    /**
     * repeat : 0
     * flow : 1
     * flowStatus : 0
     * orderId : 12
     * outTradeNo : 060315324215595
     * orderInfo : alipay_sdk=alipay-sdk-java-3.0.52.ALL&app_id=2019051064508112&biz_content=%7B%22body%22%3A%22%E8%B4%A6%E6%88%B7%E5%85%85%E5%80%BC%22%2C%22out_trade_no%22%3A%22060315324215595%22%2C%22passback_params%22%3A%22callback+params%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%B4%A6%E6%88%B7%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2290m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fsunlive.free.idcfengye.com%2Fpigx-pay-biz%2Fnotify%2Fali&sign=MKsSjdXXjcA%2F%2FMWOyUlnQQkeSVMAXBnh6lVStTGmwzWKH%2BH3dD8oJNlEwt8uL91IctjdYbJX%2FwqibpyV28n6D45WqZpdGNhtajvvcYiCb7O%2Bo5TgEiQbSEMqgx0n3gRtNmyL59fCnBwijGmEH09DULH0KlzOyX0OaU9o6YuvaGTAzqPJOUSN%2FxeeKh%2FRj0l1xRAZfAXwMIXy1wygOjzX8ziHogLMOHKwR1DR%2FV5jBCgHcoP8wnZpezhZDaeMn%2FESAcdL96S7OvbKVdaZ%2BI%2FZUQhAo3VS2pzIWE9xW82OmEXJ3Kh6dgEvgnpxfl2HD2pASxkpMsrEtM57ZD2MVay%2Flw%3D%3D&sign_type=RSA2&timestamp=2019-06-03+15%3A32%3A42&version=1.0
     * businessCode : 1559547162177@3@subscriberId@2
     */

    private String repeat;
    private String flow;
    private String flowStatus;
    private int orderId;
    private String outTradeNo;
    private String orderInfo;
    private String businessCode;

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
