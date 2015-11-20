package com.thaikv.apache.gameplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.JPanel;

import com.thaikv.apache.competitor.CompetitorManager;
import com.thaikv.apache.competitor.Items;
import com.thaikv.apache.menu.GUI;
import com.thaikv.apache.menu.MenuPlayGame;

public class PlayGame extends JPanel {
	private static final long serialVersionUID = 1L;

	private BitSet traceKey = new BitSet();

	private BackgroundGame mBackgroundGame;
	private MyPlane mMyPlane;
	private BulletManager mBulletManagerOfMyPlane;
	private BulletManager mBulletManagerOfCompetitor;
	volatile private int count = 0;
	private long timeBullet = 0;
	private CompetitorManager mCompetitorManager;
	private MenuPlayGame mMenuPlayGame;
	private int blood, heart, score, totalNormalX1, totalNormalX2, totalBomb,
			totalRocketSmall, totalRocketBig;
	private SoundLibrary mSoundLibrary;

	public PlayGame(SoundLibrary mSoundLibrary) {
		setLayout(null);
		this.mSoundLibrary = mSoundLibrary;
		mBackgroundGame = new BackgroundGame();
		mMyPlane = new MyPlane(0, 350, 30, 3, 1, this.mSoundLibrary);
		mBulletManagerOfMyPlane = new BulletManager();
		mBulletManagerOfCompetitor = new BulletManager();
		mCompetitorManager = new CompetitorManager(mMyPlane,
				mBulletManagerOfCompetitor, this.mSoundLibrary);
		mMenuPlayGame = new MenuPlayGame();
		setFocusable(true);
		addKeyListener(mKeyAdapter);
		Thread thread = new Thread(runThread);
		thread.start();

		blood = 0;
		heart = 0;
		score = 0;
		mMenuPlayGame.setBlood(blood);
		mMenuPlayGame.setHeart(heart);
		mMenuPlayGame.setScore(score);
		totalNormalX1 = 0;
		totalNormalX2 = 0;
		totalBomb = 0;
		totalRocketSmall = 0;
		totalRocketBig = 0;

		this.mSoundLibrary.stopSoundBackground();
		this.mSoundLibrary.soundNewHeart();
		this.mSoundLibrary.soundFly();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		mBackgroundGame.drawBackground(g2d);
		mMyPlane.drawMyPlain(g2d);
		mBulletManagerOfMyPlane.drawAllBullet(g2d);
		mBulletManagerOfCompetitor.drawAllBullet(g2d);

		mCompetitorManager.drawAllCompetitor(g2d);
		mMenuPlayGame.drawMenu(g2d);

		for (int i = 0; i < mBulletManagerOfMyPlane.getPairsBooms().size(); i++) {
			mBulletManagerOfMyPlane.getPairsBooms().get(i).drawDestroy(g2d);
		}

		for (int i = 0; i < mBulletManagerOfMyPlane.getPairsHits().size(); i++) {
			mBulletManagerOfMyPlane.getPairsHits().get(i).drawHit(g2d);
		}

		for (int i = 0; i < mBulletManagerOfCompetitor.getPairsHits().size(); i++) {
			mBulletManagerOfCompetitor.getPairsHits().get(i).drawHit(g2d);
		}

		for (int i = 0; i < mBulletManagerOfMyPlane
				.getPairsDestroyItemUpSkies().size(); i++) {
			mBulletManagerOfMyPlane.getPairsDestroyItemUpSkies().get(i)
					.drawDestroy(g2d);
		}

		for (int i = 0; i < mCompetitorManager.getPairsDestroybByMyPlane()
				.size(); i++) {
			mCompetitorManager.getPairsDestroybByMyPlane().get(i)
					.drawDestroy(g2d);
		}
	}

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
				mCompetitorManager.moveAllCompetior(count);
				checkKey();
				mBulletManagerOfMyPlane.moveAllBullet(mMyPlane.getX(),
						mMyPlane.getY(), count);
				mBulletManagerOfCompetitor.moveAllBullet(mMyPlane.getX(),
						mMyPlane.getY(), count);
				mBulletManagerOfMyPlane.destroyOutScreen();
				mCompetitorManager
						.destroyWithBulletOfMyPlane(mBulletManagerOfMyPlane);
				mCompetitorManager.destroyWithMyPlane();
				mMenuPlayGame.setScore(mCompetitorManager.getScore());
				mCompetitorManager.destroyItemOutScreen();
				mMyPlane.destroyWithBulletOfCompetitor(mBulletManagerOfCompetitor);
				mMenuPlayGame.setBlood(mMyPlane.getBlood());
				mMenuPlayGame.setHeart(mMyPlane.getHeart());
				mMyPlane.setmCompetitorManager(mCompetitorManager);
				destroyAll();
				if (count > 1000000) {
					count = 0;
				}
				count++;
				repaint();
			}
		}
	};

	KeyAdapter mKeyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			traceKey.set(e.getKeyCode());
		}

		public void keyReleased(KeyEvent e) {
			traceKey.clear(e.getKeyCode());
		}
	};

	public void destroyAll() {
		for (int i = 0; i < mBulletManagerOfMyPlane.getPairsBooms().size(); i++) {
			if (mBulletManagerOfMyPlane.getPairsBooms().get(i)
					.changeImageOrDestroy()) {
				mBulletManagerOfMyPlane.getPairsBooms().remove(i);
				i--;
			}
		}

		for (int i = 0; i < mBulletManagerOfMyPlane.getPairsHits().size(); i++) {
			if (mBulletManagerOfMyPlane.getPairsHits().get(i).changeImgHit()) {
				mBulletManagerOfMyPlane.getPairsHits().remove(i);
				i--;
			}
		}

		for (int i = 0; i < mBulletManagerOfMyPlane
				.getPairsDestroyItemUpSkies().size(); i++) {
			if (mBulletManagerOfMyPlane.getPairsDestroyItemUpSkies().get(i)
					.changeImageOrDestroy()) {
				mBulletManagerOfMyPlane.getPairsDestroyItemUpSkies().remove(i);
				i--;
			}
		}

		for (int i = 0; i < mBulletManagerOfCompetitor.getPairsHits().size(); i++) {
			if (mBulletManagerOfCompetitor.getPairsHits().get(i).changeImgHit()) {
				mBulletManagerOfCompetitor.getPairsHits().remove(i);
				i--;
			}
		}

		for (int i = 0; i < mCompetitorManager.getPairsDestroybByMyPlane()
				.size(); i++) {
			if (mCompetitorManager.getPairsDestroybByMyPlane().get(i)
					.changeImageOrDestroy()) {
				mCompetitorManager.getPairsDestroybByMyPlane().remove(i);
				i--;
			}
		}
	}

	public void checkKey() {
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

						mMenuPlayGame.setBlood(mMyPlane.getBlood());
						mMenuPlayGame.setHeart(mMyPlane.getHeart());

						mBulletManagerOfMyPlane.initBullet(
								mMyPlane.getX()
										+ mMyPlane.getImgMyPlane().getWidth(
												null),
								mMyPlane.getY()
										+ mMyPlane.getImgMyPlane().getHeight(
												null) - 20,
								Bullet.BULLET_NORMAL_X1, 1);
						mCompetitorManager.getArrTypes().remove(i);
						break;
					}
				}

				if (totalNormalX1 > 0) {
					totalNormalX1--;
					mMenuPlayGame.setTotalBulletNormalX1(totalNormalX1);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null),
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 20, Bullet.BULLET_NORMAL_X2, 1);
				}
				if (totalNormalX2 > 0) {
					totalNormalX2--;
					mMenuPlayGame.setTotalBulletNormalX2(totalNormalX2);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null),
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 20, Bullet.BULLET_NORMAL_X3, 1);
				}

				if (totalBomb > 0) {
					mSoundLibrary.soundOfBomb();
					totalBomb--;
					mMenuPlayGame.setTotalBulletBomb(totalBomb);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null) / 2,
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 10, Bullet.BULLET_BOMB, 1);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null),
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 20, Bullet.BULLET_NORMAL_X1, 1);
				}
				if (totalRocketSmall > 0) {
					mSoundLibrary.soundOfRocket1();
					totalRocketSmall--;
					mMenuPlayGame.setTotalBulletRocketSmall(totalRocketSmall);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null) / 2,
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 10, Bullet.BULLET_ROCKET_SMALL, 2);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null),
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 20, Bullet.BULLET_NORMAL_X1, 1);
				}
				if (totalRocketBig > 0) {
					mSoundLibrary.soundOfRocket2();
					totalRocketBig--;
					mMenuPlayGame.setTotalBulletRocketBig(totalRocketBig);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null) / 2,
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 10, Bullet.BULLET_ROCKET_BIG, 2);
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
							+ mMyPlane.getImgMyPlane().getWidth(null),
							mMyPlane.getY()
									+ mMyPlane.getImgMyPlane().getHeight(null)
									- 20, Bullet.BULLET_NORMAL_X1, 1);
				}

				if (totalNormalX1 == 0 && totalNormalX2 == 0 && totalBomb == 0
						&& totalRocketSmall == 0 && totalRocketBig == 0) {
					mBulletManagerOfMyPlane.initBullet(mMyPlane.getX()
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
