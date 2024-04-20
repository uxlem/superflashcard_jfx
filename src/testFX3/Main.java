package testFX3;

import cards.Deck;
import cards.DeckList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
// Đọc file fxml và vẽ giao diện.
			Parent root = FXMLLoader.load(getClass().getResource("/testFX3/menu.fxml"));
			primaryStage.setTitle("Super FlashCard");
			primaryStage.setScene(new Scene(root));
			//primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}