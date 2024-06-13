package reminder_streak_checker;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LearningTimeReminder {
    public void LearningReminder() {
        Thread timerThread = new Thread(() -> {
            // Set thời gian là 45p với đơn vị milisecond
            //long duration = 45 * 60 * 1000; // 45 minutes * 60 seconds/minute * 1000 milliseconds/second
        	long duration = 1000;
            try {
                // Chờ 45p
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Đổi thành method sẽ trigger ở dưới bằng JavaFX
            //System.out.println("1");
            Platform.runLater(new Runnable() {
            	public void run() {
            	Reminder rem = new Reminder();
            	rem.openReminderWindow();
            	}
            });
        });

        // Start (ko cần sửa)
        timerThread.start();
        
    }
}
