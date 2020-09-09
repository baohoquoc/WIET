package com.senior.wiet.lib.service;


import com.senior.wiet.lib.response.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by lance on 6/April/2020.
 */
public interface TemperatureService {

    @GET("/data/2.5/weather")
    Observable<Response<WeatherResponse>> weather(@Query("lat") float lat,
                                                  @Query("lon") float lon,
                                                  @Query("units") String units,
                                                  @Query("appid") String appId);
}
