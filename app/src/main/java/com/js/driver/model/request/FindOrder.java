package com.js.driver.model.request;

/**
 * Created by huyg on 2019-05-30.
 */
public class FindOrder {


    /**
     * arriveAddressCode : string
     * startAddressCode : string
     */

    private String arriveAddressCode;
    private String startAddressCode;


    public FindOrder(String arriveAddressCode, String startAddressCode) {
        this.arriveAddressCode = arriveAddressCode;
        this.startAddressCode = startAddressCode;
    }

    public String getArriveAddressCode() {
        return arriveAddressCode;
    }

    public void setArriveAddressCode(String arriveAddressCode) {
        this.arriveAddressCode = arriveAddressCode;
    }

    public String getStartAddressCode() {
        return startAddressCode;
    }

    public void setStartAddressCode(String startAddressCode) {
        this.startAddressCode = startAddressCode;
    }
}
