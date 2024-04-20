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
	// 1 == tu scene Quan ly the bam nut Them the
	// 2 == tu scene Quan ly the bam nut Sua the
	// 3 == tu scene Hoc the bam nut Sua the 
	public void setPreviousScene(int option) {
		previousScene = option;
	}
	
	static Card previously_selectedCard;
	public void setPrevSelectedCard(Card card) {
		previously_selectedCard = card;
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
	
	@FXML private Text cardIsBlankText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cardIsBlankText.setVisible(false);
		switch(previousScene)
		{
		case 1:
			title.setText("Thêm thẻ mới");
			backTextArea.setText("");
			frontTextArea.setText("");
			break;
		case 2:
		case 3:
			backTextArea.setText(previously_selectedCard.back);
			frontTextArea.setText(previously_selectedCard.front);
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
    	String backText = backTextArea.getText().trim();
    	String frontText = frontTextArea.getText().trim();
    	String looked_up = DeckList.dictionary.lookup(frontText);
    	if (frontText.equals("") || backText.equals(""))
    	{
    		cardIsBlankText.setText("Có mặt thẻ trống, tạo/sửa thẻ không thành công!");
    		cardIsBlankText.setVisible(true);
    	}
    	else
    	{
    		cardIsBlankText.setVisible(false);
    		switch(previousScene)
    		{
    		case 1:
    			if (looked_up != null)
    			{
    				cardIsBlankText.setText("Đã có thẻ trùng mặt trước, tạo thẻ không thành công!");
    				cardIsBlankText.setVisible(true);
    			}
    			else
    			{
    				cardIsBlankText.setVisible(false);
    				DeckList.defaultDeck.CardList.add(new Card(frontText,backText));
    				DeckList.dictionary.dictPut(frontText, backText);
    			}
    			break;
    		case 2:
    		case 3:
    			String textBeforeEdit = previously_selectedCard.getFront();
    			if (looked_up != null && looked_up != DeckList.dictionary.lookup(textBeforeEdit))
    			{
    				cardIsBlankText.setText("Đã có thẻ (khác thẻ này) trùng mặt trước, sửa thẻ không thành công!");
    				cardIsBlankText.setVisible(true);
    			}
    			else
    			{
    				cardIsBlankText.setVisible(false);
					previously_selectedCard.setBack(backText);
					previously_selectedCard.setFront(frontText);
					DeckList.dictionary.replace(textBeforeEdit, frontText, backText);
    			}
    			break;
    		}
    	}
    }

    @FXML
    void createNewCard(ActionEvent event) {
    	
    }
}
