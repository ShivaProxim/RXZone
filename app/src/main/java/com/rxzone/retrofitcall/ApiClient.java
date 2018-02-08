package com.rxzone.retrofitcall;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishek on 06-11-2017.
 */

public class ApiClient {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "http://ec2-13-127-106-106.ap-south-1.compute.amazonaws.com:7000/";


    public static final String LOGIN_URL = BASE_URL + "login/";

    public static final String FORGOT_URL = BASE_URL + "login/";

    public static final String GET_ALL_POSTS_URL = BASE_URL + "getAllPostMethod/";


    public static final String SHIPING_METHOD_DROP_DATA_URL = BASE_URL + "getShippingMethod/";
    public static final String PACKAGE_CON_DROP_DATA_URL = BASE_URL + "getPackageConditions/";
    public static final String GROUND_SHIPPING_DROP_DATA_URL = BASE_URL + "getGroundShipping/";

    //

//    public static final String ELASTIC_URL = "http://apiservice-ec.flikster.com/";


    /*public static final String BASE_URLL = "http://api.themoviedb.org/3/products";
    public static final String PRODUCT_DATA_URL = "http://apiservice.flikster.com/v3/product-ms/products";
    public static final String MOVIE_STORE_DATA_URL = "http://apiservice.flikster.com/v3/product-ms/products";
    public static final String CELEB_STORE_DATA_URL = "http://apiservice.flikster.com/v3/product-ms/products";
    public static final String DESIGN_DATA_URL = "http://apiservice.flikster.com/v3/designer-ms/designers";
    public static final String BRAND_DATA_URL = "http://apiservice.flikster.com/v3/brand-ms/brands";


    //Login
    public static final String LOGIN_URL = "http://apiservice.flikster.com/v3/user-ms/login/";
    //http://apiservice.flikster.com/v3/user-ms/login

    //Registe
    public static final String SIGNUP_URL = "http://apiservice.flikster.com/v3/user-ms/userReg/";

    //Send Otp
    public static final String SEND_OTP_URL = "http://apiservice.flikster.com/v3/user-ms/forgotpassword/";

    //Verify Otp
    public static final String VERIFY_OTP_URL = "http://apiservice.flikster.com/v3/user-ms/checkOtp/";


    //Check Password
    public static final String CHANGE_PASSWORD_URL = "http://apiservice.flikster.com/v3/user-ms/changePassword/";

    public static final String POST_STATUS_URL = BASE_URL + "likes-ms/isPostStatus/";
    public static final String POST_COMMENT_URL = BASE_URL + "comments-ms/postComment/";
    public static final String POST_CARD_STATUS_URL = BASE_URL + "likes-ms/postCardStatus";

    public static final String CREATE_SHARE_YOUR_STYLE_URL = BASE_URL + "share-your-style-ms/createShareYourStyle/";

//    public static final String MovieWatchStatus_URL = BASE_URL + "likes-ms/postCardStatus";

    public static final String MOVIE_WATCH_STATUS_URL = BASE_URL+ "likes-ms/isMovieWatchStatus/";

//    public static final String ALL_COMMENTS_URL = ELASTIC_URL + "isMovieWatchStatus/";

    public static final String GET_USER_WATCH_STATUS = BASE_URL + "likes-ms/getUserWatchStatusMobile/";
    //getUserWatchStatusMobile

    public static final String POST_WATCH_STATUS_URL = BASE_URL + "likes-ms/postCardStatusMobile/";

    //Auction Card first one
    public static final String AUCTION_URL = BASE_URL + "auctions-ms/getAuctions/";

    public static final String ON_GOING_BID_URL = BASE_URL + "auctions-ms/getHighBidAmount/";

    public static final String SIGNIN_FBORGMAIL_URL = "http://apiservice.flikster.com/v3/user-ms/socialReg/";
    //"http://apiservice.flikster.com/v3/user-ms/socialReg"
    //http://apiservice.flikster.com/v3/auctions-ms/auctions

    public static final String PLACE_BID_URL = BASE_URL + "auctions-ms/postBidByAuction/";
    //http://apiservice.flikster.com/v3/auctions-ms/postBidByAuction

    public static final String INSTAMOJO_URL = "http://api.instamojo.com/oauth2/token/";

    public static final String PAYMENT_REQ = "http://apiservice.flikster.com/v3/checkout-ms/paymentRequest/";
*/

    public static Retrofit getClient(String baseURL) {
        retrofit = null;
        if (retrofit == null) {
            Log.e("insdei retrfoit", "inside retroft");
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(new OkHttpClient.Builder()
                            .connectTimeout(30L, TimeUnit.SECONDS)
                            .readTimeout(30L, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Log.e("lewaving retrfoit", "leaving retroft");
        return retrofit;
    }

    public static Retrofit getClientData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
