package com.thaikv.apache.competitor;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class Soldier extends Competitor {
	private int i;

	public Soldier(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		i = 1;
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageSoldier/imgSoldier1.png")).getImage());
	}

	public void changeImg() {
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageSoldier/imgSoldier" + i + ".png")).getImage());
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
			i++;

			if (i > 9) {
				i = 1;
			}
			changeImg();
		}
	}
}
