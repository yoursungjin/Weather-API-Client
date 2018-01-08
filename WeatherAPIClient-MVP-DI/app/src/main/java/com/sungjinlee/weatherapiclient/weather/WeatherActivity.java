package com.sungjinlee.weatherapiclient.weather;


import android.content.Context;
import android.os.Bundle;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sungjinlee.weatherapiclient.R;
import com.sungjinlee.weatherapiclient.WeatherApplication;
import com.sungjinlee.weatherapiclient.data.WeatherDataRoot;
import com.sungjinlee.weatherapiclient.data.source.OpenWeatherMapClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;


import com.sungjinlee.weatherapiclient.util.WeatherDataUtils;

import javax.inject.Inject;

/**
 * Created by Sungjin Lee on 1/1/18.
 */

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {

    @BindView(R.id.currentTemp) TextView currentTemp;
    @BindView(R.id.maxMinTemp) TextView maxMinTemp;
    @BindView(R.id.currentConditionsDesc) TextView currentConditionsDesc;
    @BindView(R.id.humidity) TextView humidity;
    @BindView(R.id.windSpeed) TextView windSpeed;
    @BindView(R.id.windDirection) TextView windDirection;
    @BindView(R.id.actionBarTitle) TextView actionBarTitle;
    @BindView(R.id.weatherIcon) ImageView weatherIcon;
    @BindView(R.id.location) EditText location;

    @Inject
    WeatherPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApplication) getApplication()).getOpenWeatherMapComponent().inject(this);

        mPresenter.takeView(this);

        setContentView(R.layout.weather_act);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        ButterKnife.bind(this);

        setDefaultLocation(OpenWeatherMapClient.DEFAULT_LOCATION);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private void setDefaultLocation(String defaultLocation) {
        location.setText(defaultLocation);
        fetchData(defaultLocation);
    }

    @OnEditorAction(R.id.location)
    protected boolean mobileNumberFieldShim(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            //force the keyboard to be hidden in all situations
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            getWeather(location);
            return true;
        }
        return false;
    }

    @OnClick(R.id.getWeather)
    public void getWeather(View v) {
        String query = location.getText().toString();
        if (query.equals("")) {
            showToastWithText("Location is required");
            return;
        }
        fetchData(query);
    }


    private void fetchData(String location) {
        mPresenter.fetchData(location);
    }

    @Override
    public void showToastWithText(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showWeatherData(WeatherDataRoot root) {
        actionBarTitle.setText(root.getName());

        String iconName = root.getWeather().get(0).getIcon();
        Picasso.with(weatherIcon.getContext()).load(WeatherDataUtils.weatherIconURL(iconName)).into(weatherIcon);

        String formattedTemp = WeatherDataUtils.applyDecimalFormat(root.getMain().getTemp());
        currentTemp.setText(WeatherDataUtils.appendDegreeSymbol(formattedTemp));

        String formattedMaxTemp = WeatherDataUtils.applyDecimalFormat(root.getMain().getTempMax());
        String formattedMinTemp = WeatherDataUtils.applyDecimalFormat(root.getMain().getTempMin());
        maxMinTemp.setText(WeatherDataUtils.appendDegreeSymbol(formattedMaxTemp) + "/" + WeatherDataUtils.appendDegreeSymbol(formattedMinTemp));
        currentConditionsDesc.setText(root.getWeather().get(0).getDescription());

        humidity.setText(WeatherDataUtils.appendPercentSymbol(root.getMain().getHumidity().toString()));

        String formattedWindSpeed = WeatherDataUtils.applyDecimalFormat(root.getWind().getSpeed());
        windSpeed.setText(WeatherDataUtils.appendImperialSpeedUnit(formattedWindSpeed));

        String formattedWindDegree = WeatherDataUtils.applyDecimalFormat(root.getWind().getDeg());
        windDirection.setText(WeatherDataUtils.appendDegreeSymbol(formattedWindDegree));

    }
}
