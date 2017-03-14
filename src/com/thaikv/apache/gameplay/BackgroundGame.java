package com.thaikv.apache.gameplay;

import com.thaikv.apache.menu.GUI;

import javax.swing.*;
import java.awt.*;

public class BackgroundGame extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image imgBackground;
    private int xSand, ySand, xTent, yLeu, xHut, yHut, xHouse, yHouse, xCloud1,
            yCloud1, xCloud2, yCloud2, xCloud3, yCloud3;
    private Image imgSand, imgHut, imgTent, imgHouse, imgCloud1, imgCloud2,
            imgCloud3;

    public BackgroundGame() {
        initBackgroundGame();
    }

    private void initBackgroundGame() {
        imgBackground = new ImageIcon(getClass().getResource(
                "/imageBackground/background.png")).getImage();
        imgSand = new ImageIcon(getClass().getResource(
                "/imageBackground/imgSand.png")).getImage();
        imgTent = new ImageIcon(getClass().getResource(
                "/imageBackground/imgTent.png")).getImage();
        imgHut = new ImageIcon(getClass().getResource(
                "/imageBackground/imgHut.png")).getImage();
        imgHouse = new ImageIcon(getClass().getResource(
                "/imageBackground/imgHouse.png")).getImage();

        imgCloud1 = new ImageIcon(getClass().getResource(
                "/imageBackground/imgCloud1.png")).getImage();

        imgCloud2 = new ImageIcon(getClass().getResource(
                "/imageBackground/imgCloud2.png")).getImage();

        imgCloud3 = new ImageIcon(getClass().getResource(
                "/imageBackground/imgCloud3.png")).getImage();

        xSand = 0;
        ySand = GUI.HEIGHT_FRAME - imgSand.getHeight(null) - 20;
        xTent = 1500;
        yLeu = GUI.HEIGHT_FRAME - imgTent.getHeight(null) - 70;
        xHut = 2000;
        yHut = GUI.HEIGHT_FRAME - imgHut.getHeight(null) - 70;
        xHouse = 3000;
        yHouse = GUI.HEIGHT_FRAME - imgHouse.getHeight(null) - 70;

        xCloud1 = 1200;
        yCloud1 = 100;
        xCloud2 = 1500;
        yCloud2 = 150;
        xCloud3 = 2000;
        yCloud3 = 70;
    }

    public void drawBackground(Graphics2D g) {
        g.drawImage(imgBackground, 0, 0, null);
        g.drawImage(imgSand, xSand, ySand, null);
        g.drawImage(imgTent, xTent, yLeu, null);
        g.drawImage(imgHut, xHut, yHut, null);
        g.drawImage(imgHouse, xHouse, yHouse, null);
        g.drawImage(imgCloud1, xCloud1, yCloud1, null);
        g.drawImage(imgCloud2, xCloud2, yCloud2, null);
        g.drawImage(imgCloud3, xCloud3, yCloud3, null);
    }

    /**
     * Make the Sand run from right to left of the screen.
     */
    public void moveSand() {
        xSand--;
        if (xSand == -GUI.WIDTH_FRAME) {
            xSand = -50;
        }
    }

    /**
     * Make the Tent run from right to left of the screen
     */
    public void moveTent() {
        xTent--;
        if (xTent == -GUI.WIDTH_FRAME) {
            xTent = 1500;
        }
    }

    /**
     * Make the Hut run from right to left of the screen
     */
    public void moveHut() {
        xHut--;
        if (xHut == -GUI.WIDTH_FRAME) {
            xHut = 2000;
        }
    }

    /**
     * Make the House run from right to left of the screen
     */
    public void moveHouse() {
        xHouse--;
        if (xHouse == -GUI.WIDTH_FRAME) {
            xHouse = 3000;
        }
    }

    /**
     * Make the Cloud run from right to left of the screen
     *
     * @param count It used to adjust the movement speed of cloud.
     */
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

    /**
     * Make the Background run from right to left of the screen
     */
    public void moveAllBackground(int count) {
        moveSand();
        moveTent();
        moveHut();
        moveHouse();
        moveCloud(count);
    }

}
