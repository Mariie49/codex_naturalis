package cards;

import java.util.ArrayList;
import java.util.Random;

public class ObjectiveCard extends Card {
	
	private static ArrayList<Card> ObjectiveCardsDeck;
	
	public ObjectiveCard(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
			int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols) {
		super(type, isFront, frontCorners, backCorners, score, hasCenterSymbols, centerSymbols);
		// TODO Auto-generated constructor stub
	}





	

	
	
	
	
	public static ObjectiveCard drawCard() {

		// Draw a random card from the deck
		Random random = new Random();
		int index = random.nextInt(ObjectiveCardsDeck.size());
		ObjectiveCard card = (ObjectiveCard) ObjectiveCardsDeck.remove(index);

		return card;

	}





	
}
