package card;

import java.util.ArrayList;
import java.util.Random;

	public class GoldCard extends Card {
		
		private static final int NUM_CARDS_PER_KINGDOM = 10;
		private static ArrayList<Card> GoldCardsDeck;
		private static int points;

		public GoldCard(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
			int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols, ArrayList<Symbol> requirement) {
			super(type, isFront, frontCorners, backCorners, score, hasCenterSymbols, centerSymbols, requirement);
			// TODO Auto-generated constructor stub
		}
		
		public GoldCard() {
			super(CardType.GOLD, true, new ArrayList<>(), new ArrayList<>(), points, true, new ArrayList<>(), new ArrayList<>());
			createCards();
			shuffleDeck();
		}
		private void shuffleDeck() {
			// TODO Auto-generated method stub
			
		}

		private void createCards() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void play() {
			// TODO Auto-generated method stub
			
		}
		public static GoldCard drawCard() {

	        // Draw a random card from the deck
	        Random random = new Random();
	        int index = random.nextInt(GoldCardsDeck.size());
	        GoldCard card = (GoldCard) GoldCardsDeck.remove(index);

	        return card;
		}

}
