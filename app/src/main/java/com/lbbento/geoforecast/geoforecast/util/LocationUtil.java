package com.lbbento.geoforecast.geoforecast.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by lbbento on 4/07/2016.
 */

public class LocationUtil {
    private static final String locationProvider = LocationManager.NETWORK_PROVIDER;

    /**
     * This one gets the last known position and then the locationManager will be watching for location updates
     * @param ctx
     */
    public static Location lasKnownLocation(Context ctx) {
        if (ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( ctx, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
                return lastKnownLocation;
            }

        }
        return null;
    }

}
