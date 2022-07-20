package org.wcscda.worms.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import org.wcscda.worms.utils.MathHelper;

/* NRO 2021-09-27
 *   This class handle movable objects represented by a rectangle
 *   (which might mean all of them because of the Shape.intersects needing a rectangle)
 */
public abstract class  AbstractRectangularBoardElement extends AbstractMovable {
    private Rectangle2D innerRect;

    public AbstractRectangularBoardElement(double x, double y, double rectWidth, double rectHeight) {
        innerRect = new Rectangle2D.Double(x, y, rectWidth, rectHeight);
    }

    public int getX() {
        return (int) getInnerRect().getX();
    }

    public int getY() {
        return (int) getInnerRect().getY();
    }

    public double getCenterX() {
        return getInnerRect().getCenterX();
    }

    public double getCenterY() {
        return getInnerRect().getCenterY();
    }

    public Rectangle2D getInnerRect() {
        return innerRect;
    }

    public Shape getShape() {
        return innerRect;
    }

    @Override
    public boolean isCollidingWith(Shape s) {
        return s.intersects(getInnerRect());
    }

    @Override
    public void rawMove(double x, double y) {
        GeomUtils.moveRect(innerRect, x, y);
    }

    @Override
    protected void drawDebug(Graphics2D g, ImageObserver io) {
        g.setColor(Color.red);
        g.draw(getInnerRect());
    }

    public double distanceFromPoint(double x, double y) {
        return MathHelper.distance(getCenterX() - x, getCenterY() - y);
    }
}
