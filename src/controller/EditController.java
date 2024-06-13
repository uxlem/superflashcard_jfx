package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.GroupLayout.Alignment;

import Repositories.DateRepository;
import SqlServer.DatabaseConnector;
import SqlServer.QueryDataAccessObject;
import cards.Card;
//import cards.DeckList;
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

public class EditController implements Initializable {
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
	
	@FXML
	private Text cardIsBlankText;
	
	static Scene previousScene;
    public static void setPreviousScene(Scene scene) {
    	previousScene = scene;
    }
    static int previousOption;
    // 1 - chon Them the
    // 2 - chon Sua the trong Scene Manage
    // 3 - chon Sua the trong Scene NewStudy
    public static void setPreviousOption(int opt) {
    	previousOption = opt;
    }
    
	static Card previously_selectedCard;

	public static void setPrevSelectedCard(Card card) {
		previously_selectedCard = card;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cardIsBlankText.setVisible(false);
		switch(previousOption) {
		case 2:
		case 3:
			frontTextArea.setText(previously_selectedCard.getMatTruoc());
			backTextArea.setText(previously_selectedCard.getMatSau());
		}
	}

	@FXML
	void goBack(ActionEvent event) throws IOException {
//		Stage currentStage = (Stage) backButton.getScene().getWindow();
//		currentStage.setScene(previousScene);
//		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("edit_card.fxml"));
//		EditController con = loader.getController();
//		con.initialize(null, null);
//		currentStage.show();
		switch(previousOption) {
		case 1:
		case 2:
			Navi.goTo("/resources/manage.fxml", backButton);
			break;
		case 3:
			Navi.goTo("/resources/nstudy.fxml", backButton);
		}
	}

	@FXML
	void saveCards(ActionEvent event) {
		String backText = backTextArea.getText().trim();
		String frontText = frontTextArea.getText().trim();
		Card lookedUpCard = QueryDataAccessObject.findCardInDeck(frontText, ManageDecksController.selectedDeck);
		if (frontText.equals("") || backText.equals("")) {
			cardIsBlankText.setText("Có mặt thẻ trống, tạo/sửa thẻ không thành công!");
			cardIsBlankText.setVisible(true);
		} else {
			cardIsBlankText.setVisible(false);
			switch (previousOption) {
			case 1:
				if (lookedUpCard != null) {
					cardIsBlankText.setText("Đã có thẻ trùng mặt trước, tạo thẻ không thành công!");
					cardIsBlankText.setVisible(true);
				} else {
					cardIsBlankText.setVisible(false);
					QueryDataAccessObject.insertCard(new Card(frontText, backText, DateRepository.getDateNow()), ManageDecksController.selectedDeck);
				}
				break;
			case 2:
			case 3:
				if (lookedUpCard != null && lookedUpCard.getId() != previously_selectedCard.getId()) {
					cardIsBlankText.setText("Đã có thẻ (khác thẻ này) trùng mặt trước, sửa thẻ không thành công!");
					cardIsBlankText.setVisible(true);
				} else {
					cardIsBlankText.setVisible(false);
					QueryDataAccessObject.editCard(previously_selectedCard, frontText, backText);
					previously_selectedCard.setMatSau(backText);
					previously_selectedCard.setMatTruoc(frontText);
				}
				break;
			}
		}
	}
}
