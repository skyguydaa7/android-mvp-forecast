package com.lbbento.geoforecast.data.repository;

import android.support.annotation.NonNull;

import com.lbbento.geoforecast.data.datasource.ForecastDataSource;
import com.lbbento.geoforecast.data.model.ForecastModel;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by lbbento on 30/06/2016.
 */
public class ForecastRepository implements ForecastDataSource {

    private final ForecastDataSource mForecastRemoteDataSource;

    private final ForecastDataSource mForecastLocalDataSource;

    public ForecastRepository(@NonNull ForecastDataSource forecastRemoteDataSource,
                            @NonNull ForecastDataSource forecastLocalDataSource) {
        mForecastRemoteDataSource = forecastRemoteDataSource;
        mForecastLocalDataSource = forecastLocalDataSource;
    }



    /**
     * Gets forecast from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     */
    @Override
    public Observable<ForecastModel> getForecast(@NonNull final String latitude, @NonNull final String longitude) {

        final Observable<ForecastModel> obs = Observable.concat(
            mForecastLocalDataSource.getForecast(latitude,longitude),
            mForecastRemoteDataSource.getForecast(latitude, longitude)
        )
        .first(new Func1<ForecastModel, Boolean>() {
            @Override
            public Boolean call(ForecastModel forecastModel) {
                return (forecastModel != null);
            }
        });

        return obs;
    }

    @Override
    public void saveForecast(@NonNull ForecastModel forecast) {

    }


}