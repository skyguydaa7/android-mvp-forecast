package com.lbbento.geoforecast.geoforecast.di.component;

import com.lbbento.geoforecast.geoforecast.di.PerFragment;
import com.lbbento.geoforecast.geoforecast.di.module.ForecastModule;
import com.lbbento.geoforecast.geoforecast.di.module.NetModule;
import com.lbbento.geoforecast.geoforecast.forecast.ForecastFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lbbento on 22/07/2016.
 */

@PerFragment
@Component( dependencies = {AppComponent.class}, modules = {ForecastModule.class})
public interface ForecastComponent {
    void inject(ForecastFragment forecastFragment);
}