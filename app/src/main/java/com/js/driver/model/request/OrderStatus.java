package com.js.driver.model.request;

/**
 * Created by huyg on 2019-05-30.
 */
public class OrderStatus {


    /**
     * state : 0
     */

    private int state;

    public OrderStatus(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
