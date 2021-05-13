package vtracker.ui;

import vtracker.ValorantTracker;
import vtracker.data.Match;
import vtracker.data.MatchResult;
import vtracker.data.VtrackerDatabase;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * AddMatchDialog UI for adding a match
 * to the TextDatabase with selected
 * agent and outcome.
 *
 */

public class AddMatchDialog extends JDialog{

    public AddMatchDialog(Frame frame, String title, String[] agents, VtrackerDatabase db){
        super(frame, title, true);

        /** Label text for agent select **/
        JLabel agentlabel = new JLabel();
        agentlabel.setText("Select Agent:");

        /** Agent Selection List **/
        final JList<String>agentlist = new JList<>(agents);
        JScrollPane agentlistScroller = new JScrollPane(agentlist);

        /** RadioButtons for match Outcome **/
        JRadioButton victoryrb = new JRadioButton("Victory", true);
        JRadioButton defeatrb = new JRadioButton("Defeat");
        JRadioButton drawrb = new JRadioButton("Draw");
        ButtonGroup bg = new ButtonGroup();
        bg.add(victoryrb); bg.add(defeatrb); bg.add(drawrb);

        //Panel and border for RadioButtons
        JPanel outcomepanel = new JPanel();
        outcomepanel.setLayout(new GridLayout(3,1));
        outcomepanel.setBorder(BorderFactory.createTitledBorder("Outcome:"));
        outcomepanel.add(victoryrb); outcomepanel.add(defeatrb); outcomepanel.add(drawrb);

        /** Ok button **/
        JButton okbutton = new JButton("Ok");
        okbutton.setPreferredSize(new Dimension(110,35));
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(agentlist.isSelectionEmpty()){
                    //If no agent is selected create and show a error message
                    String message = "<html><body><div width='200px' align='center'>SELECT AGENT before adding a match!</div></body></html>";
                    JOptionPane errorpane = new JOptionPane(message,JOptionPane.PLAIN_MESSAGE);

                    //Resizing button inside the errorpane
                    JPanel buttonpanel = (JPanel)errorpane.getComponent(1);
                    JButton okbutton2 = (JButton)buttonpanel.getComponent(0);
                    okbutton2.setText("Ok");
                    okbutton2.setPreferredSize(new Dimension(110,35));
                    okbutton2.validate();
                    JDialog errordialog = errorpane.createDialog(null);
                    errordialog.setVisible(true);
                }
                else{ //Otherwise add match and hide dialog

                    String agent = agentlist.getSelectedValue();
                    MatchResult result = null;
                    if(victoryrb.isSelected()){ result = MatchResult.WIN; }
                    if(defeatrb.isSelected()){ result = MatchResult.LOSS; }
                    if(drawrb.isSelected()){ result = MatchResult.DRAW; }

                    db.addMatch(new Match(agent, result)); //Adds a match to the database
                    setVisible(false);
                    firePropertyChange("Database updated", false, true);
                    }
            }
        });

        /** Cancel button **/
        JButton cancelbutton = new JButton("Cancel");
        cancelbutton.setPreferredSize(new Dimension(110,35));
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });

        /** GridBagLayout for the dialog **/
        GridBagLayout gblayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gblayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,0,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(agentlabel, gbc);
        gbc.insets = new Insets(0,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(agentlistScroller, gbc);
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(outcomepanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        add(okbutton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(cancelbutton, gbc);
        pack();
        setResizable(false);
        setLocationRelativeTo(frame);

    }
}

