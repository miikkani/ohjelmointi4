package vtracker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
//import java.util.Hashtable;
import java.text.NumberFormat;

import vtracker.data.*;
import vtracker.ui.*;

/**
 * ValorantTracker. Epic Application #1.
 * This is the main class for application.
 *
 */
public class ValorantTracker extends JFrame {
    String[] agents;
    VtrackerDatabase db;
    StatsBuilder stats;

     ValorantTracker() {
         super("ValorantTracker");

         // initialize data and handling
         // agents, db and stats
         initializeData();

         //Get matches from database
         ArrayList<Match> matches = new ArrayList<>();
         try {
             matches = db.getMatches();
         } catch (Exception e) {e.printStackTrace();}

         // build interface
         this.setSize(460,460);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


         // Overall Win Percentage Panel
         int owinp = 52; //Replace with a method that fetches %



         //Add label to the panel
         JLabel owinlabel1 = new JLabel();
         owinlabel1.setText("Overall Win Percentage:");
         owinlabel1.setHorizontalAlignment(JLabel.CENTER);
         owinlabel1.setBorder(BorderFactory.createEmptyBorder(5,20,0,20));
         owinlabel1.setFont(owinlabel1.getFont().deriveFont(30.0f));
         JLabel owinlabel2 = new JLabel();
         owinlabel2.setText(owinp + "%");
         owinlabel2.setHorizontalAlignment(JLabel.CENTER);
         owinlabel2.setBorder(BorderFactory.createEmptyBorder(0,20,5,20));
         owinlabel2.setFont(owinlabel2.getFont().deriveFont(30.0f));

         JPanel owinpanel = new JPanel();
         owinpanel.setBackground(Color.LIGHT_GRAY);
         owinpanel.setLayout(new GridLayout(2,1));
         owinpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
         owinpanel.add(owinlabel1); owinpanel.add(owinlabel2);


         // Build table for agent win percentages
         String[] column ={"Agent","Win Percentage"};
         DefaultTableModel model = new DefaultTableModel(getStats(matches), column);
         JTable agentwinptable = new JTable(model){
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
         addbutton.setPreferredSize(new Dimension(110,35));
         AddMatchDialog addmatchdialog = new AddMatchDialog(this,"Add Match", agents, db);
         addbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 addmatchdialog.setVisible(true);

             }
         });

         addmatchdialog.addPropertyChangeListener(new PropertyChangeListener() {
             @Override
             public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                 try {
                     agentwinptable.setModel(new DefaultTableModel(getStats(db.getMatches()), column));
                 } catch (Exception e) {
                     e.printStackTrace();
                 }

                 //DEBUG!
                 System.out.println(propertyChangeEvent.getPropertyName());


                 }
             }
         );

         JButton delbutton = new JButton("Delete Latest");
         delbutton.setPreferredSize(new Dimension(110,35));
         DeleteLatestDialog deletelatestdialog = new DeleteLatestDialog(this, "Delete Latest");
         delbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 deletelatestdialog.setVisible(true);
             }
         });

         JButton delallbutton = new JButton("Delete All");
         delallbutton.setPreferredSize(new Dimension(110,35));
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

    /**
     * THIS IS TEMPORARY METHOD AND WILL BE DELETED!
     *
     * @param matches list of matches
     * @return two dimensional array of agents and their winp
     */
    public String[][] getStats(ArrayList<Match> matches) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);

        int rows = matches.size();
        String[][] data = new String[matches.size()][2];

        for(int i=0;i < rows; i++){
             data[i][0] = matches.get(i).getAgent();
             double winp = Math.random();
             data[i][1] = nf.format(winp);
        }
        return data;
    }

    /**
     * initialize datastructures and database operations
     */
    private void initializeData() {
        agents = getAgents();
        db = TextDatabase.getInstance("db.txt");
        stats = new StatsBuilder(db, agents);

    }


    /**
     * Returns a reference list of all agents in Valorant.
     *
     * @return agents      an array of agent names
     */
    private String[] getAgents() {

        return new String[]{
                        "Astra",
                        "Breach",
                        "Brimstone",
                        "Cypher",
                        "Jett",
                        "Killjoy",
                        "Omen",
                        "Phoenix",
                        "Raze",
                        "Reyna",
                        "Sage",
                        "Skye",
                        "Sova",
                        "Viper",
                        "Yoru"
        };
    }

    /**
     * ValorantTracker. Starts application, builds UI and initializes database.
     *
     * @param args          array of command line arguments
     */
    public static void main(String[] args) {
         SwingUtilities.invokeLater(ValorantTracker::new);

    }
}