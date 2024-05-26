package view;

import controller.BookController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Book;

public class InsertPage {
//	private Library lib;
//	private Account acc;
	Stage stage;
	Scene scene;
	BorderPane borderPane;
	GridPane gridPane;
	Label titleLabel, authorLabel, isbnLabel, priceLabel;
	TextField title, author, isbn, price;
	Button insertBTN;

	public InsertPage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		setEventHandler();
		scene = new Scene(borderPane, 400, 400);
	}

	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		titleLabel = new Label("Title");
		isbnLabel = new Label("ISBN");
		priceLabel = new Label("Price");
		authorLabel = new Label("Author");
		title = new TextField();
		author = new TextField();
		isbn = new TextField();
		price = new TextField();
		insertBTN = new Button("Insert Book");
	}

	private void setLayout() {
		gridPane.add(titleLabel, 0, 0);
		gridPane.add(title, 1, 0);
		gridPane.add(priceLabel, 0, 1);
		gridPane.add(price, 1, 1);
		gridPane.add(authorLabel, 0, 2);
		gridPane.add(author, 1, 2);
		gridPane.add(isbnLabel, 0, 3);
		gridPane.add(isbn, 1, 3);
		gridPane.add(insertBTN, 0, 4);
		borderPane.setCenter(gridPane);
	}

	private void setEventHandler() {
		insertBTN.setOnAction(event -> {
			String bookTitle = title.getText();
			String bookAuthor = author.getText();
			String bookISBN = isbn.getText();
			int bookPrice;
			try {
				bookPrice = Integer.parseInt(price.getText());
			} catch (NumberFormatException e) {
				System.out.println("Invalid price format.");
				return;
			}

			if (bookTitle.isEmpty() || bookAuthor.isEmpty() || bookISBN.isEmpty()) {
				System.out.println("Please fill all fields.");
				return;
			}

			Book book = new Book(bookTitle, bookPrice, bookAuthor, bookISBN);
			boolean inserted = BookController.insertBook(book);
			if (inserted) {
				HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show();
			}
		});
	}

	public Scene getScene() {
		return this.scene;
	}

}
