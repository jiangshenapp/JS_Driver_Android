package com.js.driver.model.event;

/**
 * Created by huyg on 2019-06-09.
 */
public class DictSelectEvent {

    public String labelStr;
    public String valueStr;
    public int type;


    public DictSelectEvent(String labelStr, String valueStr, int type) {
        this.labelStr = labelStr;
        this.valueStr = valueStr;
        this.type = type;
    }
}
