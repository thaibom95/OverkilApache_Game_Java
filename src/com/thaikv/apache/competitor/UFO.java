package com.thaikv.apache.competitor;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.thaikv.apache.gameplay.BulletManager;
import com.thaikv.apache.menu.GUI;

public class UFO extends Competitor {
    private Image imgUfoJet;
    private int indexImageUFO;

    /**
     * Initializes the UFO object.
     *
     * @param x                        Place the UFO in the Ox (Point O is the top left conner of the frame, Ox is from left to right).
     * @param y                        Place the UFO in the Oy.
     * @param blood                    Blood of UFO.
     * @param speed                    Movement speed of UFO.
     * @param mBulletManagerCompetitor Object management bullet of UFO.
     */
    public UFO(int x, int y, int blood, int speed,
               BulletManager mBulletManagerCompetitor) {
        super(x, y, blood, speed, mBulletManagerCompetitor);
        initUFO();
    }

    private void initUFO() {
        indexImageUFO = 1;
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

    /**
     * Make change image of UFO.
     */
    public void changeImageUFO() {
        this.imgUfoJet = new ImageIcon(getClass().getResource(
                "/imageCompetitor/imgUfoJet" + indexImageUFO + ".png")).getImage();
    }

    /**
     * Handles UFO move.
     *
     * @param count Adjust the movement speed of UFO.
     */
    @Override
    public void move(int count) {
        if (count % this.getSpeed() == 0) {
            int x = this.getX();
            x--;
            if (x < 0) {
                x = GUI.WIDTH_FRAME + 100;
            }
            this.setX(x);
            indexImageUFO++;
            if (indexImageUFO > 2) {
                indexImageUFO = 1;
            }
            changeImageUFO();
        }
    }

}
