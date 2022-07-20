package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEWIthHandler;
import org.wcscda.worms.board.AbstractRectangularBoardElement;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class ShotgunAmmo extends AbstractAmmo {
    private static final int EXPLOSION_RADIUS = 30;
    private static final int EXPLOSION_DAMAGE = 15;
    private static final int SHOTGUN_RECT_SIZE = 10;
    private static final String imagePath = "src/resources/weapons/Shotgun_small.png";
    private static Image image = null;
    private AbstractRectangularBoardElement movable;

    public ShotgunAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(SHOTGUN_RECT_SIZE, SHOTGUN_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(5);
        setInitialPosition();
    }

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        // TODO Auto-generated method stub
        if (image == null) {
            initImages();
        }
        if (Helper.getActiveWorm().getDirection() == Math.PI) {
            AffineTransform trans = AffineTransform.getTranslateInstance(Helper.getWormX() - 40, Helper.getWormY() - 10);
            trans.scale(1, 1);
            g.drawImage(image, trans, io);
        } else {
            AffineTransform trans = AffineTransform.getTranslateInstance(Helper.getWormX() + 40, Helper.getWormY() - 10);
            trans.scale(-1, 1);
            g.drawImage(image, trans, io);
        }
    }

    /* Override this method if you want to have another movement behaviour */
    @Override
    protected void createMovableRect(int rectWidth, int rectHeight) {
        setMovable(new ARBEWIthHandler(0, 0, rectWidth, rectHeight, this));
        for (int i = 0; i < Helper.getActiveWorm().getWarmsInventory().size(); i++) {
            if (Helper.getActiveWorm().getWarmsInventory().get(i).getWeapon() instanceof Shotgun) {
                Integer newAmmoNumber = Helper.getActiveWorm().getWarmsInventory().get(i).getAmmoNumber();
                Helper.getActiveWorm().getWarmsInventory().get(i).setAmmoNumber(newAmmoNumber-1);
            }
        }
    }
}
