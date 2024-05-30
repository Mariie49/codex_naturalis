package cards;

import java.util.ArrayList;

public class Card {

	private ArrayList<Corner> corners = new ArrayList<>();
	private static int ID = 1;
	private int cardId;
	
	public Card (ArrayList<Corner> corners) {
		if(corners.size() <1 && corners.size() > 4) {
			throw new IllegalArgumentException("Una carta deve contenere tra 1 a 4 corner");
		}
		this. corners = corners;
		this.cardId = ID++;
	}

	@Override
	public String toString() {
		return "Card [corners=" + corners + ", cardId=" + cardId + "]";
	}

	
	
	
	
}
