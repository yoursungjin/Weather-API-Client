package com.sungjinlee.weatherapiclient.data.source;

import com.sungjinlee.weatherapiclient.data.WeatherDataRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

public interface OpenWeatherMapClient {

    public static final String DEFAULT_LOCATION ="New York";
    //public static final String API_KEY = "put your api key here";
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<WeatherDataRoot> currentConditons(
            @Query("q") String location,
            @Query("mode") String mode,
            @Query("units") String units,
            @Query("APPID") String api_key
    );

}
