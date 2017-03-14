package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;

public class Soldier extends Competitor {
    private int indexImageSoldier;

    /**
     * Initializes the Soldier object
     *
     * @param x                        Place the Soldier in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Soldier in the Oy.
     * @param blood                    Blood of Soldier.
     * @param speed                    Movement speed of Soldier.
     * @param mBulletManagerCompetitor Object management bullet of Soldier.
     */
    public Soldier(int x, int y, int blood, int speed,
                   BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initSoldier();
    }

    private void initSoldier() {
        indexImageSoldier = 1;
        this.setImg(new ImageIcon(getClass().getResource(
                "/imageSoldier/imgSoldier1.png")).getImage());
    }

    /**
     * Make change image of Soldier.
     */
    public void changeImageSoldier() {
        this.setImg(new ImageIcon(getClass().getResource(
                "/imageSoldier/imgSoldier" + indexImageSoldier + ".png")).getImage());
    }

    /**
     * Handles Soldier move.
     *
     * @param count Adjust the movement speed of Soldier.
     */
    @Override
    public void move(int count) {
        if (count % super.getSpeed() == 0) {
            int x = this.getX();
            x += 5;
            if (x > GUI.WIDTH_FRAME) {
                x = -50;
            }
            this.setX(x);
            indexImageSoldier++;

            if (indexImageSoldier > 9) {
                indexImageSoldier = 1;
            }
            changeImageSoldier();
        }
    }
}
