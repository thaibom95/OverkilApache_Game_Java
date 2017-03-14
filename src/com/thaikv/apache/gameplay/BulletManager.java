package com.thaikv.apache.gameplay;

import java.awt.*;
import java.util.ArrayList;

public class BulletManager {
    private ArrayList<Bullet> mBullets;
    private ArrayList<DestroyItemBelowLand> mDestroyItemBelowLands;
    private ArrayList<Hit> mHits;
    private ArrayList<DestroyItemUpSky> mDestroyItemUpSkies;
    private int type;

    public BulletManager() {
        mBullets = new ArrayList<Bullet>();
        mDestroyItemBelowLands = new ArrayList<DestroyItemBelowLand>();
        mHits = new ArrayList<Hit>();
        mDestroyItemUpSkies = new ArrayList<DestroyItemUpSky>();
    }

    /**
     * Add a new bullet to bullet list.
     *
     * @param x     Place the Bullet in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y     Place the Bullet in the Oy.
     * @param type  Type of Bullet.
     * @param speed Movement speed of Bullet.
     */
    public void addBullet(int x, int y, int type, int speed) {
        Bullet mBullet = new Bullet(x, y, type, speed);
        this.type = type;
        mBullets.add(mBullet);
    }

    /**
     * Draw the bullet list to the screen.
     *
     * @param g
     */
    public void drawAllBullet(Graphics2D g) {
        for (int i = 0; i < mBullets.size(); i++) {
            mBullets.get(i).drawBullet(g);
        }
    }

    /**
     * Handles Bullet list move.
     *
     * @param xTarget Destination position according to the Ox axis.
     * @param yTarget Destination position according to the Oy axis.
     * @param count   Adjust the movement speed of Bullet.
     */
    public void moveAllBullet(int xTarget, int yTarget, int count) {
        for (int i = 0; i < mBullets.size(); i++) {
            mBullets.get(i).move(xTarget, yTarget, count);
        }
    }

    /**
     * Clear the bullet when it comes out of the screen
     */
    public void destroyOutScreen() {
        for (int i = 0; i < mBullets.size(); i++) {
            if (mBullets.get(i).isOutScreen()) {
                if (!mBullets.get(i).isCanBoom()) {
                    mBullets.remove(i);
                    break;
                } else {
                    mDestroyItemBelowLands.add(new DestroyItemBelowLand(mBullets.get(i)
                            .getxDestroy(),
                            mBullets.get(i).getyDestroy() - 20));
                    mBullets.remove(i);
                    break;
                }
            }
        }
    }

    public ArrayList<Bullet> getBullets() {
        return mBullets;
    }

    public ArrayList<DestroyItemBelowLand> getDestroyItemBelowLands() {
        return mDestroyItemBelowLands;
    }

    public ArrayList<Hit> getHits() {
        return mHits;
    }

    public ArrayList<DestroyItemUpSky> getDestroyItemUpSkies() {
        return mDestroyItemUpSkies;
    }

}
