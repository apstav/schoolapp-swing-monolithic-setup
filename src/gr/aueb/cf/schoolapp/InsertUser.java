package gr.aueb.cf.schoolapp;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import gr.aueb.cf.schoolapp.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class InsertUser extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	
	/**
	 * Create the frame.
	 */
	public InsertUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsertUser.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Χρήστη");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(new Color(165, 42, 42));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(82, 56, 84, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(165, 42, 42));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(82, 99, 84, 17);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBackground(new Color(255, 255, 255));
		txtUsername.setBounds(180, 56, 150, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO USER (USERNAME, PASSWORD) VALUES(?, ?)";
				String inputPassword;
				String inputUsername;
				int n;
				
				try (Connection conn = DBUtil.getConnection();
					PreparedStatement p = conn.prepareStatement(sql); ){
					
					inputUsername = txtUsername.getText().trim();
					inputPassword = String.valueOf(txtPassword.getPassword()).trim();
					
					if (inputPassword.equals("") || (inputUsername.equals(""))) {
						return;
					}
					
					int workload = 12;
					String salt = BCrypt.gensalt(workload);
					String hashedPassword = BCrypt.hashpw(inputPassword, salt);
					
					p.setString(1, inputUsername);
					p.setString(2, hashedPassword);
					
					n = p.executeUpdate();
					JOptionPane.showMessageDialog(null, n + "records inserted", 
							"INSERT", JOptionPane.PLAIN_MESSAGE);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}

		
		});
		btnInsert.setBackground(new Color(169, 169, 169));
		btnInsert.setForeground(new Color(0, 0, 128));
		btnInsert.setBounds(209, 176, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(true);
				Main.getInsertUser().setVisible(false);
				
			}
		});
		btnClose.setBackground(new Color(169, 169, 169));
		btnClose.setForeground(new Color(0, 0, 128));
		btnClose.setBounds(308, 176, 89, 23);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 163, 414, 2);
		contentPane.add(separator);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(180, 97, 150, 20);
		contentPane.add(txtPassword);
		
		JPanel panel = new JPanel();
		panel.setBounds(31, 22, 377, 125);
		contentPane.add(panel);
	}
}
