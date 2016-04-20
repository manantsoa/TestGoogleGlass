package com.tech.val.testglass2;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import android.opengl.GLES20;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.tech.val.testglass2.utils.LocationUtils;
import com.tech.val.testglass2.utils.OpenCVUtils;
import com.wikitude.architect.*;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SecondActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private final String LICENCE_KEY = "K2rZJKdKHdDPEPMB7NElZWD0BBk1mC/" +
            "vDCHVYfbiLvATX70ot4j/K1ENXZYr70fvgBx4PukvOoBJCvNhlGWUo99MgjVP" +
            "MLWreUxKKTUznFAcCJxm2wzXw2nVM+kO+L13cDEUwRpN6FaEKSlN4aAhqeEdDKEhKtClUGigG4Fet8" +
            "JTYWx0ZWRfXwWS9uRsEKaPIDEESM6pndH6w3mcfmt28st7cNmMzadnP9q9mP0aOXNNvKaF5CaOdzqZI" +
            "yJopiU+tsbFTFvj/fV6tnuy5hVgSSxeX7+8WQrHTQ5lwuU4AyNu677Uh7VRHMPsPIdTlK1JfGUEXZy7IGJ" +
            "W3uK6UadnGlR4ezlUyld0G8tNB9qSQJVdpcHrF6VjdRzXM/bEX0KkeFqhy0RaRk0OBArKdlKN3xt7ln01" +
            "wfa1WEWTzqUzQ3CU+F1+bX4OcqXA+Ppisp4fFGi9IKbAo/1ZNrusiYw7sweHPx54T7nwKCtRzjyu4BTuyqLG" +
            "EeQXw+9oaQ78qoBTDQ0KVN0KF1n+odmcISu1aBlYQiBrwb14/dss/ii4G3LHua8qg+8jUdWXp5timzEIsKxEfn" +
            "J6EgcGw5j1S/u84WAPza/+qYt2625oKxVzI+XsZomRXlZZm8QM1FpJ7qFDU/kXYisVJXovptePBCtGp+YTYU0xoqwEEkCsOkCQbZI=";


    private GestureDetector mGestureDetector;
    private LocationManager locationManager;
    ArchitectView architectView;
    //final StartupConfiguration config; MARCHE PAS SUR GOOGLE GLASS qui utilise version ancienne
    final ArchitectView.ArchitectConfig config = new ArchitectView.ArchitectConfig(LICENCE_KEY);
    private ColorBlobDetector mDetector;
    private Scalar mBlobColorRgba;
    private Scalar mBlobColorHsv;
    private Mat mSpectrum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mGestureDetector = new GestureDetector(this, new GlassDPadController());

        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        this.architectView.onCreate(config);
        Log.e("deviceSupported", "" + ArchitectView.isDeviceSupported(getApplicationContext()));

    }

    @Override
    public void onResume() {
        super.onResume();
        this.architectView.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.architectView.onPostCreate();
        try {
            Log.d("Success", "ARCHITECT");
            this.architectView.load("test/test.html");
        } catch (IOException e) {
            Log.d("ERROR", e.toString());
            e.printStackTrace();
        }

        /*Location loc = getLastLocation();
        architectView.callJavascript("createPins(" + loc.getLatitude() +
                "," + loc.getLongitude() +
                "," + loc.getAltitude());*/

    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        Location loc = LocationUtils.getLastLocation(this.getBaseContext());
        String call = "createPins(" + loc.getLatitude() +
                "," + loc.getLongitude() +
                "," + loc.getAltitude() + ")";
        Log.i("CALL", call);
        architectView.callJavascript(call);

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.architectView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.architectView.onDestroy();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mDetector = new ColorBlobDetector();
        mBlobColorRgba = new Scalar(255);
        mBlobColorHsv = new Scalar(255);
        mSpectrum = new Mat();
        Size SPECTRUM_SIZE = new Size(200, 64);


        // postite color
        mBlobColorRgba.val[0] = 207;
        mBlobColorRgba.val[1] = 110;
        mBlobColorRgba.val[2] = 135;
        mBlobColorRgba.val[3] = 255;

        mBlobColorHsv = OpenCVUtils.converScalarRgba2Hsv(mBlobColorRgba);
        mDetector.setHsvColor(mBlobColorHsv);

        Imgproc.resize(mDetector.getSpectrum(), mSpectrum, SPECTRUM_SIZE);
    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return null;
    }
}
