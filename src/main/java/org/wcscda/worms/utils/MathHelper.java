package org.wcscda.worms.utils;

public abstract class MathHelper {
    public static double distance(double diffX, double diffY) {
        return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }
}
