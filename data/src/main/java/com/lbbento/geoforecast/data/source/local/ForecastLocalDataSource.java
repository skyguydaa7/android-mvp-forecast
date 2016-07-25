package com.lbbento.geoforecast.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lbbento.geoforecast.data.datasource.ForecastDataSource;
import com.lbbento.geoforecast.data.entity.ForecastModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


/**
 * Created by lbbento on 25/07/2016.
 */
@Singleton
public class ForecastLocalDataSource implements ForecastDataSource {

    // Prevent direct instantiation.
    @Inject
    public ForecastLocalDataSource(@NonNull Context context) {

    }

    @Override
    public Observable<ForecastModel> getForecast(@NonNull String latitude, @NonNull String longitude) {
        //TODO - GET FROM LOCAL DATABASE

        return Observable.just(null);
    }

    @Override
    public void saveForecast(@NonNull ForecastModel forecast) {

    }
}
