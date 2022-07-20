package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEWithGravityWithHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class HolyGrenadeAmmo extends AbstractAmmo {
    private static final int GRENADE_RECT_SIZE = 30;
    private static final int EXPLOSION_RADIUS = 200;
    private static final int EXPLOSION_DAMAGE = 60;
    private static final  String imagePath = "src/resources/weapons/Holygrenadeicon.png";
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(60, 60, 0);
    }

    public HolyGrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(10);
        setInitialPosition();
    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        g.drawImage(image, (int) (getMovable().getCenterX() - 20), (int) (getMovable().getCenterY() -20), io);
    }

    /* Override this method if you want to have another movement behaviour */
    @Override
    protected void createMovableRect(int rectWidth, int rectHeight) {
        setMovable(new ARBEWithGravityWithHandler(Helper.getWormX() - rectWidth / 2, Helper.getWormY() - rectHeight / 2,
            rectWidth, rectHeight, this));
        for (int i = 0; i < Helper.getActiveWorm().getWarmsInventory().size(); i++) {
            if (Helper.getActiveWorm().getWarmsInventory().get(i).getWeapon() instanceof HolyGrenade) {
                Integer newAmmoNumber = Helper.getActiveWorm().getWarmsInventory().get(i).getAmmoNumber();
                Helper.getActiveWorm().getWarmsInventory().get(i).setAmmoNumber(newAmmoNumber-1);
            }
        }
    }
}
