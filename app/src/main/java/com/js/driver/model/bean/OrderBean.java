package com.js.driver.model.bean;

/**
 * Created by huyg on 2019/4/24.
 */
public class OrderBean {


    /**
     * carLength : string
     * carModel : string
     * createBy : 0
     * createTime : 2019-05-18T14:13:48.358Z
     * driverId : 0
     * fee : 0
     * feeType : 0
     * finishTime : 2019-05-18T14:13:48.358Z
     * goodsType : string
     * goodsVolume : 0
     * goodsWeight : 0
     * id : 0
     * image1 : string
     * image2 : string
     * jdSubscriberId : string
     * loadingTime : 2019-05-18T14:13:48.358Z
     * matchState : 0
     * matchSubscriberId : 0
     * orderNo : string
     * payTime : 2019-05-18T14:13:48.358Z
     * payType : 0
     * payWay : 0
     * receiveAddress : string
     * receiveAddressCode : string
     * receiveMobile : string
     * receiveName : string
     * receivePosition : string
     * remark : string
     * sendAddress : string
     * sendAddressCode : string
     * sendMobile : string
     * sendName : string
     * sendPosition : string
     * state : string
     * transferTime : 2019-05-18T14:13:48.358Z
     * useCarType : string
     */

    private String carLength;//车长，多选，逗号分隔
    private String carModel;//车型，多选，逗号分隔
    private int createBy;//发布人
    private String createTime;//发布时间
    private int driverId;//司机id
    private double fee;//运费
    private int feeType;//运费类型，1自己出价，2电议
    private String finishTime;//订单完成时间
    private String goodsType;//货物类型,字典表，多个
    private int goodsVolume;//货物体积，单位立方米
    private int goodsWeight;//货物重量、吨
    private long id;//主键
    private String image1;//图片1
    private String image2;//图片2
    private String jdSubscriberId;//接单会员Id
    private String loadingTime;//装货时间
    private int matchState;//匹配状态，1匹配，0未匹配
    private int matchSubscriberId;//匹配会员id
    private String orderNo;//订单号
    private String payTime;//付款时间
    private int payType;//付款方式，1到付，2现付
    private int payWay;//支付方式，1线上支付，2线下支付
    private String receiveAddress;//收货地地址
    private String receiveAddressCode;//收货地区域代码
    private String receiveMobile;//收货人手机号
    private String receiveName;//收货人
    private String receivePosition;//收货地坐标
    private String remark;//备注
    private String sendAddress;//发货地地址
    private String sendAddressCode;//发货地区域代码
    private String sendMobile;//发货人手机号
    private String sendName;//发货人姓名
    private String sendPosition;//发货地坐标
    private int state;
    private String transferTime;//配送时间
    private String useCarType;//用车类型，字典
    /**
     * id : 57
     * receiveName : null
     * jdSubscriberId : null
     * driverId : null
     * payTime : null
     * transferTime : null
     * finishTime : null
     * matchState : null
     * matchSubscriberId : null
     * commentImage1 : null
     * commentImage2 : null
     * commentImage3 : null
     * stateName : 待接单
     * stateNameDriver : 待接单
     * stateNameConsignor : 待司机接单
     * useCarTypeName :
     * goodsTypeName :
     * carLengthName :
     * carModelName :
     * receiveAddressCodeName : 浙江省宁波市鄞州区
     * sendAddressCodeName : 浙江省宁波市鄞州区
     */
    private Object commentImage1;
    private Object commentImage2;
    private Object commentImage3;
    private String stateName;
    private String stateNameDriver;
    private String stateNameConsignor;
    private String useCarTypeName;
    private String goodsTypeName;
    private String carLengthName;
    private String carModelName;
    private String receiveAddressCodeName;
    private String sendAddressCodeName;

    public String getCarLength() {
        return carLength;
    }

    public void setCarLength(String carLength) {
        this.carLength = carLength;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getFeeType() {
        return feeType;
    }

    public void setFeeType(int feeType) {
        this.feeType = feeType;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(int goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public int getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(int goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getJdSubscriberId() {
        return jdSubscriberId;
    }

    public void setJdSubscriberId(String jdSubscriberId) {
        this.jdSubscriberId = jdSubscriberId;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public int getMatchState() {
        return matchState;
    }

    public void setMatchState(int matchState) {
        this.matchState = matchState;
    }

    public int getMatchSubscriberId() {
        return matchSubscriberId;
    }

    public void setMatchSubscriberId(int matchSubscriberId) {
        this.matchSubscriberId = matchSubscriberId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getPayWay() {
        return payWay;
    }

    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveAddressCode() {
        return receiveAddressCode;
    }

    public void setReceiveAddressCode(String receiveAddressCode) {
        this.receiveAddressCode = receiveAddressCode;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePosition() {
        return receivePosition;
    }

    public void setReceivePosition(String receivePosition) {
        this.receivePosition = receivePosition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSendAddressCode() {
        return sendAddressCode;
    }

    public void setSendAddressCode(String sendAddressCode) {
        this.sendAddressCode = sendAddressCode;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getSendPosition() {
        return sendPosition;
    }

    public void setSendPosition(String sendPosition) {
        this.sendPosition = sendPosition;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getUseCarType() {
        return useCarType;
    }

    public void setUseCarType(String useCarType) {
        this.useCarType = useCarType;
    }
    public Object getCommentImage1() {
        return commentImage1;
    }

    public void setCommentImage1(Object commentImage1) {
        this.commentImage1 = commentImage1;
    }

    public Object getCommentImage2() {
        return commentImage2;
    }

    public void setCommentImage2(Object commentImage2) {
        this.commentImage2 = commentImage2;
    }

    public Object getCommentImage3() {
        return commentImage3;
    }

    public void setCommentImage3(Object commentImage3) {
        this.commentImage3 = commentImage3;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateNameDriver() {
        return stateNameDriver;
    }

    public void setStateNameDriver(String stateNameDriver) {
        this.stateNameDriver = stateNameDriver;
    }

    public String getStateNameConsignor() {
        return stateNameConsignor;
    }

    public void setStateNameConsignor(String stateNameConsignor) {
        this.stateNameConsignor = stateNameConsignor;
    }

    public String getUseCarTypeName() {
        return useCarTypeName;
    }

    public void setUseCarTypeName(String useCarTypeName) {
        this.useCarTypeName = useCarTypeName;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getCarLengthName() {
        return carLengthName;
    }

    public void setCarLengthName(String carLengthName) {
        this.carLengthName = carLengthName;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
    }

    public String getReceiveAddressCodeName() {
        return receiveAddressCodeName;
    }

    public void setReceiveAddressCodeName(String receiveAddressCodeName) {
        this.receiveAddressCodeName = receiveAddressCodeName;
    }

    public String getSendAddressCodeName() {
        return sendAddressCodeName;
    }

    public void setSendAddressCodeName(String sendAddressCodeName) {
        this.sendAddressCodeName = sendAddressCodeName;
    }
}
