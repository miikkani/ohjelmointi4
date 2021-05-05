package vtracker.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMatchDialog extends JDialog{

    public AddMatchDialog(Frame frame, String title, String[] agents){
        super(frame, title, true);

        /** Label texts **/
        JLabel agentlabel = new JLabel();
        agentlabel.setText("Select Agent:");
        JLabel outcomelabel = new JLabel();
        outcomelabel.setText("Select Outcome:");

//        /** Agent Selection List **/
//        final DefaultListModel<String>al = new DefaultListModel<>();
//            al.addElement("Astra");
//            al.addElement("Breach");
//            al.addElement("Brimstone");
//            al.addElement("Cypher");
//            al.addElement("Jett");
//            al.addElement("Killjoy");
//            al.addElement("Omen");
//            al.addElement("Phoenix");
//            al.addElement("Raze");
//            al.addElement("Reyna");
//            al.addElement("Sage");
//            al.addElement("Skye");
//            al.addElement("Sova");
//            al.addElement("Viper");
//            al.addElement("Yoru");

            final JList<String>agentlist = new JList<>(agents);

        JScrollPane agentlistScroller = new JScrollPane(agentlist);

        /** Radiobuttons for match Outcome **/
        JRadioButton victoryrb = new JRadioButton("Victory");
        JRadioButton defeatrb = new JRadioButton("Defeat");
        JRadioButton drawrb = new JRadioButton("Draw");
        ButtonGroup bg = new ButtonGroup();
        bg.add(victoryrb); bg.add(defeatrb); bg.add(drawrb);

        /** Ok and Cancel Buttons **/
        JButton okbutton = new JButton("Ok");
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                /**ADD METHOD TO ADD MATCH HERE!**/
            }
        });

        JButton cancelbutton = new JButton("Cancel");
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { setVisible(false); }
        });

        /** Layout for the dialog **/
        GridBagLayout gblayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gblayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(agentlabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(outcomelabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(agentlistScroller,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        //add(bg,gbc);
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
        setLocationRelativeTo(frame);
    }
}

