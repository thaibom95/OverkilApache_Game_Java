package com.thaikv.apache.gameplay;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Hit {
	private int x, y;
	private Image imgHit;
	private int count = 0;

	public Hit(int x, int y) {
		this.x = x;
		this.y = y;
		imgHit = new ImageIcon(getClass().getResource(
				"/imageBoom/imgHitnull.png")).getImage();
	}

	public boolean changeImgHit() {
		if (count < 50) {
			imgHit = new ImageIcon(getClass().getResource(
					"/imageBoom/imgHit2.png")).getImage();
			count++;
			return false;
		} else {
			return true;
		}
	}

	public void drawHit(Graphics2D g) {
		g.drawImage(imgHit, x, y, null);
	}
}
