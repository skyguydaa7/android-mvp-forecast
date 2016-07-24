package com.lbbento.geoforecast.geoforecast.data.source;

import android.support.annotation.NonNull;

import com.lbbento.geoforecast.geoforecast.data.ForecastModel;

import rx.Observable;

/**
 * Created by lbbento on 25/07/2016.
 */

public interface ForecastDataSource {

    Observable<ForecastModel> getForecast(@NonNull String latitude, @NonNull String longitude);

    void saveForecast(@NonNull ForecastModel forecast);

}