package com.senior.wiet.activities.splash;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.activities.information.InformationActivity;
import com.senior.wiet.activities.login.LoginActivity;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.databinding.ActivitySplashBinding;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.SplashModel;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.Utilities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class SplashActivity extends BaseActivity<SplashContract.Presenter> implements SplashContract.View, LocationListener {

    @Inject
    Context mContext;

    @Inject
    SplashPresenter mPresenter;

    @Inject
    ProgressBarDialog mProgressBarDialog;

    @Inject
    AppSharedPreference mAppSharedPreference;

    @Inject
    SplashModel mSplashModel;

    private LocationManager locationManager;

    private ActivitySplashBinding binding;
    private Disposable mDisposable;
    private static int SPLASH_TIME_OUT = 2000;
    private static FirebaseUser mFirebaseUser;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    public void timeOut() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //initRealLocation();
                mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (mFirebaseUser != null && !TextUtils.equals(Constants.EMPTY_STRING, AppSharedPreference.getInstance(SplashActivity.this).getToken())) {
                    if (AppSharedPreference.getInstance(SplashActivity.this).getIsFirstLogin()) {
                        startActivity(new Intent(SplashActivity.this, InformationActivity.class));
                        //mPresenter.updateLocation();
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        binding.setPresenter(getPresenter());
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (checkLocationPermission() == true) {
            Location last_know_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            /*This method to fix null location*/
            List<String> providers = locationManager.getProviders(true);
            for (String provider : providers) {
                locationManager.requestLocationUpdates(provider, 1000, 0,
                        new LocationListener() {

                            public void onLocationChanged(Location location) {
                            }

                            public void onProviderDisabled(String provider) {
                            }

                            public void onProviderEnabled(String provider) {
                            }

                            public void onStatusChanged(String provider, int status,
                                                        Bundle extras) {
                            }
                        });
                last_know_location = locationManager.getLastKnownLocation(provider);
                Geocoder gcd = new Geocoder(this, Locale.getDefault());
                List<Address> addresses = null;
                if (last_know_location != null) {
                    AppSharedPreference.getInstance(mContext).setRealLat( last_know_location.getLatitude());
                    AppSharedPreference.getInstance(mContext).setRealLong( last_know_location.getLongitude());

                    try {
                        addresses = gcd.getFromLocation(last_know_location.getLatitude(), last_know_location.getLongitude(), 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;  // if addresses == null
                    }
                    String address = addresses.get(0).getAddressLine(0);
                    Log.i("address", address);
                    String state = addresses.get(0).getAdminArea();
                    Log.i("state_test", state);
                    if(state.equals("Hồ Chí Minh")) state = "TP. HCM";
                    AppSharedPreference.getInstance(this).setDevicelocation(state);
                    Log.i("state_test", last_know_location.toString());
                }
                else {
                    last_know_location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
            }
            /**********************/

            /*if (last_know_location == null) {
                last_know_location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }*/
            Utilities.Log("last_know_location", last_know_location.toString());
            AppSharedPreference.getInstance(mContext).setRealLat((float) last_know_location.getLatitude());
            AppSharedPreference.getInstance(mContext).setRealLong((float) last_know_location.getLongitude());

            timeOut();
        }

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (mFirebaseUser != null && !TextUtils.equals(Constants.EMPTY_STRING, AppSharedPreference.getInstance(SplashActivity.this).getToken())) {
                    if (AppSharedPreference.getInstance(SplashActivity.this).getIsFirstLogin()) {
                        startActivity(new Intent(SplashActivity.this, InformationActivity.class));
                        //mPresenter.updateLocation();
                    }
                    initRealLocation();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, SPLASH_TIME_OUT);*/

        // First login
        // Call API
        // Success => navigate MainActivity (period of time user wait)

    }

    /*public void initRealLocation() {
        Log.i("jwt_toekn", AppSharedPreference.getInstance(this).getAccessToken());
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            String mess = getString(R.string.splash);
            Toast.makeText(mContext, mess, Toast.LENGTH_SHORT).show();
            return;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
            AppSharedPreference.getInstance(this).setRealLong(longitude);
            AppSharedPreference.getInstance(this).setRealLat(latitude);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            Log.i("address", address);
            String state = addresses.get(0).getAdminArea();
            Log.i("state_test", state);
            AppSharedPreference.getInstance(this).setDevicelocation(state);
            *//*if(!AppSharedPreference.getInstance(mContext).getIsFirstLogin()){
                mSplashModel.lastLocation(state);
            }*//*
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void attachModel(SplashModel SplashModel) {
        binding.setModel(SplashModel);
    }

    @Override
    public ActivitySplashBinding bindView() {
        return binding;
    }

    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    protected void onDestroy() {

        // This code to destroy API if LoginActivity is destroyed
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();

        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressBarDialog() {

    }

    @Override
    public void hideProgressBarDialog() {

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(this.getString(R.string.app_require_location_permission))
                        .setMessage(this.getString(R.string.do_you_agree))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(SplashActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                        Location last_know_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        /**********************/
                        List<String> providers = locationManager.getProviders(true);
                        for (String provider : providers) {
                            locationManager.requestLocationUpdates(provider, 1000, 0,
                                    new LocationListener() {

                                        public void onLocationChanged(Location location) {
                                        }

                                        public void onProviderDisabled(String provider) {
                                        }

                                        public void onProviderEnabled(String provider) {
                                        }

                                        public void onStatusChanged(String provider, int status,
                                                                    Bundle extras) {
                                        }
                                    });
                            last_know_location = locationManager.getLastKnownLocation(provider);
                            Geocoder gcd = new Geocoder(this, Locale.getDefault());
                            List<Address> addresses = null;
                            if (last_know_location != null) {
                                AppSharedPreference.getInstance(mContext).setRealLat((float) last_know_location.getLatitude());
                                AppSharedPreference.getInstance(mContext).setRealLong((float) last_know_location.getLongitude());

                                try {
                                    addresses = gcd.getFromLocation(last_know_location.getLatitude(), last_know_location.getLongitude(), 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    break;   // if addresses == null
                                }
                                String address = addresses.get(0).getAddressLine(0);
                                Log.i("address", address);
                                String state = addresses.get(0).getAdminArea();
                                Log.i("state_test", state);
                                AppSharedPreference.getInstance(this).setDevicelocation(state);
                                Log.i("state_test", last_know_location.toString());
                            }
                            else {
                                last_know_location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            }
                        }
                        /**********************/
                        AppSharedPreference.getInstance(mContext).setRealLat((float) last_know_location.getLatitude());
                        AppSharedPreference.getInstance(mContext).setRealLong((float) last_know_location.getLongitude());
                        timeOut();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), mContext.getString(R.string.application_need_location_permission), Toast.LENGTH_SHORT).show();

                }
                return;
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("location_change", location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
}
