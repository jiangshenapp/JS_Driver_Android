package com.js.driver.model.event;

/**
 * Created by huyg on 2019-06-20.
 */
public class FilterEvent {

    public String carTypeStr;
    public String lengthStr;
    public String typeStr;

    public FilterEvent(String carTypeStr, String lengthStr, String typeStr) {
        this.carTypeStr = carTypeStr;
        this.lengthStr = lengthStr;
        this.typeStr = typeStr;
    }
}
