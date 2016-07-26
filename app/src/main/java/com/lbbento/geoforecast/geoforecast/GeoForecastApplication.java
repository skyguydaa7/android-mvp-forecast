package com.lbbento.geoforecast.geoforecast;

import com.lbbento.geoforecast.geoforecast.di.component.AppComponent;
import com.lbbento.geoforecast.geoforecast.di.component.DaggerAppComponent;
import com.lbbento.geoforecast.geoforecast.di.module.AppModule;
import com.lbbento.geoforecast.geoforecast.di.module.NetModule;

/**
 * Created by lbbento on 6/07/2016.
 */

public class GeoForecastApplication extends android.app.Application {
    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .netModule(new NetModule())
                .appModule(new AppModule(this))
                .build();


    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }


}
