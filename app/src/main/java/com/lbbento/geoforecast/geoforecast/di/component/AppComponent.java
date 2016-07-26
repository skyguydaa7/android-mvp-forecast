package com.lbbento.geoforecast.geoforecast.di.component;

import android.content.Context;

import com.lbbento.geoforecast.data.repository.ForecastRepository;
import com.lbbento.geoforecast.geoforecast.base.BaseActivity;
import com.lbbento.geoforecast.geoforecast.di.module.AppModule;
import com.lbbento.geoforecast.geoforecast.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lbbento on 25/07/2016.
 */

@Component(
        modules = {AppModule.class,NetModule.class}
)
@Singleton // Constraints this component to one-per-application or unscoped bindings.
public interface AppComponent {
    void inject(BaseActivity activity);

    //Exposed to sub-graphs. - with this, ForecastComponent can inject the Context from the dependency(this)
    Context context();

    //Repos - to sub-graphs
    ForecastRepository forecastRepository();

}