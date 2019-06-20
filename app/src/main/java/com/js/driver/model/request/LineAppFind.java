package com.js.driver.model.request;

/**
 * Created by huyg on 2019-06-19.
 */
public class LineAppFind {


    /**
     * arriveAddressCode : string
     * carLength : string
     * carModel : string
     * goodsType : string
     * sort : string
     * startAddressCode : string
     * useCarType : string
     */

    private String arriveAddressCode;
    private String carLength;
    private String carModel;
    private String goodsType;
    private int sort=1;
    private String startAddressCode;
    private String useCarType;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
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
}
