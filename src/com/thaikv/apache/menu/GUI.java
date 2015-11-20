package com.thaikv.apache.menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.thaikv.apache.gameplay.HighScore;
import com.thaikv.apache.gameplay.PlayGame;
import com.thaikv.apache.gameplay.SoundLibrary;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH_FRAME = 1200;
	public static final int HEIGHT_FRAME = 700;
	private PlayGame mPlayGame;
	private Menu mMenu;
	private LoadingGame mLoadingGame;
	public static boolean IS_RUNNING;
	private SoundLibrary mSoundLibrary;

	public GUI() {
		IS_RUNNING = true;
		setLayout(new CardLayout());
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Overkill Apache");

		mSoundLibrary = new SoundLibrary();
		mSoundLibrary.setSound(true);

		mMenu = new Menu();
		mSoundLibrary.soundStartGame();
		this.add(mMenu);
		setEnvetMouse();
	}

	public void setEnvetMouse() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IS_RUNNING = false;
			}
		});

		mMenu.getLbPlay().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				mMenu.setVisible(false);
				mLoadingGame = new LoadingGame();
				GUI.this.add(mLoadingGame);
				mLoadingGame.setVisible(true);
				mSoundLibrary.stopSoundStartGame();
				mSoundLibrary.soundBackground();
			}
		});

		mMenu.getLbInstructions().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				JOptionPane
						.showMessageDialog(
								null,
								"Bạn dùng các phím  Lên, Xuống, Trái, Phải để di chuyển máy bay của mình và dùng phím Space để bắn.\nHãy cố gắng ăn các vật phẩm.\nGoodluck !  :) ",
								"Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		mMenu.getLbInfor().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				JOptionPane
						.showMessageDialog(
								null,
								"Version : 1\nOuthor : Kieu Van Thai - K58CD_UET_VNU\nClass : LAND1508E",
								"Informations", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		mMenu.getLbHighScore().addMouseListener(new MouseAdapter() {
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

	private class LoadingGame extends JPanel {
		private static final long serialVersionUID = 1L;
		private int value;
		private JProgressBar loadingBar;
		private JLabel lbBackground;
		private JLabel lbPercent;

		public LoadingGame() {
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
					LoadingGame.this.setVisible(false);
					mPlayGame = new PlayGame(mSoundLibrary);
					GUI.this.add(mPlayGame);
					mPlayGame.setVisible(true);
					mPlayGame.requestFocusInWindow();
				}
			}).start();
		}
	}

}
