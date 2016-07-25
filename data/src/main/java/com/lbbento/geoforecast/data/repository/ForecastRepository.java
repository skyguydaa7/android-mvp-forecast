package com.lbbento.geoforecast.data.repository;

import android.support.annotation.NonNull;

import com.lbbento.geoforecast.data.datasource.ForecastDataSource;
import com.lbbento.geoforecast.data.entity.ForecastModel;
import com.lbbento.geoforecast.data.source.local.ForecastLocalDataSource;
import com.lbbento.geoforecast.data.source.remote.ForecastRemoteDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by lbbento on 30/06/2016.
 */
@Singleton
public class ForecastRepository implements ForecastDataSource {

    private final ForecastDataSource mForecastRemoteDataSource;

    private final ForecastDataSource mForecastLocalDataSource;

    @Inject
    public ForecastRepository(@NonNull ForecastRemoteDataSource forecastRemoteDataSource,
                            @NonNull ForecastLocalDataSource forecastLocalDataSource) {
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