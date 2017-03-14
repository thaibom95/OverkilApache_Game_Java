package com.thaikv.apache.menu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OutsideMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH_BUTTON = 225;
    public static final int HEIGHT_BUTTON = 35;
    public static final int LOCATION_X_BUTTON = 500;
    public static final int LOCATION_Y_BUTTON = 410;
    public static final int DISTANCE_Y_BUTTON = 50;

    private JLabel lbBackground, lbPlay, lbInfor, lbInstructions, lbHighScore, lbExit;

    public OutsideMenu() {
        initComponents();
        setEventMouse();
    }

    public void initComponents() {
        setLayout(null);
        lbBackground = new JLabel();
        lbBackground.setBounds(0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME);
        lbBackground.setIcon(new ImageIcon(getClass().getResource("/imageMenu/imgBg.png")));
        this.add(lbBackground);

        lbPlay = new JLabel();
        lbPlay.setBounds(LOCATION_X_BUTTON, LOCATION_Y_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        lbPlay.setIcon(new ImageIcon(getClass().getResource("/imageMenu/imgPlaygame.png")));
        lbBackground.add(lbPlay);

        lbInstructions = new JLabel();
        lbInstructions.setBounds(LOCATION_X_BUTTON, LOCATION_Y_BUTTON + DISTANCE_Y_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        lbInstructions.setIcon(new ImageIcon(getClass().getResource("/imageMenu/imgInstructions.png")));
        lbBackground.add(lbInstructions);

        lbInfor = new JLabel();
        lbInfor.setBounds(LOCATION_X_BUTTON, LOCATION_Y_BUTTON + 2 * DISTANCE_Y_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        lbInfor.setIcon(new ImageIcon(getClass().getResource("/imageMenu/imgInfor.png")));
        lbBackground.add(lbInfor);

        lbHighScore = new JLabel();
        lbHighScore.setBounds(LOCATION_X_BUTTON, LOCATION_Y_BUTTON + 3 * DISTANCE_Y_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        lbHighScore.setIcon(new ImageIcon(getClass().getResource("/imageMenu/imgHighScore.png")));
        lbBackground.add(lbHighScore);

        lbExit = new JLabel();
        lbExit.setBounds(LOCATION_X_BUTTON, LOCATION_Y_BUTTON + 4 * DISTANCE_Y_BUTTON, WIDTH_BUTTON, HEIGHT_BUTTON);
        lbExit.setIcon(new ImageIcon(getClass().getResource("/imageMenu/imgExit.png")));
        lbBackground.add(lbExit);
    }

    /**
     * Set up event listens for mouse.
     */
    public void setEventMouse() {
        // add mouse listener for button Play.
        lbPlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lbPlay.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgPlaygameEnter.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lbPlay.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgPlaygame.png")));
            }
        });

        // add mouse listener for button Instructioins.
        lbInstructions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lbInstructions.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgInstructionsEnter.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lbInstructions.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgInstructions.png")));
            }
        });

        // add mouse listener for button Informations.
        lbInfor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lbInfor.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgInforEnter.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lbInfor.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgInfor.png")));
            }
        });

        // add mouse listener for button High Score.
        lbHighScore.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lbHighScore.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgHighScoreEnter.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lbHighScore.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgHighScore.png")));
            }
        });

        // add mouse listener for button Exit.
        lbExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                lbExit.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgExitEnter.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                lbExit.setIcon(new ImageIcon(getClass().getResource(
                        "/imageMenu/imgExit.png")));
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                super.mouseClicked(arg0);
                System.exit(0);
            }
        });
    }

    public JLabel getLbPlay() {
        return lbPlay;
    }

    public JLabel getLbInfor() {
        return lbInfor;
    }

    public JLabel getLbInstructions() {
        return lbInstructions;
    }

    public JLabel getLbHighScore() {
        return lbHighScore;
    }

}
