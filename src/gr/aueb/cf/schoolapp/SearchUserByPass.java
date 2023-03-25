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

public class SearchUserByPass extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPassword;
	private String inputPassword;

	

	/**
	 * Create the frame.
	 */
	public SearchUserByPass() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchUserByPass.class.getResource("/resources/eduv2.png")));
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
		
		txtPassword = new JTextField();
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.setBounds(107, 45, 159, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(144, 11, 85, 23);
		panel.add(lblUsername);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputPassword = txtPassword.getText().trim();
				Main.getSearchForm().setEnabled(false);
				Main.getUpdateDeleteForm().setVisible(true);			}
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



	public String getInputPassword() {
		return inputPassword;
	}
	
	
}
