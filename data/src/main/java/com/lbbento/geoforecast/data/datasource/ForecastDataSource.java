package com.lbbento.geoforecast.data.datasource;

import android.support.annotation.NonNull;

import com.lbbento.geoforecast.data.model.ForecastModel;

import rx.Observable;

/**
 * Created by lbbento on 25/07/2016.
 */

public interface ForecastDataSource {

    Observable<ForecastModel> getForecast(@NonNull String latitude, @NonNull String longitude);

    void saveForecast(@NonNull ForecastModel forecast);

}