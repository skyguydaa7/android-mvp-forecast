package com.lbbento.geoforecast.geoforecast.di.module;

import android.content.Context;

import com.lbbento.geoforecast.data.datasource.ForecastDataSource;
import com.lbbento.geoforecast.data.repository.ForecastRepository;
import com.lbbento.geoforecast.data.source.local.ForecastLocalDataSource;
import com.lbbento.geoforecast.data.source.remote.ForecastRemoteDataSource;
import com.lbbento.geoforecast.data.source.remote.api.ForecastAPIService;
import com.lbbento.geoforecast.data.source.remote.api.ServiceGenerator;
import com.lbbento.geoforecast.geoforecast.di.PerFragment;
import com.lbbento.geoforecast.geoforecast.forecast.ForecastFragmentPresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lbbento on 22/07/2016.
 */

@Module
public class ForecastModule {

    public ForecastModule() {}


    @Provides @PerFragment ForecastFragmentPresenter provideForecastFragmentPresenter(ForecastRepository forecastRepository) {
        return new ForecastFragmentPresenter(forecastRepository);
    }

}
