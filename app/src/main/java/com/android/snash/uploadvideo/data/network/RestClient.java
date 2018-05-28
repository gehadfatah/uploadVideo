package com.android.snash.uploadvideo.data.network;

import android.content.Context;

;
import com.android.snash.uploadvideo.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
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
public class RestClient {
    public static Retrofit getClient(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(context.getResources().getString(R.string.main_url2))
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public interface ApiService {


        /*       @GET("3/movie/{id}/reviews")
               Call<ReviewsData> getReviews(@Path("id") String id, @Query("api_key") String api_key);*/
       /* @Headers({"content-type: text/html; charset=UTF-8"})*/
        @Multipart
        @POST("api/upload.php")
        Call<JsonObject> upload(@Part MultipartBody.Part fileToUpload/*, @Part MultipartBody.Part submit*/);

        @Multipart
        @POST("upload.php")
        Call<JsonObject> uploadS(@Part MultipartBody.Part fileToUpload/*, @Part MultipartBody.Part submit*/);

        /*@Headers({"content-type: text/html; charset=UTF-8"})*/
        @Multipart
        @POST
        Call<JsonObject> upload(@Url String url,
                                @Part MultipartBody.Part fileToUpload/*,
                                @Part MultipartBody.Part submit*/
        );

    }


}

