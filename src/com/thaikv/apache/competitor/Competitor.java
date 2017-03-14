package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.Bullet;
import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.gameplay.MyPlane;

import java.awt.*;

public class Competitor {
    private int x, y;
    private Image img;
    private int speed;
    private int blood;
    private BulletManager mBulletManagerCompetitor;

    /**
     * Initializes the Competitor object
     *
     * @param x                        Place the Competitor in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Competitor in the Oy (Oy is from top to bottom).
     * @param blood                    Blood of Competitor.
     * @param speed                    Movement speed of Competitor.
     * @param mBulletManagerCompetitor Object management bullet of Competitor.
     */
    public Competitor(int x, int y, int blood, int speed,
                      BulletManager mBulletManagerCompetitor) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.blood = blood;
        this.mBulletManagerCompetitor = mBulletManagerCompetitor;
    }

    public void drawCompetitor(Graphics2D g) {
        g.drawImage(img, x, y, null);
    }

    /**
     * Handles Competitor move.
     *
     * @param count Adjust the movement speed of Competitor.
     */
    public void move(int count) {
    }

    /**
     * Handles the movement of Competitor's bullets.
     *
     * @param xTarget Destination position according to the Ox axis.
     * @param yTarget Destination position according to the Oy axis.
     * @param count   Adjust the movement speed of Competitor' bullets.
     */
    public void moveAllBullet(int xTarget, int yTarget, int count) {
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public BulletManager getBulletManagerCompetitor() {
        return mBulletManagerCompetitor;
    }

    /**
     * Check for Competitor collision with MyPlane bullets.
     *
     * @param mBullet This is a bullet of MyPlane.
     * @return Return true if the Competitor collides with an MyPlane bullet else return false.
     */
    public boolean isImpactWithBulletOfMyPlane(Bullet mBullet) {
        Rectangle rect1 = new Rectangle(mBullet.getX(), mBullet.getY(),
                mBullet.getImgBullet().getWidth(null), mBullet.getImgBullet().getHeight(null));
        Rectangle rect2 = new Rectangle(x, y, img.getWidth(null),
                img.getHeight(null));
        return rect1.intersects(rect2);
    }

    /**
     * Check for Competitor collision with MyPlane.
     *
     * @param mMyPlane This is a MyPlane.
     * @return Return true if the Competitor collides with an MyPlane else return false.
     */
    public boolean isImpactWithMyPlane(MyPlane mMyPlane) {
        Rectangle rect1 = new Rectangle(mMyPlane.getX(), mMyPlane.getY(),
                mMyPlane.getImgMyPlane().getWidth(null), mMyPlane
                .getImgMyPlane().getHeight(null));
        Rectangle rect2 = new Rectangle(x, y, img.getWidth(null),
                img.getHeight(null));
        return rect1.intersects(rect2);
    }

}
