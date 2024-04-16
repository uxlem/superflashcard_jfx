package cards;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Deck {
	public ObservableList<Card> CardList;	
	public Deck() {
		CardList = FXCollections.observableArrayList();
		CardList.add(new Card("hello,", "world!"));
	}
}
