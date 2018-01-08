package com.sungjinlee.weatherapiclient.weather;

/**
 * Created by Sungjin Lee on 1/1/18.
 */


import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {

    @Provides
    WeatherContract.Presenter providesWeatherPresenter() {
        return new WeatherPresenter();
    }

}
