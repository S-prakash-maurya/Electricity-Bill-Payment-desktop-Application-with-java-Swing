
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
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;

public class createConnection implements ActionListener {
    private JFrame cFrame = new JFrame();
    private Container conContainer;
    private JPanel conPanel = new JPanel(null);

    private JTextField connectionId = new JTextField();
    private JLabel connectionLabel = new JLabel("CONNECTION ID :");

    private JTextField nameField = new JTextField();
    private JLabel nameLabel = new JLabel("NAME :");
    private JTextField birthField = new JTextField();
    private JLabel birthLabel = new JLabel("DATE OF BIRTH :");
    private JTextField addressField = new JTextField();
    private JLabel addressLabel = new JLabel("ADDRESS");
    private JTextField contectField = new JTextField();
    private JLabel contectLabel = new JLabel("CONTECT :");
    private JTextField identityField = new JTextField();
    private JLabel identityLabel = new JLabel("VOTER ID :");
    private JTextField dateField = new JTextField();
    private JLabel dateLabel = new JLabel("DATE :");
    private JComboBox typeBox = new JComboBox();
    private JLabel typeLabel = new JLabel("CONN. TYPE :");

    private JButton sButton = new JButton("Submit");
    private JButton cButton = new JButton("Cancel");

    private String cdt;

    public createConnection() {
        preparedGUI();
        addComponents();
        currentDate();
    }

    private void preparedGUI() {
        cFrame.setSize(800, 750);
        cFrame.setLocationRelativeTo(null);
        cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cFrame.setLayout(null);
        cFrame.setVisible(true);
        conContainer = cFrame.getContentPane();
        conContainer.setLayout(null);
        conContainer.setBackground(Color.cyan);
    }

    public void currentDate() {
        DateTimeFormatter dTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        cdt = dTimeFormatter.format(localDate);

        dateField.setText(cdt);
    }

    private void addComponents() {

        conPanel.setBounds(120, 100, 600, 500);
        conPanel.setBackground(Color.ORANGE);
        conPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Connection", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        conContainer.add(conPanel);

        // _______________Label with field_____________________//
        connectionLabel.setBounds(100, 60, 120, 30);
        conPanel.add(connectionLabel);
        connectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        connectionId.setBounds(250, 60, 250, 30);
        connectionId.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(connectionId);

        // _______________Label with field_____________________//

        nameLabel.setBounds(100, 100, 100, 30);
        nameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(nameLabel);

        nameField.setBounds(250, 100, 250, 30);
        nameField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(nameField);

        // _______________Label with field_____________________//

        birthLabel.setBounds(100, 140, 120, 30);
        conPanel.add(birthLabel);
        birthLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        birthField.setBounds(250, 140, 250, 30);
        birthField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(birthField);

        // _______________Label with field_____________________//

        addressLabel.setBounds(100, 180, 100, 30);
        conPanel.add(addressLabel);
        addressLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        addressField.setBounds(250, 180, 250, 30);
        addressField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(addressField);

        // _______________Label with field_____________________//

        contectLabel.setBounds(100, 220, 100, 30);
        contectLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(contectLabel);

        contectField.setBounds(250, 220, 250, 30);
        contectField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(contectField);

        // _______________Label with field_____________________//

        identityLabel.setBounds(100, 260, 100, 30);
        conPanel.add(identityLabel);
        identityLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));

        identityField.setBounds(250, 260, 250, 30);
        identityField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(identityField);

        // _______________Label with field_____________________//

        dateLabel.setBounds(100, 300, 100, 30);
        conPanel.add(dateLabel);

        dateField.setBounds(250, 300, 250, 30);
        dateField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        conPanel.add(dateField);

        // _______________Label with field_____________________//

        typeLabel.setBounds(100, 340, 120, 30);
        conPanel.add(typeLabel);

        typeBox.setModel(new DefaultComboBoxModel(new String[] { "Domestic ", "Commercial" }));
        typeBox.setForeground(new Color(47, 79, 79));
        typeBox.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        typeBox.setBounds(250, 340, 250, 30);
        conPanel.add(typeBox);

        // _______________Label with field_____________________//

        sButton.setBounds(280, 380, 80, 30);
        sButton.addActionListener(this);
        conPanel.add(sButton);

        cButton.setBounds(370, 380, 80, 30);
        cButton.addActionListener(this);
        conPanel.add(cButton);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == sButton) {

            try {

                dbConnection dConnection = new dbConnection();
                String sql = " insert into connections( C_id  ,C_name ,C_BDate,C_Address  ,C_contect ,C_Addhar_id ,Issue_Date ,Conn_type) values (?,?,?,?,?,?,?,?)";
                PreparedStatement ps = dConnection.c.prepareStatement(sql);

                ps.setString(1, connectionId.getText());
                ps.setString(2, nameField.getText());
                ps.setString(3, birthField.getText());
                ps.setString(4, addressField.getText());
                ps.setString(5, contectField.getText());
                ps.setString(6, identityField.getText());
                ps.setString(7, dateField.getText());
                ps.setString(8, (String) typeBox.getSelectedItem());

                int i = ps.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Connection Issued.");
                }

                connectionId.setText(" ");
                nameField.setText(" ");
                birthField.setText(" ");
                addressField.setText(" ");
                contectField.setText(" ");
                identityField.setText(" ");
                dateField.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == cButton) {
            cFrame.setVisible(false);
            new HomeMenu().setVisible(true);
        }
    }

    void setVisible(boolean b) {
    }

    public static void main(String[] args) {
        new createConnection();
    }
}
