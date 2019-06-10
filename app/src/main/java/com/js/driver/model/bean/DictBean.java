package com.js.driver.model.bean;

/**
 * Created by huyg on 2019-05-28.
 */
public class DictBean {

    /**
     * id : 25
     * value : 1.5
     * label : 1.5米
     * type : jsmb_carLength
     * description : 1.5米
     * sort : 2
     * createTime : 2019-05-13 13:56:51
     * updateTime : 2019-05-28 01:09:13
     * remarks :
     * delFlag : 0
     */

    private int id;
    private String value;
    private String label;
    private String type;
    private String description;
    private int sort;
    private String createTime;
    private String updateTime;
    private String remarks;
    private String delFlag;
    private boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
