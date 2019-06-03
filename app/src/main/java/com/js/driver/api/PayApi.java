package com.js.driver.api;

import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.xlgcx.http.BaseHttpResponse;
import com.xlgcx.http.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by huyg on 2019-05-31.
 */
public interface PayApi {


    @FormUrlEncoded
    @POST("app/account/recharge")
    Observable<HttpResponse<PayInfo>> recharge(@Field("businessId") int businessId,
                                               @Field("channelType") int channelType,
                                               @Field("money") double money,
                                               @Field("routeId") int routeId);




    @POST("/pigx-pay-biz/pay/getRoute")
    Observable<HttpResponse<List<PayRouter>>> getPayRouter(@Query("business") int businessId,
                                                           @Query("merchantId") int merchantId);

}
