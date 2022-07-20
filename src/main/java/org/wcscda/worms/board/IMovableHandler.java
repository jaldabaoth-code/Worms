package org.wcscda.worms.board;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

public interface IMovableHandler {
    void drawMain(Graphics2D g, ImageObserver io);

    void colideWith(AbstractBoardElement movable, Point2D prevPosition);
}
