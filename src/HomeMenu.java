
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;

public class HomeMenu implements ActionListener {
    private Container c;
    private JFrame hFrame = new JFrame();
    String path = System.getProperty("user.dir") + "//assets//";
    JLabel tLabel = new JLabel("ePay");

    JButton jButtonA = new JButton();
    JButton jButtonB = new JButton();
    JButton jButtonC = new JButton();
    JButton jButtonD = new JButton();
    JButton logoutButton = new JButton("logout");

    JLabel jLabelA = new JLabel("Add Connection");
    JLabel jLabelB = new JLabel("Bill Payment");
    JLabel jLabelC = new JLabel("Connection Details");
    JLabel jLabelD = new JLabel("Cute Connection");

    public HomeMenu() {
        design();
        components();
    }

    private void design() {

        hFrame.setTitle("HOME  ");
        hFrame.setSize(800, 750);
        hFrame.setLayout(null);
        hFrame.setLocationRelativeTo(null);
        hFrame.setVisible(true);
        hFrame.setResizable(false);
        hFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = hFrame.getContentPane();
        c.setBackground(Color.PINK);
        c.setLayout(null);

    }

    private void components() {

        tLabel.setBounds(350, 60, 200, 50);
        tLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 38));
        c.add(tLabel);

        // A
        jButtonA.setBounds(200, 150, 180, 180);
        jButtonA.setIcon(new ImageIcon(
                new ImageIcon(path + "add.png").getImage().getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING)));

        jButtonA.addActionListener(this);
        c.add(jButtonA);
        // B
        jButtonB.setBounds(200, 390, 180, 180);
        jButtonB.setIcon(new ImageIcon(
                new ImageIcon(path + "pay.png").getImage().getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING)));
        jButtonB.setBackground(Color.BLACK);
        jButtonB.addActionListener(this);
        c.add(jButtonB);
        // C
        jButtonC.setBounds(420, 150, 180, 180);
        jButtonC.setIcon(new ImageIcon(
                new ImageIcon(path + "record.png").getImage().getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING)));

        jButtonC.addActionListener(this);
        c.add(jButtonC);
        // D
        jButtonD.setBounds(420, 390, 180, 180);
        jButtonD.setIcon(new ImageIcon(
                new ImageIcon(path + "cancel.png").getImage().getScaledInstance(160, 160, Image.SCALE_AREA_AVERAGING)));
        jButtonD.setBackground(Color.RED);
        jButtonD.addActionListener(this);
        c.add(jButtonD);

        logoutButton.setBounds(630, 18, 100, 30);
        logoutButton.addActionListener(this);
        logoutButton.setBackground(Color.green);
        logoutButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
        hFrame.add(logoutButton);

        jLabelA.setBounds(220, 330, 200, 40);
        jLabelB.setBounds(230, 570, 200, 40);
        jLabelC.setBounds(430, 330, 200, 40);
        jLabelD.setBounds(440, 570, 200, 40);

        c.add(jLabelA);
        c.add(jLabelB);
        c.add(jLabelC);
        c.add(jLabelD);

        jLabelA.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        jLabelB.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        jLabelC.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));
        jLabelD.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 18));

        c.repaint();
        c.revalidate();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonA) {

            this.hFrame.setVisible(false);
            new createConnection().setVisible(true);
        }
        if (e.getSource() == jButtonB) {

            this.hFrame.setVisible(false);
            new customerBills().setVisible(true);
        }
        if (e.getSource() == jButtonC) {

            this.hFrame.setVisible(false);
            new connectionD().setVisible(true);
        }
        if (e.getSource() == jButtonD) {

            this.hFrame.setVisible(false);
            new connectionC().setVisible(true);
        }
        if (e.getSource() == logoutButton) {

            int logout = JOptionPane.showConfirmDialog(logoutButton, "Are you sure logout !?");
            if (logout == JOptionPane.YES_OPTION) {
                hFrame.setVisible(false);
                new userLogin().setVisible(true);
            }

        }
    }

    public static void main(String[] args) {
        new HomeMenu();

    }

    public void setVisible(boolean b) {
    }

}
