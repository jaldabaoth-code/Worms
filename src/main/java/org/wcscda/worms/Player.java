package org.wcscda.worms;

import java.awt.Color;
import java.util.ArrayList;
import org.wcscda.worms.board.weapons.*;

public class Player {
    private final String name;
    private final Color color;
    private final ArrayList<Worm> worms = new ArrayList<Worm>();
    private AbstractWeapon currentWeapon;
    private int currentWormIndex = 0;
    private boolean debutant = false;

    public Player(String name, Color color, Boolean debutant) {
        this.name = name;
        this.color = color;
        this.debutant = debutant;
    }

    public boolean isDebutant() {
        return this.debutant;
    }

    public String getName() {
        return name;
    }

    public Worm createWorm(String nom) {
        Worm worm = new Worm(this, nom);
        worms.add(worm);
        worm.setWarmsInventory();
        return worm;
    }

    public Color getColor() {
        return color;
    }

    public AbstractWeapon getCurrentWeapon() {
        return currentWeapon;
    }

    public ArrayList<Worm> getWorms() {
        return worms;
    }

    public Worm getActiveWorm() {
        if (getWorms().isEmpty()) {
            return null;
        }
        return getWorms().get(currentWormIndex);
    }

    public void setNextWorm() {
        if (worms.isEmpty()) return;
        currentWormIndex += 1;
        currentWormIndex %= worms.size();
    }

    /* NRO 2021-09-30 : TODO-student make a better version of this, this is just a temporary version :-)
     * This should call the inventory, and handle
     */
    public void changeWeapon() {
        ArrayList<WeaponAndMunition> wormInventory = new ArrayList<>();
        for (int i = 0; i < Helper.getActiveWorm().getWarmsInventory().size(); i++) {
            if (Helper.getActiveWorm().getWarmsInventory().get(i).getAmmoNumber() == null || Helper.getActiveWorm().getWarmsInventory().get(i).getAmmoNumber() > 0) {
                wormInventory.add(Helper.getActiveWorm().getWarmsInventory().get(i));
            }
        }
        for (int i = 0; i <= wormInventory.size(); i++) {
            if (currentWeapon.equals(wormInventory.get(i).getWeapon())) {
                if (i == wormInventory.size() - 1) {
                    i = 0;
                    currentWeapon = wormInventory.get(i).getWeapon();
                    break;
                }
                currentWeapon = wormInventory.get(i + 1).getWeapon();
                break;
            }
        }
    }

    public void initWeapon() {
        currentWeapon = Helper.getActiveWorm().getWarmsInventory().get(0).getWeapon();
    }

    public int getPlayerLife() {
        int playerLife = 0;
        for ( Worm worm : this.getWorms()) {
            playerLife += worm.getLife();
        }
        return playerLife;
    }

    public static void isPlayerDie() {
        for (Player player : Helper.getTC().getPlayers()) {
            if (player.getPlayerLife() <= 0) {
                Helper.getTC().setCurrentNbPlayer(Helper.getTC().getCurrentNbPlayer() - 1);
            }
        }
    }

    public boolean hasWorms() {
        return !getWorms().isEmpty();
    }
}
