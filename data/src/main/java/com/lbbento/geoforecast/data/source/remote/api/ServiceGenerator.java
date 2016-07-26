package com.lbbento.geoforecast.data.source.remote.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lbbento.geoforecast.data.BuildConfig;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lbbento on 22/06/2016.
 */

public class ServiceGenerator {

    Retrofit mRetrofit;

    public ServiceGenerator(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    public <S> S createService(Class<S> serviceClass) {
        return mRetrofit.create(serviceClass);
    }


}
