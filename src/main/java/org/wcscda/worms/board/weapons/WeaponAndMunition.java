package org.wcscda.worms.board.weapons;

public class WeaponAndMunition {
    private AbstractWeapon weapon;
    private Integer ammoNumber;

    public WeaponAndMunition(AbstractWeapon weapon, Integer ammoNumber) {
        this.weapon = weapon;
        this.ammoNumber =  ammoNumber;
    }

    public AbstractWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    public Integer getAmmoNumber() {
        return ammoNumber;
    }

    public void setAmmoNumber(Integer ammoNumber) {
        this.ammoNumber = ammoNumber;
    }
}
