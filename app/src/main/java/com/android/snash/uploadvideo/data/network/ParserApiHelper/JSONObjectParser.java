package com.android.snash.uploadvideo.data.network.ParserApiHelper;

import com.android.snash.uploadvideo.data.network.Interfaces.CallBackJSONObject;
import com.android.snash.uploadvideo.data.network.RestClientRetrofit;
import com.google.gson.JsonObject;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//=================================================================
// this class contains all functions with return type JsonObject
//=================================================================
public class JSONObjectParser implements Callback<JsonObject> {

    private final String baseURL;
    CallBackJSONObject callBackJSONObject;
    RestClientRetrofit.RetrofitInterface retrofitInterface;

    //=================================================================
    // constructor for creating calling object for network instance
    //=================================================================
    public JSONObjectParser(String baseUrl, CallBackJSONObject call) {
        this.callBackJSONObject = call;
        this.baseURL = baseUrl;
       // retrofitInterface = GeoMingleApp.newInstance().newRetrofitRequest();
    }

    //=================================================================
    // below is all web api methods with return type jsonObject
    //=================================================================

    public void getGeomingleData(RestClientRetrofit.RetrofitInterface inter, String Url, String token) {
        String url = baseURL + "/" + Url;
        Call<JsonObject> resString = inter.getData(url, token);
        resString.enqueue(this);
    }

    public void postGeoMingleData(RestClientRetrofit.RetrofitInterface inter, String Url, JsonObject object) {
        String url = baseURL + "/" + Url;
        Call<JsonObject> resString = inter.postData(url, object, "");
        resString.enqueue(this);
    }

    public void postGeoMingleData(RestClientRetrofit.RetrofitInterface inter, String Url, JsonObject object, String header) {
        String url = baseURL + "/" + Url;
        Call<JsonObject> resString = inter.postData(url, object, header);
        resString.enqueue(this);
    }

    /*public void sendPostWithImage(String functionName, JsonObject jsonObject, MultipartBody.Part Images, String token) {
        String url = baseURL + "/" + functionName;
        String geofece_id = jsonObject.get(GeoMingleConstants.GEO_FENCE_ID).toString();
        String text = jsonObject.get(GeoMingleConstants.TEXT).toString();
        String POST_TYPE = jsonObject.get(GeoMingleConstants.POST_TYPE).toString();
        ArrayList<MultipartBody.Part> parts = new ArrayList<>();
        parts.add(Images);

        Call<JsonObject> objectRequest = retrofitInterface.upload(url, geofece_id, text, POST_TYPE, parts, token);
        objectRequest.enqueue(this);
    }*/

    public void sendPostWithImage(String functionName, RequestBody geofece_id, RequestBody text, RequestBody POST_TYPE, MultipartBody.Part Images, RequestBody date, String token) {
        String url = baseURL + "/" + functionName;
        Call<JsonObject> objectRequest = retrofitInterface.upload(url, geofece_id,text,POST_TYPE, Images,date, token);
        //Call<JsonObject> objectRequest = retrofitInterface.upload(url, "image", geofece_id,text,Images,"2017-07-08 22:26:58",token);
        objectRequest.enqueue(this);
    }

    public void sendPostRequest(String functionName, JsonObject jsonObject, String token) {
        String url = baseURL + "/" + functionName;
        Call<JsonObject> objectRequest = retrofitInterface.postData(url, jsonObject, token);
        objectRequest.enqueue(this);
    }
    public void login(String functionName, JsonObject jsonObject) {
        String url = baseURL + "/" + functionName;
        Call<JsonObject> objectRequest = retrofitInterface.postData(url, jsonObject);
        objectRequest.enqueue(this);
    }
    //=================================================================
    // Response CallBack for success response
    //=================================================================
    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        callBackJSONObject.onSuccess(response);
    }

    //=================================================================
    // Response CallBack for error response
    //=================================================================
    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        callBackJSONObject.OnFail(t);
    }
}
