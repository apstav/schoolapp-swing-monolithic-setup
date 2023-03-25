package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.schoolapp.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;



	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("User Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setBounds(16, 45, 122, 30);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setBounds(27, 94, 100, 30);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(170, 45, 144, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 182, 416, 2);
		contentPane.add(separator);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsername = txtUsername.getText().trim();
				String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
				String password = System.getenv("SCHOOL_ADMIN_PASSWORD");
				String hashedPassword;
				
				if (inputUsername.equals("") || inputPassword.equals("")) {
					return;
				}
				
				if (inputUsername.equals("admin") && (inputPassword.equals(password))) {
					Main.getLoginPage().setVisible(false);
					Main.getSearchUser().setVisible(true);
				} else {
					String sql = "SELECT PASSWORD FROM USER WHERE USERNAME = ?";
					
					try (Connection conn = DBUtil.getConnection();
							PreparedStatement p = conn.prepareStatement(sql)) {
						
						p.setString(1, inputUsername);
						ResultSet rs = p.executeQuery();
						
						if(rs.next()) {
							hashedPassword = rs.getString("PASSWORD");
						} else {
							JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						if(BCrypt.checkpw(inputPassword, hashedPassword)) {
							Main.getMenu().setVisible(true);
							Main.getLoginPage().setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "User in Password", "Error", JOptionPane.WARNING_MESSAGE);
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setForeground(new Color(0, 0, 128));
		btnLogin.setBounds(197, 209, 100, 35);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(170, 94, 144, 30);
		contentPane.add(txtPassword);
	}
}
