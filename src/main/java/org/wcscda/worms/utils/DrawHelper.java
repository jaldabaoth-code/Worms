package org.wcscda.worms.utils;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class DrawHelper {
    public static Ellipse2D.Double getCircle(double centerX, double centerY, int radius) {
        return new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    /* Take values between 0 and 1 as input */
    public static Color getColorRGB(double red, double green, double blue) {
        return getColorRGB(red, green, blue);
    }

    public static Color getColorRGB(int red, int green, int blue) {
        return new Color(red, green, blue);
    }
}
