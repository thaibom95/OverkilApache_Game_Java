package com.thaikv.apache.gameplay;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DestroyItemUpSky {
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
	public static Image IMG_DESTROY_STATE14;
	public static Image IMG_DESTROY_STATE15;
	public static Image IMG_DESTROY_STATE16;
	public static Image IMG_DESTROY_STATE17;
	public static Image IMG_DESTROY_STATE18;
	public static Image IMG_DESTROY_STATE19;
	public static Image IMG_DESTROY_STATE20;

	public int x, y;
	private Image img;
	int count = 0;

	public DestroyItemUpSky(int x, int y) {
		this.x = x;
		this.y = y;

		IMG_DESTROY_STATE1 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky1.png")).getImage();
		IMG_DESTROY_STATE2 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky2.png")).getImage();
		IMG_DESTROY_STATE3 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky3.png")).getImage();
		IMG_DESTROY_STATE4 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky4.png")).getImage();
		IMG_DESTROY_STATE5 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky5.png")).getImage();
		IMG_DESTROY_STATE6 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky6.png")).getImage();
		IMG_DESTROY_STATE7 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky7.png")).getImage();
		IMG_DESTROY_STATE8 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky8.png")).getImage();
		IMG_DESTROY_STATE9 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky9.png")).getImage();
		IMG_DESTROY_STATE10 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky10.png")).getImage();
		IMG_DESTROY_STATE11 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky11.png")).getImage();
		IMG_DESTROY_STATE12 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky12.png")).getImage();
		IMG_DESTROY_STATE13 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky13.png")).getImage();
		IMG_DESTROY_STATE14 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky14.png")).getImage();
		IMG_DESTROY_STATE15 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky15.png")).getImage();
		IMG_DESTROY_STATE16 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky16.png")).getImage();
		IMG_DESTROY_STATE17 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky17.png")).getImage();
		IMG_DESTROY_STATE18 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky18.png")).getImage();
		IMG_DESTROY_STATE19 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky19.png")).getImage();
		IMG_DESTROY_STATE20 = new ImageIcon(getClass().getResource(
				"/imageBoom/imgBoomSky20.png")).getImage();
		img = IMG_DESTROY_STATE20;

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
		} else if (count < 260) {
			img = IMG_DESTROY_STATE14;
			count++;
			return false;
		} else if (count < 280) {
			img = IMG_DESTROY_STATE15;
			count++;
			return false;
		} else if (count < 300) {
			img = IMG_DESTROY_STATE16;
			count++;
			return false;
		} else if (count < 320) {
			img = IMG_DESTROY_STATE17;
			count++;
			return false;
		} else if (count < 340) {
			img = IMG_DESTROY_STATE18;
			count++;
			return false;
		} else if (count < 360) {
			img = IMG_DESTROY_STATE19;
			count++;
			return false;
		} else if (count < 380) {
			img = IMG_DESTROY_STATE20;
			count++;
			return false;
		} else {
			return true;
		}
	}

	public void drawDestroy(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

}