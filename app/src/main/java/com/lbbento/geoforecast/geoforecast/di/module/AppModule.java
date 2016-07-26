package com.lbbento.geoforecast.geoforecast.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lbbento.geoforecast.data.datasource.ForecastDataSource;
import com.lbbento.geoforecast.data.repository.ForecastRepository;
import com.lbbento.geoforecast.data.source.local.ForecastLocalDataSource;
import com.lbbento.geoforecast.data.source.remote.ForecastRemoteDataSource;
import com.lbbento.geoforecast.data.source.remote.api.ForecastAPIService;
import com.lbbento.geoforecast.data.source.remote.api.ServiceGenerator;
import com.lbbento.geoforecast.geoforecast.GeoForecastApplication;
import com.lbbento.geoforecast.geoforecast.di.PerFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by lbbento on 25/07/2016.
 */

@Module
public class AppModule {
    private GeoForecastApplication geoForecastApplication;


    public AppModule(GeoForecastApplication geoForecastApplication) {
        this.geoForecastApplication = geoForecastApplication;
    }

    @Provides @Singleton SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(geoForecastApplication);
    }

    @Provides @Singleton GeoForecastApplication providesApplication() {
        return geoForecastApplication;
    }

    @Provides @Singleton Context providesContext() {
        return geoForecastApplication.getApplicationContext();
    }




    //Repos - Forecast - I know I could've done it directly in the classes - but this make the code more readable and easy to understand. - JUST DID THAT FOR LEARNING PURPOSES
    @Provides @Singleton ServiceGenerator provideServiceGenerator(Retrofit retrofit) {
        return new ServiceGenerator(retrofit);
    }

    @Provides @Singleton ForecastAPIService provideForecastApiService(ServiceGenerator serviceGenerator) {
        return serviceGenerator.createService(ForecastAPIService.class);
    }

    @Provides @Singleton @Named("remoteDataSource") ForecastDataSource provideForecastRemoteDataSource(ForecastAPIService forecastAPIService) {
        return new ForecastRemoteDataSource(forecastAPIService);
    }

    @Provides @Singleton @Named("localDataSource") ForecastDataSource provideForecastLocalDataSource() {
        return new ForecastLocalDataSource();
    }

    @Provides @Singleton ForecastRepository provideForecastRepository(@Named("remoteDataSource") ForecastDataSource forecastRemoreDataSource,
                                                                      @Named("localDataSource") ForecastDataSource forecastLocalDataSource) {
        return new ForecastRepository(forecastRemoreDataSource, forecastLocalDataSource);
    }


}
