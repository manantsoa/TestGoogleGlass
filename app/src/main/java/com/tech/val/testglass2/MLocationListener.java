package com.tech.val.testglass2;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.wikitude.architect.ArchitectView;

/**
 * Created by andria.razana on 20/04/2016.
 */
public class MLocationListener implements LocationListener {

    ArchitectView arView;

    public MLocationListener(ArchitectView architectView) {
        arView = architectView;
    }
    @Override
    public void onLocationChanged(Location location) {
        arView.setLocation(location.getLatitude(),location.getLongitude(), (float) location.getAltitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
