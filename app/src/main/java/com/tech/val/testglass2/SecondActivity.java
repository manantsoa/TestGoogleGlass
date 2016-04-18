package com.tech.val.testglass2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.opengl.GLES20;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.wikitude.architect.*;

import java.io.IOException;

public class SecondActivity extends Activity {

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
    ArchitectView architectView;
    //final StartupConfiguration config; MARCHE PAS SUR GOOGLE GLASS??
    final ArchitectView.ArchitectConfig config = new ArchitectView.ArchitectConfig(LICENCE_KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mGestureDetector = new GestureDetector(this, new GlassDPadController());

        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        this.architectView.onCreate(config);
        Log.e("deviceSupported", ""+ArchitectView.isDeviceSupported(getApplicationContext()));

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

    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        architectView.callJavascript("test()");
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
}
