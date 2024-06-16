package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import SqlServer.DatabaseConnector;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class TestController implements Initializable {
	@FXML private TextFlow frontTextFlow, backTextFlow;
	@FXML private Text frontText, backText;
	@FXML private Button goBackButton, goToEditButton, showAnswerButton, nextCardButton;
	
	static int deckSize;
	static int currentCardIndex = 0;
	static ObservableList<Card> CardList;
	static Scene previousScene;
    public static void setPreviousScene(Scene scene) {
    	previousScene = scene;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CardList = QueryDataAccessObject.getCardsFromDeck(ManageDecksController.selectedDeck);
		deckSize = CardList.size();
		if (deckSize > 0) {
			Card currentCard = (currentCardIndex < deckSize) ? CardList.get(currentCardIndex) : CardList.getFirst();
			frontText.setText(currentCard.getMatTruoc());
			backText.setText(currentCard.getMatSau());
		} else if (deckSize == 0) {
			frontText.setText("Chưa có thẻ nào, bạn hãy đi thêm thẻ!");
			goToEditButton.setDisable(true);
			showAnswerButton.setDisable(true);
			nextCardButton.setDisable(true);
		}
		backTextFlow.setVisible(false);
	}

	public void showAnswer(ActionEvent event) {
		backTextFlow.setVisible(true);
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
	public void goToEdit(ActionEvent event) throws IOException {
//		Stage currentStage = (Stage) goToEditButton.getScene().getWindow();
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/card_edit.fxml"));
//		Parent root = loader.load();
//		EditController.setPreviousOption(3);
//		EditController.setPreviousScene(goToEditButton.getScene());
//		EditController.setPrevSelectedCard(CardList.get(currentCardIndex));
//		((Initializable) loader.getController()).initialize(null, null);
//		currentStage.setScene(new Scene(root));
//		currentStage.show();
	}

	@FXML
	public void goToNext(ActionEvent event) {
		if (currentCardIndex+1 < deckSize)
			currentCardIndex++;
		else currentCardIndex = 0;
		
		Card currentCard = CardList.get(currentCardIndex);
		frontText.setText(currentCard.getMatTruoc());
		backTextFlow.setVisible(false);
		backText.setText(currentCard.getMatSau());
	}
}