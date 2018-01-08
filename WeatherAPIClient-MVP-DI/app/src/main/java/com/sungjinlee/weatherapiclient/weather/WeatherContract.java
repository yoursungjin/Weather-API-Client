package com.sungjinlee.weatherapiclient.weather;

import com.sungjinlee.weatherapiclient.BasePresenter;
import com.sungjinlee.weatherapiclient.BaseView;
import com.sungjinlee.weatherapiclient.data.WeatherDataRoot;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

public interface WeatherContract {

    interface View extends BaseView<Presenter> {
        void showWeatherData(WeatherDataRoot root);
        void showToastWithText(String text);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
