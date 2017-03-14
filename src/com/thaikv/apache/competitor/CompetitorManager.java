package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.*;
import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CompetitorManager {
    private ArrayList<DestroyItemUpSky> mDestroyItemUpSkies;
    private ArrayList<Competitor> mCompetitors;
    private ArrayList<Integer> arrTypes;
    private BulletManager mBulletManagerCompetitor;
    private SoundManager mSoundManager;
    private HighScore mHighScore;
    private MyPlane mMyPlane;
    private int score;
    private Random rand = new Random();

    /**
     * Initializes the CompetitorManager object.
     *
     * @param mMyPlane                 MyPlane object.
     * @param mBulletManagerCompetitor Competitor's bullet management object.
     * @param mSoundManager            Sound management object.
     */
    public CompetitorManager(MyPlane mMyPlane, BulletManager mBulletManagerCompetitor, SoundManager mSoundManager) {
        this.mMyPlane = mMyPlane;
        this.mBulletManagerCompetitor = mBulletManagerCompetitor;
        this.mSoundManager = mSoundManager;
        initCompetitorManager();
    }

    private void initCompetitorManager() {
        score = 0;
        mHighScore = new HighScore();
        mCompetitors = new ArrayList<Competitor>();
        arrTypes = new ArrayList<Integer>();
        mDestroyItemUpSkies = new ArrayList<DestroyItemUpSky>();

        mCompetitors.add(new Car(-100, 590, 2, 5,
                this.mBulletManagerCompetitor));
        mCompetitors.add(new Truck(-50, 580, 2, 10,
                this.mBulletManagerCompetitor));
        mCompetitors.add(new Motor(-200, 600, 2, 3,
                this.mBulletManagerCompetitor));
        mCompetitors.add(new Soldier(-150, 610, 1, 50,
                this.mBulletManagerCompetitor));
        mCompetitors.add(new Tank(-100, 600, 3, 10,
                this.mBulletManagerCompetitor));
        mCompetitors.add(new UFO(1400, rand.nextInt(500) + 50, 2, 20,
                mBulletManagerCompetitor));
    }

    /**
     * Create more Competitor.
     */
    public void createMoreCompetitor() {
        switch (rand.nextInt(8)) {
            case 0:
                mCompetitors.add(new Car(-100, 590, 2, 5,
                        mBulletManagerCompetitor));
                break;
            case 1:
                mCompetitors.add(new Truck(-50, 580, 2, 10,
                        mBulletManagerCompetitor));
                break;
            case 2:
                mCompetitors.add(new Motor(-200, 600, 2, 3,
                        mBulletManagerCompetitor));
                break;
            case 3:
                mCompetitors.add(new Soldier(-150, 610, 1, 50,
                        mBulletManagerCompetitor));
                break;
            case 4:
                mCompetitors.add(new Tank(-10, 600, 3, 10,
                        mBulletManagerCompetitor));
                mCompetitors.add(new Items(rand.nextInt(200) + 800, -200, 3, 200,
                        mBulletManagerCompetitor, rand.nextInt(6) + 2));
                break;
            case 5:
                mCompetitors.add(new UFO(1400, rand.nextInt(500) + 50, 2, 20,
                        mBulletManagerCompetitor));
                break;
            case 6:
                mCompetitors.add(new OrdinaryPlane(rand.nextInt(500) + 1250, rand
                        .nextInt(400) + 100, 2, 2, mBulletManagerCompetitor));
                break;
            case 7:
                mCompetitors.add(new SpecialPlane(rand.nextInt(100) - 200, rand
                        .nextInt(500) + 50, 2, 5, mBulletManagerCompetitor,
                        mMyPlane));
                mCompetitors.add(new Items(rand.nextInt(200) + 800, -200, 3, 200,
                        mBulletManagerCompetitor, rand.nextInt(6) + 2));
                break;
        }
        improveLevel();
    }

    /**
     * Increase difficulty levels for the game
     */
    public void improveLevel() {
        if (score >= 85 && score <= 95) {
            this.mSoundManager.soundCompetitors();
        }
        if (score > 90 && score < 100) {
            mCompetitors.add(new SpecialPlane(rand.nextInt(100) - 200, rand
                    .nextInt(500) + 50, 2, 5, mBulletManagerCompetitor,
                    mMyPlane));
        }
        if (score >= 270 && score <= 280) {
            this.mSoundManager.soundCompetitors();
        }
        if (score > 280 && score < 300) {
            mCompetitors.add(new Tank(-10, 600, 3, 10,
                    mBulletManagerCompetitor));
        }

        if (score >= 490 && score <= 500) {
            this.mSoundManager.soundCompetitors();
        }
        if (score > 500 && score < 530) {
            mCompetitors.add(new Tank(-10, 600, 3, 10,
                    mBulletManagerCompetitor));
        }

        if (score >= 890 && score <= 910) {
            this.mSoundManager.soundCompetitors();
        }
        if (score > 900 && score < 950) {
            mCompetitors.add(new Tank(-10, 600, 3, 10,
                    mBulletManagerCompetitor));
        }
    }

    public void drawAllCompetitor(Graphics2D g) {
        for (int i = 0; i < mCompetitors.size(); i++) {
            mCompetitors.get(i).drawCompetitor(g);
        }
    }

    /**
     * Handles of all Competitor move.
     *
     * @param count Adjust the movement speed of Competitor.
     */
    public void moveAllCompetitor(int count) {
        for (int i = 0; i < mCompetitors.size(); i++) {
            mCompetitors.get(i).move(count);
        }
    }

    /**
     * Destroy the Competitor object after being hit by MyPlane's bullet.
     *
     * @param mBulletManagerOfMyPlane MyPlane's bullet management object.
     */
    public void destroyWithBulletOfMyPlane(BulletManager mBulletManagerOfMyPlane) {
        for (int i = 0; i < mCompetitors.size(); i++) {
            for (int j = 0; j < mBulletManagerOfMyPlane.getBullets().size(); j++) {
                if (mCompetitors.get(i) instanceof Items) {
                    continue;
                }
                if (mCompetitors.get(i).isImpactWithBulletOfMyPlane(mBulletManagerOfMyPlane.getBullets().get(j))) {
                    this.mSoundManager.soundOfNormalX();
                    int damage = mBulletManagerOfMyPlane.getBullets().get(j).getDamage();
                    int blood = mCompetitors.get(i).getBlood();
                    blood -= damage;
                    mCompetitors.get(i).setBlood(blood);

                    mBulletManagerOfMyPlane.getHits().add(
                            new Hit(mBulletManagerOfMyPlane.getBullets()
                                    .get(j).getX()
                                    + mCompetitors.get(i).getImg()
                                    .getWidth(null) / 2,
                                    mBulletManagerOfMyPlane.getBullets()
                                            .get(j).getY()));

                    if (mCompetitors.get(i).getBlood() < 0) {
                        if (mCompetitors.get(i) instanceof Tank
                                || mCompetitors.get(i) instanceof Car
                                || mCompetitors.get(i) instanceof Motor
                                || mCompetitors.get(i) instanceof Truck
                                || mCompetitors.get(i) instanceof Soldier) {
                            mBulletManagerOfMyPlane.getDestroyItemBelowLands().add(
                                    new DestroyItemBelowLand(
                                            mBulletManagerOfMyPlane.getBullets().get(j).getX(),
                                            mBulletManagerOfMyPlane.getBullets().get(j).getY()
                                                    + mCompetitors.get(i).getImg().getHeight(null) / 2));
                        } else {
                            mBulletManagerOfMyPlane
                                    .getDestroyItemUpSkies()
                                    .add(new DestroyItemUpSky(
                                            mBulletManagerOfMyPlane.getBullets().get(j).getX(),
                                            mBulletManagerOfMyPlane.getBullets().get(j).getY()
                                                    - mCompetitors.get(i).getImg().getHeight(null)));
                        }
                        this.mSoundManager.soundBoom();
                        mCompetitors.remove(i);
                        createMoreCompetitor();
                        score += mCompetitors.get(i).getBlood();
                    }
                    mBulletManagerOfMyPlane.getBullets().remove(j);
                    return;
                }
            }
        }
    }

    /**
     * Destroy the Competitor object after collision with MyPlane.
     */
    public void destroyWithMyPlane() {
        for (int i = 0; i < mCompetitors.size(); i++) {
            if (mCompetitors.get(i).isImpactWithMyPlane(mMyPlane)) {
                if (mCompetitors.get(i) instanceof Items) {
                    Items mItem = (Items) mCompetitors.get(i);
                    arrTypes.add(mItem.getType());
                    mCompetitors.remove(i);
                    return;
                } else {
                    this.mSoundManager.soundBoom();
                    mDestroyItemUpSkies.add(new DestroyItemUpSky(
                            mCompetitors.get(i).getX(), mCompetitors.get(i).getY()
                            - mCompetitors.get(i).getImg().getHeight(null)));
                    score += mCompetitors.get(i).getBlood();
                    mCompetitors.remove(i);
                    int bloodMyPlane = mMyPlane.getBlood();
                    bloodMyPlane -= 2;
                    mMyPlane.setBlood(bloodMyPlane);
                    int heartMyPlane = mMyPlane.getHeart();
                    if (bloodMyPlane <= 0) {
                        this.mSoundManager.soundNewHeart();
                        bloodMyPlane = 30 + mMyPlane.getBlood();
                        heartMyPlane--;
                        mMyPlane.setBlood(bloodMyPlane);
                        mMyPlane.setHeart(heartMyPlane);
                    }
                    mMyPlane.checkHealth();
                    if (mMyPlane.getHeart() == 1 && mMyPlane.getBlood() < 15) {
                        this.mSoundManager.soundDangerous();
                    }
                    if (mMyPlane.getStatus() == MyPlane.PLANE_IS_DIE) {
                        this.mSoundManager.soundGameOver();
                        int scoreOld = mHighScore.readScore();
                        if (score > scoreOld) {
                            mHighScore.writeScore(Integer.toString(score));
                            JOptionPane.showMessageDialog(null,
                                    "YOU LOSE :( \nHigh Score : " + score);
                        } else {
                            JOptionPane.showMessageDialog(null, "YOU LOSE :( !");
                        }

                        System.exit(0);
                    }
                    createMoreCompetitor();
                    return;
                }
            }
        }
    }

    /**
     * Destroy the Item object after exiting the screen.
     */
    public void destroyItemOutScreen() {
        for (int i = 0; i < mCompetitors.size(); i++) {
            if (mCompetitors.get(i) instanceof Items) {
                if (mCompetitors.get(i).getY() > GUI.HEIGHT_FRAME) {
                    mCompetitors.remove(i);
                    i--;
                    createMoreCompetitor();
                    break;
                }
            }
        }
    }

    public ArrayList<Integer> getArrTypes() {
        return arrTypes;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<DestroyItemUpSky> getDestroyItemUpSkies() {
        return mDestroyItemUpSkies;
    }

}
