package org.wcscda.worms;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.wcscda.worms.board.ARBEWithGravity;
import org.wcscda.worms.board.AbstractBoardElement;
import org.wcscda.worms.board.IMovableVisitor;
import org.wcscda.worms.board.IVisitable;
import org.wcscda.worms.board.weapons.*;
import org.wcscda.worms.gamemechanism.Board;

public class Worm extends ARBEWithGravity implements IVisitable {
    private static final String leftFacingResource = "src/resources/WormLF.png";
    private static final String rightFacingResource = "src/resources/WormRF.png";
    private static final int imageHeight = 60;
    private static final int imageWidth = 54;
    private static final int rectPadding = 15;
    private static Image wormLF = null;
    private static Image wormRF = null;
    private int shownLife = 100;
    private int life = 100;
    private final String name;
    private final Player player;
    private boolean isUserMoving;
    private boolean inventoryView = false;
    private ArrayList<WeaponAndMunition> warmsInventory = new ArrayList<>();

    private static void initImages() {
        wormLF = new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
        wormRF = new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
    }

    /* NRO 2021-09-28 : Player is the Worm factory */
    protected Worm(Player player, String name) {
        this(player, name, getRandomStartingX(), getRandomStartingY());
    }

    /* Idem */
    protected Worm(Player player, String name, int x, int y) {
        super(x, y, imageWidth - 2 * rectPadding, imageHeight - 2 * rectPadding);
        this.player = player;
        this.name = name;
    }

    private static int getRandomStartingX() {
        return RandomGenerator.getInstance().nextInt(Board.getBWIDTH() - imageWidth);
    }

    private static int getRandomStartingY() {
        return RandomGenerator.getInstance().nextInt(Board.getBHEIGHT() - imageHeight);
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        int x = 500;
        int y = 100;
        if (wormLF == null) initImages();
        Image worm = isRightFacing() ? wormRF : wormLF;
        g.drawImage(worm, getX() - rectPadding, getY() - rectPadding, io);
        Font font = new Font("helvetica", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(player.getColor());
        g.drawString("" + getShownLife(), (int) getX(), (int) getY() - 17);
        g.drawString("" + this.getName(), (int) getX() + 5, (int) getY() - 37);
        if (inventoryView) {
            for (WeaponAndMunition weapon : getWarmsInventory()) {
                g.drawString("" + weapon.getWeapon().getName() + ": " + (weapon.getAmmoNumber() == null ?  "∞" : "" + weapon.getAmmoNumber()), x, y);
                y += 15;
            }
        }
    }

    private int getShownLife() {
        if (life < shownLife) {
            shownLife--;
        } else if (life > shownLife) {
            shownLife++;
        }
        return this.shownLife;
    }

    private boolean isRightFacing() {
        return Math.abs(getDirection()) < 1e-6;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isUserMoving() {
        return isUserMoving;
    }

    public void setUserMoving(boolean isUserMoving) {
        this.isUserMoving = isUserMoving;
    }

    @Override
    public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
        setPosition(prevPosition);
    }

    @Override
    public String toString() {
        return "Worm " + this.getName() + " / player : " + this.getPlayer();
    }

    public String getName() {
        return name;
    }

    @Override
    public void takeDamage(int damage) {
        Helper.getActivePlayer();
        if (Helper.getActivePlayer().isDebutant()) {
            life -= damage * 1.25;
        } else {
            life -= damage;
        }
        if (life <= 0) {
            Player.isPlayerDie();
            die();
        }
    }

    public void die() {
        System.out.println(Helper.getTC().getCurrentNbPlayer());
        removeSelf();
    }

    public int getLife() {
        return life;
    }

    @Override
    protected void onRemoval() {
        player.getWorms().remove(this);
    }

    @Override
    public void accept(Point2D prevPosition, IMovableVisitor visitor) {
        visitor.visit(this, prevPosition);
    }

    public ArrayList<WeaponAndMunition> getWarmsInventory() {
        return this.warmsInventory;
    }

    public void setWarmsInventory() {
      ArrayList<WeaponAndMunition> warmsInventory = new ArrayList<>();
      warmsInventory.add(new WeaponAndMunition(new Hadoken(), null));
      warmsInventory.add(new WeaponAndMunition(new Grenade(), 4));
      warmsInventory.add(new WeaponAndMunition(new Bomb(), 2));
      warmsInventory.add(new WeaponAndMunition(new HolyGrenade(), 1));
      warmsInventory.add(new WeaponAndMunition(new Shotgun(), 6));
      this.warmsInventory = warmsInventory;
    }

    public boolean isInventoryView() {
        return inventoryView;
    }

    public void setInventoryView(boolean inventoryView) {
        this.inventoryView = inventoryView;
    }
}
