package org.wcscda.worms.gamemechanism.phases;

public class MovingPhase extends AbstractPhase {
    @Override
    protected int getMaxDurationSeconds() {
        return 15;
    }

    @Override
    public void forwardKeyPressed(String key) { }
}
