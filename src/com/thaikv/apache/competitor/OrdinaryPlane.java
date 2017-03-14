package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;

import javax.swing.*;

public class OrdinaryPlane extends Competitor {
    /**
     * Initializes the OrdinaryPlane object.
     *
     * @param x                        Place the OrdinaryPlane in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the OrdinaryPlane in the Oy.
     * @param blood                    Blood of OrdinaryPlane.
     * @param speed                    Movement speed of OrdinaryPlane.
     * @param mBulletManagerCompetitor Object management bullet of OrdinaryPlane.
     */
    public OrdinaryPlane(int x, int y, int blood, int speed,
                         BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initOrdinaryPlane();
    }

    private void initOrdinaryPlane() {
        this.setImg(new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgOrdinaryPlane.png")).getImage());
    }

    /**
     * Handles OrdinaryPlane move.
     *
     * @param count Adjust the movement speed of OrdinaryPlane.
     */
    @Override
    public void move(int count) {
        if (count % this.getSpeed() == 0) {
            int x = this.getX();
            if (x >= 850) {
                x--;
            } else {
                x -= 3;
            }
            if (x < 0) {
                x = 1300;
            }
            this.setX(x);
        }
    }

}
