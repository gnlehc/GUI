package view;

import java.util.List;

import controller.BookController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Book;

public class HomePage {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label welcomeLabel;
	protected TableView<Book> bookTable;
	protected Button insertButton;
	protected Button updateButton;
	protected Button deleteButton;
	protected ObservableList<Book> listBooks;

	public HomePage(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		this.scene = new Scene(borderPane, 600, 600);
	}

	public Scene getScene() {
		return this.scene;
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		List<Book> books = BookController.getAllBooks();
		listBooks = FXCollections.observableArrayList(books);
		borderPane = new BorderPane();
		gridPane = new GridPane();
		welcomeLabel = new Label("Hello");
		TableColumn<Book, String> titleCol = new TableColumn<>("Title");
		titleCol.setCellValueFactory(cell -> cell.getValue().titleProperty());
		TableColumn<Book, String> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty());
		TableColumn<Book, String> authorCol = new TableColumn<>("Author");
		authorCol.setCellValueFactory(cell -> cell.getValue().authorProperty());
		TableColumn<Book, String> isbnCol = new TableColumn<>("ISBN");
		isbnCol.setCellValueFactory(cell -> cell.getValue().isbnProperty());

		bookTable = new TableView<>();
		bookTable.setItems(listBooks);
		bookTable.getColumns().addAll(titleCol, priceCol, authorCol, isbnCol);
		insertButton = new Button("Insert new Book");
		updateButton = new Button("Update Book");
		deleteButton = new Button("Delete Book");

		insertButton.setOnAction(event -> {
			InsertPage insertPage = new InsertPage(stage);
			stage.setScene(insertPage.getScene());
			stage.show();
		});

		updateButton.setOnAction(event -> {
//            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
//            if (selectedBook != null) {
//                UpdatePage updatePage = new UpdatePage(stage, selectedBook);
//                stage.setScene(updatePage.getScene());
//                stage.show();
//            }
			UpdatePage updatePage = new UpdatePage(stage);
			stage.setScene(updatePage.getScene());
			stage.show();
		});

		deleteButton.setOnAction(event -> {
			DeletePage deletePage = new DeletePage(stage);
			stage.setScene(deletePage.getScene());
			stage.show();
		});
	}

	public void addBookToTable(Book book) {
		bookTable.getItems().add(book);
	}

	private void setLayout() {
		gridPane.add(welcomeLabel, 0, 0);
		gridPane.add(bookTable, 0, 1);
		gridPane.add(deleteButton, 0, 2);
		gridPane.add(insertButton, 1, 2);
		gridPane.add(updateButton, 2, 2);
		borderPane.setCenter(gridPane);
	}
}
