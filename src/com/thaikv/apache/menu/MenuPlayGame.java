package com.thaikv.apache.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MenuPlayGame {
	private int blood;
	private int totalBulletNormalX1, totalBulletNormalX2, totalBulletBomb,
			totalBulletRocketSmall, totalBulletRocketBig;
	private int heart, score;
	private boolean sound;

	public MenuPlayGame() {
		score = 0;
		heart = 0;
		blood = 30;
		totalBulletNormalX1 = 0;
		totalBulletNormalX2 = 0;
		totalBulletBomb = 0;
		totalBulletRocketSmall = 0;
		totalBulletRocketBig = 0;
		sound = false;
	}

	public void drawMenu(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.BOLD, 16));
		g.drawString("Score : ", 10, 25);
		g.drawString(Integer.toString(score), 80, 25);

		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRect(10, 35, 154, 15);
		g.setColor(Color.RED);
		g.fillRect(12, 37, blood * 5, 11);

		g.setColor(Color.WHITE);
		g.drawString("Heart : ", 10, 70);
		g.drawString(Integer.toString(heart), 80, 70);

		g.setColor(Color.WHITE);
		g.drawRect(180, 10, 420, 60);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Tahoma", Font.BOLD, 14));
		g.drawString("Bullet X1", 200, 25);
		g.drawString("Bullet X2", 280, 25);
		g.drawString("Bomb", 360, 25);
		g.drawString("Rocket 1", 430, 25);
		g.drawString("Rocket 2", 520, 25);

		g.drawString(Integer.toString(totalBulletNormalX1), 210, 50);
		g.drawString(Integer.toString(totalBulletNormalX2), 290, 50);
		g.drawString(Integer.toString(totalBulletBomb), 370, 50);
		g.drawString(Integer.toString(totalBulletRocketSmall), 450, 50);
		g.drawString(Integer.toString(totalBulletRocketBig), 530, 50);
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getTotalBulletNormalX1() {
		return totalBulletNormalX1;
	}

	public void setTotalBulletNormalX1(int totalBulletNormalX1) {
		this.totalBulletNormalX1 = totalBulletNormalX1;
	}

	public int getTotalBulletNormalX2() {
		return totalBulletNormalX2;
	}

	public void setTotalBulletNormalX2(int totalBulletNormalX2) {
		this.totalBulletNormalX2 = totalBulletNormalX2;
	}

	public int getTotalBulletBomb() {
		return totalBulletBomb;
	}

	public void setTotalBulletBomb(int totalBulletBomb) {
		this.totalBulletBomb = totalBulletBomb;
	}

	public int getTotalBulletRocketSmall() {
		return totalBulletRocketSmall;
	}

	public void setTotalBulletRocketSmall(int totalBulletRocketSmall) {
		this.totalBulletRocketSmall = totalBulletRocketSmall;
	}

	public int getTotalBulletRocketBig() {
		return totalBulletRocketBig;
	}

	public void setTotalBulletRocketBig(int totalBulletRocketBig) {
		this.totalBulletRocketBig = totalBulletRocketBig;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}
}
