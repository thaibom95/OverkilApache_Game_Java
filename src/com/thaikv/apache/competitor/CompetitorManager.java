package com.thaikv.apache.competitor;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.gameplay.DestroyItemBelowLand;
import com.thaikv.apache.gameplay.DestroyItemUpSky;
import com.thaikv.apache.gameplay.HighScore;
import com.thaikv.apache.gameplay.Hit;
import com.thaikv.apache.gameplay.MyPlane;
import com.thaikv.apache.gameplay.SoundLibrary;
import com.thaikv.apache.menu.GUI;

public class CompetitorManager {
	private ArrayList<Competitor> arrCompetitors;
	private Random rand = new Random();
	private MyPlane mMyPlane;
	private BulletManager mBulletManagerCompetitor;
	private ArrayList<Integer> arrTypes;
	private int score;
	private ArrayList<DestroyItemUpSky> pairsDestroybByMyPlane;
	private HighScore mHighScore;
	private SoundLibrary mSoundLibrary;

	public CompetitorManager(MyPlane mMyPlane,
			BulletManager mBulletManagerCompetitor, SoundLibrary mSoundLibrary) {
		this.mSoundLibrary = mSoundLibrary;
		score = 0;
		mHighScore = new HighScore();
		this.mMyPlane = mMyPlane;
		this.mBulletManagerCompetitor = mBulletManagerCompetitor;
		arrCompetitors = new ArrayList<Competitor>();
		arrTypes = new ArrayList<Integer>();
		pairsDestroybByMyPlane = new ArrayList<DestroyItemUpSky>();

		arrCompetitors.add(new Car(-100, 590, 2, 5,
				this.mBulletManagerCompetitor));
		arrCompetitors.add(new Truck(-50, 580, 2, 10,
				this.mBulletManagerCompetitor));
		arrCompetitors.add(new Moto(-200, 600, 2, 3,
				this.mBulletManagerCompetitor));
		arrCompetitors.add(new Soldier(-150, 610, 1, 50,
				this.mBulletManagerCompetitor));
		arrCompetitors.add(new Tank(-100, 600, 3, 10,
				this.mBulletManagerCompetitor));
		arrCompetitors.add(new UFO(1400, rand.nextInt(500) + 50, 2, 20,
				mBulletManagerCompetitor));
	}

	public void initCompetitor() {
		switch (rand.nextInt(8)) {
		case 0:
			arrCompetitors.add(new Car(-100, 590, 2, 5,
					mBulletManagerCompetitor));
			break;
		case 1:
			arrCompetitors.add(new Truck(-50, 580, 2, 10,
					mBulletManagerCompetitor));
			break;
		case 2:
			arrCompetitors.add(new Moto(-200, 600, 2, 3,
					mBulletManagerCompetitor));
			break;
		case 3:
			arrCompetitors.add(new Soldier(-150, 610, 1, 50,
					mBulletManagerCompetitor));
			break;
		case 4:
			arrCompetitors.add(new Tank(-10, 600, 3, 10,
					mBulletManagerCompetitor));
			arrCompetitors.add(new Items(rand.nextInt(200) + 800, -200, 3, 200,
					mBulletManagerCompetitor, rand.nextInt(6) + 2));
			break;
		case 5:
			arrCompetitors.add(new UFO(1400, rand.nextInt(500) + 50, 2, 20,
					mBulletManagerCompetitor));
			break;
		case 6:
			arrCompetitors.add(new OrdinaryPlane(rand.nextInt(500) + 1250, rand
					.nextInt(400) + 100, 2, 2, mBulletManagerCompetitor));
			break;
		case 7:
			arrCompetitors.add(new SpecialPlane(rand.nextInt(100) - 200, rand
					.nextInt(500) + 50, 2, 5, mBulletManagerCompetitor,
					mMyPlane));
			arrCompetitors.add(new Items(rand.nextInt(200) + 800, -200, 3, 200,
					mBulletManagerCompetitor, rand.nextInt(6) + 2));
			break;
		}
		improveLevel();
	}

	public void improveLevel() {
		if (score >= 85 && score <= 95) {
			this.mSoundLibrary.soundCompetitors();
		}
		if (score > 90 && score < 100) {
			arrCompetitors.add(new SpecialPlane(rand.nextInt(100) - 200, rand
					.nextInt(500) + 50, 2, 5, mBulletManagerCompetitor,
					mMyPlane));
		}
		if (score >= 270 && score <= 280) {
			this.mSoundLibrary.soundCompetitors();
		}
		if (score > 280 && score < 300) {
			arrCompetitors.add(new Tank(-10, 600, 3, 10,
					mBulletManagerCompetitor));
		}
		
		if (score >= 490 && score <= 500) {
			this.mSoundLibrary.soundCompetitors();
		}
		if (score > 500 && score < 530) {
			arrCompetitors.add(new Tank(-10, 600, 3, 10,
					mBulletManagerCompetitor));
		}
		
		if (score >= 890 && score <= 910) {
			this.mSoundLibrary.soundCompetitors();
		}
		if (score > 900 && score < 950) {
			arrCompetitors.add(new Tank(-10, 600, 3, 10,
					mBulletManagerCompetitor));
		}
	}

	public void drawAllCompetitor(Graphics2D g) {
		for (int i = 0; i < arrCompetitors.size(); i++) {
			arrCompetitors.get(i).drawCompetitor(g);
		}
	}

	public void moveAllCompetior(int count) {
		for (int i = 0; i < arrCompetitors.size(); i++) {
			arrCompetitors.get(i).move(count);
		}
	}

	public void destroyWithBulletOfMyPlane(BulletManager mBulletManagerOfMyPlane) {
		for (int i = 0; i < arrCompetitors.size(); i++) {
			for (int j = 0; j < mBulletManagerOfMyPlane.getArrBullets().size(); j++) {
				if (arrCompetitors.get(i) instanceof Items) {
					continue;
				}
				if (arrCompetitors.get(i).isVaChamWithBullet(
						mBulletManagerOfMyPlane.getArrBullets().get(j))) {
					this.mSoundLibrary.soundOfNormalX();
					int dagame = mBulletManagerOfMyPlane.getArrBullets().get(j)
							.getDagame();
					int blood = arrCompetitors.get(i).getBlood();
					blood -= dagame;
					arrCompetitors.get(i).setBlood(blood);

					mBulletManagerOfMyPlane.getPairsHits().add(
							new Hit(mBulletManagerOfMyPlane.getArrBullets()
									.get(j).getX()
									+ arrCompetitors.get(i).getImg()
											.getWidth(null) / 2,
									mBulletManagerOfMyPlane.getArrBullets()
											.get(j).getY()));

					if (arrCompetitors.get(i).getBlood() < 0) {
						if (arrCompetitors.get(i) instanceof Tank
								|| arrCompetitors.get(i) instanceof Car
								|| arrCompetitors.get(i) instanceof Moto
								|| arrCompetitors.get(i) instanceof Truck
								|| arrCompetitors.get(i) instanceof Soldier) {
							mBulletManagerOfMyPlane.getPairsBooms().add(
									new DestroyItemBelowLand(
											mBulletManagerOfMyPlane
													.getArrBullets().get(j)
													.getX(),
											mBulletManagerOfMyPlane
													.getArrBullets().get(j)
													.getY()
													+ arrCompetitors.get(i)
															.getImg()
															.getHeight(null)
													/ 2));
						} else {
							mBulletManagerOfMyPlane
									.getPairsDestroyItemUpSkies()
									.add(new DestroyItemUpSky(
											mBulletManagerOfMyPlane
													.getArrBullets().get(j)
													.getX(),
											mBulletManagerOfMyPlane
													.getArrBullets().get(j)
													.getY()
													- arrCompetitors.get(i)
															.getImg()
															.getHeight(null)));
						}
						this.mSoundLibrary.soundBoom();
						arrCompetitors.remove(i);
						initCompetitor();
						score += arrCompetitors.get(i).getBlood();
					}
					mBulletManagerOfMyPlane.getArrBullets().remove(j);
					return;
				}
			}
		}
	}

	public void destroyWithMyPlane() {
		for (int i = 0; i < arrCompetitors.size(); i++) {
			if (arrCompetitors.get(i).isVaChamWithMyPlane(mMyPlane)) {
				if (arrCompetitors.get(i) instanceof Items) {
					Items mItem = (Items) arrCompetitors.get(i);
					arrTypes.add(mItem.getType());
					arrCompetitors.remove(i);
					return;
				} else {
					this.mSoundLibrary.soundBoom();
					pairsDestroybByMyPlane.add(new DestroyItemUpSky(
							arrCompetitors.get(i).getX(), arrCompetitors.get(i)
									.getY()
									- arrCompetitors.get(i).getImg()
											.getHeight(null)));
					score += arrCompetitors.get(i).getBlood();
					arrCompetitors.remove(i);
					int bloodMyPlane = mMyPlane.getBlood();
					bloodMyPlane -= 2;
					mMyPlane.setBlood(bloodMyPlane);
					int heartMyPlane = mMyPlane.getHeart();
					if (bloodMyPlane <= 0) {
						this.mSoundLibrary.soundNewHeart();
						bloodMyPlane = 30 + mMyPlane.getBlood();
						heartMyPlane--;
						mMyPlane.setBlood(bloodMyPlane);
						mMyPlane.setHeart(heartMyPlane);
					}
					mMyPlane.checkHealth();
					if (mMyPlane.getHeart() == 1 && mMyPlane.getBlood() < 15) {
						this.mSoundLibrary.soundDangerous();
					}
					if (mMyPlane.getStatus() == MyPlane.PLANE_IS_DIE) {
						this.mSoundLibrary.soundGameOver();
						int scoreOld = mHighScore.readScore();
						if (score > scoreOld) {
							mHighScore.wtiteScore(Integer.toString(score));
							JOptionPane.showMessageDialog(null,
									"YOU LOSE :( \nHigh Score : " + score);
						} else {
							JOptionPane
									.showMessageDialog(null, "YOU LOSE :( !");
						}

						System.exit(0);
					}
					initCompetitor();
					return;
				}
			}
		}
	}

	public void destroyItemOutScreen() {
		for (int i = 0; i < arrCompetitors.size(); i++) {
			if (arrCompetitors.get(i) instanceof Items) {
				if (arrCompetitors.get(i).getY() > GUI.HEIGHT_FRAME) {
					arrCompetitors.remove(i);
					i--;
					initCompetitor();
					break;
				}
			}
		}
	}

	public ArrayList<Integer> getArrTypes() {
		return arrTypes;
	}

	public void setArrTypes(ArrayList<Integer> arrTypes) {
		this.arrTypes = arrTypes;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Competitor> getArrCompetitors() {
		return arrCompetitors;
	}

	public void setArrCompetitors(ArrayList<Competitor> arrCompetitors) {
		this.arrCompetitors = arrCompetitors;
	}

	public ArrayList<DestroyItemUpSky> getPairsDestroybByMyPlane() {
		return pairsDestroybByMyPlane;
	}

}
