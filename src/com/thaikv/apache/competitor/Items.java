package com.thaikv.apache.competitor;

import com.thaikv.apache.gameplay.BulletManager;

import javax.swing.*;

public class Items extends Competitor {
    public static final int ITEM_NORMAL_X2 = 2;
    public static final int ITEM_NORMAL_X3 = 3;
    public static final int ITEM_ROCKET_SMALL = 4;
    public static final int ITEM_ROCKET_BIG = 5;
    public static final int ITEM_BOMB = 6;
    public static final int ITEM_AMBULANCE = 7;

    private int typeItem, indexImageItem;

    /**
     * Initializes the Items object.
     *
     * @param x                        Place the Items in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the Items in the Oy.
     * @param blood                    Blood of Items.
     * @param speed                    Movement speed of Items.
     * @param mBulletManagerCompetitor Object management bullet of Items.
     * @param type                     Type of items.
     */
    public Items(int x, int y, int blood, int speed,
                 BulletManager mBulletManagerCompetitor, int type) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        this.typeItem = type;
        initItems();
    }

    private void initItems() {
        indexImageItem = 1;
        switch (this.typeItem) {
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

    /**
     * Make change image of Item.
     */
    public void changeImageItem() {
        switch (this.typeItem) {
            case ITEM_NORMAL_X2:
                this.setImg(new ImageIcon(getClass().getResource(
                        "/imageItems/imgNormalX2_" + indexImageItem + ".png")).getImage());
                break;
            case ITEM_NORMAL_X3:
                this.setImg(new ImageIcon(getClass().getResource(
                        "/imageItems/imgNormalX3_" + indexImageItem + ".png")).getImage());
                break;
            case ITEM_ROCKET_SMALL:
                this.setImg(new ImageIcon(getClass().getResource(
                        "/imageItems/imgRocketSmall_" + indexImageItem + ".png")).getImage());
                break;
            case ITEM_ROCKET_BIG:
                this.setImg(new ImageIcon(getClass().getResource(
                        "/imageItems/imgRocketBig_" + indexImageItem + ".png")).getImage());
                break;
            case ITEM_BOMB:
                this.setImg(new ImageIcon(getClass().getResource(
                        "/imageItems/imgBomb_" + indexImageItem + ".png")).getImage());
                break;
            case ITEM_AMBULANCE:
                this.setImg(new ImageIcon(getClass().getResource(
                        "/imageItems/imgAmbulance_" + indexImageItem + ".png")).getImage());
                break;
            default:
                break;
        }

    }

    /**
     * Handles Items move.
     *
     * @param count Adjust the movement speed of Items.
     */
    @Override
    public void move(int count) {
        if (count % this.getSpeed() == 0) {
            int x, y;
            x = this.getX();
            y = this.getY();
            x -= 2;
            y += 10;
            indexImageItem++;
            if (indexImageItem > 3) {
                indexImageItem = 1;
            }
            changeImageItem();
            this.setX(x);
            this.setY(y);
        }
    }

    public int getType() {
        return typeItem;
    }

    public void setType(int type) {
        this.typeItem = type;
    }

}
