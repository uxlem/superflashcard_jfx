package testFX3;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class StudyController implements Initializable {
	@FXML
	private Text front;
	@FXML
	private Text back;
	@FXML
	private Button revealButton;
	@FXML
	private Rectangle frontRect;
	@FXML
	private Text frontTitle;
	@FXML
	private Button MenuButton;
	@FXML
	private Button editCardButton;
	@FXML
	private Button nextCardButton;
	@FXML
	private Text cardNumString;
	
	static int deckSize;
	static int currentCardIndex = 0;
	static ObservableList<Card> CardList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CardList = QueryDataAccessObject.getData(Main.currentConnection);
		deckSize = CardList.size();
		cardNumString.setText((String)("Bộ thẻ có "+deckSize+" thẻ"));
		if (deckSize > 0) {
			Card currentCard = CardList.get(currentCardIndex);
			front.setText(currentCard.getMatTruoc());
			back.setText(currentCard.getMatSau());
		}
		back.setVisible(false);
	}

	public void revealBack(ActionEvent event) {

		back.setVisible(true);

	}

	@FXML
	public void backToMenu(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) MenuButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		currentStage.setScene(new Scene(root));
		currentStage.show();
	}

	@FXML
	public void goToEdit(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) editCardButton.getScene().getWindow();
		// Parent root = FXMLLoader.load(getClass().getResource("card_edit.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("card_edit.fxml"));
		Parent root = loader.load();
		EditController EditSceneController = loader.getController();
		EditSceneController.setPreviousScene(3);
		EditSceneController.setPrevSelectedCard(CardList.get(currentCardIndex));
		EditSceneController.initialize(null, null);
		currentStage.setScene(new Scene(root));
		currentStage.show();
	}

	@FXML
	public void goToNext(ActionEvent event) {
		if (currentCardIndex+1 < deckSize)
			currentCardIndex++;
		else currentCardIndex = 0;
		
		Card currentCard = CardList.get(currentCardIndex);
		front.setText(currentCard.getMatTruoc());
		back.setVisible(false);
		back.setText(currentCard.getMatSau());
	}
}