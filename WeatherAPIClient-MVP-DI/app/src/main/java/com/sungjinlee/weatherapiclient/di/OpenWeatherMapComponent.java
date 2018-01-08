package com.sungjinlee.weatherapiclient.di;

import com.sungjinlee.weatherapiclient.data.source.OpenWeatherMapModule;
import com.sungjinlee.weatherapiclient.weather.WeatherActivity;

import dagger.Component;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = OpenWeatherMapModule.class)
public interface OpenWeatherMapComponent {
    void inject(WeatherActivity activity);
}
