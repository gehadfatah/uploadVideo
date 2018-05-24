package com.android.snash.uploadvideo.data.network;

import android.content.Context;


import com.android.snash.uploadvideo.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by godaa on 29/04/2017.
 */
//retrieve data using retrofit
public class RestClientRetrofit {

    public static Retrofit getClient(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .baseUrl(context.getResources().getString(R.string.main_url))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public interface RetrofitInterface {

        //=================================================================
        // getting data from web api method with GET Request Type
        // it's optional to add headers to Requested Method
        //=================================================================
        @Headers({
                "content-type: application/json"})
        @GET
        Call<JsonObject> getData(@Url String url, @Header("token") String token);

        @GET("users/{user}/starred")
        // Observable<List<String>> getStarredRepositories(@Path("user") String userName);

        //=================================================================
        // getting data from web api method with POST Request Type and send Data via url
        // it's optional to add headers to Requested Method
        //=================================================================
        @Headers({
                "content-type: application/json"})
        @POST
        Call<JsonObject> postData(@Url String url, @Header("token") String token);

        //=================================================================
        // getting data from web api method with POST Request Type and send Data via Request Body
        // it's optional to add headers to Requested Method
        //=================================================================
        @Headers({
                "content-type: application/json"})
        @POST
        Call<JsonObject> postData(@Url String url, @Body JsonObject jsonObject, @Header("token") String token);

        @Headers({
                "content-type: application/json"})
        @POST
        Call<JsonObject> postData(@Url String url, @Body JsonObject jsonObject);

        //=================================================================
        // uploading images/sound/videos/documents with multipart retrofit
        // it's optional to add headers to Requested Method
        //=================================================================
        @Multipart
        @POST
        Call<JsonObject> upload(@Url String url,
                                @Part("geofence_id") String geofece_id,
                                @Part("text") String text,
                                @Part("post_type") String post_type,
                                @Part MultipartBody.Part image,
                                @Part("date_time") String date_time,
                                @Header("token") String token);

        @Multipart
        @POST
        Call<JsonObject> upload(@Url String url,
                                @Part("geofence_id") RequestBody geofece_id,
                                @Part("text") RequestBody text,
                                @Part("post_type") RequestBody post_type,
                                @Part MultipartBody.Part image,
                                @Part("date_time") RequestBody date_time,
                                @Header("token") String token);

        //=================================================================
        // getting data from web api method with GET Request Type
        // it's optional to add headers to Requested Method
        //=================================================================
        @Headers({
                "content-type: application/json"})
        @GET
        Call<JsonArray> getDataArray(@Url String url, @Header("token") String token);


    }
}

