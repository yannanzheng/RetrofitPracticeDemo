package com.jephy.retrofitpracticedemo;

import retrofit2.*;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jfyang on 12/14/17.
 */

public interface GitHubService {

    @FormUrlEncoded
    @POST("index/updateInfoAll")
    Call<MyResponse> listRepos(@Field("type") String type);
}
