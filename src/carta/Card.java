package carta;

import java.util.ArrayList;

public abstract class Card {

	ArrayList<Card> intersections;

	public Card(ArrayList<Card> intersections) {
		this.intersections = intersections;
	}
}
