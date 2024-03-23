package view;

import java.util.List;
import controller.BookController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdatePage {
    private Stage stage;
    private Scene scene;
    private BorderPane borderPane;
    private GridPane gridPane;
    private Label titleLabel, priceLabel;
    private TextField price;
    private Button updateBTN;
    private ComboBox<String> titleComboBox;
    
    public UpdatePage(Stage stage) {
        this.stage = stage;
        init();
        setLayout();
        scene = new Scene(borderPane, 400, 400);
    }

    private void init() {
        borderPane = new BorderPane();
        gridPane = new GridPane();
        titleLabel = new Label("Title");
        priceLabel = new Label("Price");
        titleComboBox = new ComboBox<>();
        price = new TextField();
        updateBTN = new Button("Update Book");
        List<String> books = BookController.getAllBookName();
        titleComboBox.setItems(FXCollections.observableArrayList(books));
        updateBTN.setOnAction(event -> {
            String selectedTitle = titleComboBox.getValue();
            String bookPrice = price.getText();
            boolean updated = BookController.updateBookPrice(selectedTitle, Integer.parseInt(bookPrice));
            
            if(updated) {
                HomePage homePage = new HomePage(stage);
                stage.setScene(homePage.getScene());
                stage.show();
            }
        });
    }

    private void setLayout() {
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleComboBox, 1, 0);
        gridPane.add(priceLabel, 0, 1);
        gridPane.add(price, 1, 1);
//        gridPane.add(authorLabel, 0, 2);
//        gridPane.add(author, 1, 2);
//        gridPane.add(isbnLabel, 0, 3);
//        gridPane.add(isbn, 1, 3);
        gridPane.add(updateBTN, 0, 3);
        borderPane.setCenter(gridPane);
    }

    public Scene getScene() {
        return this.scene;
    }
}
