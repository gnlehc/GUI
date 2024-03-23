package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;
	// singleton
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "");
			statement = connection.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUsersTable() {
		String query = "CREATE TABLE IF NOT EXISTS Users("
				+ "UserID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "Username VARCHAR(50) NOT NULL,"
				+ "Email VARCHAR(50) NOT NULL,"
				+ "Password VARCHAR(50) NOT NULL"
				+ ")";
		try {
			exec(query);			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void crateBooksTable() {
		String query = "CREATE TABLE IF NOT EXISTS Books("
				+ "BookID INT AUTO_INCREMENT PRIMARY KEY,"
				+ "Title VARCHAR(50) NOT NULL,"
				+ "Price INT NOT NULL,"
				+ "Author VARCHAR(50) NOT NULL,"
				+ "ISBN VARCHAR(20)"
				+ ")";
		try {
			exec(query);			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void migrateTables() {
		createUsersTable();
		crateBooksTable();
	}
	 
	public void exec(String query) {
		try {
			statement.execute(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
