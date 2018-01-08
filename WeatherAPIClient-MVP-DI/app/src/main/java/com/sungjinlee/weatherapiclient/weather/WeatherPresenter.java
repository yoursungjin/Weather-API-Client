package com.sungjinlee.weatherapiclient.weather;

import android.util.Log;
import com.sungjinlee.weatherapiclient.data.WeatherDataRoot;
import com.sungjinlee.weatherapiclient.data.source.WeatherRepository;

import javax.annotation.Nullable;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sungjin Lee on 1/1/18.
 */


final class WeatherPresenter implements WeatherContract.Presenter {

    @Inject
    WeatherRepository mWeatherRepository;

    @Nullable
    private WeatherContract.View mWeatherView;

    @Override
    public void takeView(WeatherContract.View view) {
        this.mWeatherView = view;
    }


    @Override
    public void dropView() {
        mWeatherView = null;
    }

    @Inject
    public WeatherPresenter () {

    }

    public void fetchData(String query) {
        try {
            mWeatherRepository.fetchData(query, new Callback<WeatherDataRoot>() {
                @Override
                public void onResponse(Call<WeatherDataRoot> call, Response<WeatherDataRoot> response) {
                    if (response.isSuccessful()) {
                        mWeatherView.showWeatherData(response.body());

                    } else {
                        // error response, no access to resource
                        try {
                            mWeatherView.showToastWithText(response.errorBody().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<WeatherDataRoot> call, Throwable t) {
                    mWeatherView.showToastWithText(t.getMessage());
                    Log.d("Error", t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
