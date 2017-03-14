package com.thaikv.apache.gameplay;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates an explosion effect when the bullet hits the target and explodes.
 */
public class Hit {
    private int x, y;
    private Image imgHit;
    private int count = 0;

    /**
     * Initializes the Hit object.
     *
     * @param x Place the Hit in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y Place the Hit in the Oy (Oy is from top to bottom).
     */
    public Hit(int x, int y) {
        this.x = x;
        this.y = y;
        initHit();
    }

    private void initHit() {
        imgHit = new ImageIcon(getClass().getResource("/imageBoom/imgHitnull.png")).getImage();
    }

    /**
     * Make change image of hit to create an explosion effect.
     *
     * @return Return true if the bullets explode and change the image of the Hit done else return false.
     */
    public boolean isChangedImageHit() {
        if (count < 50) {
            imgHit = new ImageIcon(getClass().getResource("/imageBoom/imgHit2.png")).getImage();
            count++;
            return false;
        } else {
            return true;
        }
    }

    public void drawHit(Graphics2D g) {
        g.drawImage(imgHit, x, y, null);
    }
}
