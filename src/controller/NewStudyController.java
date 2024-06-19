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
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class NewStudyController implements Initializable {
	@FXML private TextArea frontTextArea, backTextArea;
	@FXML private Button goBackButton, goToEditButton, showAnswerButton, nextCardButton;
	@FXML private RowConstraints row1Constraints;
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
			frontTextArea.setText(currentCard.getMatTruoc());
			backTextArea.setText(currentCard.getMatSau());
		} else if (deckSize == 0) {
			frontTextArea.setText("Chưa có thẻ nào, bạn hãy đi thêm thẻ!");
			goToEditButton.setDisable(true);
			showAnswerButton.setDisable(true);
			nextCardButton.setDisable(true);
		}
		backTextArea.setVisible(false);
		backTextArea.setManaged(false);
		row1Constraints.setPercentHeight(0);
	}

	public void showAnswer(ActionEvent event) {
		backTextArea.setVisible(true);
		backTextArea.setManaged(true);
		row1Constraints.setPercentHeight(50);
	}

	@FXML
	public void goBack(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) goBackButton.getScene().getWindow();
		Parent root = FXMLLoader.load(this.getClass().getResource("/managecards2.fxml"));
		double w = currentStage.getWidth(), h = currentStage.getHeight();
		currentStage.setScene(new Scene(root));
		currentStage.setHeight(h); currentStage.setWidth(w);
		currentStage.show();
	}

	@FXML
	public void goToEdit(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) goToEditButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/card_edit.fxml"));
		Parent root = loader.load();
		EditController.setPreviousOption(3);
		EditController.setPreviousScene(goToEditButton.getScene());
		EditController.setPrevSelectedCard(CardList.get(currentCardIndex));
		((Initializable) loader.getController()).initialize(null, null);
		currentStage.setScene(new Scene(root));
		currentStage.show();
	}

	@FXML
	public void goToNext(ActionEvent event) {
		if (currentCardIndex+1 < deckSize)
			currentCardIndex++;
		else currentCardIndex = 0;
		
		Card currentCard = CardList.get(currentCardIndex);
		frontTextArea.setText(currentCard.getMatTruoc());
		backTextArea.setVisible(false);
		backTextArea.setManaged(false);
		row1Constraints.setPercentHeight(0);
		backTextArea.setText(currentCard.getMatSau());
		
	}
}