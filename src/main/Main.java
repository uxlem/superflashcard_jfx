package main;

import java.sql.SQLException;

import SqlServer.DatabaseConnector;
//import cards.Deck;
//import cards.DeckList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Mở CSDL và Bảng flashcards
			DatabaseConnector.openDB();

			// Đọc file fxml và vẽ giao diện.
			Parent root = FXMLLoader.load(getClass().getResource("/controller/menu.fxml"));
			primaryStage.setTitle("Super FlashCard");
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			DatabaseConnector.closeConnection();
		}
	}

	public static void main(String[] args) {
		launch(args);
		DatabaseConnector.closeConnection();
		System.out.println("Dong app!");
	}
}