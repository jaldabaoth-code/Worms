package org.wcscda.worms.board;

import java.awt.*;
import java.awt.image.ImageObserver;
import org.wcscda.worms.Helper;
import org.wcscda.worms.utils.DrawHelper;

public class Explosion extends AbstractDrawableElement {
    private static final int LIFE_DURATION = 40;
    private final double centerX;
    private final double centerY;
    private final double radius;
    private final int createdPhase;

    public Explosion(double centerX, double centerY, int explosionRadius) {
        super(true);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = explosionRadius;
        this.createdPhase = Helper.getClock();
    }

    private int getLifeTime() {
        return Helper.getClock() - createdPhase;
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        Shape explosion = DrawHelper.getCircle(centerX, centerY, (int) (radius * (0.1 + getLifeTime() * 0.9 / LIFE_DURATION)));
        g.setColor(DrawHelper.getColorRGB(255, 255 - 255 * getLifeTime() / LIFE_DURATION, 0));
        g.fill(explosion);
        if (getLifeTime() == LIFE_DURATION) {
            removeSelf();
        }
    }
}
