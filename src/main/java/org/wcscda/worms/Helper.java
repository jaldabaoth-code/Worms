package org.wcscda.worms;

import org.wcscda.worms.board.WormField;
import org.wcscda.worms.board.weapons.*;
import org.wcscda.worms.gamemechanism.*;

public abstract class Helper {
    public static Player getActivePlayer() {
        return getTC().getActivePlayer();
    }

    public static Worm getActiveWorm() {
        return getActivePlayer().getActiveWorm();
    }

    public static double getWormX() {
        return getActiveWorm().getCenterX();
    }

    public static double getWormY() {
        return getActiveWorm().getCenterY();
    }

    public static TimeController getTC() {
        return TimeController.getInstance();
    }

    public static PhysicalController getPC() {
        return PhysicalController.getInstance();
    }

    public static WormField getField() {
        return getPC().getWormField();
    }

    public static int getClock() {
        return getTC().getPhaseCount();
    }

    public static AbstractWeapon getCurrentWeapon() {
        return getActivePlayer().getCurrentWeapon();
    }
}
