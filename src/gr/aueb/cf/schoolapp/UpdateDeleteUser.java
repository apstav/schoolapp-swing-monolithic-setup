package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.cj.protocol.Resultset;

import gr.aueb.cf.schoolapp.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class UpdateDeleteUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private Connection conn;
	private PreparedStatement p;
	private ResultSet rs;
	private JPasswordField txtPassword;
	
	/**
	 * Create the frame.
	 */
	public UpdateDeleteUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
//				String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE?";
//				
//				try {
//					Connection conn = DBUtil.getConnection();
//					p = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
//							ResultSet.CONCUR_UPDATABLE); 
//					
//					p.setString(1, Main.getSearchForm().getInputLastname() + '%');
//					rs = p.executeQuery();
//					
//					if (rs.next()) {
//						txtId.setText(Integer.toString(rs.getInt("ID")));
//						txtFirstname.setText(rs.getString("FIRSTNAME"));
//						txtUsername.setText(rs.getString("LASTNAME"));
//					} else {
//						return;
//					}
//					
//				}catch (SQLException e1) {
//					e1.printStackTrace();
//				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (rs != null) rs.close();
					if (p != null) p.close();
					if (conn != null) conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateDeleteUser.class.getResource("/resources/eduv2.png")));
		setTitle("Ενημέρωση / Διαγραφή Χρήστη");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 443, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(18, 75, 82, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(18, 117, 82, 14);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setBounds(117, 72, 170, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response;
				int numberOfRowsAffected;
				String inputUsername = txtUsername.getText().trim();
				String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
				String sql = "DELETE FROM USER WHERE USERNAME = ?";
				String hashedPassword;
				
				if (inputUsername.equals("") || inputPassword.equals("")) {
					return;
				}
				
				try {
					conn = DBUtil.getConnection();
					p = conn.prepareStatement(sql);
					p.setString(1, inputUsername);
					
					
					response = JOptionPane.showConfirmDialog(null, "Είστε Σίγουρος;", "Delete", 
							JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						numberOfRowsAffected = p.executeUpdate();
						JOptionPane.showMessageDialog(null, numberOfRowsAffected + " rows deleted", "Delete", 
								JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(24, 215, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsername = txtUsername.getText().trim();
				String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
				
				String hashedPassword;
				
				if (inputUsername.equals("") || inputPassword.equals("")) {
					return;
				}
				
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
							Main.getInsertNewPassword().setVisible(true);
							Main.getUpdateDeleteUser().setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Wrong Password", "Error", JOptionPane.WARNING_MESSAGE);
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnUpdate.setBounds(126, 215, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setBounds(227, 215, 89, 23);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 162, 413, 2);
		contentPane.add(separator);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(117, 114, 170, 20);
		contentPane.add(txtPassword);
	}
}
