package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class Truck extends Competitor {

    /**
     * Initializes the Truck object
     *
     * @param x                        Place the Truck in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Truck in the Oy.
     * @param blood                    Blood of Truck.
     * @param speed                    Movement speed of Truck.
     * @param mBulletManagerCompetitor Object management bullet of Truck.
     */
    public Truck(int x, int y, int blood, int speed,
                 BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initTruck();
    }

    private void initTruck() {
        Image imgTruck = new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgTruck.png")).getImage();
        this.setImg(imgTruck);
    }

    /**
     * Handles Truck move.
     *
     * @param count Adjust the movement speed of Truck.
     */
    @Override
    public void move(int count) {
        if (count % super.getSpeed() == 0) {
            int x = this.getX();
            x++;
            if (x > GUI.WIDTH_FRAME) {
                x = -200;
            }
            this.setX(x);
        }
    }
}
