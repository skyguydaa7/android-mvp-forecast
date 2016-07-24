package com.lbbento.geoforecast.geoforecast.data.source.remote;

import android.support.annotation.NonNull;

import com.lbbento.geoforecast.geoforecast.BuildConfig;
import com.lbbento.geoforecast.geoforecast.api.ForecastAPIService;
import com.lbbento.geoforecast.geoforecast.api.ServiceGenerator;
import com.lbbento.geoforecast.geoforecast.data.ForecastModel;
import com.lbbento.geoforecast.geoforecast.data.source.ForecastDataSource;
import com.lbbento.geoforecast.geoforecast.di.PerFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lbbento on 30/06/2016.
 */
@PerFragment
public class ForecastRemoteDataSource implements ForecastDataSource {


    // Prevent direct instantiation.
    @Inject
    public ForecastRemoteDataSource() {}

    @Override
    public Observable<ForecastModel> getForecast(@NonNull String latitude, @NonNull String longitude) {


        //Creates service
        final ForecastAPIService mForecastAPIService =
                ServiceGenerator.createService(ForecastAPIService.class);


        return mForecastAPIService
                .getForecast(BuildConfig.API_KEY, latitude+","+longitude)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
                    //TODO - save to DB
                    //.doOnNext();

    }

    @Override
    public void saveForecast(@NonNull ForecastModel forecast) {
        // Not required
    }



}