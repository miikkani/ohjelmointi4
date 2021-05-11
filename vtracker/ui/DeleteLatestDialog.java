package vtracker.ui;

import vtracker.data.VtrackerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeleteLatestDialog extends JDialog{
    public DeleteLatestDialog(Frame frame, String title, VtrackerDatabase db){
        super(frame, title, true);

        LayoutManager layout = new GridLayout(3,1);
        JPanel textpanel = new JPanel(layout);
        textpanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        textpanel.add(new JLabel("This will DELETE your latest match:", JLabel.CENTER));
        textpanel.add(new JLabel("Killjoy - Victory", JLabel.CENTER));
        textpanel.add(new JLabel("Continue?", JLabel.CENTER));

        JButton okbutton = new JButton("Ok");
        okbutton.setPreferredSize(new Dimension(110,35));
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.deleteLatestMatch();
                setVisible(false);
                firePropertyChange("OK clicked", true, false);
            }
        });

        JButton cancelbutton = new JButton("Cancel");
        cancelbutton.setPreferredSize(new Dimension(110,35));
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        GridBagLayout gblayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gblayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(textpanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        add(okbutton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(cancelbutton, gbc);
        pack();
        setResizable(false);
        setLocationRelativeTo(frame);
    }
}