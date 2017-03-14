package com.thaikv.apache.gameplay;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundManager {

	public static AudioClip START_GAME;
	public static AudioClip FLY;
	public static AudioClip MUSIC_BACKGROUND;
	public static AudioClip LOAD_BULLET;
	public static AudioClip NEW_HEART;
	public static AudioClip BULLET_BOMB;
	public static AudioClip BULLET_RK1;
	public static AudioClip BULLET_RK2;
	public static AudioClip BULLET_NORMAL_X;
	public static AudioClip MUSIC_BOOM;
	public static AudioClip BULLET_NORMAL;
	public static AudioClip DANGEROUS;
	public static AudioClip GAMVE_OVER;
	public static AudioClip COMPETITORS;

	public void setSound(boolean logic) {
		if (logic) {
			START_GAME = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_start_game.wav"));
			FLY = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_flying.wav"));
			MUSIC_BACKGROUND = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_background.wav"));
			LOAD_BULLET = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_add_ammunition.wav"));
			NEW_HEART = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_unbloody.wav"));
			BULLET_BOMB = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_bomb.wav"));
			BULLET_RK1 = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_no_rocket_1.wav"));
			BULLET_RK2 = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_no_rocket_2.wav"));
			BULLET_NORMAL_X = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_gun_1.wav"));
			MUSIC_BOOM = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_no.wav"));
			BULLET_NORMAL = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_rocket.wav"));
			DANGEROUS = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_danger.wav"));
			GAMVE_OVER = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_game_over.wav"));
			COMPETITORS = Applet.newAudioClip(getClass().getResource(
					"/sounds/sound_enemy_came.wav"));
		} else {
			START_GAME = null;
			FLY = null;
			MUSIC_BACKGROUND = null;
			LOAD_BULLET = null;
			NEW_HEART = null;
			BULLET_BOMB = null;
			BULLET_NORMAL_X = null;
			BULLET_RK1 = null;
			BULLET_RK2 = null;
			MUSIC_BOOM = null;
			BULLET_NORMAL = null;
			DANGEROUS = null;
			GAMVE_OVER = null;
			COMPETITORS = null;
		}
	}

	public void soundStartGame() {
		if (START_GAME != null) {
			START_GAME.loop();
			START_GAME.play();
		}
	}

	public void stopSoundStartGame() {
		START_GAME.stop();
		START_GAME = null;
	}

	public void soundFly() {
		if (FLY != null) {
			FLY.loop();
			FLY.play();
		}
	}

	public void stopSoundFly() {
		FLY.stop();
		FLY = null;
	}

	public void soundBackground() {
		if (MUSIC_BACKGROUND != null) {
			MUSIC_BACKGROUND.play();
		}
	}

	public void stopSoundBackground() {
		MUSIC_BACKGROUND.stop();
		MUSIC_BACKGROUND = null;
	}

	public void soundLoadBullet() {
		if (LOAD_BULLET != null) {
			LOAD_BULLET.play();
		}
	}

	public void stopSoundLoadBullet() {
		LOAD_BULLET.stop();
		LOAD_BULLET = null;
	}

	public void soundNewHeart() {
		if (NEW_HEART != null) {
			NEW_HEART.play();
		}
	}

	public void stopSoundNewHeart() {
		NEW_HEART.stop();
		NEW_HEART = null;
	}

	public void soundOfBomb() {
		if (BULLET_BOMB != null) {
			BULLET_BOMB.play();
		}
	}

	public void sopSoundOfBomb() {
		BULLET_BOMB.stop();
		BULLET_BOMB = null;
	}

	public void soundOfRocket1() {
		if (BULLET_RK1 != null) {
			BULLET_RK1.play();
		}
	}

	public void stopSoundOfRk1() {
		BULLET_RK1.stop();
		BULLET_RK1 = null;
	}

	public void soundOfRocket2() {
		if (BULLET_RK2 != null) {
			BULLET_RK2.play();
		}
	}

	public void stopsoundRk2() {
		BULLET_RK2.stop();
		BULLET_RK2 = null;
	}

	public void soundBoom() {
		if (MUSIC_BOOM != null) {
			MUSIC_BOOM.play();
		}
	}

	public void stopSoundBoom() {
		MUSIC_BOOM.stop();
		MUSIC_BOOM = null;
	}

	public void soundOfNormalX() {
		if (BULLET_NORMAL_X != null) {
			BULLET_NORMAL_X.play();
		}
	}

	public void stopSoundOFNormalX() {
		BULLET_NORMAL_X.stop();
		BULLET_NORMAL_X = null;

	}

	public void soundOfNormal() {
		if (BULLET_NORMAL != null) {
			BULLET_NORMAL.play();
		}
	}

	public void stopSoundOfNormal() {
		BULLET_NORMAL.stop();
		BULLET_NORMAL = null;
	}

	public void soundDangerous() {
		if (DANGEROUS != null) {
			DANGEROUS.play();
		}
	}

	public void stopSoundDangerous() {
		DANGEROUS.stop();
		DANGEROUS = null;
	}

	public void soundGameOver() {
		if (GAMVE_OVER != null) {
			GAMVE_OVER.play();
		}
	}

	public void stopSoundGameOver() {
		GAMVE_OVER.stop();
		GAMVE_OVER = null;
	}

	public void soundCompetitors() {
		if (COMPETITORS != null) {
			COMPETITORS.play();
		}
	}

	public void stopSounCompetitors() {
		COMPETITORS.stop();
		COMPETITORS = null;
	}

}