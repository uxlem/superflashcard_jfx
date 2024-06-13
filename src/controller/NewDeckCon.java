package controller;

import java.net.URL;
import java.util.ResourceBundle;

import SqlServer.QueryDataAccessObject;
import cards.Deck;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewDeckCon implements Initializable{
	@FXML private TextField newDeckNameTextField;
	@FXML private Button addDeckButton;
		
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		BooleanBinding nameOK = newDeckNameTextField.textProperty().isEmpty();
		addDeckButton.disableProperty().bind(nameOK);
		
	}
	
	@FXML void addDeck() {
		String name = newDeckNameTextField.getText().trim();
		if (name.isBlank())
		{
			System.out.println("Tên thẻ trống");
		}
		else {
			QueryDataAccessObject.insertDeck(new Deck(name));
		}
		
	}
}
