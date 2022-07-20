package org.wcscda.worms.board;

import java.awt.geom.Point2D;

public interface IVisitable {
    void accept(Point2D prevPosition, IMovableVisitor imv);
}
