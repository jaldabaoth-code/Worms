package org.wcscda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import org.wcscda.worms.*;
import org.wcscda.worms.gamemechanism.phases.*;

public class Shotgun extends AbstractWeapon {
    private static final String imagePath = "src/resources/weapons/Shotgun_small.png";
    private static Image image = null;
    private int nbFiredShoots = 0;
    private static String name = "Shotgun";

    public String getName() {
        return name;
    }

    public static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        if (getAngle() > Math.PI / 2) {
            AffineTransform trans = AffineTransform.getTranslateInstance(Helper.getWormX() - 40, Helper.getWormY());
            trans.scale(1, 1);
            g.drawImage(image, trans, io);
        } else {
            AffineTransform trans = AffineTransform.getTranslateInstance(Helper.getWormX() + 40, Helper.getWormY());
            trans.scale(-1, 1);
            g.drawImage(image, trans, io);
        }
    }

    public AbstractPhase getNextPhase() {
        nbFiredShoots++;
        return new MovingPhase();
    }

    public void triggerAmmoExplosion() {
        if (nbFiredShoots == 2) {
            super.triggerAmmoExplosion();
        } else {
            Helper.getTC().setCurrentPhase(new WormMovingPhase());
        }
    }

    public boolean isChangingWeaponDisabled() {
        return nbFiredShoots != 0;
    }
}
