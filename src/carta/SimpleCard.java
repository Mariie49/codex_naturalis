package carta;

import java.util.ArrayList;

public class SimpleCard extends Card {

	Symbol symbol;
	
	public SimpleCard(ArrayList<Card> intersections, Symbol symbol) {
		super(intersections);
		this.symbol = symbol;
		
	}
	
}
