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

public class SearchForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastname;
	private String inputLastname;

	

	/**
	 * Create the frame.
	 */
	public SearchForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchForm.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτή");
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
		
		txtLastname = new JTextField();
		txtLastname.setBackground(new Color(255, 255, 255));
		txtLastname.setBounds(101, 45, 159, 20);
		panel.add(txtLastname);
		txtLastname.setColumns(10);
		
		JLabel lblLastname = new JLabel("Επώνυμο");
		lblLastname.setForeground(new Color(128, 0, 0));
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastname.setBounds(144, 11, 85, 23);
		panel.add(lblLastname);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLastname = txtLastname.getText().trim();
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
				Main.getSearchForm().setEnabled(false);
				Main.getInsertForm().setVisible(true);
			}
		});
		btnInsert.setForeground(new Color(0, 0, 139));
		btnInsert.setBounds(136, 36, 100, 40);
		panel_1.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(true);
				Main.getSearchForm().setVisible(false);
			}
		});
		btnClose.setForeground(new Color(0, 0, 139));
		btnClose.setBounds(292, 265, 100, 40);
		contentPane.add(btnClose);
	}



	public String getInputLastname() {
		return inputLastname;
	}
	
}
