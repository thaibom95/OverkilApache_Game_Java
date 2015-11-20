package com.thaikv.apache.competitor;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class UFO extends Competitor {
	private Image imgUfoJet;
	private int i;

	public UFO(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		i = 1;
		this.setImg(new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgUfo.png")).getImage());
		imgUfoJet = new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgUfoJet2.png")).getImage();
	}

	@Override
	public void drawCompetitor(Graphics2D g) {
		super.drawCompetitor(g);
		g.drawImage(imgUfoJet, this.getX() + 10, this.getY()
				+ this.getImg().getHeight(null), null);
	}

	public void changeImg() {
		this.imgUfoJet = new ImageIcon(getClass().getResource(
				"/imageCompetitor/imgUfoJet" + i + ".png")).getImage();
	}

	@Override
	public void move(int count) {
		if (count % this.getSpeed() == 0) {
			int x = this.getX();
			x--;
			if (x < 0) {
				x = GUI.WIDTH_FRAME + 100;
			}
			this.setX(x);
			i++;
			if (i > 2) {
				i = 1;
			}
			changeImg();
		}
	}

}
