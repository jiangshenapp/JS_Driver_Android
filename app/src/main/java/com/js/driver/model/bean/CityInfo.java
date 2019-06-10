package com.js.driver.model.bean;

import java.util.List;

/**
 * Created by huyg on 2019-06-06.
 */
public class CityInfo {

    private List<AreaBean> province;
    private List<AreaBean> city;
    private List<AreaBean> district;


    public List<AreaBean> getProvince() {
        return province;
    }

    public void setProvince(List<AreaBean> province) {
        this.province = province;
    }

    public List<AreaBean> getCity() {
        return city;
    }

    public void setCity(List<AreaBean> city) {
        this.city = city;
    }

    public List<AreaBean> getDistrict() {
        return district;
    }

    public void setDistrict(List<AreaBean> district) {
        this.district = district;
    }
}
