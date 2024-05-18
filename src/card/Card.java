package card;

import java.util.ArrayList;

public abstract class Card {

	protected ArrayList<Corner> corners;
	Color color;
	Symbol symbol;
	ArrayList<Side> sides;
	
	public Card(Color color, ArrayList<Corner> corners, ArrayList<Side> sides) {
		this.color = color;
		this.corners = corners;
		this.sides = sides;
	}
	
	public Card(Color color, ArrayList<Corner> corners,Symbol symbol, ArrayList<Side> sides) {
		this.color = color;
		this.corners = corners;
		this.symbol = symbol;
		this.sides = sides;
	}

	public ArrayList<Corner> getCorner(){
		return corners;
	}

}
