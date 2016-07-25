package com.lbbento.geoforecast.data.entity;

/**
 * Created by lbbento on 25/07/2016.
 */

public class ForecastModel {
    //TODO - complete dataset
    private String timezone;
    private String latitude;
    private String longitude;




    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
