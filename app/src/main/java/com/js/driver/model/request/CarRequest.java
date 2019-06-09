package com.js.driver.model.request;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/09
 * desc   :
 * version: 3.0.0
 */
public class CarRequest {

    /**
     * image1 : e24671a644d74ab29af29cf63dc16884.png
     * carModelId : 28
     * image2 : af946a26a8b54a159d6f71a7e08b8a51.png
     * capacityVolume : 3
     * state : 0
     * carLengthId : 25
     * cphm : 3
     * capacityTonnage : 3
     */

    private String image1;
    private String carModelId;
    private String image2;
    private String capacityVolume;
    private String state;
    private String carLengthId;
    private String cphm;
    private String capacityTonnage;

    public CarRequest(String image1, String carModelId, String image2, String capacityVolume, String state, String carLengthId, String cphm, String capacityTonnage) {
        this.image1 = image1;
        this.carModelId = carModelId;
        this.image2 = image2;
        this.capacityVolume = capacityVolume;
        this.state = state;
        this.carLengthId = carLengthId;
        this.cphm = cphm;
        this.capacityTonnage = capacityTonnage;
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

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getCapacityVolume() {
        return capacityVolume;
    }

    public void setCapacityVolume(String capacityVolume) {
        this.capacityVolume = capacityVolume;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCarLengthId() {
        return carLengthId;
    }

    public void setCarLengthId(String carLengthId) {
        this.carLengthId = carLengthId;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getCapacityTonnage() {
        return capacityTonnage;
    }

    public void setCapacityTonnage(String capacityTonnage) {
        this.capacityTonnage = capacityTonnage;
    }
}
