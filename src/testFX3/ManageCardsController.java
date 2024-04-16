package testFX3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cards.Card;
import cards.DeckList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageCardsController implements Initializable{

    @FXML
    private Button MenuButton;

    @FXML
    private Button deleteCardButton;

    @FXML
    private Button detailButton;

    @FXML
    private Button editCardButton;

    @FXML
    private Button newCardButton;

    @FXML
    private TableView<Card> cardTable;

    @FXML
    private TableColumn<Card, String> frontCol;
    
    @FXML
    private TableColumn<Card, String> backCol;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	frontCol.setCellValueFactory(new PropertyValueFactory<>("front"));
    	backCol.setCellValueFactory(new PropertyValueFactory<>("back"));
    	cardTable.setItems(DeckList.defaultDeck.CardList);
    }
    
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) MenuButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		currentStage.setScene(new Scene(root));
		currentStage.show();
    }

    @FXML
    void goToCreateNewCard(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) newCardButton.getScene().getWindow();
		//Parent root = FXMLLoader.load(getClass().getResource("card_edit.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("card_edit.fxml"));
		Parent root = loader.load();
		EditController EditSceneController = loader.getController();
		EditSceneController.setPreviousScene(1);
		EditSceneController.initialize(null, null);
		currentStage.setScene(new Scene(root));
		currentStage.show();
    }

    @FXML
    void goToDetails(ActionEvent event) {

    }

    @FXML
    void goToEdit(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) newCardButton.getScene().getWindow();
		//Parent root = FXMLLoader.load(getClass().getResource("card_edit.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("card_edit.fxml"));
		Parent root = loader.load();
		EditController EditSceneController = loader.getController();
		EditSceneController.setPreviousScene(2);
		EditSceneController.initialize(null, null);
		currentStage.setScene(new Scene(root));
		currentStage.show();
    }

}
