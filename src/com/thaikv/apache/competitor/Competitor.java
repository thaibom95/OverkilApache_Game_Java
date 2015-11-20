package com.thaikv.apache.competitor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.thaikv.apache.gameplay.Bullet;
import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.gameplay.MyPlane;

public class Competitor {
	private int x, y;
	private Image img;
	private int speed;
	private int blood;
	private BulletManager mBulletManagerCompetitor;

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

	public void move(int count) {

	}

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

	public void setBulletManagerCompetitor(
			BulletManager mBulletManagerCompetitor) {
		this.mBulletManagerCompetitor = mBulletManagerCompetitor;
	}

	public boolean isVaChamWithBullet(Bullet mBullet) {
		Rectangle rect1 = new Rectangle(mBullet.getX(), mBullet.getY(), mBullet
				.getImgBullet().getWidth(null), mBullet.getImgBullet()
				.getHeight(null));
		Rectangle rect2 = new Rectangle(x, y, img.getWidth(null),
				img.getHeight(null));
		return rect1.intersects(rect2);
	}

	public boolean isVaChamWithMyPlane(MyPlane mMyPlane) {
		Rectangle rect1 = new Rectangle(mMyPlane.getX(), mMyPlane.getY(),
				mMyPlane.getImgMyPlane().getWidth(null), mMyPlane
						.getImgMyPlane().getHeight(null));
		Rectangle rect2 = new Rectangle(x, y, img.getWidth(null),
				img.getHeight(null));
		return rect1.intersects(rect2);
	}

}
