package com.sungjinlee.weatherapiclient.di;


import com.sungjinlee.weatherapiclient.data.source.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    Retrofit retrofit();
//    OkHttpClient.Builder okHttpClient();
//    WeatherRepository weatherRepository();
}