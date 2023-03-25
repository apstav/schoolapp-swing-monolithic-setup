package gr.aueb.cf.schoolapp;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection conn;

	

	/**
	 * Create the frame.
	 */
	public Menu() {
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				String url = "jdbc:mysql://localhost:3306/school?serverTimeZone=UTC";
//				String username = "root";
//				String password = "Eisaivlakas1@";
//				
//				try {
//					//Class.forName("com.mysql.cj.jdbc.Driver);
//					conn = DriverManager.getConnection(url, username, password);
//					System.out.println("Connected!");
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
		setTitle("Διαχείρiση Εκπαιδευτικού Συστήματος");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resources/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblNewLabel.setForeground(new Color(34, 139, 34));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(44, 19, 345, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(45, 20, 345, 46);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 414, 2);
		contentPane.add(separator);
		
		JButton btnTeachers = new JButton("");
		btnTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSearchForm().setVisible(true);
			}
		});
		btnTeachers.setBounds(23, 98, 33, 23);
		contentPane.add(btnTeachers);
		
		JButton btnStudents = new JButton("");
		btnStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSearchStudentForm().setVisible(true);
			}
		});
		btnStudents.setBounds(23, 132, 33, 23);
		contentPane.add(btnStudents);
		
		JLabel lblTeachers = new JLabel("Εκπαιδευτές");
		lblTeachers.setForeground(new Color(128, 0, 0));
		lblTeachers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTeachers.setBounds(82, 99, 142, 20);
		contentPane.add(lblTeachers);
		
		JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
		lblStudents.setForeground(new Color(128, 0, 0));
		lblStudents.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStudents.setBounds(82, 133, 142, 20);
		contentPane.add(lblStudents);
	}



//	public static Connection getConn() {
//		return conn;
//	}
	

}
