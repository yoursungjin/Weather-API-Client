package com.sungjinlee.weatherapiclient.data.source;

import com.sungjinlee.weatherapiclient.di.UserScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

@Module
public class OpenWeatherMapModule {

    @Provides
    @UserScope
    public OpenWeatherMapClient providesOpenWeatherMapClient(Retrofit retrofit) {
        return retrofit.create(OpenWeatherMapClient.class);
    }
}
