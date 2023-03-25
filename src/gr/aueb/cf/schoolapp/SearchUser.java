package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private String inputUsername;
	private String inputPassword;

	

	/**
	 * Create the frame.
	 */
	public SearchUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchUser.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή / Αναζήτηση Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 361);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 11, 386, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setBounds(107, 45, 159, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsernamePass = new JLabel("Username / Password");
		lblUsernamePass.setForeground(new Color(128, 0, 0));
		lblUsernamePass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsernamePass.setBounds(96, 11, 180, 23);
		panel.add(lblUsernamePass);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputUsername = txtUsername.getText().trim();
				inputPassword = txtUsername.getText().trim();
				Main.getSearchForm().setEnabled(false);
				Main.getUpdateDeleteUser().setVisible(true);			}
		});
		btnSearch.setForeground(new Color(0, 0, 139));
		btnSearch.setBounds(136, 84, 100, 40);
		panel.add(btnSearch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(24, 152, 386, 93);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnInsert = new JButton("Εισαγωγή");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(false);
				Main.getInsertUser().setVisible(true);
			}
		});
		btnInsert.setForeground(new Color(0, 0, 139));
		btnInsert.setBounds(136, 29, 100, 40);
		panel_1.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setForeground(new Color(0, 0, 139));
		btnClose.setBounds(292, 265, 100, 40);
		contentPane.add(btnClose);
	}



	public String getInputUsername() {
		return inputUsername;
	}

	


	
}
