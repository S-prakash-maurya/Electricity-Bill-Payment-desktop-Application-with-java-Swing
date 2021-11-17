import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.*;

import java.awt.*;

public class connectionD implements ActionListener {

    private JFrame cutConnFrame = new JFrame();
    private Container conContainer;
    private JScrollPane jScrollPane = new JScrollPane();
    private JTable jdTable = new JTable();
    private JButton bButton = new JButton("Back ");
    private JTextArea searchField = new JTextArea();

    private JLabel tlLabel = new JLabel("Connections list");

    public connectionD() {
        CustomerDetails();
        cutConnFrameUi();
        cutConnUi();

    }

    private void CustomerDetails() {

        try {
            dbConnection dConnection = new dbConnection();
            String sql = "select * from connections";
            PreparedStatement ps = dConnection.c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            jdTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            ps.close();

        } catch (Exception e) {
        }
    }

    private void cutConnFrameUi() {
        cutConnFrame.setBounds(60, 60, 1250, 800);
        cutConnFrame.setLocationRelativeTo(null);
        cutConnFrame.setLayout(null);
        cutConnFrame.setResizable(false);
        cutConnFrame.setVisible(true);
        conContainer = cutConnFrame.getContentPane();
        conContainer.setLayout(null);
        conContainer.setBackground(Color.orange);

    }

    private void cutConnUi() {

        tlLabel.setBounds(500, 10, 250, 50);
        tlLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 28));
        conContainer.add(tlLabel);

        bButton.setBounds(150, 80, 80, 30);
        bButton.addActionListener(this);
        bButton.setBackground(Color.white);
        conContainer.add(bButton);

        searchField.setBounds(250, 80, 650, 30);
        searchField.setBorder(new BevelBorder(BevelBorder.RAISED));
        searchField.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        searchField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent key) {
                if (!Character.isDigit(key.getKeyChar()))
                    key.consume();
            }

            public void keyReleased(KeyEvent key) {
                try {
                    dbConnection con = new dbConnection();
                    String sql = "select * from  connections where concat( C_id,C_Addhar_id) like ?";
                    PreparedStatement st = con.c.prepareStatement(sql);
                    st.setString(1, "%" + searchField.getText() + "%");
                    ResultSet rs = st.executeQuery();
                    jdTable.setModel(DbUtils.resultSetToTableModel(rs));
                    rs.close();
                    st.close();
                } catch (Exception e) {
                }
            }
        });
        conContainer.add(searchField);

        jScrollPane.setBounds(80, 120, 1000, 600);
        jScrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        conContainer.add(jScrollPane);

        jdTable.setBackground(new Color(255, 255, 255));
        jdTable.setForeground(Color.BLACK);
        jdTable.setDragEnabled(false);

        jdTable.setBorder(new BevelBorder(BevelBorder.RAISED));
        jdTable.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        jdTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = jdTable.getSelectedRow();
                searchField.setText(jdTable.getModel().getValueAt(row, 0).toString());
            }
        });
        jScrollPane.setViewportView(jdTable);

        conContainer.repaint();
        conContainer.revalidate();
    }

    public static void main(String[] args) {
        new connectionD();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            dbConnection sConnection = new dbConnection();
            if (ae.getActionCommand().equalsIgnoreCase("searchField")) {
                String sql = "select * from create_connection where concat( cid,id) like ?";
                PreparedStatement st = sConnection.c.prepareStatement(sql);
                st.setString(1, "%" + searchField.getText() + "%");
                ResultSet rs = st.executeQuery();
                jdTable.setModel(DbUtils.resultSetToTableModel(rs));
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ae.getSource() == bButton) {
            cutConnFrame.setVisible(false);
            new HomeMenu().setVisible(true);
        }
    }

    public void setVisible(boolean b) {
    }

}
