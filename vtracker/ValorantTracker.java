package vtracker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Hashtable;

import vtracker.data.*;
import vtracker.ui.*;

/**
 * ValorantTracker is an application for
 * following personal win percentages of agents in Valorant game. User
 * can save matches to database and application shows overall win percentage
 * and agent specific win percentages.
 * <p>
 * Every match has name of the agent
 * used in match, result of the match and a timestamp when match was added
 * to the database. Timestamps are not currently in use and are implemented for
 * use in possible future versions.
 *
 */
public class ValorantTracker extends JFrame {
    String[] agents;
    VtrackerDatabase db;
    StatsBuilder stats;

    /**
     * Constructor for ValorantTracker. Connects to database and builds
     * user interface.
     */
     ValorantTracker() {
         super("Valorant Tracker");

         /* setup data structures and operations */
         initializeData();

         /* Overall win percentage panel */
         JLabel owinlabel1 = new JLabel();
         owinlabel1.setText("Overall Win Percentage:");
         owinlabel1.setHorizontalAlignment(JLabel.CENTER);
         owinlabel1.setBorder(BorderFactory.createEmptyBorder(5,20,0,20));
         owinlabel1.setFont(owinlabel1.getFont().deriveFont(30.0f));

         JLabel owinlabel2 = new JLabel();
         owinlabel2.setText(formatOverallWinp());
         owinlabel2.setHorizontalAlignment(JLabel.CENTER);
         owinlabel2.setBorder(BorderFactory.createEmptyBorder(0,20,5,20));
         owinlabel2.setFont(owinlabel2.getFont().deriveFont(30.0f));

         JPanel owinpanel = new JPanel();
         owinpanel.setBackground(Color.LIGHT_GRAY);
         owinpanel.setLayout(new GridLayout(2,1));
         owinpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
         owinpanel.add(owinlabel1); owinpanel.add(owinlabel2);


         /* Agent win percentage table */
         String[] column ={"Agent:","Win Percentage:"};
         DefaultTableModel model = new DefaultTableModel(formatStats(stats.getStats()), column);

         /* Override to make cells not editable */
         JTable agentwinptable = new JTable(model){
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
         };

         /* Paint every second row gray */
         agentwinptable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
             @Override
             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                 final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                 c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                 return this;
             }
         });

         /* Make table not interactive */
         agentwinptable.getTableHeader().setReorderingAllowed(false);
         agentwinptable.getTableHeader().setResizingAllowed(false);
         agentwinptable.setFocusable(false);

         /* Remove horizontal lines */
         agentwinptable.setShowHorizontalLines(false);
         agentwinptable.setRowSelectionAllowed(false);

         /* Headers start from left */
         DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) agentwinptable.getTableHeader().getDefaultRenderer();
         renderer.setHorizontalAlignment(JLabel.LEFT);

         /* Add the table to scrollpane */
         JScrollPane sp = new JScrollPane(agentwinptable);
         sp.setPreferredSize((new Dimension(300, 263)));


         /* Creating Dialogs for buttons */
         AddMatchDialog addmatchdialog = new AddMatchDialog(this,"Add Match", agents, db);
         DeleteLatestDialog deletelatestdialog = new DeleteLatestDialog(this, "Delete Latest", db);
         DeleteAllDialog deletealldialog = new DeleteAllDialog(this, "Delete All", db);

         /* Add Match Button */
         JButton addbutton = new JButton("Add Match");
         addbutton.setPreferredSize(new Dimension(110,35));

         /* Listener to open AddMatchDialog on button click */
         addbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 addmatchdialog.setVisible(true);
             }
         });

         /* Listener to update win% elements when exiting dialog */
         addmatchdialog.addPropertyChangeListener(new PropertyChangeListener() {
             @Override
             public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                 try {
                     stats.refresh();
                     stats.calculateStats();
                     owinlabel2.setText(formatOverallWinp());
                     agentwinptable.setModel(new DefaultTableModel(formatStats(stats.getStats()), column));
                     deletelatestdialog.updateLatestMatch(db);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }
         );

         /* Delete Latest Button */
         JButton delbutton = new JButton("Delete Latest");
         delbutton.setPreferredSize(new Dimension(110,35));

         /* Listener to open DeleteLatestDialog on button click */
         delbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 deletelatestdialog.setVisible(true);
             }
         });

         /* Listener to update win% elements when exiting dialog */
         deletelatestdialog.addPropertyChangeListener(new PropertyChangeListener() {
             @Override
             public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                 try {
                     stats.refresh();
                     stats.calculateStats();
                     owinlabel2.setText(formatOverallWinp());
                     agentwinptable.setModel(new DefaultTableModel(formatStats(stats.getStats()), column));
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });

         /* Delete All Button */
         JButton delallbutton = new JButton("Delete All");
         delallbutton.setPreferredSize(new Dimension(110,35));

         /* Listener to open DeleteAllDialog on button click */
         delallbutton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                deletealldialog.setVisible(true);
             }
         });

         /* Listener to update win% elements when exiting dialog */
         deletealldialog.addPropertyChangeListener(new PropertyChangeListener() {
             @Override
             public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                 try {
                     stats.refresh();
                     stats.calculateStats();
                     owinlabel2.setText(formatOverallWinp());
                     agentwinptable.setModel(new DefaultTableModel(formatStats(stats.getStats()), column));
                     deletelatestdialog.updateLatestMatch(db);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });

         /* GridBagLayout to position main screen elements */
         GridBagLayout layout = new GridBagLayout();
         GridBagConstraints gbc = new GridBagConstraints();
         this.setLayout(layout);

         gbc.fill = GridBagConstraints.HORIZONTAL;
         gbc.insets = new Insets(10,10,0,10);
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
         gbc.insets = new Insets(10,10,10,0);
         this.add(addbutton, gbc); //Add Match button added
         gbc.gridx = 1;
         gbc.gridy = 2;
         this.add(delbutton, gbc); //Delete Latest button added
         gbc.gridx = 3;
         gbc.gridy = 2;
         gbc.anchor = GridBagConstraints.EAST;
         gbc.insets = new Insets(10,5,10,10);
         this.add(delallbutton, gbc); //Delete All button added

         /* JFrame settings */
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         this.setVisible(true);
    }

    /**
     * Format stats for JTable
     *
     * @param stats list of matches
     * @return two dimensional string array of agents and their win percentages.
     */
    private String[][] formatStats(Hashtable<String, Double> stats) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(0);

        int rows = stats.size();
        String[][] data = new String[rows][2];

        for(int i=0;i < rows; i++){
             data[i][0] = agents[i];
             data[i][1] = nf.format(stats.get(agents[i]));
        }
        return data;
    }

    /**
     * Format overall win percentage as a string for JLabel.
     *
     * @return      a formatted string representing win percentage
     */
    private String formatOverallWinp() {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(0);
        return nf.format(stats.getOverallWinPercentage());
    }

    /*
     * initializes list of agents, connects to database and
     * builds initial statistics.
     */
    private void initializeData() {
        agents = getAgents();
        db = TextDatabase.getInstance("db.txt");
        stats = new StatsBuilder(db, agents);
        stats.refresh();
        stats.calculateStats();
    }


    /**
     * Returns a string array of all agents in Valorant.
     *
     * @return agents      a string array of agent names
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
     * ValorantTracker entry point. Starts application in thread.
     *
     * @param args          array of command line arguments
     */
    public static void main(String[] args) {
         SwingUtilities.invokeLater(ValorantTracker::new);

    }
}