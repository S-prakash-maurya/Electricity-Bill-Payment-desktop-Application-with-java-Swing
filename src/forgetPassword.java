
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.border.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;

public class forgetPassword implements ActionListener {

    private JFrame jFrame = new JFrame();
    private Container contentPane;

    private JLabel idLabel = new JLabel("ID  :");
    private JLabel dobLabel = new JLabel("DOB :");
    private JLabel cLabel = new JLabel("CONTECT :");

    private JTextField idjField = new JTextField();
    private JTextField djField = new JTextField();
    private JLabel hintLabel = new JLabel("yyyy/mm/dd");
    private JTextField cjField = new JTextField();

    private JLabel nLabel = new JLabel(" NAME :");
    private JLabel pLabel = new JLabel(" PASSWORD :");

    private JLabel njLabel = new JLabel(" ");
    private JLabel pjLabel = new JLabel(" ");

    private JButton sButton = new JButton("Submit");
    private JButton cButton = new JButton("Cancel");

    public forgetPassword() {
        frame();
        designs();
    }

    private void frame() {
        jFrame.setSize(800, 600);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = jFrame.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(0, 191, 255));
    }

    private void designs() {

        JLabel textLabel = new JLabel("Forget Password");
        textLabel.setBounds(250, 5, 350, 80);
        textLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
        textLabel.setForeground(Color.black);
        textLabel.setBackground(Color.GREEN);
        contentPane.add(textLabel);

        idLabel.setBounds(105, 160, 100, 30);
        idjField.setBounds(240, 160, 260, 30);

        dobLabel.setBounds(105, 200, 100, 30);
        hintLabel.setBounds(510, 200, 120, 30);
        djField.setBounds(240, 200, 260, 30);

        cLabel.setBounds(105, 240, 100, 30);
        cjField.setBounds(240, 240, 260, 30);

        nLabel.setBounds(100, 280, 100, 30);
        njLabel.setBounds(240, 280, 260, 30);

        pLabel.setBounds(100, 320, 120, 30);
        pjLabel.setBounds(240, 320, 260, 30);

        idLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        dobLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        hintLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        cLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        nLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        pLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

        idjField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        djField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        cjField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

        njLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        pjLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

        njLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        pjLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

        contentPane.add(idLabel);
        contentPane.add(idjField);

        contentPane.add(dobLabel);
        contentPane.add(hintLabel);
        contentPane.add(djField);

        contentPane.add(cLabel);
        contentPane.add(cjField);

        contentPane.add(nLabel);
        contentPane.add(njLabel);

        contentPane.add(pLabel);
        contentPane.add(pjLabel);

        sButton.setBounds(275, 360, 80, 30);
        sButton.addActionListener(this);
        sButton.setBackground(Color.BLACK);
        sButton.setForeground(Color.YELLOW);
        contentPane.add(sButton);

        cButton.setBounds(380, 360, 80, 30);
        cButton.addActionListener(this);
        cButton.setBackground(Color.BLACK);
        cButton.setForeground(Color.YELLOW);
        contentPane.add(cButton);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == sButton) {
            try {

                dbConnection fConnection = new dbConnection();
                String sql = "select * from admin where  a_id=? and  b_date = ? and a_contect  =? ";
                PreparedStatement ps = fConnection.c.prepareStatement(sql);

                ps.setString(1, idjField.getText());
                ps.setString(2, djField.getText());
                ps.setString(3, cjField.getText());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    njLabel.setText(rs.getString("a_Name"));
                    pjLabel.setText(rs.getString("Password"));

                }

            } catch (

            SQLException e) {
                e.printStackTrace();
            }

        }

        if (ae.getSource() == cButton) {
            jFrame.setVisible(false);
            new userLogin().setVisible(true);

        }
    }

    public

    static void main(String[] args) {
        new forgetPassword();

    }

    public void setVisible(boolean b) {
    }
}
