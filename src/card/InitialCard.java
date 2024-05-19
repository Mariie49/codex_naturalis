package card;

import java.util.ArrayList;
import java.util.Random;

	public class InitialCard extends Card {
		private static ArrayList<Card> InitialCardsDeck;


		public InitialCard(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
				int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols) {
			super(type, isFront, frontCorners, backCorners, score, hasCenterSymbols, centerSymbols);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void play() {
			// TODO Auto-generated method stub

		}
		public void chooseOrientationAndPlaceInitialCard(Card initialCard) {
			// Implement the logic to choose the orientation and place the initial card on the manuscript
			// ...
		}
		public static InitialCard drawCard() {

			// Draw a random card from the deck
			Random random = new Random();
			int index = random.nextInt(InitialCardsDeck.size());
			InitialCard card = (InitialCard) InitialCardsDeck.remove(index);

			return card;

		}

		public void printFrontSide() {
			// TODO Auto-generated method stub
			
		}

		public void printBackSide() {
			// TODO Auto-generated method stub
			
		}

		public Card getFrontSide() {
			// TODO Auto-generated method stub
			return null;
		}

		public Card getBackSide() {
			// TODO Auto-generated method stub
			return null;
		}
}
