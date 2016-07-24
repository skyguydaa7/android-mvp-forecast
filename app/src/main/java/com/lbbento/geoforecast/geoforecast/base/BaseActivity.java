package com.lbbento.geoforecast.geoforecast.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.lbbento.geoforecast.geoforecast.GeoForecastApplication;
import com.lbbento.geoforecast.geoforecast.di.component.AppComponent;

/**
 * Created by lbbento on 25/07/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getAppComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragment.getClass().getName())
                .commitAllowingStateLoss();
    }

    /**
     * Get the Main ForecastApplication component for dependency injection.
     *
     */
    protected AppComponent getAppComponent() {
        return ((GeoForecastApplication)getApplication()).getAppComponent();
    }

}
