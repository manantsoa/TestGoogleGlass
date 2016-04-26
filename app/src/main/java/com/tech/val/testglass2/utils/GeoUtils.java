package com.tech.val.testglass2.utils;

import android.util.Log;

import com.tech.val.testglass2.models.Coordinates;

/**
 * Created by andria.razana on 26/04/2016.
 */
public class GeoUtils {

    private static final float EARTH_RADIUS = 6371;

    /**
     * Calcule des coordonnées
     * en fonction d'une origine, d'une distance et d'un angle
     * @param coord
     * @param distance
     * @param bearing l'angle de direction entre 360 et 0
     * @return
     */
    public static Coordinates computeCoord(Coordinates coord, float distance, float bearing) {
        float lat, lon;

        float delta = distance / EARTH_RADIUS;
        float aLat = (float) Math.toRadians(coord.getLat());
        float aLon = (float) Math.toRadians(coord.getLon());
        float angle = (float) Math.toRadians(bearing);

        lat = (float) Math.asin( Math.sin(aLat) * Math.cos(delta) +
                                    Math.cos(aLat) * Math.sin(delta) * Math.cos(angle));


        lon = (float) (aLon + Math.atan2( Math.sin(angle) * Math.sin(delta) * Math.cos(aLat) ,
                                                    Math.cos(delta) - Math.sin(aLat) * Math.sin(lat)));

        lat = (float) Math.toDegrees(lat);
        lon = (float) Math.toDegrees(lon);


        return new Coordinates(lat, lon);
    }
}
