package reminder_streak_checker;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reminder {
	public void openReminderWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/time_reminder.fxml"));
    		Parent root;
			root = loader.load();
			Stage newDeckDialog = new Stage();
    		((Initializable) loader.getController()).initialize(null, null);
    		newDeckDialog.setScene(new Scene(root));
    		newDeckDialog.setTitle("Super Flashcard - Lưu ý thời gian sử dụng");
    		newDeckDialog.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
