package com.mohsendeadspace.retrofit_java.Retrofit;

import com.mohsendeadspace.retrofit_java.Modles.UserModle;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginuser.php")
    Call<UserModle> loginUser(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("registeruser.php")
    Call<UserModle> registerUser(@Field("email") String email,
                              @Field("username") String username,
                                 @Field("phone") String phone,
                                 @Field("password") String password);

}
