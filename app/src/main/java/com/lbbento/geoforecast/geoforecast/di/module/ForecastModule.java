package com.lbbento.geoforecast.geoforecast.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lbbento on 22/07/2016.
 */

@Module
public class ForecastModule {
    private Context ctx;

    public ForecastModule(Context ctx) {
        this.ctx = ctx;
    }

    @Provides Context provideContext() {
        return ctx;
    }
}
