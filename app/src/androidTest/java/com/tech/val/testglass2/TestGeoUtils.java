package com.tech.val.testglass2;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.tech.val.testglass2.models.Coordinates;
import com.tech.val.testglass2.utils.GeoUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by andria.razana on 26/04/2016.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TestGeoUtils {


    @Test
    public final void testComputeCoord() {
        Coordinates origin = new Coordinates(53.320556f, -1.729722f);
        float distance = 54.8f;
        float bearing = 180;

        Coordinates result = GeoUtils.computeCoord(origin, distance, bearing);

        //assertSame(result.getLat(), 52.827778);
        assertSame(result.getLon(), -1.729722);

    }
}
