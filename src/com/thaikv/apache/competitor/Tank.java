package com.thaikv.apache.competitor;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class Tank extends Competitor {
	private Image imgFire;
	private boolean canShoot;
	private int i, j;

	public Tank(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		canShoot = false;
		i = 1;
		j = 0;
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgTank1.png")).getImage());
	}

	@Override
	public void drawCompetitor(Graphics2D g) {
		super.drawCompetitor(g);
		this.getBulletManagerCompetitor().drawAllBullet(g);
		g.drawImage(imgFire, this.getX() - 50, this.getY() - 80, null);
	}

	public void changeImg() {
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgTank" + i + ".png")).getImage());
	}

	public void changeImgShoot() {
		this.imgFire = new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgFireTank" + j + ".png")).getImage();
	}

	@Override
	public void move(int count) {
		if (count % super.getSpeed() == 0) {
			int x = this.getX();
			x += 5;
			if (x > GUI.WIDTH_FRAME) {
				x = -50;
			}
			this.setX(x);

			if (x % 100 == 0) {
				j++;
				if (j > 10) {
					j = 1;
				}
				i++;
				if (i > 5) {
					i = 1;
				}
				if (i == 2 || i == 3) {
					canShoot = true;
				}
				changeImg();
				changeImgShoot();
			} else {
				canShoot = false;
			}

			if (canShoot) {
				for (int i = 0; i < 2; i++) {

					this.getBulletManagerCompetitor().initBullet(this.getX(),
							this.getY(), 5, 4);
				}
			}
		}
	}

	@Override
	public void moveAllBullet(int xTarget, int yTarget, int count) {
		super.moveAllBullet(xTarget, yTarget, count);
		this.getBulletManagerCompetitor().moveAllBullet(0, 0, count);
	}

}
