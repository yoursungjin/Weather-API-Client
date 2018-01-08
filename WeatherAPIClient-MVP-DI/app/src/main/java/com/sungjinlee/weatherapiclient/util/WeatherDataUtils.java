package com.sungjinlee.weatherapiclient.util;

import android.support.annotation.NonNull;

import java.text.DecimalFormat;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Sungjin Lee on 1/1/18.
 */


public class WeatherDataUtils {

    /**
     * The degree symbol is appended.
     */
    @NonNull
    public static String appendDegreeSymbol(String number) {
        if (number == null)
            return null;
        return number + (char) 0x00B0;
    }

    /**
     * The percent symbol is appended.
     */
    @NonNull
    public static String appendPercentSymbol(String number) {
        if (number == null)
            return null;
        return number + "%";
    }

    /**
     * The imperial speed unit is appended.
     */
    @NonNull
    public static String appendImperialSpeedUnit(String number) {
        if (number == null)
            return null;
        return number + " mph";
    }

    /**
     * The weather icon url is set.
     */
    @NonNull
    public static String weatherIconURL(@NonNull String iconName) {
        return "http://openweathermap.org/img/w/" + iconName + ".png";
    }


    @NonNull
    public static String applyDecimalFormat(Double number) {
        if (number == null)
            return null;
        DecimalFormat df = new DecimalFormat("####");
        return df.format(number);
    }

}
