package com.lbbento.geoforecast.geoforecast.di.component;

import com.lbbento.geoforecast.geoforecast.base.BaseActivity;
import com.lbbento.geoforecast.geoforecast.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lbbento on 25/07/2016.
 */
@Component(
        modules = AppModule.class
)
@Singleton // Constraints this component to one-per-application or unscoped bindings.
public interface AppComponent {
    void inject(BaseActivity activity);

}