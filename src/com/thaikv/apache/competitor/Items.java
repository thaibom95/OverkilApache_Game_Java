package com.thaikv.apache.competitor;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;

public class Items extends Competitor {
	public static final int ITEM_NORMAL_X2 = 2;
	public static final int ITEM_NORMAL_X3 = 3;
	public static final int ITEM_ROCKET_SMALL = 4;
	public static final int ITEM_ROCKET_BIG = 5;
	public static final int ITEM_BOMB = 6;
	public static final int ITEM_AMBULANCE = 7;

	private int type, i;

	public Items(int x, int y, int blood, int speed,
			BulletManager mBulletManagerCompetitor, int type) {
		super(x, y, blood, speed, mBulletManagerCompetitor);
		i = 1;
		this.type = type;
		switch (this.type) {
		case ITEM_NORMAL_X2:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgNormalX2_2.png")).getImage());
			break;
		case ITEM_NORMAL_X3:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgNormalX3_2.png")).getImage());
			break;
		case ITEM_ROCKET_SMALL:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgRocketSmall_2.png")).getImage());
			break;
		case ITEM_ROCKET_BIG:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgRocketBig_2.png")).getImage());
			break;
		case ITEM_BOMB:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgBomb_2.png")).getImage());
			break;
		case ITEM_AMBULANCE:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgAmbulance_2.png")).getImage());
			break;
		default:
			break;
		}
	}

	public void changeImg() {
		switch (this.type) {
		case ITEM_NORMAL_X2:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgNormalX2_" + i + ".png")).getImage());
			break;
		case ITEM_NORMAL_X3:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgNormalX3_" + i + ".png")).getImage());
			break;
		case ITEM_ROCKET_SMALL:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgRocketSmall_" + i + ".png")).getImage());
			break;
		case ITEM_ROCKET_BIG:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgRocketBig_" + i + ".png")).getImage());
			break;
		case ITEM_BOMB:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgBomb_" + i + ".png")).getImage());
			break;
		case ITEM_AMBULANCE:
			this.setImg(new ImageIcon(getClass().getResource(
					"/imageItems/imgAmbulance_" + i + ".png")).getImage());
			break;
		default:
			break;
		}

	}

	@Override
	public void move(int count) {
		if (count % this.getSpeed() == 0) {
			int x, y;
			x = this.getX();
			y = this.getY();
			x -= 2;
			y += 10;
			i++;
			if (i > 3) {
				i = 1;
			}
			changeImg();
			this.setX(x);
			this.setY(y);
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
