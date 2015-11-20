package com.thaikv.apache.gameplay;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class BulletManager {
	private ArrayList<Bullet> arrBullets;
	private ArrayList<DestroyItemBelowLand> pairsBooms;
	private ArrayList<Hit> pairsHits;
	private ArrayList<DestroyItemUpSky> pairsDestroyItemUpSkies;
	private int type;

	public BulletManager() {
		arrBullets = new ArrayList<Bullet>();
		pairsBooms = new ArrayList<DestroyItemBelowLand>();
		pairsHits = new ArrayList<Hit>();
		pairsDestroyItemUpSkies = new ArrayList<DestroyItemUpSky>();
	}

	public void initBullet(int x, int y, int type, int speed) {
		Bullet mBullet = new Bullet(x, y, type, speed);
		this.type = type;
		arrBullets.add(mBullet);
	}

	public void drawAllBullet(Graphics2D g) {
		for (int i = 0; i < arrBullets.size(); i++) {
			arrBullets.get(i).drawBullet(g);
		}
	}

	public void moveAllBullet(int xTarget, int yTarget, int count) {
		for (int i = 0; i < arrBullets.size(); i++) {
			arrBullets.get(i).move(xTarget, yTarget, count);
		}
	}

	public void destroyOutScreen() {
		for (int i = 0; i < arrBullets.size(); i++) {
			if (arrBullets.get(i).isOutScreen()) {
				if (!arrBullets.get(i).isCanBoom()) {
					arrBullets.remove(i);
					break;
				} else {
					pairsBooms.add(new DestroyItemBelowLand(arrBullets.get(i)
							.getxDestroy(),
							arrBullets.get(i).getyDestroy() - 20));
					arrBullets.remove(i);
					break;
				}
			}
		}
	}

	public void remove(int position) {
		arrBullets.remove(position);
	}

	public void drawAllFire(final Graphics2D g) {
		for (int i = 0; i < arrBullets.size(); i++) {
			if (arrBullets.get(i).isCanBoom()) {
				arrBullets.get(i).drawFire(g);
			}
		}
	}

	public int getType() {
		return type;
	}

	public ArrayList<Bullet> getArrBullets() {
		return arrBullets;
	}

	public void setArrBullets(ArrayList<Bullet> arrBullets) {
		this.arrBullets = arrBullets;
	}

	public ArrayList<DestroyItemBelowLand> getPairsBooms() {
		return pairsBooms;
	}

	public void setPairsBooms(ArrayList<DestroyItemBelowLand> pairsBooms) {
		this.pairsBooms = pairsBooms;
	}

	public ArrayList<Hit> getPairsHits() {
		return pairsHits;
	}

	public void setPairsHits(ArrayList<Hit> pairsHits) {
		this.pairsHits = pairsHits;
	}

	public ArrayList<DestroyItemUpSky> getPairsDestroyItemUpSkies() {
		return pairsDestroyItemUpSkies;
	}

	public void setPairsDestroyItemUpSkies(
			ArrayList<DestroyItemUpSky> pairsDestroyItemUpSkies) {
		this.pairsDestroyItemUpSkies = pairsDestroyItemUpSkies;
	}

}
