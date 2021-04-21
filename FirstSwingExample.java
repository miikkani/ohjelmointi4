import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstSwingExample {

    public static void main(String[] args) {

        int astrawinp = 52;

        JFrame f = new JFrame("Valorant Tracker"); //Creating instance of JFrame

        JLabel agentwinp = new JLabel();
        agentwinp.setBounds(20, 100, 500, 400);
        agentwinp.setText("Astra: " + astrawinp + "%");

        JTextField winp = new JTextField();
        winp.setBounds(20,20, 500, 60);
        winp.setText("POINTLESS TEXTFIELD!");
        winp.setFont(winp.getFont().deriveFont(40.0f));

        String data[][]={{"Astra",astrawinp + "%"}, //
        {"Breach","53%"},
        {"Brimstone", "54%"}};
        String column[]={"Agent","Win Percentage"};
        JTable agentwinptable = new JTable(data,column); // Creating the JTable
        JScrollPane sp = new JScrollPane(agentwinptable);
        sp.setBounds(20,120,500,100);


        JRadioButton outcomeVictory = new JRadioButton("Victory");
        outcomeVictory.setBounds(20, 140, 100, 20);
        JRadioButton outcomeDefeat = new JRadioButton("Defeat");
        outcomeDefeat.setBounds(20,160,100,20);
        JRadioButton outcomeDraw = new JRadioButton("Draw");
        outcomeDraw.setBounds(20,180,100,20);

        ButtonGroup outcomebg = new ButtonGroup();
        outcomebg.add(outcomeVictory);outcomebg.add(outcomeDefeat);outcomebg.add(outcomeDraw);

        JButton b = new JButton("Show Win %"); //Creating instance of JButton
        b.setBounds(20, 500, 140, 40);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                winp.setText("BAD OVERALL WIN % :-D");
            }
        });


        f.add(agentwinp);
        f.add(winp); //Adding textfield in JFrame
        f.add(sp);
        f.add(outcomeVictory);f.add(outcomeDefeat);f.add(outcomeDraw);
        f.add(b); //Adding button in JFrame


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(560, 600);
        f.setLayout(null);
        f.setVisible(true);
    }
}
