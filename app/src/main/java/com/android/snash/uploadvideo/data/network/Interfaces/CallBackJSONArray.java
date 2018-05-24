package com.android.snash.uploadvideo.data.network.Interfaces;

import com.google.gson.JsonArray;

import retrofit2.Response;

/**
 * Created by aayman on 5/23/2017.
 */

public interface CallBackJSONArray {
    void onSuccess(Response<JsonArray> o);

    void OnFail(Throwable o);
}
