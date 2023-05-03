package com.example.lksapps.API;

import com.example.lksapps.Model.LoginResponse;
import com.example.lksapps.Model.RegisterResponse;
import com.example.lksapps.Model.RetrieveResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIRequestData {

        @FormUrlEncoded
        @POST("login.php")
        Call<LoginResponse> userLogin(
                @Field("username") String username,
                @Field("password") String password
        );
        @Multipart
        @POST("register.php")
        Call<RegisterResponse> userRegistrasi(
                @Part MultipartBody.Part foto,
                @Part("nama_user") RequestBody nama_user,
                @Part("username") RequestBody username,
                @Part("alamat") RequestBody alamat,
                @Part("telepon") RequestBody telepon,
                @Part("password") RequestBody password
        );
        @GET("retrievebrg.php")
        Call<RetrieveResponse> retrieveData();
;
}
