import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Insert extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;   // ExpenseID
    private JTextField textField_1; // Amount
    private JComboBox<String> comboBox;

    // Oracle DB details
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; // or :xe if needed
    private static final String USERNAME = "c#vishnu";  // match your schema
    private static final String PASSWORD = "453";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Insert frame = new Insert();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Insert() {
        setTitle("Add Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Add Expense");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(157, 23, 134, 20);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Expense ID");
        lblNewLabel_1.setBounds(63, 85, 71, 20);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(150, 86, 112, 18);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Category");
        lblNewLabel_2.setBounds(63, 124, 71, 20);
        contentPane.add(lblNewLabel_2);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Food", "Travel", "Entertainment", "Shopping"}));
        comboBox.setBounds(150, 120, 112, 20);
        contentPane.add(comboBox);

        JLabel lblNewLabel_3 = new JLabel("Amount");
        lblNewLabel_3.setBounds(63, 156, 71, 20);
        contentPane.add(lblNewLabel_3);

        textField_1 = new JTextField();
        textField_1.setBounds(150, 153, 112, 18);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("Add");
        btnNewButton.setBounds(178, 208, 84, 20);
        contentPane.add(btnNewButton);

        // Action listener for Add button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertExpense();
            }
        });
    }

    private void insertExpense() {
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to DB
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Prepare SQL insert
            String sql = "INSERT INTO ExpenseTracker (ExpenseID, Category, Amount) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Get values from form
            int expenseId = Integer.parseInt(textField.getText());
            String category = (String) comboBox.getSelectedItem();
            double amount = Double.parseDouble(textField_1.getText());

            // Bind parameters
            pstmt.setInt(1, expenseId);
            pstmt.setString(2, category);
            pstmt.setDouble(3, amount);

            // Execute insert
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Expense added successfully!");
            }

            // Close connection
            pstmt.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

