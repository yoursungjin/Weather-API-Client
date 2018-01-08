package com.sungjinlee.weatherapiclient.di;

import com.sungjinlee.weatherapiclient.weather.WeatherModule;

import dagger.Component;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

@Component(modules = WeatherModule.class)
public interface WeatherPresenterComponent {

}
