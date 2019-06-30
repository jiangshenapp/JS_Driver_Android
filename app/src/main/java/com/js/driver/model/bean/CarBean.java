package com.js.driver.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huyg on 2019/4/29.
 */
public class CarBean implements Parcelable {

    /**
     * id : 12
     * image2 : 3cea4a7a289c419aaa2a41617ef22c4c.png
     * state : 0
     * cphm : 2
     * carLengthId : 25
     * image1 : fd79f07622dc441db0cd00651295b347.png
     * carModelId : 28
     * capacityTonnage : 2
     * carLengthName :
     * stateName : 待审核
     * carModelName :
     * subscriberId : 2
     * capacityVolume : 2
     */

    private int id;
    private String image2;
    private int state;
    private String cphm;
    private String carLengthId;
    private String image1;
    private String carModelId;
    private int capacityTonnage;
    private String carLengthName;
    private String stateName;
    private String carModelName;
    private int subscriberId;
    private int capacityVolume;
    private String tradingNo; //营运许可
    private String transportNo; //运输许可

    public CarBean(){

    }

    protected CarBean(Parcel in) {
        id = in.readInt();
        image2 = in.readString();
        state = in.readInt();
        cphm = in.readString();
        carLengthId = in.readString();
        image1 = in.readString();
        carModelId = in.readString();
        capacityTonnage = in.readInt();
        carLengthName = in.readString();
        stateName = in.readString();
        carModelName = in.readString();
        subscriberId = in.readInt();
        capacityVolume = in.readInt();
        tradingNo = in.readString();
        transportNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image2);
        dest.writeInt(state);
        dest.writeString(cphm);
        dest.writeString(carLengthId);
        dest.writeString(image1);
        dest.writeString(carModelId);
        dest.writeInt(capacityTonnage);
        dest.writeString(carLengthName);
        dest.writeString(stateName);
        dest.writeString(carModelName);
        dest.writeInt(subscriberId);
        dest.writeInt(capacityVolume);
        dest.writeString(tradingNo);
        dest.writeString(transportNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarBean> CREATOR = new Creator<CarBean>() {
        @Override
        public CarBean createFromParcel(Parcel in) {
            return new CarBean(in);
        }

        @Override
        public CarBean[] newArray(int size) {
            return new CarBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getCarLengthId() {
        return carLengthId;
    }

    public void setCarLengthId(String carLengthId) {
        this.carLengthId = carLengthId;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    public int getCapacityTonnage() {
        return capacityTonnage;
    }

    public void setCapacityTonnage(int capacityTonnage) {
        this.capacityTonnage = capacityTonnage;
    }

    public String getCarLengthName() {
        return carLengthName;
    }

    public void setCarLengthName(String carLengthName) {
        this.carLengthName = carLengthName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getCapacityVolume() {
        return capacityVolume;
    }

    public void setCapacityVolume(int capacityVolume) {
        this.capacityVolume = capacityVolume;
    }

    public String getTradingNo() {
        return tradingNo;
    }

    public void setTradingNo(String tradingNo) {
        this.tradingNo = tradingNo;
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }
}
