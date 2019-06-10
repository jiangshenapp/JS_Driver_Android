package com.js.driver.api;

import com.js.driver.model.bean.BillBean;
import com.js.driver.model.bean.CarBean;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.request.CarRequest;
import com.js.driver.model.request.DriverVerifiedRequest;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.http.BaseHttpResponse;
import com.xlgcx.http.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/09
 * desc   : 我的车辆
 * version: 3.0.0
 */
public interface CarApi {


    /**
     * 车辆列表
     */
    @POST("app/car/list")
    Observable<HttpResponse<ListResponse<CarBean>>> getCarList();


    /**
     * 车辆详情
     */
    @POST("/app/car/get/{id}")
    Observable<HttpResponse<CarBean>> getCarDetail(@Path("id") long id);


    /**
     * 绑定车辆
     */
    @POST("app/car/add")
    Observable<BaseHttpResponse> bindingCar(@Body CarRequest data);


    /**
     * 解绑车辆
     */
    @POST("/app/car/unbinding/{id}")
    Observable<BaseHttpResponse> unbindingCar(@Path("id") long id);
}
