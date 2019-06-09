package com.js.driver.api;

import com.js.driver.model.bean.CarBean;
import com.js.driver.model.bean.DriverBean;
import com.js.driver.model.response.ListResponse;
import com.xlgcx.http.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * author : hzb
 * e-mail : hanzhanbing@evcoming.com
 * time   : 2019/06/09
 * desc   :
 * version: 3.0.0
 */
public interface DriverApi {

    /**
     * 园区司机列表
     */
    @POST("app/park/drivers")
    Observable<HttpResponse<ListResponse<DriverBean>>> getDriverList();
}
