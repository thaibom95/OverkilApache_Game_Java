package com.thaikv.apache.gameplay;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.thaikv.apache.menu.GUI;

public class BackgroundGame extends JPanel {
	private static final long serialVersionUID = 1L;

	private Image imgBackground;
	private int xDat, yDat, xLeu, yLeu, xChoi, yChoi, xNha, yNha, xCloud1,
			yCloud1, xCloud2, yCloud2, xCloud3, yCloud3;
	private Image imgSand, imgChoi, imgLeu, imgNha, imgCloud1, imgCloud2,
			imgCloud3;

	public BackgroundGame() {
		imgBackground = new ImageIcon(getClass().getResource(
				"/imageBackground/background.png")).getImage();
		imgSand = new ImageIcon(getClass().getResource(
				"/imageBackground/imgDat.png")).getImage();
		imgLeu = new ImageIcon(getClass().getResource(
				"/imageBackground/imgLeu.png")).getImage();
		imgChoi = new ImageIcon(getClass().getResource(
				"/imageBackground/imgChoi.png")).getImage();
		imgNha = new ImageIcon(getClass().getResource(
				"/imageBackground/imgNha.png")).getImage();

		imgCloud1 = new ImageIcon(getClass().getResource(
				"/imageBackground/imgCloud1.png")).getImage();

		imgCloud2 = new ImageIcon(getClass().getResource(
				"/imageBackground/imgCloud2.png")).getImage();

		imgCloud3 = new ImageIcon(getClass().getResource(
				"/imageBackground/imgCloud3.png")).getImage();

		xDat = 0;
		yDat = GUI.HEIGHT_FRAME - imgSand.getHeight(null) - 20;
		xLeu = 1500;
		yLeu = GUI.HEIGHT_FRAME - imgLeu.getHeight(null) - 70;
		xChoi = 2000;
		yChoi = GUI.HEIGHT_FRAME - imgChoi.getHeight(null) - 70;
		xNha = 3000;
		yNha = GUI.HEIGHT_FRAME - imgNha.getHeight(null) - 70;

		xCloud1 = 1200;
		yCloud1 = 100;
		xCloud2 = 1500;
		yCloud2 = 150;
		xCloud3 = 2000;
		yCloud3 = 70;

	}

	public void drawBackground(Graphics2D g) {
		g.drawImage(imgBackground, 0, 0, null);
		g.drawImage(imgSand, xDat, yDat, null);
		g.drawImage(imgLeu, xLeu, yLeu, null);
		g.drawImage(imgChoi, xChoi, yChoi, null);
		g.drawImage(imgNha, xNha, yNha, null);
		g.drawImage(imgCloud1, xCloud1, yCloud1, null);
		g.drawImage(imgCloud2, xCloud2, yCloud2, null);
		g.drawImage(imgCloud3, xCloud3, yCloud3, null);
	}

	public void moveSand() {
		xDat--;
		if (xDat == -GUI.WIDTH_FRAME) {
			xDat = -50;
		}
	}

	public void moveLeu() {
		xLeu--;
		if (xLeu == -GUI.WIDTH_FRAME) {
			xLeu = 1500;
		}
	}

	public void moveChoi() {
		xChoi--;
		if (xChoi == -GUI.WIDTH_FRAME) {
			xChoi = 2000;
		}
	}

	public void moveNha() {
		xNha--;
		if (xNha == -GUI.WIDTH_FRAME) {
			xNha = 3000;
		}
	}

	public void moveCloud(int count) {
		if (count % 20 == 0) {
			xCloud1--;
			if (xCloud1 == -500) {
				xCloud1 = 1200;
			}
			xCloud2--;
			if (xCloud2 == -500) {
				xCloud2 = 1500;
			}
			xCloud3--;
			if (xCloud3 == -500) {
				xCloud3 = 2000;
			}
		} 
	}

	public void moveAllBackground(int count) {
		moveSand();
		moveLeu();
		moveChoi();
		moveNha();
		moveCloud(count);
	}

}
