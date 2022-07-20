package org.wcscda.worms.gamemechanism.phases;

import java.awt.*;
import java.awt.image.ImageObserver;

public class EndOfGamePhase extends AbstractPhase {
    @Override
    protected int getMaxDurationSeconds() {
        return 0;
    }

    @Override
    public void forwardKeyPressed(String key) { }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        // By default do nothing, let's the other do something if needed
    }
}
