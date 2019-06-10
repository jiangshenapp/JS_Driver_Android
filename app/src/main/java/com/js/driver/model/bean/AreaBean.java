package com.js.driver.model.bean;

import java.util.List;

/**
 * Created by huyg on 2019-06-05.
 */
public class AreaBean implements Cloneable {


    /**
     * name : 全国
     * code : 0
     * lon : 0
     * lat : 0
     * gcjlon :
     * gcjlat :
     */

    private String name;
    private String code;
    private String lon;
    private String lat;
    private String gcjlon;
    private String gcjlat;
    private List<AreaBean> child;
    private boolean checked;
    private String alias;


    public AreaBean() {

    }

    public AreaBean(String name, String code, boolean checked, String alias) {
        this.name = name;
        this.code = code;
        this.checked = checked;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getGcjlon() {
        return gcjlon;
    }

    public void setGcjlon(String gcjlon) {
        this.gcjlon = gcjlon;
    }

    public String getGcjlat() {
        return gcjlat;
    }

    public void setGcjlat(String gcjlat) {
        this.gcjlat = gcjlat;
    }

    public List<AreaBean> getChild() {
        return child;
    }

    public void setChild(List<AreaBean> child) {
        this.child = child;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
