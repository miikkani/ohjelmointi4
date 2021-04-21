import javax.swing.*;

/**
 * ValorantTracker. Epic Application #1.
 * This is the main class for application.
 *
 */
public class ValorantTracker {
     ValorantTracker() {

         JFrame jfrm  = new JFrame("ValorantTracker");
         jfrm.setSize(400,150);
         jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         JLabel test = new JLabel("Win % 100!");

         jfrm.add(test);

         jfrm.setVisible(true);

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