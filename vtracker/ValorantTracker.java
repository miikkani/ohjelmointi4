package vtracker;

import javax.swing.*;
import java.awt.*;

import vtracker.data.*;

/**
 * ValorantTracker. Epic Application #1.
 * This is the main class for application.
 *
 */
public class ValorantTracker extends JFrame {
     ValorantTracker() {
         super("ValorantTracker");

         this.setSize(560,600);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         /*********************************
          *********************************
          * Miikka's testing
          *
            // 'connect' to database and get the match
            AbstractDatabase db = new TestDatabase();
            Match m = db.getMatch(1);

          *********************************
          *********************************/



         //Overall Win Percentage Panel
         int owinp = 52; //Replace with a method that fetches %

         JPanel owinpanel = new JPanel();
         owinpanel.setPreferredSize(new Dimension(520,120));
         owinpanel.setBackground(Color.GRAY);

         JLabel owinlabel = new JLabel();
         owinlabel.setText("Overall Win Percentage: " + owinp + "%");
         owinlabel.setFont(owinlabel.getFont().deriveFont(40.0f));
         owinpanel.add(owinlabel);

         //Agent Win Percentage Panel
         JPanel awinpanel = new JPanel();
         awinpanel.setPreferredSize(new Dimension(520, 320));

         int astrawinp = 52; //Replace with a method that fetches agent win %'s
         String data[][]={{"Astra",astrawinp + "%"},
                 {"Breach",53 + "%"},
                 {"Brimstone", 54 + "%"},
                 {"Cypher", 51 + "%"},
                 {"Jett", 45 + "%"},
                 {"Killjoy", 49 + "%"},
                 {"Omen", 53 + "%"},
                 {"Phoenix", 55 + "%"},
                 {"Raze", 46 + "%"},
                 {"Reyna", 51 + "%"},
                 {"Sage", 52 + "%"},
                 {"Skye", 44 + "%"},
                 {"Sova", 58 + "%"},
                 {"Viper", 45 + "%"},
                 {"Yoru", 41 + "%"}};
         String column[]={"Agent","Win Percentage"};
         JTable agentwinptable = new JTable(data,column);
         agentwinptable.setFocusable(false);
         agentwinptable.setRowSelectionAllowed(false);
         JScrollPane sp = new JScrollPane(agentwinptable);

         //Adding elemenets
         this.setLayout(new GridLayout(3,1));
         this.add(owinpanel);
         this.add(sp);
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