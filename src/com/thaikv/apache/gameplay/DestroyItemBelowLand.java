package com.thaikv.apache.gameplay;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DestroyItemBelowLand {
	public static Image IMG_DESTROY_STATE1;
	public static Image IMG_DESTROY_STATE2;
	public static Image IMG_DESTROY_STATE3;
	public static Image IMG_DESTROY_STATE4;
	public static Image IMG_DESTROY_STATE5;
	public static Image IMG_DESTROY_STATE6;
	public static Image IMG_DESTROY_STATE7;
	public static Image IMG_DESTROY_STATE8;
	public static Image IMG_DESTROY_STATE9;
	public static Image IMG_DESTROY_STATE10;
	public static Image IMG_DESTROY_STATE11;
	public static Image IMG_DESTROY_STATE12;
	public static Image IMG_DESTROY_STATE13;

	public int x, y;
	private Image img;
	int count = 0;

	public DestroyItemBelowLand(int x, int y) {
		this.x = x;
		this.y = y;
		
		IMG_DESTROY_STATE1 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom1.png")).getImage();
		IMG_DESTROY_STATE2 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom2.png")).getImage();
		IMG_DESTROY_STATE3 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom3.png")).getImage();
		IMG_DESTROY_STATE4 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom4.png")).getImage();
		IMG_DESTROY_STATE5 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom5.png")).getImage();
		IMG_DESTROY_STATE6 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom6.png")).getImage();
		IMG_DESTROY_STATE7 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom7.png")).getImage();
		IMG_DESTROY_STATE8 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom8.png")).getImage();
		IMG_DESTROY_STATE9 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom9.png")).getImage();
		IMG_DESTROY_STATE10 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom10.png")).getImage();
		IMG_DESTROY_STATE11 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom11.png")).getImage();
		IMG_DESTROY_STATE12 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom12.png")).getImage();
		IMG_DESTROY_STATE13 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoom13.png")).getImage();
		img = IMG_DESTROY_STATE13;
	}

	public boolean changeImageOrDestroy() {
		if (count < 10) {
			img = IMG_DESTROY_STATE1;
			count++;
			return false;
		} else if (count < 20) {
			img = IMG_DESTROY_STATE2;
			count++;
			return false;
		} else if (count < 40) {
			img = IMG_DESTROY_STATE3;
			count++;
			return false;
		} else if (count < 60) {
			img = IMG_DESTROY_STATE4;
			count++;
			return false;
		} else if (count < 80) {
			img = IMG_DESTROY_STATE5;
			count++;
			return false;
		} else if (count < 100) {
			img = IMG_DESTROY_STATE6;
			count++;
			return false;
		} else if (count < 120) {
			img = IMG_DESTROY_STATE7;
			count++;
			return false;
		} else if (count < 140) {
			img = IMG_DESTROY_STATE8;
			count++;
			return false;
		} else if (count < 160) {
			img = IMG_DESTROY_STATE9;
			count++;
			return false;
		} else if (count < 180) {
			img = IMG_DESTROY_STATE10;
			count++;
			return false;
		} else if (count < 200) {
			img = IMG_DESTROY_STATE11;
			count++;
			return false;
		} else if (count < 220) {
			img = IMG_DESTROY_STATE12;
			count++;
			return false;
		} else if (count < 240) {
			img = IMG_DESTROY_STATE13;
			count++;
			return false;
		} else {
			return true;
		}
	}

	public void drawDestroy(Graphics2D g) {
		g.drawImage(img, x + img.getWidth(null) / 2 - 150, y - 50, null);
	}

}