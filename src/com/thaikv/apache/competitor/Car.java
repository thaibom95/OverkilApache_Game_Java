package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class Car extends Competitor {
    /**
     * Initializes the Car object
     *
     * @param x                        Place the Car in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Car in the Oy.
     * @param blood                    Blood of Car.
     * @param speed                    Movement speed of Car.
     * @param mBulletManagerCompetitor Object management bullet of Car.
     */
    public Car(int x, int y, int blood, int speed,
               BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initCar();
    }

    private void initCar() {
        Image imgCar = new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgCar.png")).getImage();
        this.setImg(imgCar);
    }

    /**
     * Handles Car move.
     *
     * @param count Adjust the movement speed of Car.
     */
    @Override
    public void move(int count) {
        if (count % super.getSpeed() == 0) {
            int x = this.getX();
            x++;
            if (x > GUI.WIDTH_FRAME) {
                x = -100;
            }
            this.setX(x);
        }
    }
}
