package com.lbbento.geoforecast.geoforecast.forecast;

/**
 * Created by lbbento on 30/06/2016.
 */

import android.location.Location;

import com.lbbento.geoforecast.data.model.ForecastModel;
import com.lbbento.geoforecast.geoforecast.base.BaseFragmentContract;
import com.lbbento.geoforecast.geoforecast.base.BasePresenterContract;

/**
 * This specifies the methods that have to be implemented between view and presenter
 */
public interface ForecastFragmentContract {

    interface View extends BaseFragmentContract<Presenter> {

        void showForecast(ForecastModel forecast);

        void showNoGps();

        void showNotFound();
    }

    interface Presenter<T> extends BasePresenterContract<T> {

        void loadForecast(boolean forceUpdate, Location location);

        void refresh();

    }
}