import javafx.scene.text.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sajith Deshappriya on 12/14/2016.
 */

public class User extends JFrame {

    static private int wins = 0;
    private int credit;
    private int bet = 0;
    private int losses = 0;
    private float average = 0;
    private int creditLost = 0;
    private int numberOfMatches;
    private JLabel lbl, lbl2, lbl3, lbl4;

    //Customized Constructor for User class
    public User(JLabel lbl, JLabel lbl2, JLabel lbl3, JLabel lbl4) {
        credit = 10;
        this.lbl = lbl;
        this.lbl2 = lbl2;
        this.lbl3 = lbl3;
        this.lbl4 = lbl4;
    }

    //Getter for total matches
    public int getNumberOfMatches() {

        return numberOfMatches;
    }

    //Setter for total matches
    public void setNumberOfMatches() {
        numberOfMatches++;
    }

    //Gtter for Credit value
    public void setCredit() {
        credit++;
    }

    //Getter for Credit value
    public int getCredit() {
        return credit;
    }

    //Getter for bet value
    public int getBet() {
        return bet;
    }

    //Setter for Bet values
    public void setBet() {
        bet++;
        credit--;
        //Validations for bet value
        if (bet > 3) {
            JOptionPane.showMessageDialog(null, "You can bet only upto 3 credits", "", JOptionPane.WARNING_MESSAGE);
            bet = 3;
            credit = credit + 1;
        }
        if (credit < 0) {
            JOptionPane.showMessageDialog(null, "Please add credits", "", JOptionPane.WARNING_MESSAGE);
        }
    }

    //Setter for max number of bets
    public boolean setMaxBet() {
        bet = 3;
        credit = credit - 3;
        if (credit < 0) {
            JOptionPane.showMessageDialog(null, "Please add credits", "", JOptionPane.WARNING_MESSAGE);
            bet = 0;
            credit = credit + 3;
            return false;
        }
        return true;
    }

    //Gtter for number wins
    public int getWins() {
        return wins;
    }

    //Setter for number of Wins
    public void setWins() {
        wins++;
    }

    //Getter for number of losses
    public int getLosses() {
        return losses;
    }

    //Setter for number of losses
    public void setLosses() {
        losses++;
    }

    //Calculation for winnig the game
    public String winning(int reel1, int reel2, int reel3) {
        String status = "Try again";
        if (((reel1 == reel2) && (reel2 == reel3)) || reel1 == reel2 || reel2 == reel3 || reel1 == reel3) {
            //matching reels
            if ((reel1 == reel2) && (reel2 == reel3)) {
                lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                credit = credit + (Reel.symbol[reel1].getValue() * bet) * 2;
                bet = 0;
                status = "Win!";
            } else if (reel1 == reel2) {
                lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                //credit = credit + (Reel.symbol[reel1].getValue() * bet)*2;
                status = "Bingo!You have a free chance";
            } else if (reel2 == reel3) {
                lbl3.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                //credit = credit + (Reel.symbol[reel2].getValue() * bet)*2;
                status = "Bingo!You have a free chance";

            } else if (reel1 == reel3) {
                lbl2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                lbl4.setBorder(BorderFactory.createLineBorder(Color.red, 5));
                //credit = credit + (Reel.symbol[reel1].getValue() * bet)*3;
                status = "Bingo!You have a free chance";
            }
            setWins();
            setText();
        } else {
            setLosses();
            creditLost = credit - bet;
            bet = 0;
            setText();
        }
        return status;
    }

    //Update credits and bet values on the label
    public void setText() {
        lbl.setText("Credits: " + credit);
        if (bet > 0) {
            lbl.setText("Credits: " + credit + " Bet: " + bet);
        }
    }

    //Calculate game average
    public float calAverage() {
        average = ((getWins() - getLosses()) / (float) getNumberOfMatches())*100;
        return average;
    }

    //View Statics
    public void viewStatics() {
        //Draw a new window
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 500);
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(38,50,56));
        add(panel);
        //Label for number of matches
        JLabel lbl = new JLabel();
        c.gridy = 0;
        c.gridx = 0;
        //c.gridwidth=2;
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Calibri", Font.BOLD,20));
        panel.add(lbl, c);
        lbl.setText("Number of Matches: " + getNumberOfMatches());
        //Label for number of wins
        JLabel lbl2 = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        lbl2.setForeground(Color.WHITE);
        lbl2.setFont(new Font("Calibri", Font.BOLD,20));
        panel.add(lbl2, c);
        lbl2.setText("Number of Wins: " + getWins());
        //Label for number of credits
        JLabel lbl3 = new JLabel();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth=2;
        lbl3.setForeground(Color.WHITE);
        lbl3.setFont(new Font("Calibri", Font.BOLD,20));
        panel.add(lbl3, c);
        lbl3.setText("Number of Credits: " + getCredit());
        //Label for game average
        JLabel lbl4 = new JLabel();
        c.gridx = 0;
        c.gridy = 4;
        lbl4.setForeground(Color.WHITE);
        lbl4.setFont(new Font("Calibri", Font.BOLD,20));
        panel.add(lbl4, c);
        lbl4.setText("Average: ");
        JProgressBar averageBar = new JProgressBar();
        averageBar.setValue((int) calAverage());
        averageBar.setStringPainted(true);
        c.gridx=1;
        c.gridy=4;
        panel.add(averageBar,c);
        JLabel lbl5 = new JLabel();
        lbl5.setForeground(Color.WHITE);
        lbl5.setFont(new Font("Calibri", Font.BOLD,20));
        lbl5.setText(String.valueOf(averageBar.getValue())+"%");
        c.gridy=5;
        c.gridx=1;
        panel.add(lbl5,c);
        //Button to back to game window
        JButton btn = new JButton("Back");
        c.gridx = 0;
        c.gridy = 6;
        panel.add(btn, c);
        //Button to save statics into a file
        JButton btn2 = new JButton("Save Statics");
        c.gridx = 1;
        c.gridy = 6;
        panel.add(btn2, c);

        setVisible(true);
        //Add an action for back button
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("button cliked");
                setVisible(false);
            }
        });
        //Add an action for Save button
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("buutton cliked");
                saveData();
            }
        });
    }

    //Resetting bets
    public void setReset() {
        credit = 10;
        bet = 0;
        lbl2.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        lbl3.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        lbl4.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        setText();
    }

    //Saving data into a file
    public void saveData() {
        DateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date dateobj = new Date();
        String date = df.format(dateobj);
        String fileName = date + ".txt";
        try {
            File f = new File(System.getProperty("user.home") + "/Desktop");
            File file = new File(f, fileName);//create a text file
            file.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {//write data line by line
                bw.write(date);
                bw.newLine();
                bw.write("\tNumber of Matches:" + getNumberOfMatches());
                bw.newLine();
                bw.write("\tWons: " + getWins());
                bw.newLine();
                bw.write("\tCredits: " + getCredit());
                bw.newLine();
                bw.write("\tLosses: " + getLosses());
                bw.newLine();
                bw.write("\tCredits Lost: " + creditLost);
                bw.newLine();
                bw.write("\tAverage: " + average);
                bw.flush();
                bw.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error Saving File!", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error Saving File!", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}

