package com.lbbento.geoforecast.geoforecast.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.lbbento.geoforecast.geoforecast.R;
import com.lbbento.geoforecast.geoforecast.base.BaseActivity;
import com.lbbento.geoforecast.geoforecast.di.component.DaggerForecastComponent;
import com.lbbento.geoforecast.geoforecast.di.component.ForecastComponent;
import com.lbbento.geoforecast.geoforecast.di.module.ForecastModule;
import com.lbbento.geoforecast.geoforecast.di.module.NetModule;
import com.lbbento.geoforecast.geoforecast.forecast.ForecastFragment;
import com.lbbento.geoforecast.geoforecast.util.MyPermissions;

public class MainActivity extends BaseActivity {


    //Components
    ForecastComponent forecastComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkGpsPermission();

        //Injectors to be used by Fragments
        initializeInjectors();

        //Initialize with home fragment
        addFragment(R.id.main_content_frame, ForecastFragment.newInstance());

    }

    private void initializeInjectors() {
        this.forecastComponent = DaggerForecastComponent.builder()
                .appComponent(getAppComponent())
                .forecastModule(new ForecastModule())
                .build();
    }

    //Components
    public ForecastComponent getForecastComponent() {
        return forecastComponent;
    }

    private void checkGpsPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MyPermissions.MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MyPermissions.MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //ReInitialize with home fragment - could've just send an update to the fragment
                    addFragment(R.id.main_content_frame, ForecastFragment.newInstance());

                }

                return;
            }
        }
    }


}
