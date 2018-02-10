package com.rxzone.retrofitcall;

import com.rxzone.HomeActivity.AddPostFragment.AddProductData;
import com.rxzone.HomeActivity.AllPostFragment.AllPostData;
import com.rxzone.HomeActivity.LoginActivity.LoginData;
import com.rxzone.model.CommonDropDownData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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


    //
    @POST()
    Call<ArrayList<CommonDropDownData>> shippingDropDownReq(@Url String url);


    @POST(ApiClient.SAVE_PRODUCT_DETAILS_URL)
    Call<AddProductData> submitPostReq(@Header("Authorization") String token,
            @Body AddProductData addProductData);


}
