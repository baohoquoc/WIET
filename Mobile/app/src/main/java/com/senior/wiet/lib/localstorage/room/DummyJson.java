package com.senior.wiet.lib.localstorage.room;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senior.wiet.lib.response.LoginResponse;
import com.senior.wiet.lib.response.RecommendResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

public class DummyJson {

    private Context mContext;
    StringBuilder builder;

    @Inject
    public DummyJson(Context context) {
        mContext = context;
    }

    public Observable<Response<LoginResponse>> getItemsFromJson() {
        Gson gson = new Gson();
        try {
            String jsonDataString = convertJsonToString();
            return gson.fromJson(jsonDataString, new TypeToken<ArrayList<LoginResponse>>() {
            }.getType());
        } catch (IOException exception) {
            //Log.e(HomeActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
        return null;
    }

    public Observable<Response<RecommendResponse>> getItemsFromJsons() {
        Gson gson = new Gson();
        try {
            String jsonDataString = convertJsonToString();
            //Type fonudelistType= new TypeToken<ArrayList<RecommendResponse>>() {}.getType();
            //List<RecommendResponse> list= new Gson().fromJson(jsonDataString, fonudelistType);
            return gson.fromJson(jsonDataString, new TypeToken<ArrayList<RecommendResponse>>() {
            }.getType());
            //return gson.fromJson(jsonDataString, (Type) RecommendResponse.class);
        } catch (IOException exception) {
            //Log.e(HomeActivity.class.getName(), "Unable to parse JSON file.", exception);
        }
        return null;
    }

    private String convertJsonToString() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonDataString;
            inputStream =// new FileInputStream("E:\\_Bootcamp\\wiet\\Mobile\\app\\src\\main\\res\\raw\\recommend_json");
                    mContext.getResources().openRawResource(mContext.getResources().getIdentifier("recommend_json", "raw", mContext.getPackageName()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }

}
