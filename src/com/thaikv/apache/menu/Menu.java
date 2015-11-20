package com.thaikv.apache.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lbBackground, lbPlay, lbInfor, lbInstructions, lbHighScore,
			lbExit;

	public Menu() {
		setLayout(null);
		initComps();
		eventMouse();
	}

	public void initComps() {
		lbBackground = new JLabel();
		lbBackground.setBounds(0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME);
		lbBackground.setIcon(new ImageIcon(getClass().getResource(
				"/imageMenu/imgBg.png")));
		this.add(lbBackground);

		lbPlay = new JLabel();
		lbPlay.setBounds(500, 410, 224, 34);
		lbPlay.setIcon(new ImageIcon(getClass().getResource(
				"/imageMenu/imgPlaygame.png")));
		lbBackground.add(lbPlay);

		lbInstructions = new JLabel();
		lbInstructions.setBounds(500, 460, 224, 34);
		lbInstructions.setIcon(new ImageIcon(getClass().getResource(
				"/imageMenu/imgInstructions.png")));
		lbBackground.add(lbInstructions);

		lbInfor = new JLabel();
		lbInfor.setBounds(500, 510, 224, 34);
		lbInfor.setIcon(new ImageIcon(getClass().getResource(
				"/imageMenu/imgInfor.png")));
		lbBackground.add(lbInfor);

		lbHighScore = new JLabel();
		lbHighScore.setBounds(500, 560, 224, 34);
		lbHighScore.setIcon(new ImageIcon(getClass().getResource(
				"/imageMenu/imgHighScore.png")));
		lbBackground.add(lbHighScore);

		lbExit = new JLabel();
		lbExit.setBounds(500, 610, 224, 34);
		lbExit.setIcon(new ImageIcon(getClass().getResource(
				"/imageMenu/imgExit.png")));
		lbBackground.add(lbExit);
	}

	public void eventMouse() {
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
