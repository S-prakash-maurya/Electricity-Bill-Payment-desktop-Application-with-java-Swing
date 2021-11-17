import java.awt.event.*;
import java.sql.PreparedStatement;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;
import javax.swing.border.*;

public class connectionC implements ActionListener {

    private JFrame cFrame = new JFrame();
    private Container cContainer;
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable jTable = new JTable();

    private JLabel searchLabel = new JLabel("   Search");
    private JButton deleteButton = new JButton("delete");
    private JTextField searchText = new JTextField();
    private JButton returnButton = new JButton("RETURN ");

    public connectionC() {
        findData();
        aboutFrame();
        aboutUI();
    }

    private void findData() {
        try {
            dbConnection tConnection = new dbConnection();
            String sql = "select * from connections";
            PreparedStatement ps = tConnection.c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            jTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void aboutFrame() {
        cFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        cFrame.setSize(d.height, d.width);
        cFrame.setLayout(null);
        cFrame.setVisible(true);
        cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cContainer = cFrame.getContentPane();
        cContainer.setBackground(Color.MAGENTA);
        cContainer.setLayout(null);
    }

    private void aboutUI() {

        jScrollPane.setBounds(100, 110, 1320, 600);
        jScrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        cContainer.add(jScrollPane);

        jTable.setBackground(new Color(255, 255, 255));
        jTable.setForeground(Color.BLACK);
        jTable.setDragEnabled(false);
        jTable.setBorder(new BevelBorder(BevelBorder.RAISED));
        jTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = jTable.getSelectedRow();
                enable();
                searchText.setText(jTable.getModel().getValueAt(row, 0).toString());
            }

            private void enable() {
                deleteButton.setEnabled(true);
            }
        });
        jScrollPane.setViewportView(jTable);

        searchLabel.setBounds(120, 63, 100, 30);
        searchLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        searchLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        cContainer.add(searchLabel);

        searchText.setBounds(260, 62, 820, 35);
        searchText.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        cContainer.add(searchText);

        deleteButton.setBounds(1150, 65, 100, 30);
        deleteButton.setEnabled(false);

        deleteButton.addActionListener(this);
        deleteButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        cContainer.add(deleteButton);

        returnButton.setBounds(1280, 65, 100, 30);
        returnButton.addActionListener(this);
        cContainer.add(returnButton);

        cContainer.repaint();
        cContainer.revalidate();
    }

    public static void main(String[] args) {
        new connectionC();
    }

    public void setVisible(boolean b) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            try {
                dbConnection dlConnection = new dbConnection();
                int id = Integer.parseInt(searchText.getText());

                String sql = "delete from connections where C_id =" + id + "";
                PreparedStatement ps = dlConnection.c.prepareStatement(sql);

                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Do you want to continue  ?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.NO_OPTION) {
                } else if (response == JOptionPane.YES_OPTION) {
                    int rs = ps.executeUpdate();
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(null, "Record deleted successfully");
                        findData();
                    } else
                        JOptionPane.showMessageDialog(null, "Record not found");
                } else if (response == JOptionPane.CLOSED_OPTION) {
                }
                ps.close();

            } catch (

            Exception ae) {

            }
        }

        if (e.getSource() == returnButton) {
            cFrame.setVisible(false);
            new HomeMenu().setVisible(true);
        }
    }
}
