package org.wcscda.worms.board;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

public class ARBEWIthHandler extends AbstractRectangularBoardElement {
    private final IMovableHandler movableHandler;

    public ARBEWIthHandler(double x, double y, double rectWidth, double rectHeight, IMovableHandler movableHandler) {
        super(x, y, rectWidth, rectHeight);
        this.movableHandler = movableHandler;
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        movableHandler.drawMain(g, io);
    }

    @Override
    public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
        movableHandler.colideWith(movable, prevPosition);
    }
}
