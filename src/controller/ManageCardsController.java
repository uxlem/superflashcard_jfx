package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import SqlServer.DatabaseConnector;
import SqlServer.QueryDataAccessObject;
import cards.Card;
//import cards.DeckList;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManageCardsController implements Initializable{

    @FXML
    private Button MenuButton;

    @FXML
    private Button detailButton;

    @FXML
    private Button deleteCardButton;
    
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
    
    static ResultSet resultSet = null;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	frontCol.setCellValueFactory(new PropertyValueFactory<>("matTruoc"));
    	backCol.setCellValueFactory(new PropertyValueFactory<>("matSau"));
    	cardTable.setItems(QueryDataAccessObject.getCardsFromDeck(ManageDecksController.selectedDeck));
    	cardTable.setFixedCellSize(24.0);
    	frontCol.prefWidthProperty().bind(cardTable.widthProperty().multiply(0.45));
    	backCol.prefWidthProperty().bind(cardTable.widthProperty().multiply(0.45));
    	editCardButton.setDisable(true);
    	deleteCardButton.setDisable(true);
    	detailButton.setDisable(true);
    }
    
    static Scene previousScene;
    public static void setPreviousScene(Scene scene) {
    	previousScene = scene;
    }
    
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
//    	Stage currentStage = (Stage) MenuButton.getScene().getWindow();
////		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
//		currentStage.setScene(previousScene);
//		currentStage.show();
		Navi.goTo("/resources/manage_decks.fxml", MenuButton);
    }

    @FXML
    void goToCreateNewCard(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) newCardButton.getScene().getWindow();
		//Parent root = FXMLLoader.load(getClass().getResource("card_edit.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/card_edit.fxml"));
		Parent root = loader.load();
		EditController.setPreviousOption(1);
		EditController EditSceneController = loader.getController();
		EditController.setPreviousScene(newCardButton.getScene());
		EditSceneController.initialize(null, null);
		currentStage.setScene(new Scene(root));
		currentStage.show();
    }

    @FXML
    void goToDetails(ActionEvent event) throws IOException {
    	NewStudyController.setPreviousScene(detailButton.getScene());
    	NewStudyController.currentCardIndex = cardTable.getItems().indexOf(selectedCard);
    	Navi.goTo("/resources/nstudy.fxml", detailButton);
    }

    private static Card selectedCard;
    
    @FXML
    void goToEdit(ActionEvent event) throws IOException {
    	Scene currentScene = newCardButton.getScene();
    	Stage currentStage = (Stage) currentScene.getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/card_edit.fxml"));
		Parent root = loader.load();
		EditController.setPreviousScene(currentScene);
		EditController.setPreviousOption(2);
		EditController.setPrevSelectedCard(selectedCard);
		((Initializable) loader.getController()).initialize(null, null);
		currentStage.setScene(new Scene(root));
		currentStage.show();
    }
    
    @FXML void getSelectedCard(MouseEvent event) {
    	if (event.getClickCount() == 1) //Checking click
        {
    		selectedCard = cardTable.getSelectionModel().getSelectedItem();
    		if (selectedCard != null) 
    			{
    				editCardButton.setDisable(false);
    				deleteCardButton.setDisable(false);
    				detailButton.setDisable(false);
    			}
    		else 
    			{
    				editCardButton.setDisable(true);
    				deleteCardButton.setDisable(true);
    				detailButton.setDisable(true);
    			}
        }
    	
    }

    @FXML void goToDelete(ActionEvent event) {
    	QueryDataAccessObject.deleteCard(selectedCard);
    	cardTable.getItems().remove(selectedCard);
    	cardTable.refresh();
    }
}
