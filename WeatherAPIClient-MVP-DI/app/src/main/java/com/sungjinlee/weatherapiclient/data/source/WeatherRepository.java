package com.sungjinlee.weatherapiclient.data.source;

import com.sungjinlee.weatherapiclient.data.WeatherDataRoot;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by Sungjin Lee on 1/1/18.
 */

public class WeatherRepository {

    @Inject
    OpenWeatherMapClient mOpenWeatherMapClient;

    @Inject
    public WeatherRepository() {

    }

    public void fetchData(String query, Callback<WeatherDataRoot> callback) throws IOException {
        Call<WeatherDataRoot> call = mOpenWeatherMapClient.currentConditons(query, "json", "imperial", OpenWeatherMapClient.API_KEY);
        call.enqueue(callback);
    }

}
