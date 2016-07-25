package com.lbbento.geoforecast.data.source.remote.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lbbento.geoforecast.data.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lbbento on 22/06/2016.
 */

public class ServiceGenerator {

    private static Gson gson = new GsonBuilder().create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson));


    public static <S> S createService(Class<S> serviceClass) {


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        OkHttpClient client;

        client = httpClient.build();

        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }


}
