package com.js.driver.model.request;

/**
 * Created by huyg on 2019-06-19.
 */
public class OrderDistribution {
    private long dirverId;
    private long carId;

    public OrderDistribution(long dirverId,long carId) {
        this.dirverId = dirverId;
        this.carId = carId;
    }
}
