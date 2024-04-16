package testFX3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.GroupLayout.Alignment;

import cards.Card;
import cards.DeckList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class EditController implements Initializable{
	static int previousScene = 0;
	
	public void setPreviousScene(int option) {
		previousScene = option;
	}
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button newButton;
	
	@FXML
	private Text title; 
	
	@FXML
	private TextArea backTextArea;
	
	@FXML
	private TextArea frontTextArea;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		switch(previousScene)
		{
		case 1:
			title.setText("Thêm thẻ mới");
			
			break;
		case 2:
		case 3:
			title.setText("Sửa thẻ");
			
			break;
		}
	}
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) backButton.getScene().getWindow();
		Parent root = null;
		switch(previousScene)
		{
		case 1:
		case 2:
			root = FXMLLoader.load(getClass().getResource("manage.fxml"));
			break;
		case 3:
			root = FXMLLoader.load(getClass().getResource("study.fxml"));
		}
		currentStage.setScene(new Scene(root));
		currentStage.show();
    }

    @FXML
    void saveCards(ActionEvent event) {
    	DeckList.defaultDeck.CardList.add(new Card(frontTextArea.getText(),backTextArea.getText()));
    }

    @FXML
    void createNewCard(ActionEvent event) {
    	
    }
}
