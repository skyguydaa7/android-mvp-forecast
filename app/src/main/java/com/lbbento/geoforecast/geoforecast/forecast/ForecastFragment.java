package com.lbbento.geoforecast.geoforecast.forecast;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lbbento.geoforecast.data.entity.ForecastModel;
import com.lbbento.geoforecast.geoforecast.R;
import com.lbbento.geoforecast.geoforecast.base.BaseFragment;
import com.lbbento.geoforecast.geoforecast.main.MainActivity;
import com.lbbento.geoforecast.geoforecast.util.LocationUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lbbento on 25/07/2016.
 */

public class ForecastFragment extends BaseFragment implements ForecastFragmentContract.View {

    @BindView(R.id.txtTimezone) TextView txtTimezone;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefreshLayout;


    @Inject
    protected ForecastFragmentPresenter mPresenter;
    LocationManager locationManager;


    public ForecastFragment() { }

    public static ForecastFragment newInstance() {
        ForecastFragment f = new ForecastFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_forecast, container, false);

        //ButterKnife
        ButterKnife.bind(this, root);

        //swipe refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });


        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Injection
        ((MainActivity)this.getActivity()).getForecastComponent().inject(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.setView(null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);

        mPresenter.loadForecast(false, LocationUtil.lasKnownLocation(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();

        //Listen for location changes
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000000, 5000, locationListener);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }




    @Override
    public void showForecast(ForecastModel forecast) {
        txtTimezone.setText(forecast.getTimezone());
//        viewWidgets.txtLatLong.setText(String.format("%s,%s", forecast.getLatitude() , forecast.getLongitude()));
//        viewWidgets.txtSummary.setText(String.format("%s", forecast.getCurrently().getSummary()));
//        viewWidgets.txtTemperatureApparent.setText(String.format("%s %s", forecast.getCurrently().getApparentTemperature().toString(),
//                getResources().getString(R.string.format_temperature)));
//        viewWidgets.txtTemperatureReal.setText(String.format("%s %s", forecast.getCurrently().getTemperature().toString(),
//                getResources().getString(R.string.format_temperature)));
//        viewWidgets.txtPrecip.setText(String.format("%s %s - %s %s", forecast.getCurrently().getPrecipIntensity().toString(),
//                getResources().getString(R.string.format_precip_inten),
//                forecast.getCurrently().getPrecipProbability().toString(),
//                getResources().getString(R.string.format_precip_chance)));
//        viewWidgets.txtWind.setText(String.format("%s %s %s", getResources().getString(R.string.format_wind),
//                forecast.getCurrently().getWindSpeed(),
//                getResources().getString(R.string.format_speed)));
//        viewWidgets.txtVisibility.setText(String.format("%s: %s ", getResources().getString(R.string.format_visibility),
//                forecast.getCurrently().getVisibility().toString() ));
//        viewWidgets.txtHumidity.setText(String.format("%s%s", forecast.getCurrently().getHumidity().toString(),
//                getResources().getString(R.string.format_percentage) ));
//        viewWidgets.txtMore.setText(String.format("%s:%s\n%s:%s\n%s:%s", getResources().getString(R.string.format_dew_point),
//                forecast.getCurrently().getDewPoint().toString(),
//                getResources().getString(R.string.format_pressure),
//                forecast.getCurrently().getPressure().toString(),
//                getResources().getString(R.string.format_cloud_cover),
//                forecast.getCurrently().getCloudCover().toString()));
//        String icon = Forecast.icons.get(forecast.getCurrently().getIcon()).toString();
//        Picasso.with(getContext()).load(icon).placeholder(R.drawable.placeholder).into(viewWidgets.icon);

        setLoadingIndicator(false);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        swipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showLoadingError() {
        //TODO Show error when rying to retrieve data
        setLoadingIndicator(false);
    }

    @Override
    public void showNoGps() {
        //TODO Show no permisson Error
        setLoadingIndicator(false);

    }

    @Override
    public void showNotFound() {
        //TODO Show not found
        setLoadingIndicator(false);

    }



    //Location
    // Define a listener that responds to location updates
    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            if (location != null) {
                mPresenter.loadForecast(false, location);
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };


}
