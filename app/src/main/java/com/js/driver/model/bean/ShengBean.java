package com.js.driver.model.bean;


import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Sai on 15/11/22.
 */
public class ShengBean implements IPickerViewData {
    public String name;
    public List<Shi> city;
    public static class Shi{
        public String name;
        public List<String> area;

    }
    //  这个要返回省的名字
    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
