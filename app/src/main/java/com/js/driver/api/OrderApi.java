package com.js.driver.api;

import com.js.driver.model.bean.OrderBean;
import com.js.driver.model.request.FindOrder;
import com.js.driver.model.request.OrderStatus;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.http.BaseHttpResponse;
import com.xlgcx.http.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by huyg on 2019-05-30.
 */
public interface OrderApi {


    /**
     * 拒绝配送
     * @param id 订单id
     * @return
     */
    @POST("app/driver/order/cancelDistribution/{id}")
    Observable<BaseHttpResponse> cancelDistribution(@Path("id") long id);


    /**
     * 取消接货
     */
    @POST("/app/driver/order/cancelReceive/{id}")
    Observable<BaseHttpResponse> cancelReceive(@Path("id") long id);


    /**
     * 回执评价
     * @param id
     * @return
     */
    @POST("app/driver/order/comment/{id}")
    Observable<BaseHttpResponse> comment(@Path("id") long id);


    /**
     * 完成配送
     * @param id
     * @return
     */
    @POST("app/driver/order/completeDistribution/{id}")
    Observable<BaseHttpResponse> completeDistribution(@Path("id") long id);


    /**
     * 等待货主接货
     * @param id
     * @return
     */
    @POST("app/driver/order/consignorReceive/{id}")
    Observable<BaseHttpResponse> consignorReceive(@Path("id") long id);


    /**
     * 开始配送
     * @param id
     * @return
     */
    @POST("app/driver/order/distribution/{id}")
    Observable<BaseHttpResponse> distribution(@Path("id") long id);


    /**
     * 找货 所有待分配订单
     * @param current
     * @param size
     * @param findOrder
     * @return
     */
    @POST("app/driver/order/find")
    Observable<HttpResponse<ListResponse<OrderBean>>> find(@Query("current") int current,
                                                           @Query("size") int size,
                                                           @Body FindOrder findOrder);


    /**
     * 我的运单
     * @param current
     * @param size
     * @param orderStatus
     * @return
     */
    @POST("app/driver/order/list")
    Observable<HttpResponse<ListResponse<OrderBean>>> getOrderList(@Query("current") int current,
                                                        @Query("size") int size,
                                                        @Body OrderStatus orderStatus);


    /**
     * 接单
     * @param id
     * @return
     */
    @POST("app/driver/order/receive/{id}")
    Observable<BaseHttpResponse> receive(@Path("id") long id);


    /**
     * 拒绝接单
     * @param id
     * @return
     */
    @POST("app/driver/order/refuse/{id}")
    Observable<BaseHttpResponse> refuse(@Path("id") long id);


    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @GET("app/order/get/{id}")
    Observable<HttpResponse<OrderBean>> getOrderDetail(@Path("id") long id);
}
