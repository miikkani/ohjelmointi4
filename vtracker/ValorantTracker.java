package vtracker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vtracker.ui.*;

/**
 * ValorantTracker. Epic Application #1.
 * This is the main class for application.
 *
 */
public class ValorantTracker extends JFrame {
     ValorantTracker() {
         super("ValorantTracker");

         this.setSize(460,460);
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



         /** Overall Win Percentage Panel **/
         int owinp = 52; //Replace with a method that fetches %

         JPanel owinpanel = new JPanel();
         owinpanel.setBackground(Color.GRAY);

         //Add label to the panel
         JLabel owinlabel = new JLabel();
         owinlabel.setText("Overall Win Percentage: " + owinp + "%");
         owinlabel.setFont(owinlabel.getFont().deriveFont(30.0f));
         owinpanel.add(owinlabel);


         /** Agent Win Percentage Table **/
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

         //Make the table uninteractive
         JTable agentwinptable = new JTable(data,column){
             @Override
             public boolean isCellEditable(int row, int column) { //Override to make cells not editable
                 return false;
             }
         };
         agentwinptable.setFocusable(false);
         agentwinptable.setRowSelectionAllowed(false);

         //Headers start from left
         DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) agentwinptable.getTableHeader().getDefaultRenderer();
         renderer.setHorizontalAlignment(JLabel.LEFT);

         //Add the table to scrollpane
         JScrollPane sp = new JScrollPane(agentwinptable);
         sp.setPreferredSize((new Dimension(300, 263)));


         /** Buttons **/
         JButton addbutton = new JButton("Add Match");
         AddMatchDialog addmatchdialog = new AddMatchDialog(this,"Add Match");
         addbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 addmatchdialog.setVisible(true);
             }
         });

         JButton delbutton = new JButton("Delete Latest");
         DeleteLatestDialog deletelatestdialog = new DeleteLatestDialog(this, "Delete Latest");
         delbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 deletelatestdialog.setVisible(true);
             }
         });

         JButton delallbutton = new JButton("Delete All");
         DeleteAllDialog deletealldialog = new DeleteAllDialog(this, "Delete All");
         delallbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                deletealldialog.setVisible(true);
             }
         });


         /** GridBagLayout **/
         GridBagLayout layout = new GridBagLayout();
         GridBagConstraints gbc = new GridBagConstraints();
         this.setLayout(layout);

         gbc.fill = GridBagConstraints.HORIZONTAL;
         gbc.insets = new Insets(5,5,5,5);
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.gridwidth = 4;
         this.add(owinpanel,gbc); //Overall Win Percentage panel added
         gbc.gridx = 0;
         gbc.gridy = 1;
         this.add(sp,gbc); //Agent Win Percentage panel added
         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.gridwidth = 1;
         gbc.fill = GridBagConstraints.NONE;
         gbc.anchor = GridBagConstraints.WEST;
         this.add(addbutton, gbc); //Add Match button added
         gbc.gridx = 1;
         gbc.gridy = 2;
         this.add(delbutton, gbc); //Delete Latest button added
         gbc.gridx = 3;
         gbc.gridy = 2;
         gbc.anchor = GridBagConstraints.EAST;
         this.add(delallbutton, gbc); //Delete All button added

         this.setLocationRelativeTo(null);
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