package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class Motor extends Competitor {
    /**
     * Initializes the Motor object.
     *
     * @param x                        Place the Motor in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Motor in the Oy.
     * @param blood                    Blood of Motor.
     * @param speed                    Movement speed of Motor.
     * @param mBulletManagerCompetitor Object management bullet of Motor.
     */
    public Motor(int x, int y, int blood, int speed,
                 BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initMotor();
    }

    private void initMotor() {
        Image imgMotor = new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgMotor.png")).getImage();
        this.setImg(imgMotor);
    }

    /**
     * Handles Motor move.
     *
     * @param count Adjust the movement speed of Motor.
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
