package gr.aueb.cf.schoolapp;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class InsertForm extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastname;
	private JTextField txtFirstname; 

	
	/**
	 * Create the frame.
	 */
	public InsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLastname.setText("");
				txtFirstname.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsertForm.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Μαθητή");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setForeground(new Color(165, 42, 42));
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastname.setBounds(82, 65, 84, 20);
		contentPane.add(lblLastname);
		
		JLabel lblFirstname = new JLabel("Όνομα");
		lblFirstname.setForeground(new Color(165, 42, 42));
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setBounds(82, 108, 84, 17);
		contentPane.add(lblFirstname);
		
		txtLastname = new JTextField();
		txtLastname.setBackground(new Color(255, 255, 255));
		txtLastname.setBounds(180, 65, 150, 20);
		contentPane.add(txtLastname);
		txtLastname.setColumns(50);
		
		txtFirstname = new JTextField();
		txtFirstname.setBackground(new Color(255, 255, 255));
		txtFirstname.setBounds(180, 106, 150, 20);
		contentPane.add(txtFirstname);
		txtFirstname.setColumns(50);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES(?, ?)";
				String inputLastname;
				String inputFirstname;
				int n;
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement p = conn.prepareStatement(sql);){
					
					
					inputLastname = txtLastname.getText().trim();
					inputFirstname = txtFirstname.getText().trim();
					
					if (inputLastname.equals("") || (inputFirstname.equalsIgnoreCase(""))) {
						return;
					}
					
					p.setString(1, inputFirstname);
					p.setString(2, inputLastname);
					
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
				Main.getSearchForm().setEnabled(true);
				Main.getInsertForm().setVisible(false);
				
			}
		});
		btnClose.setBackground(new Color(169, 169, 169));
		btnClose.setForeground(new Color(0, 0, 128));
		btnClose.setBounds(308, 176, 89, 23);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 163, 414, 2);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(65, 35, 303, 117);
		contentPane.add(panel);
	}
}
