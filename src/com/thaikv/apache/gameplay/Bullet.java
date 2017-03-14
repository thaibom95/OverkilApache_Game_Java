package com.thaikv.apache.gameplay;

import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    public static final int BULLET_NORMAL_X1 = 1;
    public static final int BULLET_NORMAL_X2 = 2;
    public static final int BULLET_NORMAL_X3 = 3;
    public static final int BULLET_BOMB = 4;
    public static final int BULLET_CROSS = 5;
    public static final int BULLET_ROCKET_SMALL = 6;
    public static final int BULLET_ROCKET_BIG = 7;
    public static final int BULLET_TARGET = 8;

    private int x, y;
    private Image imgBullet, imgFire;
    private int damage, speed, type;
    private int xDestroy, yDestroy;
    private boolean canBoom, checkShootTarget, checkOrient;
    //Attributes are component of strange line:  m*(xTargetOrigin - x0) + n*(yTargetOrigin - y0) = 0;
    private int x0, y0, xTargetOrigin, yTargetOrigin, m, n;

    /**
     * Initializes the Bullet object.
     *
     * @param x     Place the Bullet in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y     Place the Bullet in the Oy (Oy is from top to bottom).
     * @param type  Types of ammunition.
     * @param speed Movement speed.
     */
    public Bullet(int x, int y, int type, int speed) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.speed = speed;
        canBoom = false;
        checkShootTarget = true;
        checkOrient = true;
        switch (this.type) {
            case BULLET_NORMAL_X1:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgBulletNormalX1.png")).getImage();
                damage = 1;
                break;
            case BULLET_NORMAL_X2:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgBulletNormalX2.png")).getImage();
                damage = 2;
                break;
            case BULLET_NORMAL_X3:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgBulletNormalX3.png")).getImage();
                damage = 3;
                break;
            case BULLET_BOMB:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgBomb.png")).getImage();
                damage = 2;
                break;
            case BULLET_CROSS:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgCross.png")).getImage();
                damage = 1;
                break;
            case BULLET_ROCKET_SMALL:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgRocketSmall.png")).getImage();
                damage = 2;
                break;
            case BULLET_ROCKET_BIG:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgRocketBig.png")).getImage();
                damage = 4;
                break;
            case BULLET_TARGET:
                imgBullet = new ImageIcon(getClass().getResource(
                        "/imageBullet/imgTarget.png")).getImage();
                damage = 2;
                break;
            default:
                break;
        }
    }

    public void drawBullet(Graphics2D g) {
        g.drawImage(imgBullet, x, y, null);
    }

    /**
     * Handles Bullet move.
     *
     * @param xTarget Destination position according to the Ox axis.
     * @param yTarget Destination position according to the Oy axis.
     * @param count   Adjust the movement speed of Bullet.
     */
    public void move(int xTarget, int yTarget, int count) {
        if (count % speed == 0) {
            switch (type) {
                case BULLET_NORMAL_X1:
                    x++;
                    break;
                case BULLET_NORMAL_X2:
                    x++;
                    break;
                case BULLET_NORMAL_X3:
                    x++;
                    break;
                case BULLET_BOMB:
                    speed = 5;
                    x--;
                    y += 2;
                    if (y >= GUI.HEIGHT_FRAME - 50) {
                        y = GUI.HEIGHT_FRAME - 40;
                    }
                    break;
                case BULLET_CROSS:
                    x--;
                    y--;
                    break;
                case BULLET_ROCKET_SMALL:
                    x++;
                    y++;
                    if (y >= GUI.HEIGHT_FRAME - 50) {
                        y = GUI.HEIGHT_FRAME - 40;
                    }
                    break;
                case BULLET_ROCKET_BIG:
                    x++;
                    y++;
                    if (y >= GUI.HEIGHT_FRAME - 50) {
                        y = GUI.HEIGHT_FRAME - 40;
                    }
                    break;
                case BULLET_TARGET:
                    if (checkShootTarget) {
                        xTargetOrigin = xTarget;
                        yTargetOrigin = yTarget;
                        checkShootTarget = false;
                    }
                    findTarget(xTargetOrigin, yTargetOrigin);

                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Finds the target.
     *
     * @param xTarget Destination position according to the Ox axis
     * @param yTarget Destination position according to the Oy axis
     */
    public void findTarget(int xTarget, int yTarget) {
        if (checkOrient) {
            x0 = this.x;
            y0 = this.y;
            m = y0 - yTarget;
            n = xTarget - x0;
            checkOrient = false;
        }
        if (n != 0) {
            if (x0 > xTarget) {
                x--;
            } else {
                x++;
            }
            y = (int) ((-m * (x - xTarget)) / n + yTarget);
        }
    }

    /**
     * Check that the bullet is out of the screen.
     *
     * @return Returns true if the bullet came out of the screen else return false.
     */
    public boolean isOutScreen() {
        boolean isOutScreen = false;
        if (x > GUI.WIDTH_FRAME || x < 0 || y < 0) {
            isOutScreen = true;
        }
        if (y > GUI.HEIGHT_FRAME - 50) {
            xDestroy = x;
            yDestroy = y;
            isOutScreen = true;
            canBoom = true;
        }
        return isOutScreen;
    }


    public void drawFire(Graphics2D g) {
        imgFire = new ImageIcon(getClass().getResource(
                "/imageFire/imgFire1.png")).getImage();
        g.drawImage(imgFire, 100, 100 / 2, null);
    }

    public int getxDestroy() {
        return xDestroy;
    }

    public int getyDestroy() {
        return yDestroy;
    }

    public boolean isCanBoom() {
        return canBoom;
    }

    public Image getImgBullet() {
        return imgBullet;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

}
