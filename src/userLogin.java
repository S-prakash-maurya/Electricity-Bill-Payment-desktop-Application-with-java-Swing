import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userLogin implements ActionListener {
    private JFrame frame;
    private Container c;

    private JPanel signUPanel = new JPanel();

    private JTextField textFieldI = new JTextField();
    private JTextField textFieldP = new JTextField();
    private JLabel jLabelE = new JLabel("ID :");
    private JLabel jLabelP = new JLabel("PASSWORD :");
    private JButton loginButton = new JButton("LOGIN");
    private JButton signUpButton = new JButton("SIGN - UP");
    private JButton forgetButton = new JButton("Forget Password");

    public userLogin() {
        prepareGUI();
        addComponents();

    }

    private void prepareGUI() {
        frame = new JFrame();
        frame.setTitle("Electricity Management");
        frame.setSize(980, 760);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = frame.getContentPane();
        c.setBackground(Color.BLUE);
        c.setLayout(null);

    }

    private void addComponents() {

        JLabel textLabel = new JLabel("User LoGin ..");
        textLabel.setBounds(300, 35, 350, 50);
        textLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 38));
        textLabel.setForeground(Color.white);
        textLabel.setBackground(Color.GREEN);
        textLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        c.add(textLabel);

        signUPanel.setBounds(160, 120, 640, 540);
        signUPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        signUPanel.setBackground(Color.ORANGE);
        signUPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Login Here", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        signUPanel.setLayout(null);
        c.add(signUPanel);

        // ************Labels............ */
        jLabelE.setBounds(100, 180, 80, 30);
        jLabelE.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        signUPanel.add(jLabelE);

        jLabelP.setBounds(100, 220, 120, 30);
        jLabelP.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        signUPanel.add(jLabelP);
        // ***************text field for id and password */
        textFieldI.setBounds(220, 180, 260, 30);
        textFieldI.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        signUPanel.add(textFieldI);

        textFieldP.setBounds(220, 220, 260, 30);
        textFieldP.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        signUPanel.add(textFieldP);
        // ****************operation Buttons*********** */
        loginButton.setBounds(250, 265, 80, 30);
        loginButton.addActionListener(this);
        loginButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.YELLOW);

        signUPanel.add(loginButton);

        signUpButton.setBounds(350, 265, 90, 30);
        signUpButton.addActionListener(this);
        signUpButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        signUpButton.setBackground(Color.BLACK);
        signUpButton.setForeground(Color.YELLOW);
        signUpButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        signUPanel.add(signUpButton);

        forgetButton.setBounds(245, 305, 200, 30);
        forgetButton.addActionListener(this);
        forgetButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        forgetButton.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        forgetButton.setForeground(Color.red);
        signUPanel.add(forgetButton);
    }

    public void actionPerformed(ActionEvent aEvent) {
        if (aEvent.getSource() == loginButton) {
            // Boolean status = false;
            try {

                dbConnection connection = new dbConnection();

                String lId = textFieldI.getText();
                String lPassword = textFieldP.getText();

                String sql = " select a_id ,Password from admin where a_Id= ? and Password = ? ";
                PreparedStatement ps = connection.c.prepareStatement(sql);

                ps.setString(1, lId);
                ps.setString(2, lPassword);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    frame.setVisible(false);
                    System.out.println("connected");
                    new HomeMenu().setVisible(true);
                    JOptionPane.showMessageDialog(null, "You have successfully logged in");
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Username & Password");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (aEvent.getSource() == signUpButton) {
            frame.setVisible(false);
            new userSign().setVisible(true);

        }

        else if (aEvent.getSource() == forgetButton) {
            frame.setVisible(false);
            new forgetPassword().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new userLogin();

    }

    public void setVisible(boolean b) {
    }

}
