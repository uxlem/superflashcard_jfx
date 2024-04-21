package testFX3;

import java.sql.Connection;
import java.sql.SQLException;

import SqlServer.DatabaseConnector;
import SqlServer.QueryDataAccessObject;
import cards.Deck;
//import cards.DeckList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	static DatabaseConnector currentDatabaseConnector;
	static Connection currentConnection;

	static void openDB() {
		String url = "jdbc:h2:./testbase";
		String username = "nice";
		String password = "password";
		currentDatabaseConnector = new DatabaseConnector(url, username, password);
		currentConnection = currentDatabaseConnector.getConnection();
		QueryDataAccessObject.createTable(currentConnection);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// Mở CSDL và Bảng FlashCards
			openDB();

			// Đọc file fxml và vẽ giao diện.
			Parent root = FXMLLoader.load(getClass().getResource("/testFX3/menu.fxml"));
			primaryStage.setTitle("Super FlashCard");
			primaryStage.setScene(new Scene(root));
			// primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		try {
			if (currentConnection != null)
				currentConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Da dong connection.");
		}
		System.out.println("Dong app!");
	}
}