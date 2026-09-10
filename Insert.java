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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Insert extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_1; // Amount input
    private JComboBox<String> comboBox; // Category dropdown

    // Updated Oracle Database credentials for FREEPDB1
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    private static final String USERNAME = "vishnu";
    private static final String PASSWORD = "453";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Insert frame = new Insert();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Insert() {
        setTitle("Add Expense");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes this window without exiting the whole app
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Add Expense");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitle.setBounds(157, 23, 134, 25);
        contentPane.add(lblTitle);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblCategory.setBounds(63, 88, 71, 20);
        contentPane.add(lblCategory);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Food", "Travel", "Entertainment", "Groceries", "Utilities"}));
        comboBox.setBounds(164, 88, 150, 25);
        contentPane.add(comboBox);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblAmount.setBounds(63, 140, 71, 20);
        contentPane.add(lblAmount);

        textField_1 = new JTextField();
        textField_1.setBounds(164, 140, 150, 25);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(178, 200, 100, 30);
        contentPane.add(btnAdd);

        // Action listener for Add button
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertExpense();
            }
        });
    }

    private void insertExpense() {
        String amountText = textField_1.getText().trim();

        // 1. Input Validation
        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an amount!", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String category = (String) comboBox.getSelectedItem();

        // 2. SQL Query: Auto-generates non-repeating ExpenseID starting from 1
        String sql = "INSERT INTO ExpenseTracker (ExpenseID, Category, Amount) " +
                     "VALUES ((SELECT NVL(MAX(ExpenseID), 0) + 1 FROM ExpenseTracker), ?, ?)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setString(1, category);
                pstmt.setDouble(2, amount);

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Expense added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    textField_1.setText(""); // Reset text field after saving
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}