package controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button manageButton;

    @FXML
    private Button studyButton;

    @FXML
    void goToManage(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) manageButton.getScene().getWindow();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("manage.fxml"));
		Parent root = loader.load();
		ManageCardsController.setPreviousScene(manageButton.getScene());
		double w = currentStage.getWidth(), h = currentStage.getHeight();
		currentStage.setScene(new Scene(root));
		currentStage.setHeight(h); currentStage.setWidth(w);
		currentStage.show();
    }

    @FXML
    void goToStudy(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) studyButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("nstudy.fxml"));
		NewStudyController.setPreviousScene(studyButton.getScene());
		double w = currentStage.getWidth(), h = currentStage.getHeight();
		currentStage.setScene(new Scene(root));
		currentStage.setHeight(h); currentStage.setWidth(w);
		currentStage.show();
    }
}
