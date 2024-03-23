package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import database.DatabaseSingleton;
import javafx.collections.ObservableList;
import model.Book;
import model.User;

public class BookController {
	protected static DatabaseConnection db = DatabaseSingleton.getInstance();
	public static Boolean insertBook(Book book) {
		
		String query = "INSERT INTO Books(Title, Price, Author, ISBN) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, book.getTitle());
			stmt.setInt(2, book.getPrice());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getISBN());
			int rowAffected = stmt.executeUpdate();
			System.out.println("Rows Affected from insert book: " + rowAffected);
			return rowAffected > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static List<Book> getAllBooks(){
		String query = "SELECT * FROM Books";
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("Title");
				String price = rs.getString("Price");
				String author = rs.getString("Author");
				String isbn = rs.getString("ISBN");
				Book book = new Book(title, Integer.parseInt(price), author, isbn);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public static List<String> getAllBookName(){
		String query = "SELECT Title FROM Books";
		List<String> booksTitle = new ArrayList<String>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("Title");
				booksTitle.add(title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksTitle;
	}
	
	public static Boolean updateBookPrice(String title, int price) {
		if(bookExists(title)) {
			String query = "UPDATE Books SET Price = ? WHERE Title = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setInt(1, price);
				stmt.setString(2, title);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public static Boolean DeleteBook(String title) {
		if(bookExists(title)) {
			String query = "DELETE FROM Books WHERE Title = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setString(1, title);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void insertDefaultBook() {
		try {
			insertBook(new Book("Only Dull People Are Brilliant at Breakfast", 200000, "Oscar Wilde", "9780241251805"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Boolean bookExists(String title) {
		String query = "SELECT COUNT(*) FROM Books WHERE Title = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, title);
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
	
}
