package com.example.vazhikatti;

import com.example.vazhikatti.video;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/register")
    Call<ResponseBody> register(@Body String mobile);

    @POST("/verify")
    Call<ResponseBody> verify(@Body String mobile, @Body String otp, @Body String username, @Body String password);

    @POST("/login")
    Call<ResponseBody> login(@Body String mobile);

    @Multipart
    @POST("/upload")
    Call<ResponseBody> uploadVideo(@Part MultipartBody.Part video, @Part("title") RequestBody title,
                                   @Part("description") RequestBody description, @Part("playlist") RequestBody playlist);

    @GET("/all")
    Call<List<video>> getAllVideos();

    @GET("/playlist/{playlist}")
    Call<List<video>> getVideosByPlaylist(@Path("playlist") String playlist);

    @GET("/favorites")
    Call<List<video>> getFavoriteVideos();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://1192.168.110.227:8083/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}