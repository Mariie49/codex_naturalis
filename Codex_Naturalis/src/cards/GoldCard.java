package cards;

import java.util.ArrayList;
import java.util.Random;

public class GoldCard extends Card {
	private static ArrayList<Card> GoldCardsDeck;

	public GoldCard(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
			int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols) {
		super(type, isFront, frontCorners, backCorners, score, hasCenterSymbols, centerSymbols);
		// TODO Auto-generated constructor stub
	}
	
    
	
	public static GoldCard drawCard() {

        // Draw a random card from the deck
        Random random = new Random();
        int index = random.nextInt(GoldCardsDeck.size());
        GoldCard card = (GoldCard) GoldCardsDeck.remove(index);

        return card;
	}

}
