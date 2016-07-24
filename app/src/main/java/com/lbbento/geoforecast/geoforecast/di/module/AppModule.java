package com.lbbento.geoforecast.geoforecast.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lbbento.geoforecast.geoforecast.GeoForecastApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lbbento on 25/07/2016.
 */

@Module
public class AppModule {
    private GeoForecastApplication geoForecastApplication;

    public AppModule(GeoForecastApplication geoForecastApplication) {
        this.geoForecastApplication = geoForecastApplication;
    }

    @Provides @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(geoForecastApplication);
    }

    @Provides @Singleton
    GeoForecastApplication providesApplication() {
        return geoForecastApplication;
    }

    @Provides @Singleton
    Context providesContext() {
        return geoForecastApplication.getApplicationContext();
    }

}
