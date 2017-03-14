package com.thaikv.apache.gameplay;

import com.thaikv.apache.competitor.CompetitorManager;
import com.thaikv.apache.competitor.Items;
import com.thaikv.apache.menu.GUI;
import com.thaikv.apache.menu.InsideMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

public class PlayGame extends JPanel {
    private static final long serialVersionUID = 1L;

    private BitSet traceKey = new BitSet();

    private BackgroundGame mBackgroundGame;
    private MyPlane mMyPlane;
    private BulletManager mBulletManagerOfMyPlane;
    private BulletManager mBulletManagerOfCompetitor;
    // volatile : Used for multi-threaded processing. The count variable will be synchronized in multi thread.
    volatile private int count = 0;
    private long timeBullet = 0;
    private CompetitorManager mCompetitorManager;
    private InsideMenu mInsideMenu;
    private int blood, heart, score, totalNormalX1, totalNormalX2, totalBomb,
            totalRocketSmall, totalRocketBig;
    private SoundManager mSoundManager;

    public PlayGame(SoundManager mSoundManager) {
        this.mSoundManager = mSoundManager;
        this.mSoundManager.stopSoundBackground();
        this.mSoundManager.soundNewHeart();
        this.mSoundManager.soundFly();
        initPlayGame();
    }

    private void initPlayGame() {
        mBackgroundGame = new BackgroundGame();
        mMyPlane = new MyPlane(0, 350, 30, 3, 1, this.mSoundManager);
        mBulletManagerOfMyPlane = new BulletManager();
        mBulletManagerOfCompetitor = new BulletManager();
        mCompetitorManager = new CompetitorManager(mMyPlane, mBulletManagerOfCompetitor, this.mSoundManager);
        mInsideMenu = new InsideMenu();
        setLayout(null);
        setFocusable(true);
        addKeyListener(mKeyAdapter);
        Thread thread = new Thread(runThread);
        thread.start();

        blood = 0;
        heart = 0;
        score = 0;
        mInsideMenu.setBlood(blood);
        mInsideMenu.setHeart(heart);
        mInsideMenu.setScore(score);
        totalNormalX1 = 0;
        totalNormalX2 = 0;
        totalBomb = 0;
        totalRocketSmall = 0;
        totalRocketBig = 0;
    }

    /**
     * Draw all objects onto the screen.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        mBackgroundGame.drawBackground(g2d);
        mMyPlane.drawMyPlain(g2d);
        mBulletManagerOfMyPlane.drawAllBullet(g2d);
        mBulletManagerOfCompetitor.drawAllBullet(g2d);

        mCompetitorManager.drawAllCompetitor(g2d);
        mInsideMenu.drawMenu(g2d);

        // Draw to creates an explosive effect when the MyPlane's rocket hits the land.
        for (int i = 0; i < mBulletManagerOfMyPlane.getDestroyItemBelowLands().size(); i++) {
            mBulletManagerOfMyPlane.getDestroyItemBelowLands().get(i).drawDestroyItemBelowLand(g2d);
        }

        // Draw to creates an explosive effect when the MyPlane't bullet hits the Competitors.
        for (int i = 0; i < mBulletManagerOfMyPlane.getHits().size(); i++) {
            mBulletManagerOfMyPlane.getHits().get(i).drawHit(g2d);
        }

        // Draw to creates an explosive effect when the Competitors't bullet hits the MyPlane.
        for (int i = 0; i < mBulletManagerOfCompetitor.getHits().size(); i++) {
            mBulletManagerOfCompetitor.getHits().get(i).drawHit(g2d);
        }

        // Draw to create an explosive effect when the MyPlane's bullet hits the Competitor and explodes in the sky.
        for (int i = 0; i < mBulletManagerOfMyPlane.getDestroyItemUpSkies().size(); i++) {
            mBulletManagerOfMyPlane.getDestroyItemUpSkies().get(i).drawDestroyItemUpSky(g2d);
        }

        // Draw to create an explosive effect when Competitor collides with MyPlane and explodes in the sky.
        for (int i = 0; i < mCompetitorManager.getDestroyItemUpSkies().size(); i++) {
            mCompetitorManager.getDestroyItemUpSkies().get(i).drawDestroyItemUpSky(g2d);
        }
    }

    /**
     * Main thread to play game.
     */
    Runnable runThread = new Runnable() {
        @Override
        public void run() {
            while (GUI.IS_RUNNING) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mBackgroundGame.moveAllBackground(count);
                eventKeyboard();
                mBulletManagerOfMyPlane.moveAllBullet(mMyPlane.getX(), mMyPlane.getY(), count);
                mBulletManagerOfMyPlane.destroyOutScreen();

                mBulletManagerOfCompetitor.moveAllBullet(mMyPlane.getX(), mMyPlane.getY(), count);

                mCompetitorManager.moveAllCompetitor(count);
                mCompetitorManager.destroyWithBulletOfMyPlane(mBulletManagerOfMyPlane);
                mCompetitorManager.destroyWithMyPlane();
                mCompetitorManager.destroyItemOutScreen();

                mMyPlane.destroyWithBulletOfCompetitor(mBulletManagerOfCompetitor);
                mMyPlane.setCompetitorManager(mCompetitorManager);

                mInsideMenu.setScore(mCompetitorManager.getScore());
                mInsideMenu.setBlood(mMyPlane.getBlood());
                mInsideMenu.setHeart(mMyPlane.getHeart());

                updateEffects();

                if (count > 1000000) {
                    count = 0;
                }
                count++;
                repaint();
            }
        }
    };

    /**
     * Listen to the state of the key
     */
    KeyAdapter mKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            traceKey.set(e.getKeyCode());
        }

        public void keyReleased(KeyEvent e) {
            traceKey.clear(e.getKeyCode());
        }
    };

    /**
     * Image update creates explosion effects and remove old effect.
     */
    public void updateEffects() {
        // Change explosive effects image when MyPlane's rocket hit the land and remove old effect.
        for (int i = 0; i < mBulletManagerOfMyPlane.getDestroyItemBelowLands().size(); i++) {
            if (mBulletManagerOfMyPlane.getDestroyItemBelowLands().get(i).isChangedImageOrDestroy()) {
                mBulletManagerOfMyPlane.getDestroyItemBelowLands().remove(i);
                i--;
            }
        }

        // Change explosive effects image when MyPlane's bullet hit Competitor and remove old effect.
        for (int i = 0; i < mBulletManagerOfMyPlane.getHits().size(); i++) {
            if (mBulletManagerOfMyPlane.getHits().get(i).isChangedImageHit()) {
                mBulletManagerOfMyPlane.getHits().remove(i);
                i--;
            }
        }

        // Change explosive effects image in the sky when MyPlane't bullet hit Competitor and remove old effect.
        for (int i = 0; i < mBulletManagerOfMyPlane.getDestroyItemUpSkies().size(); i++) {
            if (mBulletManagerOfMyPlane.getDestroyItemUpSkies().get(i).isChangedImageOrDestroy()) {
                mBulletManagerOfMyPlane.getDestroyItemUpSkies().remove(i);
                i--;
            }
        }

        // Change explosive effects image when Competitor's bullet hit MyPlane and remove old effect.
        for (int i = 0; i < mBulletManagerOfCompetitor.getHits().size(); i++) {
            if (mBulletManagerOfCompetitor.getHits().get(i).isChangedImageHit()) {
                mBulletManagerOfCompetitor.getHits().remove(i);
                i--;
            }
        }

        // Change explosive effects image in the sky when Competitor collided with MyPlane and remove old effect.
        for (int i = 0; i < mCompetitorManager.getDestroyItemUpSkies().size(); i++) {
            if (mCompetitorManager.getDestroyItemUpSkies().get(i).isChangedImageOrDestroy()) {
                mCompetitorManager.getDestroyItemUpSkies().remove(i);
                i--;
            }
        }
    }

    /**
     * Event handler of the keyboard.
     */
    public void eventKeyboard() {
        if (traceKey.isEmpty()) {
            mMyPlane.setImgMyPlane();
        }
        if (traceKey.get(KeyEvent.VK_LEFT)) {
            mMyPlane.move(MyPlane.LEFT, count);
        }
        if (traceKey.get(KeyEvent.VK_RIGHT)) {
            mMyPlane.move(MyPlane.RIGHT, count);
        }
        if (traceKey.get(KeyEvent.VK_UP)) {
            mMyPlane.move(MyPlane.UP, count);
        }
        if (traceKey.get(KeyEvent.VK_DOWN)) {
            mMyPlane.move(MyPlane.DOWN, count);
        }

        if (traceKey.get(KeyEvent.VK_SPACE)) {
            if (timeBullet == 0) {
                for (int i = 0; i < mCompetitorManager.getArrTypes().size(); i++) {

                    switch (mCompetitorManager.getArrTypes().get(i)) {
                        case Items.ITEM_NORMAL_X2:
                            totalNormalX1 += 30;
                            mCompetitorManager.getArrTypes().remove(i);
                            break;
                        case Items.ITEM_NORMAL_X3:
                            totalNormalX2 += 30;
                            mCompetitorManager.getArrTypes().remove(i);
                            break;
                        case Items.ITEM_BOMB:
                            totalBomb += 30;
                            mCompetitorManager.getArrTypes().remove(i);
                            break;
                        case Items.ITEM_ROCKET_SMALL:
                            totalRocketSmall += 30;
                            mCompetitorManager.getArrTypes().remove(i);
                            break;
                        case Items.ITEM_ROCKET_BIG:
                            totalRocketBig += 30;
                            mCompetitorManager.getArrTypes().remove(i);
                            break;
                        case Items.ITEM_AMBULANCE:
                            blood = mMyPlane.getBlood() + 10;
                            heart = mMyPlane.getHeart();
                            if (blood > 30) {
                                blood = 30;
                                if (heart < 3) {
                                    heart++;
                                }
                            }
                            mMyPlane.setBlood(blood);
                            mMyPlane.setHeart(heart);

                            mInsideMenu.setBlood(mMyPlane.getBlood());
                            mInsideMenu.setHeart(mMyPlane.getHeart());

                            mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                            + mMyPlane.getImgMyPlane().getWidth(
                                            null), mMyPlane.getY()
                                            + mMyPlane.getImgMyPlane().getHeight(
                                            null) - 20,
                                    Bullet.BULLET_NORMAL_X1, 1);
                            mCompetitorManager.getArrTypes().remove(i);
                            break;
                    }
                }

                if (totalNormalX1 > 0) {
                    totalNormalX1--;
                    mInsideMenu.setTotalBulletNormalX1(totalNormalX1);

                    // Create new BULLET_NORMAL_X2 bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null),
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 20, Bullet.BULLET_NORMAL_X2, 1);
                }
                if (totalNormalX2 > 0) {
                    totalNormalX2--;
                    mInsideMenu.setTotalBulletNormalX2(totalNormalX2);

                    // Create new BULLET_NORMAL_X3 bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null),
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 20, Bullet.BULLET_NORMAL_X3, 1);
                }

                if (totalBomb > 0) {
                    mSoundManager.soundOfBomb();
                    totalBomb--;
                    mInsideMenu.setTotalBulletBomb(totalBomb);

                    // Create new BULLET_BOMB bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null) / 2,
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 10, Bullet.BULLET_BOMB, 1);

                    // Create new BULLET_NORMAL_X1 bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null),
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 20, Bullet.BULLET_NORMAL_X1, 1);
                }
                if (totalRocketSmall > 0) {
                    mSoundManager.soundOfRocket1();
                    totalRocketSmall--;
                    mInsideMenu.setTotalBulletRocketSmall(totalRocketSmall);

                    // Create new BULLET_ROCKET_SMALL bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null) / 2,
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 10, Bullet.BULLET_ROCKET_SMALL, 2);

                    // Create new BULLET_NORMAL_X1 bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null),
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 20, Bullet.BULLET_NORMAL_X1, 1);
                }
                if (totalRocketBig > 0) {
                    mSoundManager.soundOfRocket2();
                    totalRocketBig--;
                    mInsideMenu.setTotalBulletRocketBig(totalRocketBig);

                    // Create new BULLET_ROCKET_BIG bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null) / 2,
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 10, Bullet.BULLET_ROCKET_BIG, 2);

                    // Create new BULLET_NORMAL_X1 bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null),
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 20, Bullet.BULLET_NORMAL_X1, 1);
                }

                if (totalNormalX1 == 0 && totalNormalX2 == 0 && totalBomb == 0
                        && totalRocketSmall == 0 && totalRocketBig == 0) {

                    // Create new BULLET_NORMAL_X1 bullet for MyPlane.
                    mBulletManagerOfMyPlane.addBullet(mMyPlane.getX()
                                    + mMyPlane.getImgMyPlane().getWidth(null),
                            mMyPlane.getY()
                                    + mMyPlane.getImgMyPlane().getHeight(null)
                                    - 20, Bullet.BULLET_NORMAL_X1, 1);
                }

                timeBullet = 150;
            }
            if (timeBullet > 0) {
                timeBullet--;
            }
        }
    }
}
