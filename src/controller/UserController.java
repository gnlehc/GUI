package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.DatabaseConnection;
import database.DatabaseSingleton;
import model.Account;
import model.User;

public class UserController {
	protected static DatabaseConnection db = DatabaseSingleton.getInstance();
	public static Boolean insertUser(User user) {
		// validate users input
		if(validateIsExists(user)) {
			return false;
		}
		
		String query = "INSERT INTO Users(Username, Email, Password) "
				+ "VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			int rowAffected = stmt.executeUpdate();
			System.out.println("Rows Affected from insert user: " + rowAffected);
			return rowAffected > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void insertDefaultAccount() {
		try {
			insertUser(new User("gnlehc", "admin@gmail.com", "123"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Boolean validateIsExists(User user) {
		String query = "SELECT COUNT(*) FROM Users WHERE Email = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, user.getEmail());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Boolean Login(String email, String password) {
		String query = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			System.out.println(email);
			System.out.println(password);
			ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				User currentUser = new User(rs.getString("Username"), rs.getString("Email"), rs.getString("Password"));
//	            acc.setCurrUser(currentUser);
//	            return true;
//	        }
			return rs.next();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
