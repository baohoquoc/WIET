package com.senior.wiet.lib.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.senior.wiet.R;
import com.senior.wiet.fragments.meal.MealPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.mealtoday.MealValue;
import com.senior.wiet.lib.response.MealResponse;
import com.senior.wiet.lib.response.WeatherResponse;
import com.senior.wiet.lib.service.TemperatureService;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Mitt on 21/February/2020.
 */
public class MealModel extends BaseObservable {

    private Context context;
    private DisposableManager mDisposableManager;
    private TemperatureService temperatureService;
    private MealPresenter mealPresenter;
    private WietApiService wietApiService;
    private AppSharedPreference mAppSharedPreference;

    public String txtName1, txtName2, txtName3, txtName4, distance1, distance2, distance3, distance4, price1, price2, price3, price4, temperature;


    private double bDistance, lDistance, dDistance, tDistance;


    public MealModel(DisposableManager mDisposableManager, Context context, TemperatureService temperatureService, WietApiService wietApiService, AppSharedPreference appSharedPreference) {
        this.context = context;
        this.mDisposableManager = mDisposableManager;
        this.temperatureService = temperatureService;
        this.wietApiService = wietApiService;
        this.mAppSharedPreference = appSharedPreference;
    }

    public void attachPresenter(MealPresenter mealPresenter) {
        this.mealPresenter = mealPresenter;
    }

    public void getWeatherByAPI(float lat, float lon, String units, String appId) {
        if (Utilities.isNetworkConnected(context)) {
            mealPresenter.destroyAPI(mDisposableManager.weather(temperatureService.weather(lat, lon, units, appId),
                    new IDisposableListener<WeatherResponse>() {
                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onHandleData(WeatherResponse weatherResponse) {
                            saveTemperature(weatherResponse.getTemperatureModel());
                            updateTemperature(weatherResponse.getTemperatureModel());
                            mealPresenter.tempSuccess(weatherResponse.getTemperatureModel());
                        }

                        @Override
                        public void onRequestWrongData(int t, String message) {

                        }

                        @Override
                        public void onApiFailure(Throwable error) {
                        }
                    }));
        }
    }

    public void mealtoday(String token, int temp) {
        if (Utilities.isNetworkConnected(context)) {
            mealPresenter.destroyAPI(mDisposableManager.mealtoday(wietApiService.mealtoday("Bearer " + token, temp), new IDisposableListener<MealResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(MealResponse mealResponse) {

                    if (null == mealResponse.getMealValue().getMeal().getBreakfast() || null == mealResponse.getMealValue().getMeal().getLunch() || null == mealResponse.getMealValue().getMeal().getDinner() || null == mealResponse.getMealValue().getMeal().getTemperature()) {
                        String mes = context.getString(R.string.noData);
                        setDistance1(mes);
                        setDistance2(mes);
                        setDistance3(mes);
                        setDistance4(mes);
                    } else {
                        saveLocalStorage(mealResponse.getMealValue());
                        updateDataOnUI(mealResponse.getMealValue());
                        mealPresenter.mealTodaySuccess(mealResponse.getMealValue());
                    }
                }

                @Override
                public void onRequestWrongData(int t, String message) {

                }

                @Override
                public void onApiFailure(Throwable error) {

                }
            }));
        }
    }

    public double distance(float lat1, float lon1, float lat2, float lon2) {
        double R = 6378.137;
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000;
    }

    private void saveLocalStorage(MealValue mealValue) {

        mAppSharedPreference.setBid(mealValue.getMeal().getBreakfast().getId());
        mAppSharedPreference.setLid(mealValue.getMeal().getLunch().getId());
        mAppSharedPreference.setDid(mealValue.getMeal().getDinner().getId());
        mAppSharedPreference.setTid(mealValue.getMeal().getTemperature().getId());

        mAppSharedPreference.setBLatitude(mealValue.getMeal().getBreakfast().getLatitude());
        mAppSharedPreference.setLLatitude(mealValue.getMeal().getLunch().getLatitude());
        mAppSharedPreference.setDLatitude(mealValue.getMeal().getDinner().getLatitude());
        mAppSharedPreference.setTLatitude(mealValue.getMeal().getTemperature().getLatitude());

        mAppSharedPreference.setBLongitude(mealValue.getMeal().getBreakfast().getLongitude());
        mAppSharedPreference.setLLongitude(mealValue.getMeal().getLunch().getLongitude());
        mAppSharedPreference.setDLongitude(mealValue.getMeal().getDinner().getLongitude());
        mAppSharedPreference.setTLongitude(mealValue.getMeal().getTemperature().getLongitude());

        calculatorDistance();
    }

    private void saveTemperature(TemperatureModel temperatureModel) {
        mAppSharedPreference.setTemp(temperatureModel.getTemp());
        Log.d("TEMP", "" + temperatureModel.getTemp());
    }

    public void calculatorDistance() {
        double rLat = mAppSharedPreference.getRealLat();
        double rLon = mAppSharedPreference.getRealLong();

        float bLat = mAppSharedPreference.getBLatitude();
        float lLat = mAppSharedPreference.getLLatitude();
        float dLat = mAppSharedPreference.getDLatitude();
        float tLat = mAppSharedPreference.getTLatitude();

        float bLon = mAppSharedPreference.getBLongitude();
        float lLon = mAppSharedPreference.getLLongitude();
        float dLon = mAppSharedPreference.getDLongitude();
        float tLon = mAppSharedPreference.getTLongitude();

        bDistance = distance((float) rLat, (float) rLon, bLat, bLon) / 1000;
        lDistance = distance((float) rLat, (float) rLon, lLat, lLon) / 1000;
        dDistance = distance((float) rLat, (float) rLon, dLat, dLon) / 1000;
        tDistance = distance((float) rLat, (float) rLon, tLat, tLon) / 1000;
    }

    private void updateTemperature(TemperatureModel temperatureModel) {
        Log.d("Temp", "" + temperatureModel.getTemp());
        // String mess= getString(R.string.alert_endofrecomment);
        String mes = context.getString(R.string.txttemperature) + " ";
        setTemperature(mes + temperatureModel.getTemp() + (char) 0x00B0 + "C");
    }

    private void updateDataOnUI(MealValue mealValue) {
        setTxtName1(mealValue.getMeal().getBreakfast().getName().replaceAll("[()]", ""));
        setTxtName2(mealValue.getMeal().getLunch().getName().replaceAll("[()]", ""));
        setTxtName3(mealValue.getMeal().getDinner().getName().replaceAll("[()]", ""));
        setTxtName4(mealValue.getMeal().getTemperature().getName().replaceAll("[()]", ""));

        mealPresenter.updatePlaceHolderFood(mealValue.getMeal());


        setDistance1(String.format("%.1f", bDistance) + " " + context.getString(R.string.km_from_here));
        setDistance2(String.format("%.1f", lDistance) + " " + context.getString(R.string.km_from_here));
        setDistance3(String.format("%.1f", dDistance) + " " + context.getString(R.string.km_from_here));
        setDistance4(String.format("%.1f", tDistance) + " " + context.getString(R.string.km_from_here));

        int priceB = mealValue.getMeal().getBreakfast().getPrice();
        int priceL = mealValue.getMeal().getLunch().getPrice();
        int priceD = mealValue.getMeal().getDinner().getPrice();
        int priceT = mealValue.getMeal().getTemperature().getPrice();
        String lang = Resources.getSystem().getConfiguration().locale.getLanguage();
        if (lang.equalsIgnoreCase("en")) {
            setPrice1("VND " + NumberFormat.getNumberInstance(Locale.US).format(priceB));
            setPrice2("VND " + NumberFormat.getNumberInstance(Locale.US).format(priceL));
            setPrice3("VND " + NumberFormat.getNumberInstance(Locale.US).format(priceD));
            setPrice4("VND " + NumberFormat.getNumberInstance(Locale.US).format(priceT));
        } else {
            setPrice1(NumberFormat.getNumberInstance(Locale.US).format(priceB) + " VNĐ");
            setPrice2(NumberFormat.getNumberInstance(Locale.US).format(priceL) + " VNĐ");
            setPrice3(NumberFormat.getNumberInstance(Locale.US).format(priceD) + " VNĐ");
            setPrice4(NumberFormat.getNumberInstance(Locale.US).format(priceT) + " VNĐ");
        }
    }

    @Bindable
    public String getTxtName2() {
        return txtName2;
    }

    @Bindable
    public void setTxtName2(String value) {
        this.txtName2 = value;
        notifyPropertyChanged(com.senior.wiet.BR.txtName2);
    }

    @Bindable
    public String getTxtName1() {
        return txtName1;
    }

    @Bindable
    public void setTxtName1(String value) {
        this.txtName1 = value;
        notifyPropertyChanged(com.senior.wiet.BR.txtName1);
    }

    @Bindable
    public String getTxtName3() {
        return txtName3;
    }

    @Bindable
    public void setTxtName3(String value) {
        this.txtName3 = value;
        notifyPropertyChanged(com.senior.wiet.BR.txtName3);
    }

    @Bindable
    public String getTxtName4() {
        return txtName4;
    }

    @Bindable
    public void setTxtName4(String value) {
        this.txtName4 = value;
        notifyPropertyChanged(com.senior.wiet.BR.txtName4);
    }

    @Bindable
    public String getDistance1() {
        return distance1;
    }

    @Bindable
    public void setDistance1(String value) {
        this.distance1 = value;
        notifyPropertyChanged(com.senior.wiet.BR.distance1);
    }

    @Bindable
    public String getDistance2() {
        return distance2;
    }

    @Bindable
    public void setDistance2(String value) {
        this.distance2 = value;
        notifyPropertyChanged(com.senior.wiet.BR.distance2);
    }

    @Bindable
    public String getDistance3() {
        return distance3;
    }

    @Bindable
    public void setDistance3(String value) {
        this.distance3 = value;
        notifyPropertyChanged(com.senior.wiet.BR.distance3);
    }

    @Bindable
    public String getDistance4() {
        return distance4;
    }

    @Bindable
    public void setDistance4(String value) {
        this.distance4 = value;
        notifyPropertyChanged(com.senior.wiet.BR.distance4);
    }

    @Bindable
    public String getTemperature() {
        return temperature;
    }

    @Bindable
    public void setTemperature(String value) {
        this.temperature = value;
        notifyPropertyChanged(com.senior.wiet.BR.temperature);
    }

    @Bindable
    public String getPrice1() {
        return price1;
    }

    @Bindable
    public void setPrice1(String value) {
        this.price1 = value;
        notifyPropertyChanged(com.senior.wiet.BR.price1);
    }

    @Bindable
    public String getPrice2() {
        return price2;
    }

    @Bindable
    public void setPrice2(String value) {
        this.price2 = value;
        notifyPropertyChanged(com.senior.wiet.BR.price2);
    }

    @Bindable
    public String getPrice3() {
        return price3;
    }

    @Bindable
    public void setPrice3(String value) {
        this.price3 = value;
        notifyPropertyChanged(com.senior.wiet.BR.price3);
    }

    @Bindable
    public String getPrice4() {
        return price4;
    }

    @Bindable
    public void setPrice4(String value) {
        this.price4 = value;
        notifyPropertyChanged(com.senior.wiet.BR.price4);
    }


}