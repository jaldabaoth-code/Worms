package org.wcscda.worms.board;

import java.awt.geom.Rectangle2D;

public abstract class GeomUtils {
    public static void moveRect(Rectangle2D rect, double x, double y) {
        rect.setRect(rect.getX() + x, rect.getY() + y, rect.getWidth(), rect.getHeight());
    }
}
