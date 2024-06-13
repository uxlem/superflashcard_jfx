package main;

import java.io.IOException;
import java.sql.SQLException;

import SqlServer.DatabaseConnector;
//import cards.Deck;
//import cards.DeckList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reminder_streak_checker.DailyCheck;
import reminder_streak_checker.LearningTimeReminder;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Mở CSDL và Bảng flashcards
			DatabaseConnector.openDB();
			DailyCheck dailyCheck = new DailyCheck();

	        //Lệnh ghi vào file days_logged_in.txt ngày đã log-in
	        dailyCheck.DailyCheckMethod();
	        //Lệnh get chuỗi log in (sau đó làm gì thì làm idk)
	        dailyCheck.getDayStreaks();
	        
	        //LearningTimeReminder
	        LearningTimeReminder timerReminder = new LearningTimeReminder();
	        //Lệnh chạy Reminder
	        timerReminder.LearningReminder();
	        
			// Đọc file fxml và vẽ giao diện.
			Parent root = FXMLLoader.load(Main.class.getResource("/resources/manage_decks.fxml"));
			primaryStage.setTitle("Super FlashCard");
			primaryStage.setMinHeight(480);
			primaryStage.setMinWidth(640);
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.setResizable(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			DatabaseConnector.closeConnection();
			System.out.println("Dong app!");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		launch(args);
		DatabaseConnector.closeConnection();
		System.out.println("Dong app!");
	}
}