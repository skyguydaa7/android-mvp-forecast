package com.lbbento.geoforecast.geoforecast.api;

import com.lbbento.geoforecast.geoforecast.data.ForecastModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lbbento on 30/06/2016.
 */

public interface ForecastAPIService {

    @GET("forecast/{token}/{geo}")
    Observable<ForecastModel> getForecast(@Path("token") String token, @Path("geo") String geo);

}
