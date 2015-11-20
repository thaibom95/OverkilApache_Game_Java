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

	public SpecialPlane(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor, MyPlane mMyPlane) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgSpecialPlane.png")).getImage());
		this.mMyPlane = mMyPlane;
	}

	@Override
	public void drawCompetitor(Graphics2D g) {
		super.drawCompetitor(g);
		this.getBulletManagerCompetitor().drawAllBullet(g);
	}

	@Override
	public void move(int count) {
		if (count % this.getSpeed() == 0) {
			int x = this.getX();
			x++;
			if (x == rand.nextInt(400) + 50) {
				this.getBulletManagerCompetitor().initBullet(
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

	@Override
	public void moveAllBullet(int xTarget, int yTarget, int count) {
		super.moveAllBullet(xTarget, yTarget, count);
		this.getBulletManagerCompetitor().moveAllBullet(
				mMyPlane.getMyPlane().getX(), mMyPlane.getMyPlane().getY(),
				count);
	}

}
