package com.thaikv.apache.competitor;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class Truck extends Competitor {

	public Truck(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		Image imgTruck = new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgTruck.png")).getImage();
		this.setImg(imgTruck);
	}

	@Override
	public void move(int count) {
		if (count % super.getSpeed() == 0) {
			int x = this.getX();
			x++;
			if (x > GUI.WIDTH_FRAME) {
				x = -200;
			}
			this.setX(x);
		}
	}
}
