package gr.aueb.cf.schoolapp;

import java.awt.EventQueue;

public class Main {

	private static Menu menu;
	private static SearchForm searchForm;
	private static InsertForm insertForm;
	private static UpdateDeleteForm updateDeleteForm;
	
	private static SearchStudentForm searchStudentForm;
	private static InsertStudentForm insertStudentForm;
	private static UpdateDeleteStudentForm updateDeleteStudentForm;
	
	private static LoginPage loginPage;
	private static InsertUser insertUser;
	private static SearchUser searchUser;
	private static UpdateDeleteUser updateDeleteUser;
	private static InsertNewPassword insertNewPassword;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new Menu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(false);
					
					searchForm = new SearchForm();
					searchForm.setLocationRelativeTo(null);
					searchForm.setVisible(false);
					
					insertForm = new InsertForm();
					insertForm.setLocationRelativeTo(null);
					insertForm.setVisible(false);
					
					updateDeleteForm = new UpdateDeleteForm();
					updateDeleteForm.setLocationRelativeTo(null);
					updateDeleteForm.setVisible(false);
					
					loginPage = new LoginPage();
					loginPage.setLocationRelativeTo(null);
					loginPage.setVisible(true);
					
					insertUser = new InsertUser();
					insertUser.setLocationRelativeTo(null);
					insertUser.setVisible(false);
					
					searchUser = new SearchUser();
					searchUser.setLocationRelativeTo(null);
					searchUser.setVisible(false);
					
					updateDeleteUser = new UpdateDeleteUser();
					updateDeleteUser.setLocationRelativeTo(null);
					updateDeleteUser.setVisible(false);
					
					insertNewPassword = new InsertNewPassword();
					insertNewPassword.setLocationRelativeTo(null);
					insertNewPassword.setVisible(false);
					
					searchStudentForm = new SearchStudentForm();
					searchStudentForm.setLocationRelativeTo(null);
					searchStudentForm.setVisible(false);
					
					insertStudentForm = new InsertStudentForm();
					insertStudentForm.setLocationRelativeTo(null);
					insertStudentForm.setVisible(false);
					
					updateDeleteStudentForm = new UpdateDeleteStudentForm();
					updateDeleteStudentForm.setLocationRelativeTo(null);
					updateDeleteStudentForm.setVisible(false);
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}


	public static Menu getMenu() {
		return menu;
	}


	public static SearchForm getSearchForm() {
		return searchForm;
	}


	public static InsertForm getInsertForm() {
		return insertForm;
	}


	public static UpdateDeleteForm getUpdateDeleteForm() {
		return updateDeleteForm;
	}


	public static LoginPage getLoginPage() {
		return loginPage;
	}


	public static InsertUser getInsertUser() {
		return insertUser;
	}


	public static SearchUser getSearchUser() {
		return searchUser;
	}


	public static UpdateDeleteUser getUpdateDeleteUser() {
		return updateDeleteUser;
	}


	public static InsertNewPassword getInsertNewPassword() {
		return insertNewPassword;
	}


	public static SearchStudentForm getSearchStudentForm() {
		return searchStudentForm;
	}


	public static InsertStudentForm getInsertStudentForm() {
		return insertStudentForm;
	}


	public static UpdateDeleteStudentForm getUpdateDeleteStudentForm() {
		return updateDeleteStudentForm;
	}
	
	
	
	

}
