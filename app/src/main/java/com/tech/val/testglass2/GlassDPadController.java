package com.tech.val.testglass2;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by andria.razana on 13/04/2016.
 */
public class GlassDPadController extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d("Event", "On Single Tap");
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("Event", "On Single Tap 2");
        return super.onSingleTapUp(e);    //To change body of overridden methods use File | Settings | File Templates.
    }

}
