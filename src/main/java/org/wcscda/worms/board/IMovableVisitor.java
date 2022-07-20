package org.wcscda.worms.board;

import java.awt.geom.Point2D;
import org.wcscda.worms.Worm;

public interface IMovableVisitor {
    void visit(AbstractMovable ab, Point2D prevPosition);

    void visit(ARBEWithGravity arbewg, Point2D prevPosition);

    void visit(Worm worm, Point2D prevPosition);
}
