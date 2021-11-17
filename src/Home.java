import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.*;
import java.awt.*;

public class Home implements ActionListener {
    private JFrame hFrame = new JFrame();
    private Container cn;

    private JButton loginButton = new JButton("LoGin");

    public Home() {
        GuiDesign();
        addComponents();
    }

    private void GuiDesign() {
        hFrame.setSize(800, 300);
        hFrame.setLocationRelativeTo(null);
        hFrame.setVisible(true);
        hFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cn = hFrame.getContentPane();
        cn.setLayout(null);
        cn.setBackground(Color.yellow);

    }

    private void addComponents() {

        loginButton.setBounds(480, 200, 250, 30);
        loginButton.addActionListener(this);
        loginButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        loginButton.setBounds(426, 192, 85, 29);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(new BevelBorder(BevelBorder.RAISED));

        cn.add(loginButton);

    }

    public static void main(String[] args) {
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            hFrame.setVisible(false);
            new userLogin().setVisible(true);
        }

    }
}
