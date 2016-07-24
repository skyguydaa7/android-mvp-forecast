package com.lbbento.geoforecast.geoforecast.base;


/**
 * Created by lbbento on 30/06/2016.
 */


public interface BaseFragmentContract<T>  {

    void setLoadingIndicator(boolean active);

    void showLoadingError();
}