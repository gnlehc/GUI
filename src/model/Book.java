package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	int bookId;
	String title;
	int price;
	String author;
	String ISBN;
	
	public Book(String title, int price, String author, String iSBN) {
		super();
		this.title = title;
		this.price = price;
		this.author = author;
		this.ISBN = iSBN;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public StringProperty titleProperty() {
        return new SimpleStringProperty(title);
    }
	public StringProperty priceProperty() {
        return new SimpleStringProperty(Integer.toString(price));
    }
	public StringProperty authorProperty() {
		return new SimpleStringProperty(author);
	}
	public StringProperty isbnProperty() {
		return new SimpleStringProperty(ISBN);
	}
	
}
