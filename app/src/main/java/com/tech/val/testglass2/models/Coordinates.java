package com.tech.val.testglass2.models;

/**
 * Created by andria.razana on 26/04/2016.
 */
public class Coordinates {
    private float lat, lon;

    public Coordinates(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
}
