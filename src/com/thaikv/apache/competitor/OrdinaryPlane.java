package com.thaikv.apache.competitor;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;

public class OrdinaryPlane extends Competitor {

	public OrdinaryPlane(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgOrdinaryPlane.png")).getImage());
	}

	@Override
	public void move(int count) {
		if (count % this.getSpeed() == 0) {
			int x = this.getX();
			if (x >= 850) {
				x--;
			} else {
				x -= 3;
			}
			if (x < 0) {
				x = 1300;
			}
			this.setX(x);
		}
	}

}
