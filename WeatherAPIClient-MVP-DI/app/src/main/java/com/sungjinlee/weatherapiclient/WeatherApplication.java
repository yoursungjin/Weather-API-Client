package com.sungjinlee.weatherapiclient;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.sungjinlee.weatherapiclient.data.source.NetModule;
import com.sungjinlee.weatherapiclient.data.source.OpenWeatherMapClient;
import com.sungjinlee.weatherapiclient.data.source.OpenWeatherMapModule;
import com.sungjinlee.weatherapiclient.data.source.WeatherRepository;
import com.sungjinlee.weatherapiclient.di.ApplicationModule;


import com.sungjinlee.weatherapiclient.di.DaggerNetComponent;
import com.sungjinlee.weatherapiclient.di.DaggerOpenWeatherMapComponent;
import com.sungjinlee.weatherapiclient.di.DaggerWeatherPresenterComponent;
import com.sungjinlee.weatherapiclient.di.NetComponent;
import com.sungjinlee.weatherapiclient.di.OpenWeatherMapComponent;
import com.sungjinlee.weatherapiclient.di.WeatherPresenterComponent;

import javax.inject.Inject;


/**
 * Created by Sungjin Lee on 1/1/18.
 */

public class WeatherApplication extends Application {

    @Inject
    WeatherRepository weatherRepository;

    private NetComponent mNetComponent;
    private OpenWeatherMapComponent mOpenWeatherMapComponent;
    private WeatherPresenterComponent mPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(OpenWeatherMapClient.BASE_URL))
                .build();

        mOpenWeatherMapComponent = DaggerOpenWeatherMapComponent.builder()
                .netComponent(mNetComponent)
                .openWeatherMapModule(new OpenWeatherMapModule())
                .build();

        mPresenterComponent = DaggerWeatherPresenterComponent.builder()
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public OpenWeatherMapComponent getOpenWeatherMapComponent() {
        return mOpenWeatherMapComponent;
    }

    public WeatherPresenterComponent getWeatherPresenterComponent() { return mPresenterComponent; }

    /**
     * Our Espresso tests need to be able to get an instance of the {@link WeatherRepository}
     * so that we can delete all weather data before running each test
     */
    @VisibleForTesting
    public WeatherRepository getWeatherRepository() {
        return weatherRepository;
    }
}
