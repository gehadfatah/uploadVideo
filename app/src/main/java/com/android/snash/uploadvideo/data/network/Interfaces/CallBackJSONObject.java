package com.android.snash.uploadvideo.data.network.Interfaces;

import com.google.gson.JsonObject;

import retrofit2.Response;


//=================================================================
// interface to be implemented by calling class or instance for jsonObject return type
//=================================================================
public interface CallBackJSONObject {

    void onSuccess(Response<JsonObject> o);

    void OnFail(Throwable o);
}
