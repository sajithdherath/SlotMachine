import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sajith Deshappriya on 12/12/2016.
 */
public class SlotMachine extends JFrame {
    static int clickFlag = 0;

    public SlotMachine() {
        //Game main menu
        super("Slot Machine Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);

        //creating the layout
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(38,50,56));
        add(panel);

        JLabel lblTypo = new JLabel("SLOT MACHINE");
        lblTypo.setFont(new Font("SlotMachine", Font.PLAIN, 90));
        lblTypo.setForeground(new Color(255,87,34));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridy=0;
        c.gridx=1;
        c.gridwidth=2;
        c.insets = new Insets(0, 0, 80, 0);
        panel.add(lblTypo,c);

        //placing images for reel1
        ImageIcon img1 = new ImageIcon(getClass().getResource("images/coin.png"));
        JLabel lbl1 = new JLabel(img1);
        lbl1.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth=1;
        c.insets = new Insets(0, 0, 100, 10);
        panel.add(lbl1, c);

        //placing images for reel2
        ImageIcon img2 = new ImageIcon(getClass().getResource("images/coin.png"));
        JLabel lbl2 = new JLabel(img2);
        lbl2.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 100, 10);
        panel.add(lbl2, c);

        //placing images for reel3
        ImageIcon img3 = new ImageIcon(getClass().getResource("images/coin.png"));
        JLabel lbl3 = new JLabel(img3);
        lbl3.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 100, 10);
        panel.add(lbl3, c);

        //textfiled for status
        JLabel txtStatus = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 50, 10, 0);
        txtStatus.setForeground(new Color(255,255,0));
        panel.add(txtStatus, c);

        //button for Spin
        JButton btnSpin = new JButton("Spin");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(10, 0, 10, 0);
        btnSpin.setBackground(Color.green);
        panel.add(btnSpin, c);
        Reel reel1 = new Reel(lbl1);
        Reel reel2 = new Reel(lbl2);
        Reel reel3 = new Reel(lbl3);

        //Label for display current credits and number of bets
        JLabel status = new JLabel();
        c.gridy = 3;
        c.gridx = 1;
        c.insets = new Insets(0, 10, 10, 10);
        status.setForeground(new Color(255,255,0));
        panel.add(status, c);

        //get values of credits and bets
        User user = new User(status, lbl1, lbl2, lbl3);
        user.setText();

        //button for add a coin
        JButton btnAddCoin = new JButton("Add Coin");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 10, 10, 10);
        btnAddCoin.setBackground(new Color(211,47,47));
        btnAddCoin.setForeground(Color.WHITE);
        panel.add(btnAddCoin, c);
        btnAddCoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setCredit();
                user.setText();
            }
        });

        //button for reset
        JButton btnReset = new JButton("Rest");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(0, 10, 10, 10);
        btnReset.setBackground(new Color(211,47,47));
        btnReset.setForeground(Color.WHITE);
        panel.add(btnReset, c);

        //button for bet one
        JButton btnBetOne = new JButton("Bet One");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0, 10, 0, 10);
        btnBetOne.setBackground(new Color(211,47,47));
        btnBetOne.setForeground(Color.WHITE);
        panel.add(btnBetOne, c);
        btnBetOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setBet();
                user.setText();
            }
        });

        //button for bet max
        JButton btnBetMax = new JButton("Bet Max");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        btnBetMax.setBackground(new Color(211,47,47));
        btnBetMax.setForeground(Color.WHITE);
        panel.add(btnBetMax, c);
        btnBetMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (user.setMaxBet()) {
                    btnBetOne.setEnabled(false);
                    btnBetMax.setEnabled(false);
                }
                user.setText();
            }
        });

        //Set an action for Reset button
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.setReset();
                if (!btnBetMax.isEnabled()) {
                    btnBetMax.setEnabled(true);
                    btnBetOne.setEnabled(true);
                }
            }
        });

        //button for statics
        JButton btnStatics = new JButton("Statics");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        btnStatics.setBackground(new Color(211,47,47));
        btnStatics.setForeground(Color.WHITE);
        panel.add(btnStatics, c);
        btnStatics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.viewStatics();

            }
        });

        //Stop reels by mouse clicking on reel1
        lbl1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                reel1.stopSpin();
                reel2.stopSpin();
                reel3.stopSpin();
                txtStatus.setText(user.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));
                clickFlag++;
                btnBetMax.setEnabled(true);
                btnBetOne.setEnabled(true);
                btnSpin.setEnabled(true);
            }
        });

        //Stop reels by mouse clicking on reel2
        lbl2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                reel1.stopSpin();
                reel2.stopSpin();
                reel3.stopSpin();
                txtStatus.setText(user.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));
                clickFlag++;
                btnBetMax.setEnabled(true);
                btnBetOne.setEnabled(true);
                btnSpin.setEnabled(true);
            }
        });

        //Stop reels by mouse clicking on reel3
        lbl3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                reel1.stopSpin();
                reel3.stopSpin();
                reel2.stopSpin();
                btnBetMax.setEnabled(true);
                btnBetOne.setEnabled(true);
                txtStatus.setText(user.winning(reel1.getGenNumber(), reel2.getGenNumber(), reel3.getGenNumber()));
                clickFlag++;
                btnSpin.setEnabled(true);
            }
        });

        //Add an action for spin button
        btnSpin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (clickFlag >= 1) {
                    //spin after thread started
                    if (user.getBet() == 0) {
                        JOptionPane.showMessageDialog(null, "Bet First", "", JOptionPane.WARNING_MESSAGE);
                    } else {
                        txtStatus.setText("");
                        reel1.timer.start();
                        reel2.timer.start();
                        reel3.timer.start();
                        clickFlag = 0;
                        btnSpin.setEnabled(false);
                        lbl1.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
                        lbl2.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
                        lbl3.setBorder(BorderFactory.createLineBorder(Color.orange, 5));
                        user.setNumberOfMatches();
                    }
                } else {
                    if (user.getBet() != 0) {
                        reel1.start();
                        reel2.start();
                        reel3.start();
                        user.setNumberOfMatches();
                        clickFlag++;
                        btnSpin.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Bet First", "", JOptionPane.WARNING_MESSAGE);
                    }

                }

            }
        });
        setVisible(true);
    }


    public static void main(String[] args) {
        //Welcome menu
        SlotMachine game = new SlotMachine();
    }

}
