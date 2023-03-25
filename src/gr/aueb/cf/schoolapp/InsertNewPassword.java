package gr.aueb.cf.schoolapp;


import gr.aueb.cf.schoolapp.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewPassword extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JTextField txtUsername;

	
	/**
	 * Create the frame.
	 */
	public InsertNewPassword() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
//				txtUsername.setText("");
//				txtPassword.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsertNewPassword.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Νέου Κωδικού");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(new Color(165, 42, 42));
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassword.setBounds(49, 99, 117, 17);
		contentPane.add(lblNewPassword);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE USER SET PASSWORD=? WHERE USERNAME=?";
				String inputUsername;
				String inputOldPassword;
				String inputNewPassword;
				int n;
				
				try (Connection conn = DBUtil.getConnection();
					PreparedStatement p = conn.prepareStatement(sql); ){
					
					inputUsername = String.valueOf(txtUsername.getText()).trim();
					inputOldPassword = String.valueOf(txtOldPassword.getPassword()).trim();
					inputNewPassword = String.valueOf(txtNewPassword.getPassword()).trim();
					
					if (inputOldPassword.equals("") || (inputNewPassword.equals(""))) {
						return;
					}
					
					int workload = 12;
					String salt = BCrypt.gensalt(workload);
					String hashedPassword = BCrypt.hashpw(inputNewPassword, salt);
					
					p.setString(2, inputUsername);
					p.setString(1, hashedPassword);
					
					n = p.executeUpdate();
					JOptionPane.showMessageDialog(null, n + "records updated", 
							"UPDATE", JOptionPane.PLAIN_MESSAGE);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}

		
		});
		btnUpdate.setBackground(new Color(169, 169, 169));
		btnUpdate.setForeground(new Color(0, 0, 128));
		btnUpdate.setBounds(209, 176, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnClose.setBackground(new Color(169, 169, 169));
		btnClose.setForeground(new Color(0, 0, 128));
		btnClose.setBounds(308, 176, 89, 23);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 163, 414, 2);
		contentPane.add(separator);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(165, 42, 42));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(82, 54, 84, 17);
		contentPane.add(lblPassword);
		
		txtOldPassword = new JPasswordField();
		txtOldPassword.setBounds(195, 47, 170, 30);
		contentPane.add(txtOldPassword);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(195, 92, 170, 30);
		contentPane.add(txtNewPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setForeground(new Color(165, 42, 42));
		lblUsername.setBounds(66, 11, 100, 24);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(195, 5, 170, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
	}
}
