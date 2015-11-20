package com.thaikv.apache.gameplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HighScore {
	private String score;
	private File mFile;

	public boolean isExist() {
		if (mFile.exists() == true) {
			return true;
		}
		return false;
	}

	public HighScore() {
		mFile = new File("D:\\HighCore.txt");
		if (!isExist()) {
			try {
				mFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void wtiteScore(String score) {
		try {
			FileOutputStream outputStream = new FileOutputStream(mFile);
			byte[] b = score.getBytes();
			try {
				outputStream.write(b);
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int readScore() {
		try {
			FileInputStream fileInputStream = new FileInputStream(mFile);
			int viTri;
			String str = "";
			viTri = fileInputStream.read();
			while (viTri != -1) {
				str += (char) viTri;
				viTri = fileInputStream.read();
			}
			score = str;
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (score.equals("")) {
			return 0;
		}
		return (Integer.valueOf(score));
	}
}
