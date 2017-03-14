package com.thaikv.apache.competitor;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.gameplay.MyPlane;
import com.thaikv.apache.menu.GUI;

public class SpecialPlane extends Competitor {
    private MyPlane mMyPlane;
    private Random rand = new Random();

    /**
     * Initializes the SpecialPlane object
     *
     * @param x                        Place the SpecialPlane in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the SpecialPlane in the Oy.
     * @param blood                    Blood of SpecialPlane.
     * @param speed                    Movement speed of SpecialPlane.
     * @param mBulletManagerCompetitor Object management bullet of SpecialPlane.
     */
    public SpecialPlane(int x, int y, int blood, int speed,
                        BulletManager mBulletManagerCompetitor, MyPlane mMyPlane) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        this.mMyPlane = mMyPlane;
        initSpecialPlane();
    }

    private void initSpecialPlane() {
        this.setImg(new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgSpecialPlane.png")).getImage());
    }

    @Override
    public void drawCompetitor(Graphics2D g) {
        super.drawCompetitor(g);
        this.getBulletManagerCompetitor().drawAllBullet(g);
    }

    /**
     * Handles SpecialPlane move.
     *
     * @param count Adjust the movement speed of SpecialPlane.
     */
    @Override
    public void move(int count) {
        if (count % this.getSpeed() == 0) {
            int x = this.getX();
            x++;
            if (x == rand.nextInt(400) + 50) {
                this.getBulletManagerCompetitor().addBullet(
                        this.getX() + mMyPlane.getImgMyPlane().getWidth(null)
                                / 2,
                        this.getY() + mMyPlane.getImgMyPlane().getHeight(null)
                                / 2, 8, 10);
            }
            if (x > GUI.WIDTH_FRAME) {
                x = -100;
            }
            this.setX(x);
        }
    }

    /**
     * Handles the movement of SpecialPlane's bullets.
     *
     * @param xTarget Destination position according to the Ox axis.
     * @param yTarget Destination position according to the Oy axis.
     * @param count   Adjust the movement speed of SpecialPlane' bullets.
     */
    @Override
    public void moveAllBullet(int xTarget, int yTarget, int count) {
        super.moveAllBullet(xTarget, yTarget, count);
        this.getBulletManagerCompetitor().moveAllBullet(
                mMyPlane.getMyPlane().getX(), mMyPlane.getMyPlane().getY(),
                count);
    }

}
