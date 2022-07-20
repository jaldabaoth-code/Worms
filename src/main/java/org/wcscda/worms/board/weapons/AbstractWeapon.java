package org.wcscda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.lang.reflect.Constructor;
import org.wcscda.worms.Helper;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.MovingPhase;

public abstract class AbstractWeapon {
    private double angle;

    public AbstractWeapon() {
        this.angle = Helper.getActiveWorm().getDirection();
    }

    public void draw(Graphics2D g, ImageObserver io) { }

    public double getAngle() {
        return this.angle;
    }

    public void fire() {
        Helper.getTC().setCurrentPhase(getNextPhase());
        createNewAmmo(getAngle());
    }

    /* NRO 2021-09-30 : by default, the weapons are looking for Ammo class of the same name as the class + Ammo
     * (for ex Shotgun => ShotgunAmmo, Hadoken => HadokenAmmo) You can override the default behaviour by overriding this method.
     */
    protected void createNewAmmo(double angle) {
        String ammoClassName = this.getClass().getName() + "Ammo";
        Class<?> ammoClass = null;
        try {
            ammoClass = Class.forName(ammoClassName);
        } catch (ClassNotFoundException e) {
            System.err.println("Error, Ammo not found for class" + this.getClass().getName() + "it should be named" + ammoClassName);
            System.exit(1);
        }
        Constructor<?> ctor;
        try {
            ctor = ammoClass.getConstructor(Double.class);
            ctor.newInstance(new Object[] {angle});
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void incrementAngle(double incrAngle) {
        this.angle += incrAngle;
    }

    public AbstractPhase getNextPhase() {
        return new MovingPhase();
    }

    public boolean isChangingWeaponDisabled() {
        return false;
    }

    public void triggerAmmoExplosion() {
        Helper.getTC().setNextWorm();
    }

    public String getName() {
        return null;
    }
}
