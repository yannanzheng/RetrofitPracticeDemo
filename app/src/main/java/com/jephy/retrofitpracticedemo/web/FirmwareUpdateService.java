package com.jephy.retrofitpracticedemo.web;

import retrofit2.*;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jfyang on 12/14/17.
 */

public interface FirmwareUpdateService {

    @FormUrlEncoded
    @POST("index/updateInfoAll")
    Call<FirmwareUpgradeResponse> listRepos(@Field("type") String type);
}
