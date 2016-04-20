package com.tech.val.testglass2.utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Created by andria.razana on 19/04/2016.
 */
public class OpenCVUtils {

    public static Scalar converScalarRgba2Hsv(Scalar RgbaColor) {
        Mat pointMatHsv = new Mat();
        Mat pointMatRgba = new Mat(1, 1, CvType.CV_8UC3, RgbaColor);
        Imgproc.cvtColor(pointMatRgba, pointMatHsv, Imgproc.COLOR_RGB2HSV_FULL,
                4);

        return new Scalar(pointMatHsv.get(0, 0));
    }
}
