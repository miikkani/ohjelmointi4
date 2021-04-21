package vtracker;

import javax.swing.*;

/**
 * ValorantTracker. Epic Application #1.
 * This is the main class for application.
 *
 */
public class ValorantTracker extends JFrame {
     ValorantTracker() {
         super("ValorantTracker");

         this.setSize(400,150);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         JLabel test = new JLabel("Win % 100!");

         this.add(test);

         this.setVisible(true);

         test.setText(String.valueOf(SwingUtilities.isEventDispatchThread()));


    }





    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 new ValorantTracker();
             }
         });

    }
}