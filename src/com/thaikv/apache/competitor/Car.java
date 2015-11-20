package com.thaikv.apache.competitor;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class Car extends Competitor {

	public Car(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		Image imgCar = new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgCar.png")).getImage();
		this.setImg(imgCar);
	}

	@Override
	public void move(int count) {
		if (count % super.getSpeed() == 0) {
			int x = this.getX();
			x++;
			if (x > GUI.WIDTH_FRAME) {
				x = -100;
			}
			this.setX(x);
		}
	}
}
