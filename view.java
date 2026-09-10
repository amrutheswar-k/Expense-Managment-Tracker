import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class view extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTable table;

    // Oracle Database details - updated to FREEPDB1 for Oracle 23ai / 21c Free
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    private static final String USERNAME = "vishnu"; // Or "c#vishnu" / "c##vishnu" depending on Step 2
    private static final String PASSWORD = "453";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                view frame = new view();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public view() {
        setTitle("Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(34, 33, 420, 300);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null},
        		{null, null, null},
        		{null, null, null},
        	},
        	new String[] {
        		"EXPENSEID", "CATEGORY", "AMOUNT"
        	}
        ));
        scrollPane.setViewportView(table);

        loadData();
    }

    private void loadData() {
        String sql = "SELECT ExpenseID, Category, Amount FROM ExpenseTracker";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Try-with-resources handles closing Connection, PreparedStatement, and ResultSet automatically
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println("Database connected successfully!");

                int rowCount = 0;
                while (rs.next()) {
                    int expenseId = rs.getInt("ExpenseID");
                    String category = rs.getString("Category");
                    double amount = rs.getDouble("Amount");

                    model.addRow(new Object[] { expenseId, category, amount });
                    rowCount++;
                }

                System.out.println("Data loaded successfully! Total rows retrieved: " + rowCount);
            }
        } catch (Exception e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}