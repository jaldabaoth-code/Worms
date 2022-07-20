package org.wcscda.worms.gamemechanism.events;

public class EndOfTurnEvent extends AbstractWormEvent {
    private final int phase;

    public static void addListener() { }

    public EndOfTurnEvent(int phase) {
        this.phase = phase;
    }
}
