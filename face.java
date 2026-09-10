import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class face extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    face frame = new face();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public face() {
        setTitle("Main Menu - Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("EXPENSE MANAGEMENT TRACKER");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTitle.setBounds(80, 21, 280, 23);
        contentPane.add(lblTitle);
        
        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(169, 75, 100, 30);
        contentPane.add(btnAdd);
        
        JButton btnView = new JButton("VIEW");
        btnView.setBounds(169, 130, 100, 30);
        contentPane.add(btnView);
        
        JButton btnExit = new JButton("EXIT");
        btnExit.setBounds(169, 185, 100, 30);
        contentPane.add(btnExit);

        // --- Action Listeners ---

        // 1. Open the Insert/Add Expense Window
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Insert insertWindow = new Insert();
                insertWindow.setVisible(true);
            }
        });

        // 2. Open the View Expenses Window
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view viewWindow = new view();
                viewWindow.setVisible(true);
            }
        });

        // 3. Exit the Application
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}