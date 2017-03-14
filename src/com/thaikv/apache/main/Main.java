package com.thaikv.apache.main;

import com.thaikv.apache.menu.GUI;

public class Main {
    /**
     * First run when the program runs.
     *
     * @param args Not used.
     * @return The main function returns no value.
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
        // show screen.
        gui.setVisible(true);
    }
}
