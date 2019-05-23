package com.js.driver.api;

import com.js.driver.model.bean.AuthInfo;
import com.js.driver.model.bean.UserInfo;
import com.js.driver.model.request.DriverVerifiedRequest;
import com.js.driver.model.request.ParkVerifiedRequest;
import com.xlgcx.http.BaseHttpResponse;
import com.xlgcx.http.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by huyg on 2019/4/21.
 */
public interface UserApi {

    /**
     * 绑定手机号
     * @param mobile
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/bindMobile")
    Observable<BaseHttpResponse> bindMobile(@Field("mobile") String mobile, @Field("code") String code);

    /**
     * 短信验证码登录
     * @param mobile
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/smsLogin")
    Observable<HttpResponse<String>> smsLogin(@Field("mobile") String mobile, @Field("code") String code);


    /**
     * 密码登录
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/login")
    Observable<HttpResponse<String>> login(@Field("mobile") String mobile,
                                       @Field("password") String password);


    /**
     * 退出登录
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/logout")
    Observable<BaseHttpResponse> logout(@Field("token") String token);


    /**
     * 获取当前登录人信息
     * @return
     */
    @GET("app/subscriber/profile")
    Observable<HttpResponse<UserInfo>> profile();


    /**
     * 会员注册
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/registry")
    Observable<BaseHttpResponse> registry(@Field("mobile") String mobile,
                                          @Field("password") String password,
                                          @Field("code") String code);


    /**
     * 重置密码步骤1
     * @param mobile
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/resetPwdStep1")
    Observable<BaseHttpResponse> resetPwdStep1(@Field("mobile") String mobile, @Field("code") String code);


    /**
     * 重置密码步骤2
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/resetPwdStep2")
    Observable<BaseHttpResponse> resetPwdStep2(@Field("mobile") String mobile, @Field("password") String password);


    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/sendSmsCode")
    Observable<BaseHttpResponse> sendSmsCode(@Field("mobile") String mobile);


    /**
     * 设置密码
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/setPwd")
    Observable<BaseHttpResponse> setPwd(@Field("password") String password);


    /**
     * 修改头像
     * @param avatar
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/changeAvatar")
    Observable<BaseHttpResponse> changeAvatar(@Field("avatar") String avatar);


    /**
     * 修改昵称
     * @param nickname
     * @return
     */
    @FormUrlEncoded
    @POST("app/subscriber/changeNickname")
    Observable<BaseHttpResponse> changeNickname(@Field("nickname") String nickname);


    /**
     * 个人司机认证
     * @param data
     * @return
     */
    @POST("app/subscriber/verify/driverVerified")
    Observable<BaseHttpResponse> driverVerified(@Body DriverVerifiedRequest data);


    /**
     * 获取司机认证信息
     * @return
     */
    @POST("app/subscriber/verify/getDriverVerifiedInfo")
    Observable<HttpResponse<AuthInfo>> getDriverVerifiedInfo();


    /**
     * 园区成员认证
     * @param data
     * @return
     */
    @POST("app/subscriber/verify/parkVerified")
    Observable<BaseHttpResponse> parkVerified(@Body ParkVerifiedRequest data);


    /**
     * 获取园区成员认证信息
     * @return
     */
    @POST("app/subscriber/verify/getParkVerifiedInfo")
    Observable<HttpResponse<AuthInfo>> getParkVerifiedInfo();

}
