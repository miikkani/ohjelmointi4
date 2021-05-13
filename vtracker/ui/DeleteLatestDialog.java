package vtracker.ui;

import vtracker.data.Match;
import vtracker.data.MatchResult;
import vtracker.data.VtrackerDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DeleteLatestDialog UI for deleting
 * latest entry to the TextDatabase.
 *
 */

public class DeleteLatestDialog extends JDialog{
    JLabel matchlabel;

    public DeleteLatestDialog(Frame frame, String title, VtrackerDatabase db){
        super(frame, title, true);


        /** Text to warn the user of what is being deleted */
        LayoutManager layout = new GridLayout(3,1);
        JPanel textpanel = new JPanel(layout);
        textpanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        textpanel.add(new JLabel("This will DELETE your latest match:", JLabel.CENTER));
        matchlabel = new JLabel("", JLabel.CENTER);
        updateLatestMatch(db); //Sets latest added match to matchlabel
        textpanel.add(matchlabel);
        textpanel.add(new JLabel("Continue?", JLabel.CENTER));

        /** Ok Button */
        JButton okbutton = new JButton("Ok");
        okbutton.setPreferredSize(new Dimension(110,35));
        okbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.deleteLatestMatch(); //Deletes latest match in database
                setVisible(false);
                updateLatestMatch(db); //Sets latest added match to matchlabel
                firePropertyChange("OK clicked", true, false);
            }
        });

        /** Cancel Button */
        JButton cancelbutton = new JButton("Cancel");
        cancelbutton.setPreferredSize(new Dimension(110,35));
        cancelbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        /** GridBagLayout for the dialog **/
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

    /**
     * Updates DeleteLatestDialog info text to
     * show what match user is about to delete
     * or if database is empty.
     *
     * @param db    instance of TextDatabase
     */
    public void updateLatestMatch(VtrackerDatabase db){
        try {
            Match m = db.getLatestMatch();
            if (m == null){
                matchlabel.setText("Database empty!");
            } else {
                MatchResult result = m.getResult();
                if (result == MatchResult.WIN) {
                    matchlabel.setText(m.getAgent() + " - Victory");
                }
                if (result == MatchResult.LOSS) {
                    matchlabel.setText(m.getAgent() + " - Defeat");
                }
                if (result == MatchResult.DRAW) {
                    matchlabel.setText(m.getAgent() + " - Draw");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}