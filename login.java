import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uname;
	private JTextField upass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(123, 89, 89, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(123, 128, 59, 12);
		contentPane.add(lblNewLabel_1);
		
		uname = new JTextField();
		uname.setBounds(190, 90, 96, 18);
		contentPane.add(uname);
		uname.setColumns(10);
		
		upass = new JTextField();
		upass.setBounds(190, 125, 96, 18);
		contentPane.add(upass);
		upass.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uzn = uname.getText();
				String pxy = upass.getText();
				
				if (uzn.equals("admin")&& pxy.equals("123")) {
					new face().setVisible(true);
					JOptionPane.showMessageDialog(null, "Login Succesful");
				}else {
					JOptionPane.showMessageDialog(null, "Login Failed");
					
				}
			}
		});
		btnNewButton.setBounds(190, 165, 84, 20);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(149, 45, 51, -23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("LOGIN CREDINTIALS");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(159, 23, 159, 27);
		contentPane.add(lblNewLabel_3);

	}
}
