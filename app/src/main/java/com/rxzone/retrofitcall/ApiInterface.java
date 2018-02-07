package com.rxzone.retrofitcall;

import com.rxzone.Activities.Fragments.AllPostFragment.AllPostData;
import com.rxzone.Activities.LoginActivity.LoginData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by abhishek on 06-11-2017.
 */

public interface ApiInterface {

    @POST(ApiClient.LOGIN_URL)
    Call<LoginData> getLogInReq(@Body LoginData loginData);

   /* @POST(ApiClient.LOGIN_URL)
    Call<LoginData> verifyOtpData(@Body LoginData emailRegisterPostData);*/

    @GET
    Call<ArrayList<AllPostData>> getAllPostsReq(@Url String url);


}
