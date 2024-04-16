package cards;

import javafx.beans.value.ObservableStringValue;

public class Card {
	public String front, back;
	public Card(String frontText, String backText) {
		this.front = frontText;
		this.back = backText;
	}
	
	public String getFront()
	{
		return this.front;
	}
	public String getBack()
	{
		return this.back;
	}
}
