package org.wcscda.worms.board.weapons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import org.wcscda.worms.Helper;

public class Hadoken extends AbstractWeapon {
    private static final int hadokenRadius = 70;
    private static String name = "Hadoken";

    public String getName() {
        return name;
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        Ellipse2D circle = new Ellipse2D.Double(Helper.getWormX() - hadokenRadius, Helper.getWormY() - hadokenRadius, 2 * hadokenRadius, 2 * hadokenRadius);
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(10));
        g.draw(circle);
    }
}
