package com.js.driver.model.event;

import com.js.driver.model.bean.DictBean;

import java.util.List;

/**
 * Created by huyg on 2019-06-09.
 */
public class DictSelectEvent {

    public List<DictBean> mDicts;
    public int type;


    public DictSelectEvent(List<DictBean> mDicts, int type) {
        this.mDicts = mDicts;
        this.type = type;
    }
}
