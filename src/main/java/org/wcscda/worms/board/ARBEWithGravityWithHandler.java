package org.wcscda.worms.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

public class ARBEWithGravityWithHandler extends ARBEWithGravity {
    private final IMovableHandler movableHandler;
    private static final int STANDING_RECTANGLE_HEIGHT = 5;
    private Rectangle2D supportRect;

    public ARBEWithGravityWithHandler(double x, double y, int rectWidth, int rectHeight, IMovableHandler movableHandler) {
        super(x, y, rectWidth, rectHeight);
        this.movableHandler = movableHandler;
        supportRect = new Rectangle2D.Double(x, y + rectHeight + STANDING_RECTANGLE_HEIGHT, rectWidth, STANDING_RECTANGLE_HEIGHT);
    }

    public Rectangle2D getOuterRect() {
        return supportRect;
    }

    public boolean isStandingOn(Shape s) {
        return s.intersects(getOuterRect());
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        movableHandler.drawMain(g, io);
    }

    @Override
    public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
        movableHandler.colideWith(movable, prevPosition);
    }

    @Override
    public void rawMove(double x, double y) {
        super.rawMove(x, y);
        GeomUtils.moveRect(supportRect, x, y);
    }

    @Override
    public void accept(Point2D prevPosition, IMovableVisitor visitor) {
        visitor.visit(this, prevPosition);
    }

    @Override
    protected void drawDebug(Graphics2D g, ImageObserver io) {
        super.drawDebug(g, io);
        g.setColor(Color.ORANGE);
        g.draw(getOuterRect());
    }

    public boolean isSubjectToGravity() {
        return true;
    }
}
