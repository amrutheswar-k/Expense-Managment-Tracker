import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EXPENSE MANGMENT TRACKER");
		lblNewLabel.setBounds(139, 21, 187, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("ADD ");
		btnNewButton.setBounds(169, 83, 90, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("VIEW");
		btnNewButton_1.setBounds(169, 138, 90, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.setBounds(169, 195, 90, 29);
		contentPane.add(btnNewButton_2);

	}
}
