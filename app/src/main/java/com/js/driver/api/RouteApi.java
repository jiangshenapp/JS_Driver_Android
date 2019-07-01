package com.js.driver.api;

import com.js.driver.model.bean.RouteBean;
import com.js.driver.model.request.RouteRequest;
import com.js.driver.model.response.ListResponse;
import com.js.http.BaseHttpResponse;
import com.js.http.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/12
 * desc   :
 * version: 3.0.0
 */
public interface RouteApi {


    /**
     * 我的线路
     */
    @POST("app/line/myLines")
    Observable<HttpResponse<ListResponse<RouteBean>>> getRouteList(@Body RouteRequest routeRequest);


    /**
     * 线路详情
     */
    @POST("app/line/get/{id}")
    Observable<HttpResponse<RouteBean>> getRouteDetail(@Path("id") long id);


    /**
     * 删除线路
     */
    @POST("app/line/remove/{id}")
    Observable<BaseHttpResponse> removeRoute(@Path("id") long id);


    /**
     * 申请精品线路
     */
    @POST("app/line/classic/{id}")
    Observable<BaseHttpResponse> applyClassicLine(@Path("id") long id);


    /**
     * 启用停用 1启用0停用
     */
    @POST("app/line/enable")
    Observable<BaseHttpResponse> enableLine(@Query("lineId") long driverId,
                                            @Query("enable") long enable);


    /**
     * 添加线路
     */
    @POST("app/line/add")
    Observable<BaseHttpResponse> addLine(@Body RouteRequest data);


    /**
     * 编辑线路
     */
    @POST("app/line/edit")
    Observable<BaseHttpResponse> editLine(@Body RouteRequest data);
}
