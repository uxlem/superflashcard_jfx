package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Navi {
	public static void goTo(String fxml, Button button) throws IOException {
		Stage currentStage = (Stage) button.getScene().getWindow();
		Parent root = FXMLLoader.load(MenuController.class.getResource(fxml));
		double w = currentStage.getWidth(), h = currentStage.getHeight();
		currentStage.setScene(new Scene(root));
		currentStage.setHeight(h); currentStage.setWidth(w);
		currentStage.show();
	}
}
