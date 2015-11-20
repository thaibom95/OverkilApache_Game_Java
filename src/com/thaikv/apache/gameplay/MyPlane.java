package com.thaikv.apache.gameplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.thaikv.apache.competitor.CompetitorManager;
import com.thaikv.apache.menu.GUI;

public class MyPlane {
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int SPACE = 4;

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
	private SoundLibrary mSoundLibrary;

	public MyPlane(int x, int y, int blood, int heart, int speed,
			SoundLibrary mSoundLibrary) {
		this.mSoundLibrary = mSoundLibrary;
		this.x = x;
		this.y = y;
		this.blood = blood;
		this.heart = heart;
		this.speed = speed;
		status = PLANE_IS_STRONG;
		imgMyPlane = new ImageIcon(getClass().getResource(
				"/imageMyPlane/imgMyPlain.png")).getImage();
		mHighScore = new HighScore();
	}

	public void drawMyPlain(Graphics2D g) {
		g.drawImage(imgMyPlane, x, y, null);
		checkHealth();
		if (status == PLANE_IS_WEAK) {
			g.drawImage(imgStatus, x, y, null);
		}
	}

	public void move(int orient, int count) {
		if (count % speed == 0) {

			switch (orient) {
			case LEFT:
				x--;
				imgMyPlane = new ImageIcon(getClass().getResource(
						"/imageMyPlane/imgMyPlainBack.png")).getImage();
				if (x < 0) {
					x = 0;
				}
				break;
			case RIGHT:
				x++;
				imgMyPlane = new ImageIcon(getClass().getResource(
						"/imageMyPlane/imgMyPlainForward.png")).getImage();
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
				"/imageMyPlane/imgMyPlain.png")).getImage();
	}

	public boolean isVaChamWithBulletOfCompetitor(Bullet mBullet) {
		Rectangle rect1 = new Rectangle(mBullet.getX(), mBullet.getY(), mBullet
				.getImgBullet().getWidth(null), mBullet.getImgBullet()
				.getHeight(null));
		Rectangle rect2 = new Rectangle(x, y, imgMyPlane.getWidth(null),
				imgMyPlane.getHeight(null));
		return rect1.intersects(rect2);
	}

	public void destroyWithBulletOfCompetitor(
			BulletManager mBulletManagerOfCompetitor) {
		for (int i = 0; i < mBulletManagerOfCompetitor.getArrBullets().size(); i++) {
			if (isVaChamWithBulletOfCompetitor(mBulletManagerOfCompetitor
					.getArrBullets().get(i))) {
				mBulletManagerOfCompetitor.getPairsHits().add(
						new Hit(mBulletManagerOfCompetitor.getArrBullets()
								.get(i).getX(), mBulletManagerOfCompetitor
								.getArrBullets().get(i).getY()));

				int bloodMyPlane = this.getBlood();
				bloodMyPlane -= mBulletManagerOfCompetitor.getArrBullets()
						.get(i).getDagame();
				this.setBlood(bloodMyPlane);
				int heartMyPlane = this.getHeart();
				if (bloodMyPlane <= 0) {
					this.mSoundLibrary.soundNewHeart();
					bloodMyPlane = this.getBlood() + 30;
					heartMyPlane--;
					this.setBlood(bloodMyPlane);
					this.setHeart(heartMyPlane);
				}

				checkHealth();
				if (this.getHeart() == 1 && this.getBlood() < 15) {
					this.mSoundLibrary.soundDangerous();
				}

				if (this.getStatus() == PLANE_IS_DIE) {
					this.mSoundLibrary.soundGameOver();
					int scoreOld = mHighScore.readScore();
					if (mCompetitorManager.getScore() > scoreOld) {
						mHighScore.wtiteScore(Integer
								.toString(mCompetitorManager.getScore()));
						JOptionPane.showMessageDialog(null,
								"YOU LOSE :( \nHigh Score : "
										+ mCompetitorManager.getScore());
					} else {
						JOptionPane.showMessageDialog(null, "YOU LOSE :( !");
					}
					System.exit(0);
				}
				mBulletManagerOfCompetitor.getArrBullets().remove(i);
				break;
			}
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public CompetitorManager getmCompetitorManager() {
		return mCompetitorManager;
	}

	public void setmCompetitorManager(CompetitorManager mCompetitorManager) {
		this.mCompetitorManager = mCompetitorManager;
	}

}
