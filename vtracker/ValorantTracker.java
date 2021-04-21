package vtracker;

import javax.swing.*;

import vtracker.data.*;

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


        AbstractDatabase db = new TestDatabase();
        Match m = db.getMatch(1);

         JLabel test = new JLabel(String.valueOf(m));

         this.add(test);

         this.setVisible(true);


    }





    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 new ValorantTracker();
             }
         });

    }
}