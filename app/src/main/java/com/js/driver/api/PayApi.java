package com.js.driver.api;

import com.js.driver.model.bean.AccountInfo;
import com.js.driver.model.bean.BillBean;
import com.js.driver.model.bean.PayInfo;
import com.js.driver.model.bean.PayRouter;
import com.js.driver.model.bean.UserInfo;
import com.xlgcx.http.BaseHttpResponse;
import com.xlgcx.http.HttpResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by huyg on 2019-05-31.
 */
public interface PayApi {

    /**
     * 账单明细
     */
    @GET("app/account/getTradeRecord")
    Observable<HttpResponse<List<BillBean>>> getTradeRecord(@Query("type") long type);


    /**
     * 账户详情
     */
    @GET("app/account/getBySubscriber")
    Observable<HttpResponse<AccountInfo>> getBySubscriber();


    /**
     * 提现申请
     */
    @FormUrlEncoded
    @POST("app/account/balanceWithdraw")
    Observable<BaseHttpResponse> balanceWithdraw(@Field("withdrawType") int withdrawType,
                                               @Field("withdrawChannel") int withdrawChannel,
                                               @Field("bankCard") String bankCard,
                                               @Field("khh") String khh,
                                                 @Field("zh") String zh,
                                                 @Field("zfbzh") String zfbzh,
                                                 @Field("zfbmc") String zfbmc);

    /**
     * 账户充值
     */
    @FormUrlEncoded
    @POST("app/account/recharge")
    Observable<HttpResponse<PayInfo>> recharge(@Field("tradeType") int tradeType,
                                               @Field("channelType") int channelType,
                                               @Field("money") double money,
                                               @Field("routeId") int routeId);


    /**
     * 获取支付路由
     */
    @POST("/pigx-pay-biz/pay/getRoute")
    Observable<HttpResponse<List<PayRouter>>> getPayRouter(@Query("business") int businessId,
                                                           @Query("merchantId") int merchantId);

    /**
     * 运力端缴纳保证金
     */
    @POST("app/account/rechargeDriverDeposit")
    Observable<BaseHttpResponse> payAccount(@Query("deposit") double deposit);

}
