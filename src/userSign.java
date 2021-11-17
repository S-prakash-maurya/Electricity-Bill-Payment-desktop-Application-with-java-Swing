
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.*;

import java.awt.event.*;
import java.sql.PreparedStatement;
import java.awt.*;

public class userSign implements ActionListener {
    JFrame loginFrame;
    private Container loginContainer;

    private JPanel loginJPanel = new JPanel();
    private JLabel idLabel = new JLabel("ID :");
    private JTextField idField = new JTextField(); // Customer Name
    private JLabel nameLabel = new JLabel("NAME :");
    private JTextField nameField = new JTextField(); // Customer Father Name
    private JLabel passwordLabel = new JLabel("PASSWORD :");
    private JTextField passwordField = new JTextField(); // Customer Contact Number
    private JLabel dateLabel = new JLabel("DATE :");
    private JTextField dateField = new JTextField(); // Customer Address
    private JLabel contectLabel = new JLabel("CONTECT :");
    private JTextField contectField = new JTextField(); // Customer Identity Card Number
    private JButton subButton = new JButton("Submit");
    private JButton canButton = new JButton("Cancel");

    public userSign() {
        loginFrameUi();
        loginUi();
    }

    private void loginFrameUi() {
        loginFrame = new JFrame();
        loginFrame.setTitle("Sign-Up ");
        loginFrame.setSize(800, 600);
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginContainer = loginFrame.getContentPane();
        loginContainer.setBackground(Color.cyan);
        loginContainer.setLayout(null);
    }

    private void loginUi() {

        JLabel textLabel = new JLabel("Sign - Up..");
        textLabel.setBounds(300, 5, 250, 80);
        textLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
        textLabel.setForeground(Color.black);
        textLabel.setBackground(Color.GREEN);

        loginContainer.add(textLabel);

        loginJPanel.setBounds(100, 80, 600, 400);
        loginJPanel.setBackground(Color.YELLOW);
        loginJPanel.setLayout(null);
        loginJPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Sign-UP", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        loginContainer.add(loginJPanel);

        idLabel.setBounds(110, 100, 200, 30);
        idField.setBounds(240, 100, 200, 30);
        // idField.setEditable(false);

        nameLabel.setBounds(110, 140, 200, 30);
        nameField.setBounds(240, 140, 200, 30);

        passwordLabel.setBounds(110, 180, 200, 30);
        passwordField.setBounds(240, 180, 200, 30);

        dateLabel.setBounds(110, 220, 200, 30);
        dateField.setBounds(240, 220, 200, 30);

        String date = new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
        dateField.setText(date);

        contectLabel.setBounds(110, 260, 200, 30);
        contectField.setBounds(240, 260, 200, 30);

        subButton.setBounds(255, 300, 80, 30);
        canButton.setBounds(350, 300, 80, 30);

        loginJPanel.add(idLabel);
        loginJPanel.add(idField);

        loginJPanel.add(nameLabel);
        loginJPanel.add(nameField);

        loginJPanel.add(passwordLabel);
        loginJPanel.add(passwordField);

        loginJPanel.add(dateLabel);
        loginJPanel.add(dateField);

        loginJPanel.add(contectLabel);
        loginJPanel.add(contectField);

        subButton.addActionListener(this);
        loginJPanel.add(subButton);

        canButton.addActionListener(this);
        loginJPanel.add(canButton);

        idLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contectLabel.setFont(new Font("Arial", Font.BOLD, 18));

        idField.setFont(new Font("Arial", Font.BOLD, 18));
        nameField.setFont(new Font("Arial", Font.BOLD, 18));
        passwordField.setFont(new Font("Arial", Font.BOLD, 18));
        dateField.setFont(new Font("Arial", Font.BOLD, 18));
        contectField.setFont(new Font("Arial", Font.BOLD, 18));
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == subButton) {
            dbConnection connection = new dbConnection();
            try {
                String sql = "insert into admin (a_id ,  a_name , Password , b_date , a_contect ) values (?,?,?,?,?) ";
                PreparedStatement ps = connection.c.prepareStatement(sql);

                ps.setString(1, idField.getText());
                ps.setString(2, nameField.getText());
                ps.setString(3, passwordField.getText());
                ps.setString(4, dateField.getText());
                ps.setString(5, contectField.getText());

                int i = ps.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "successfully inserted..");
                }

                idField.setText(" ");
                nameField.setText(" ");
                passwordField.setText(" ");
                // dateField.setText(" ");
                contectField.setText(" ");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == canButton) {
            loginFrame.setVisible(false);
            new userLogin().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new userSign();
    }

    public void setVisible(boolean b) {
    }

}
