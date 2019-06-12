package com.js.driver.model.request;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/12
 * desc   :
 * version: 3.0.0
 */
public class RouteRequest {

    /**
     * arriveAddressCode : string
     * carLength : string
     * carModel : string
     * goodsType : string
     * sort : string
     * startAddressCode : string
     * useCarType : string
     * remark : string
     */

    private String arriveAddressCode;
    private String carLength;
    private String carModel;
    private String goodsType;
    private String sort;
    private String startAddressCode;
    private String useCarType;
    private String remark;

    public RouteRequest() {

    }

    //查询
    public RouteRequest(String arriveAddressCode, String carLength, String carModel, String goodsType, String sort, String startAddressCode, String useCarType) {
        this.arriveAddressCode = arriveAddressCode;
        this.carLength = carLength;
        this.carModel = carModel;
        this.goodsType = goodsType;
        this.sort = sort;
        this.startAddressCode = startAddressCode;
        this.useCarType = useCarType;
    }

    //添加
    public RouteRequest(String arriveAddressCode, String carLength, String carModel, String startAddressCode, String remark) {
        this.arriveAddressCode = arriveAddressCode;
        this.carLength = carLength;
        this.carModel = carModel;
        this.startAddressCode = startAddressCode;
        this.remark = remark;
    }

    public String getArriveAddressCode() {
        return arriveAddressCode;
    }

    public void setArriveAddressCode(String arriveAddressCode) {
        this.arriveAddressCode = arriveAddressCode;
    }

    public String getCarLength() {
        return carLength;
    }

    public void setCarLength(String carLength) {
        this.carLength = carLength;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getStartAddressCode() {
        return startAddressCode;
    }

    public void setStartAddressCode(String startAddressCode) {
        this.startAddressCode = startAddressCode;
    }

    public String getUseCarType() {
        return useCarType;
    }

    public void setUseCarType(String useCarType) {
        this.useCarType = useCarType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
