package com.thaikv.apache.gameplay;

import java.io.*;

public class HighScore {
    private String score;
    private File mFile;

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

    /**
     * Check the existence of the file.
     *
     * @return Return true if the file already exits else return false.
     */
    public boolean isExist() {
        if (mFile.exists() == true) {
            return true;
        }
        return false;
    }

    /**
     * Write score to the HighCore.txt file.
     *
     * @param score Scores are recorded in the HighCore.txt file.
     */
    public void writeScore(String score) {
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

    /**
     * Read score from the HighCore.txt file.
     *
     * @return Return score.
     */
    public int readScore() {
        try {
            FileInputStream fileInputStream = new FileInputStream(mFile);
            int index;
            String str = "";
            index = fileInputStream.read();
            while (index != -1) {
                str += (char) index;
                index = fileInputStream.read();
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
