package com.lbbento.geoforecast.geoforecast.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lbbento.geoforecast.geoforecast.data.ForecastModel;
import com.lbbento.geoforecast.geoforecast.data.source.ForecastDataSource;
import com.lbbento.geoforecast.geoforecast.di.PerFragment;

import javax.inject.Inject;

import rx.Observable;


/**
 * Created by lbbento on 25/07/2016.
 */
@PerFragment
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
