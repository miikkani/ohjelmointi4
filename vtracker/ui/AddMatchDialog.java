package vtracker.ui;

import vtracker.data.Match;
import vtracker.data.MatchResult;
import vtracker.data.VtrackerDatabase;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMatchDialog extends JDialog{

    public AddMatchDialog(Frame frame, String title, String[] agents){
        super(frame, title, true);

        /** Label texts **/
        JLabel agentlabel = new JLabel();
        agentlabel.setText("Select Agent:");

        /** Agent Selection List **/
        final JList<String>agentlist = new JList<>(agents);
        JScrollPane agentlistScroller = new JScrollPane(agentlist);

        /** Radiobuttons for match Outcome **/
        JRadioButton victoryrb = new JRadioButton("Victory", true);
        JRadioButton defeatrb = new JRadioButton("Defeat");
        JRadioButton drawrb = new JRadioButton("Draw");
        ButtonGroup bg = new ButtonGroup();
        bg.add(victoryrb); bg.add(defeatrb); bg.add(drawrb);

        JPanel outcomepanel = new JPanel();
        outcomepanel.setLayout(new GridLayout(3,1));
        outcomepanel.setBorder(BorderFactory.createTitledBorder("Outcome:"));
        outcomepanel.add(victoryrb); outcomepanel.add(defeatrb); outcomepanel.add(drawrb);

        /** Ok and Cancel Buttons **/
        JButton okbutton = new JButton("Ok");
        okbutton.setPreferredSize(new Dimension(110,35));
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(agentlist.isSelectionEmpty()){ //If no agent is selected warn user!
                    String message = "<html><body><div width='200px' align='center'>SELECT AGENT before adding a match!</div></body></html>";
                    JOptionPane.showMessageDialog(AddMatchDialog.super.rootPane, message,"", JOptionPane.PLAIN_MESSAGE);
                }
                else{ //Otherwise add match and hide dialog

                    String agent = agentlist.getSelectedValue();
                    MatchResult result = null;
                    if(victoryrb.isSelected()){ result = MatchResult.WIN; }
                    if(defeatrb.isSelected()){ result = MatchResult.LOSS; }
                    if(drawrb.isSelected()){ result = MatchResult.DRAW; }

                    Match match = new Match(agent, result);
                    System.out.println(match); //For Testing Purposes Only!

                    setVisible(false);
                    /** ADD METHOD TO ADD MATCH HERE! */
                    //VtrackerDatabase.addMatch(match);?
                }
            }
        });

        JButton cancelbutton = new JButton("Cancel");
        cancelbutton.setPreferredSize(new Dimension(110,35));
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });

        /** Layout for the dialog **/
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

