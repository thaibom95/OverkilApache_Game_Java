package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class Tank extends Competitor {
    private Image imgFire;
    private boolean canShoot;
    private int indexImageTank, indexImageFireTank;

    /**
     * Initializes the Tank object
     *
     * @param x                        Place the Tank in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Tank in the Oy.
     * @param blood                    Blood of Tank.
     * @param speed                    Movement speed of Tank.
     * @param mBulletManagerCompetitor Object management bullet of Tank.
     */
    public Tank(int x, int y, int blood, int speed,
                BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initTank();
    }

    private void initTank() {
        canShoot = false;
        indexImageTank = 1;
        indexImageFireTank = 0;
        this.setImg(new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgTank1.png")).getImage());
    }

    @Override
    public void drawCompetitor(Graphics2D g) {
        super.drawCompetitor(g);
        this.getBulletManagerCompetitor().drawAllBullet(g);
        g.drawImage(imgFire, this.getX() - 50, this.getY() - 80, null);
    }

    /**
     * Make change image of Tank.
     */
    public void changeImageTank() {
        this.setImg(new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgTank" + indexImageTank + ".png")).getImage());
    }

    /**
     * Make change image shoot of Tank.
     */
    public void changeImageShootOfTank() {
        this.imgFire = new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgFireTank" + indexImageFireTank + ".png")).getImage();
    }

    /**
     * Handles Tank move.
     *
     * @param count Adjust the movement speed of Tank.
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

            if (x % 100 == 0) {
                indexImageFireTank++;
                if (indexImageFireTank > 10) {
                    indexImageFireTank = 1;
                }
                indexImageTank++;
                if (indexImageTank > 5) {
                    indexImageTank = 1;
                }
                if (indexImageTank == 2 || indexImageTank == 3) {
                    canShoot = true;
                }
                changeImageTank();
                changeImageShootOfTank();
            } else {
                canShoot = false;
            }

            if (canShoot) {
                for (int i = 0; i < 2; i++) {

                    this.getBulletManagerCompetitor().addBullet(this.getX(),
                            this.getY(), 5, 4);
                }
            }
        }
    }

    /**
     * Handles the movement of Tank's bullets.
     *
     * @param xTarget Destination position according to the Ox axis.
     * @param yTarget Destination position according to the Oy axis.
     * @param count   Adjust the movement speed of Tank' bullets.
     */
    @Override
    public void moveAllBullet(int xTarget, int yTarget, int count) {
        super.moveAllBullet(xTarget, yTarget, count);
        this.getBulletManagerCompetitor().moveAllBullet(0, 0, count);
    }

}
