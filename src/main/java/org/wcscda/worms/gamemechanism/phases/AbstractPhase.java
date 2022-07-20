package org.wcscda.worms.gamemechanism.phases;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.time.Duration;
import java.time.Instant;
import org.wcscda.worms.Player;
import org.wcscda.worms.board.AbstractDrawableElement;

public abstract class AbstractPhase extends AbstractDrawableElement {
    private Instant phaseStart;
    private Player activePlayer;

    protected abstract int getMaxDurationSeconds();

    protected AbstractPhase() {
        super(true);
        phaseStart = Instant.now();
    }

    public abstract void forwardKeyPressed(String key);

    public boolean isFinished() {
        Duration timeElapsed = Duration.between(Instant.now(), phaseStart);
        return timeElapsed.toSeconds() > getMaxDurationSeconds();
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        // By default do nothing, let's the other do something if needed
    }
}
