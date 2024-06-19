package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import SqlServer.QueryDataAccessObject;
import cards.Card;
//import cards.DeckList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TestController implements Initializable {
	
	@FXML private TextArea frontTextArea, backTextArea;
	@FXML private RowConstraints row1Constraints;
	@FXML private Button goBackButton, showAnswerButton, nextCardButton;
	@FXML private TextField answerTextField;
	@FXML private AnchorPane rootAnchorPane;
	@FXML private GridPane cardGridPane;
	
	int deckSize;
	int currentCardIndex = 0;
	ObservableList<Card> CardList;
	static Scene previousScene;
    public static void setPreviousScene(Scene scene) {
    	previousScene = scene;
    }
    
    int correctAnswerCount = 0;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CardList = QueryDataAccessObject.getShuffledCardsFromDeck(ManageDecksController.selectedDeck);
		deckSize = CardList.size();
		if (deckSize > 0) {
			Card currentCard = (currentCardIndex < deckSize) ? CardList.get(currentCardIndex) : CardList.getFirst();
			frontTextArea.setText(currentCard.getMatTruoc());
			backTextArea.setText(currentCard.getMatSau());
		} else if (deckSize == 0) {
			frontTextArea.setText("Bộ thẻ chưa có thẻ nào, bạn hãy thêm thẻ!");
			showAnswerButton.setDisable(true);
			nextCardButton.setDisable(true);
		}
		backTextArea.setVisible(false);
		backTextArea.setManaged(false);
		row1Constraints.setPercentHeight(0);
		nextCardButton.setManaged(false);
		
	}

	@FXML
	public void showAnswer(ActionEvent event) {
		answerTextField.setText(answerTextField.getText().strip());
		if (answerTextField.getText().equals(backTextArea.getText()))
		{
			// báo đúng
			answerTextField.setStyle("-fx-background-color: #AFFD8C");
			correctAnswerCount++;
		}
		else 
		{
			// báo sai
			answerTextField.setStyle("-fx-background-color: #FF899D");
		}
		
		backTextArea.setVisible(true);
		backTextArea.setManaged(true);
		row1Constraints.setPercentHeight(40);
		
		showAnswerButton.setManaged(false);
		showAnswerButton.setVisible(false);
		
//		answerTextField.setManaged(false);
//		answerTextField.setVisible(false);
		
		nextCardButton.setManaged(true);
		nextCardButton.setVisible(true);
	}

	@FXML
	public void goBack(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) goBackButton.getScene().getWindow();
		Parent root = FXMLLoader.load(this.getClass().getResource("/manage_decks.fxml"));
		double w = currentStage.getWidth(), h = currentStage.getHeight();
		currentStage.setScene(new Scene(root));
		currentStage.setHeight(h); currentStage.setWidth(w);
		currentStage.show();
	}

	@FXML
	public void goToNext(ActionEvent event) {
		answerTextField.setStyle("-fx-background-color: #FFFFFF");
		if (currentCardIndex+1 < deckSize) {
			currentCardIndex++;
			System.out.println("Next card index is " + currentCardIndex);
			Card currentCard = CardList.get(currentCardIndex);
			frontTextArea.setText(currentCard.getMatTruoc());
			backTextArea.setText(currentCard.getMatSau());
			backTextArea.setVisible(false);
			backTextArea.setManaged(false);
			row1Constraints.setPercentHeight(0);
			
			nextCardButton.setManaged(false);
			nextCardButton.setVisible(false);
			
	//		answerTextField.setManaged(true);
	//		answerTextField.setVisible(true);
			answerTextField.setText("");
			
			showAnswerButton.setManaged(true);
			showAnswerButton.setVisible(true);
		} else {
			frontTextArea.setText("Bạn đã kiểm tra xong!\nKết quả: "+correctAnswerCount+"/"+deckSize);
			
			nextCardButton.setManaged(false);
			nextCardButton.setVisible(false);
			
			answerTextField.setManaged(false);
			answerTextField.setVisible(false);
			
			showAnswerButton.setManaged(false);
			showAnswerButton.setVisible(false);
			
			backTextArea.setVisible(false);
			backTextArea.setManaged(false);
			row1Constraints.setPercentHeight(0);
			
		}
		
	}
}