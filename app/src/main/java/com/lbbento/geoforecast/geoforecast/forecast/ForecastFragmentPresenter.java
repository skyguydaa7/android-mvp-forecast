package com.lbbento.geoforecast.geoforecast.forecast;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lbbento.geoforecast.data.model.ForecastModel;
import com.lbbento.geoforecast.data.repository.ForecastRepository;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by lbbento on 25/07/2016.
 */

public class ForecastFragmentPresenter implements ForecastFragmentContract.Presenter<ForecastFragment> {

    private final ForecastRepository mForecastRepository;
    private ForecastFragmentContract.View view;

    private Location mLocation;

    @Nullable
    private Subscription mSubscription = Subscriptions.empty();

    public ForecastFragmentPresenter(@NonNull ForecastRepository forecastRepository) {
        mForecastRepository = forecastRepository;
    }


    @Override
    public void setView(ForecastFragment view) {
        this.view = view;
        if (view == null) {
            mSubscription.unsubscribe();
        }

    }


    @Override
    public void refresh() {
        if (mLocation != null)
            loadForecast(true, true, String.valueOf(mLocation.getLatitude()), String.valueOf(mLocation.getLongitude()));
    }

    @Override
    public void loadForecast(boolean forceUpdate, Location location) {
        if (location != null) {
            loadForecast(forceUpdate, true, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            mLocation = location;
        }
    }


    private void loadForecast(boolean forceUpdate, final boolean showLoadingUI, @NonNull String latitude, @NonNull String longitude) {
        if (showLoadingUI) {
            view.setLoadingIndicator(true);
        }



        mSubscription = mForecastRepository
                .getForecast(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForecastModel>() {
                    @Override
                    public void onCompleted() {
                        view.setLoadingIndicator(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showLoadingError();
                    }

                    @Override
                    public void onNext(ForecastModel forecast) {
                        if (forecast != null)
                            view.showForecast(forecast);
                    }
                });
    }



}