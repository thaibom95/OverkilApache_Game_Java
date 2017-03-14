package com.thaikv.apache.gameplay;

import com.thaikv.apache.competitor.CompetitorManager;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class MyPlane {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static final int PLANE_IS_STRONG = 1;
    public static final int PLANE_IS_WEAK = 2;
    public static final int PLANE_IS_DIE = 3;

    private int x, y;
    private Image imgMyPlane, imgStatus;
    private int blood;
    private int heart;
    private int speed;
    private int status;
    private HighScore mHighScore;
    private CompetitorManager mCompetitorManager;
    private SoundManager mSoundManager;

    /**
     * Create an MyPlane object.
     *
     * @param x             Place the MyPlane in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y             Place the MyPlane in the Oy (Oy is from top to bottom).
     * @param blood         Blood of MyPlane in 1 heart, if blood = 0 then heart = heart - 1.
     * @param heart         Heart of MyPlane, if heart = 0 then game over.
     * @param speed         Movement speed of MyPlane.
     * @param mSoundManager Sound Management object.
     */
    public MyPlane(int x, int y, int blood, int heart, int speed, SoundManager mSoundManager) {
        this.mSoundManager = mSoundManager;
        this.x = x;
        this.y = y;
        this.blood = blood;
        this.heart = heart;
        this.speed = speed;
        status = PLANE_IS_STRONG;
        imgMyPlane = new ImageIcon(getClass().getResource(
                "/imageMyPlane/imgMyPlane.png")).getImage();
        mHighScore = new HighScore();
    }

    /**
     * Draw the MyPlane.
     */
    public void drawMyPlain(Graphics2D g) {
        g.drawImage(imgMyPlane, x, y, null);
        checkHealth();
        if (status == PLANE_IS_WEAK) {
            g.drawImage(imgStatus, x, y, null);
        }
    }

    /**
     * Handles MyPlane move.
     *
     * @param orient Orientation movement of MyPlane.
     * @param count  Adjust the movement speed of MyPlane.
     */
    public void move(int orient, int count) {
        if (count % speed == 0) {
            switch (orient) {
                case LEFT:
                    x--;
                    imgMyPlane = new ImageIcon(getClass().getResource(
                            "/imageMyPlane/imgMyPlaneBack.png")).getImage();
                    if (x < 0) {
                        x = 0;
                    }
                    break;
                case RIGHT:
                    x++;
                    imgMyPlane = new ImageIcon(getClass().getResource(
                            "/imageMyPlane/imgMyPlaneForward.png")).getImage();
                    if (x > GUI.WIDTH_FRAME - imgMyPlane.getWidth(null)) {
                        x = GUI.WIDTH_FRAME - imgMyPlane.getWidth(null);
                    }
                    break;
                case UP:
                    y--;
                    if (y < 0) {
                        y = 0;
                    }
                    break;
                case DOWN:
                    y++;
                    if (y > GUI.HEIGHT_FRAME - imgMyPlane.getHeight(null) - 40) {
                        y = GUI.HEIGHT_FRAME - imgMyPlane.getHeight(null) - 50;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Check status of MyPlane.
     */
    public void checkHealth() {
        if (heart == 1) {
            status = PLANE_IS_WEAK;
        }
        if (heart == 0) {
            status = PLANE_IS_DIE;
        }
    }


    public void setImgMyPlane() {
        imgMyPlane = new ImageIcon(getClass().getResource(
                "/imageMyPlane/imgMyPlane.png")).getImage();
    }

    /**
     * Check for MyPlane collision with enemy bullets.
     *
     * @param mBullet This is a bullet of enemy.
     * @return Return true if the MyPlane collides with an enemy bullet else return false.
     */
    public boolean isImpactWithBulletOfCompetitor(Bullet mBullet) {
        Rectangle rect1 = new Rectangle(mBullet.getX(), mBullet.getY(), mBullet.getImgBullet().getWidth(null),
                mBullet.getImgBullet().getHeight(null));
        Rectangle rect2 = new Rectangle(x, y, imgMyPlane.getWidth(null),
                imgMyPlane.getHeight(null));
        return rect1.intersects(rect2);
    }

    /**
     * Delete the bullet after collides with MyPlane.
     *
     * @param mBulletManagerOfCompetitor The management object of the bullet.
     */
    public void destroyWithBulletOfCompetitor(BulletManager mBulletManagerOfCompetitor) {
        for (int i = 0; i < mBulletManagerOfCompetitor.getBullets().size(); i++) {
            if (isImpactWithBulletOfCompetitor(mBulletManagerOfCompetitor.getBullets().get(i))) {
                mBulletManagerOfCompetitor.getHits().add(
                        new Hit(mBulletManagerOfCompetitor.getBullets().get(i).getX(),
                                mBulletManagerOfCompetitor.getBullets().get(i).getY()));

                int bloodMyPlane = this.getBlood();
                bloodMyPlane -= mBulletManagerOfCompetitor.getBullets().get(i).getDamage();
                this.setBlood(bloodMyPlane);
                int heartMyPlane = this.getHeart();
                if (bloodMyPlane <= 0) {
                    this.mSoundManager.soundNewHeart();
                    bloodMyPlane = this.getBlood() + 30;
                    heartMyPlane--;
                    this.setBlood(bloodMyPlane);
                    this.setHeart(heartMyPlane);
                }

                checkHealth();
                if (this.getHeart() == 1 && this.getBlood() < 15) {
                    this.mSoundManager.soundDangerous();
                }

                if (this.getStatus() == PLANE_IS_DIE) {
                    this.mSoundManager.soundGameOver();
                    int scoreOld = mHighScore.readScore();
                    if (mCompetitorManager.getScore() > scoreOld) {
                        mHighScore.writeScore(Integer.toString(mCompetitorManager.getScore()));
                        JOptionPane.showMessageDialog(null,
                                "YOU LOSE :( \nHigh Score : "
                                        + mCompetitorManager.getScore());
                    } else {
                        JOptionPane.showMessageDialog(null, "YOU LOSE :( !");
                    }
                    System.exit(0);
                }
                mBulletManagerOfCompetitor.getBullets().remove(i);
                break;
            }
        }
    }

    public int getStatus() {
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public Image getImgMyPlane() {
        return imgMyPlane;
    }

    public MyPlane getMyPlane() {
        return this;
    }

    public void setCompetitorManager(CompetitorManager mCompetitorManager) {
        this.mCompetitorManager = mCompetitorManager;
    }

}
