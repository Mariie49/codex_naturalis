package card;

import java.util.ArrayList;

public class Side {
	ArrayList<Corner> corners;
	Symbol symbol;
	int value;
	
	public Side (ArrayList<Corner> corners, Symbol symbol, int value) {
		this.corners = corners;
		this.symbol = symbol;
		this.value = value;
	}
	
}
