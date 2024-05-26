package view;

import java.util.List;

import controller.BookController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeletePage {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label titleLabel;
	protected Button deleteBTN;
	protected ComboBox<String> titleComboBox;

	public DeletePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 400, 400);
	}

	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		titleLabel = new Label("Title");
		titleComboBox = new ComboBox<>();
		deleteBTN = new Button("Delete Book");
		List<String> books = BookController.getAllBookName();
		titleComboBox.setItems(FXCollections.observableArrayList(books));
		deleteBTN.setOnAction(event -> {
			String selectedTitle = titleComboBox.getValue();
			boolean updated = BookController.DeleteBook(selectedTitle);
			if (updated) {
				HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show();
			}
		});
	}

	private void setLayout() {
		gridPane.add(titleLabel, 0, 0);
		gridPane.add(titleComboBox, 1, 0);
		gridPane.add(deleteBTN, 0, 2);
		borderPane.setCenter(gridPane);
	}

	public Scene getScene() {
		return this.scene;
	}
}
