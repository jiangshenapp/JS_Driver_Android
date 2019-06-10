package com.js.driver.model.event;

import com.js.driver.model.bean.AreaBean;

/**
 * Created by huyg on 2019-06-06.
 */
public class CitySelectEvent {
    public int type;
    public AreaBean areaBean;

    public CitySelectEvent(int type, AreaBean areaBean) {
        this.type = type;
        this.areaBean = areaBean;
    }
}
