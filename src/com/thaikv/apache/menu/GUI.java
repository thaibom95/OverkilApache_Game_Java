package com.thaikv.apache.menu;

import com.thaikv.apache.gameplay.HighScore;
import com.thaikv.apache.gameplay.PlayGame;
import com.thaikv.apache.gameplay.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH_FRAME = 1200;
    public static final int HEIGHT_FRAME = 700;

    public static boolean IS_RUNNING;
    private PlayGame mPlayGame;
    private OutsideMenu mOutsideMenu;
    private SplashGame mLoadingGame;
    private SoundManager mSoundManager;

    public GUI() {
        initUI();
    }

    /**
     * Set up the interface frame.
     */
    private void initUI() {
        IS_RUNNING = true;
        setLayout(new CardLayout());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Overkill Apache");

        mSoundManager = new SoundManager();
        mSoundManager.setSound(true);

        mOutsideMenu = new OutsideMenu();
        mSoundManager.soundStartGame();
        this.add(mOutsideMenu);
        setEventMouse();
    }

    /**
     * Set up event listens for mouse.
     */
    public void setEventMouse() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                IS_RUNNING = false;
            }
        });

        mOutsideMenu.getLbPlay().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mOutsideMenu.setVisible(false);
                mLoadingGame = new SplashGame();
                mLoadingGame.runProgressing();
                GUI.this.add(mLoadingGame);
                mLoadingGame.setVisible(true);
                mSoundManager.stopSoundStartGame();
                mSoundManager.soundBackground();
            }
        });

        mOutsideMenu.getLbInstructions().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Use the Up, Down, Left, Right keys to move your plane " +
                                        "and use Space key to shoot.\nTry to eat the items.\nGoodluck !  :) ",
                                "Instructions", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mOutsideMenu.getLbInfor().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Version : 1.0\nOuthor : Kieu Van Thai - K58CD_UET_VNU\nSchool : University of Engineering and Technology" +
                                        " - Vietnam National University(Hanoi)",
                                "Informations", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mOutsideMenu.getLbHighScore().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HighScore mHighScore = new HighScore();
                int score = mHighScore.readScore();
                JOptionPane.showMessageDialog(null, "High Score : " + score,
                        "High Score", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    /**
     * The class SplashGame creates the effect of loading data into the game.
     */
    private class SplashGame extends JPanel {
        private static final long serialVersionUID = 1L;
        private int value;
        private JProgressBar loadingBar;
        private JLabel lbBackground;
        private JLabel lbPercent;

        public SplashGame() {
            initStartGame();
        }

        /**
         * Set up interface load data into the game.
         */
        private void initStartGame() {
            setLayout(null);
            lbBackground = new JLabel();
            lbBackground.setBounds(0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME);
            lbBackground.setIcon(new ImageIcon(getClass().getResource(
                    "/imageBackground/imgBackgroundLoading.png")));
            this.add(lbBackground);

            UIManager.put("ProgressBar.background", Color.WHITE);
            UIManager.put("ProgressBar.foreground", Color.GREEN);

            loadingBar = new JProgressBar(0, 100);
            loadingBar.setBounds(300, 600, 600, 30);
            lbBackground.add(loadingBar);

            lbPercent = new JLabel("0 %", SwingConstants.CENTER);
            lbPercent.setBounds(0, 0, 600, 30);
            loadingBar.add(lbPercent);
        }

        /**
         * Run the progress bar.
         */
        public void runProgressing() {
            value = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (value < 100) {
                        value++;
                        loadingBar.setValue(value);
                        lbPercent.setText(String.valueOf(value + " %"));
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SplashGame.this.setVisible(false);
                    mPlayGame = new PlayGame(mSoundManager);
                    GUI.this.add(mPlayGame);
                    mPlayGame.setVisible(true);
                    mPlayGame.requestFocusInWindow();
                }
            }).start();
        }
    }


}
