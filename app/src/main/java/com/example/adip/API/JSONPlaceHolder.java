package com.example.adip.API;

import android.database.Observable;
import android.net.Uri;

import com.example.adip.Classes.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolder {

    @POST("details")
    Call<User> register(@Body User user);

    @GET("details")
    Call<List<User>> getUser();

    @GET("details")
    Call<User> getUserUsingPhoneNumber(@Query("phoneNumber") String phoneNumber);

    @POST("details")
    Call<TempClass> uploadImage(@Body TempClass tempClass);

    @GET("details")
    Call<List<TempClass>> getUri();
}
