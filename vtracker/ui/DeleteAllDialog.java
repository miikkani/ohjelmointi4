package vtracker.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAllDialog extends JDialog{
    public DeleteAllDialog(Frame frame, String title){
        super(frame, title, true);

        LayoutManager layout = new GridLayout(2,1);
        JPanel textpanel = new JPanel(layout);
        textpanel.add(new JLabel("This will DELETE ALL data from ALL AGENTS", JLabel.CENTER));
        textpanel.add(new JLabel("Continue?", JLabel.CENTER));


        JButton okbutton = new JButton("Ok");
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                /**ADD METHOD TO DELETE ALL MATCHES HERE!**/
            }
        });

        JButton cancelbutton = new JButton("Cancel");
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
