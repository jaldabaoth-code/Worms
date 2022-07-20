package org.wcscda.worms.board;

import java.awt.Shape;
import java.util.stream.Stream;

public abstract class AbstractBoardElement extends AbstractDrawableElement {
    public static Stream<AbstractBoardElement> getAllBoardElement() {
        return AbstractDrawableElement.getAllDrawable().stream()
            .filter(ade -> ade instanceof AbstractBoardElement)
            .map(AbstractBoardElement.class::cast);
    }

    public abstract Shape getShape();
}
