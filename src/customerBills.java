import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class customerBills implements ActionListener {

    private JFrame customerFrame = new JFrame();
    private Container customerContainer;
    private JPanel billPanel = new JPanel(null);

    private JLabel nameLabel = new JLabel("NAME");
    private JLabel dateLabel = new JLabel("DATE");
    private JLabel typeLabel = new JLabel("C. TYPE");
    private JLabel amountLabel = new JLabel("AMOUNT");
    private JLabel numberLabel = new JLabel("C. NUMBER");
    private JLabel mobileLabel = new JLabel("MOBILE.");
    private JLabel payeLabel = new JLabel("PAYE NAME");

    private JTextField nameField = new JTextField();
    private JTextField dateField = new JTextField();
    private JComboBox typeBox = new JComboBox();
    private JTextField amountField = new JTextField();
    private JTextField numberField = new JTextField();
    private JTextField mobilField = new JTextField();
    private JTextField payeField = new JTextField();
    private JButton sButton = new JButton("Submit");
    private JButton cButton = new JButton("Cancel");

    String cd;

    public customerBills() {
        billFrame();
        billUi();
        currentDate();
    }

    public void currentDate() {
        DateTimeFormatter dTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        cd = dTimeFormatter.format(localDate);

        dateField.setText(cd);
    }

    private void billFrame() {

        customerFrame.setSize(900, 700);
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setLayout(null);
        customerFrame.setVisible(true);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerContainer = customerFrame.getContentPane();
        customerContainer.setBackground(Color.GREEN);
        customerContainer.setLayout(null);
    }

    private void billUi() {

        billPanel.setBounds(150, 50, 600, 550);
        billPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Bill Payments ",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        billPanel.setBackground(Color.cyan);
        customerContainer.add(billPanel);

        // ______________________________________ label with
        // field______________________________________________/

        numberLabel.setBounds(100, 100, 80, 30);
        billPanel.add(numberLabel);

        numberField.setBounds(200, 100, 250, 30);
        numberField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        billPanel.add(numberField);
        // ______________________________________ label with
        // field______________________________________________/
        nameLabel.setBounds(100, 140, 80, 30);
        billPanel.add(nameLabel);

        nameField.setBounds(200, 140, 250, 30);
        nameField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        billPanel.add(nameField);
        // ______________________________________ label with
        // field______________________________________________/
        dateLabel.setBounds(100, 260, 80, 30);
        billPanel.add(dateLabel);

        dateField.setBounds(200, 260, 250, 30);
        dateField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        billPanel.add(dateField);
        // ______________________________________ label with
        // field______________________________________________/
        typeLabel.setBounds(100, 180, 80, 30);
        billPanel.add(typeLabel);

        typeBox.setModel(new DefaultComboBoxModel(new String[] { "Domestic ", "Commercial" }));
        typeBox.setForeground(new Color(47, 79, 79));
        typeBox.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        typeBox.setBounds(200, 180, 250, 30);
        billPanel.add(typeBox);
        // ______________________________________ label with
        // field______________________________________________/
        amountLabel.setBounds(100, 220, 80, 30);
        billPanel.add(amountLabel);

        amountField.setBounds(200, 220, 250, 30);
        amountField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        billPanel.add(amountField);
        // ______________________________________ label with
        // field______________________________________________/

        mobileLabel.setBounds(100, 300, 80, 30);
        billPanel.add(mobileLabel);

        mobilField.setBounds(200, 300, 250, 30);
        mobilField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        billPanel.add(mobilField);
        // ______________________________________ label with
        // field______________________________________________/
        payeLabel.setBounds(100, 340, 80, 30);
        billPanel.add(payeLabel);

        payeField.setBounds(200, 340, 250, 30);
        payeField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        billPanel.add(payeField);

        nameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        dateLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        typeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        amountLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        numberLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        mobileLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        payeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        // ***********Buttons ************ */

        sButton.setBounds(225, 390, 80, 30);
        sButton.addActionListener(this);
        sButton.setBackground(Color.BLACK);
        sButton.setForeground(Color.YELLOW);
        billPanel.add(sButton);

        cButton.setBounds(325, 390, 80, 30);
        cButton.addActionListener(this);
        cButton.setBackground(Color.BLACK);
        cButton.setForeground(Color.YELLOW);
        billPanel.add(cButton);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sButton) {
            try {
                dbConnection conn = new dbConnection();
                String sql = "insert into bill_payment( c_id  ,c_name  ,ctype ,amount ,pay_date,contect ,payy) values (?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.c.prepareStatement(sql);

                ps.setString(1, numberField.getText());
                ps.setString(2, nameField.getText());
                ps.setString(3, (String) typeBox.getSelectedItem());
                ps.setString(4, amountField.getText());
                ps.setString(5, dateField.getText());
                ps.setString(6, mobilField.getText());
                ps.setString(7, payeField.getText());

                int i = ps.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "payed your bill successfully");
                }

                numberField.setText("");
                nameField.setText("");
                amountField.setText("");
                dateField.setText("");
                mobilField.setText("");
                payeField.setText("");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cButton) {
            customerFrame.setVisible(false);
            new HomeMenu().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new customerBills();
    }

    public void setVisible(boolean b) {
    }
}
