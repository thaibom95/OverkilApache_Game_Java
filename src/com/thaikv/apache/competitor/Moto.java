package com.thaikv.apache.competitor;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class Moto extends Competitor {
	public Moto(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		Image imgMoto = new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgMoto.png")).getImage();
		this.setImg(imgMoto);
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
