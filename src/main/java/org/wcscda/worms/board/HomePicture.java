package org.wcscda.worms.board;

import org.wcscda.worms.Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class HomePicture  extends AbstractDrawableElement{
    private static final  String imagePath = "src/resources/weapons/homePicture.png";
    private static Image image = null;
    private int initTime;

    public HomePicture() {
        initTime = Helper.getClock();
    }

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(1200, 800, 0);
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        if (initTime + 100 < Helper.getClock()) {
            removeSelf();
        }
        g.drawImage(image, 1, 1, io );
    }

    @Override
    protected Integer getDepth() {
        return 1;
    }
}
