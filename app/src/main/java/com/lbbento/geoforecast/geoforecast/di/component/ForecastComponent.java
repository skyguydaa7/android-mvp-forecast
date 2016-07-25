package com.lbbento.geoforecast.geoforecast.di.component;

import com.lbbento.geoforecast.geoforecast.di.PerFragment;
import com.lbbento.geoforecast.geoforecast.di.module.ForecastModule;
import com.lbbento.geoforecast.geoforecast.forecast.ForecastFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lbbento on 22/07/2016.
 */

@Singleton
@Component( modules = {ForecastModule.class})
public interface ForecastComponent {
    void inject(ForecastFragment forecastFragment);
}