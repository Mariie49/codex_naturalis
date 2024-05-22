package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GoldCard extends Card {

	private static final int NUM_CARDS_PER_KINGDOM = 10;
	private static ArrayList<Card> GoldCardsDeck;
	private static int points;
	private static SpecialSymbol firstSpecialSymbol;

	public GoldCard(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
			int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols, ArrayList<Symbol> requirement, SpecialSymbol firstSpecialSymbol) {
		super(type, isFront, frontCorners, backCorners, score, hasCenterSymbols, centerSymbols, requirement, firstSpecialSymbol);
		// TODO Auto-generated constructor stub
	}

	public GoldCard() {
		super(CardType.GOLD, true, new ArrayList<>(), new ArrayList<>(), points, true, new ArrayList<>(), new ArrayList<>(), firstSpecialSymbol);
		createCards();
		shuffleDeck();
	}

	private void createCards() {
		// TODO Auto-generated method stub
		ArrayList<Symbol> kingdoms = new ArrayList<>();
		kingdoms.add(Symbol.PLANT_KINGDOM);
		kingdoms.add(Symbol.ANIMAL_KINGDOM);
		kingdoms.add(Symbol.FUNGI_KINGDOM);
		kingdoms.add(Symbol.INSECT_KINGDOM); 
		Corner corn = new Corner();
		GoldCardsDeck = new ArrayList<>();

		for(Symbol kingdom : kingdoms) {

			//Create 3 cards with 2 empty corners, 1 corner null and 1 with specialSymbol.

			Corner fCorner1 = createCorner(CornerState.EMPTY, null, null, true);

			SpecialSymbol firstSpecialSymbol = SpecialSymbol.getRandomSpecialSymbol();
			ArrayList<SpecialSymbol> usedSpecialSymbol = new ArrayList<>();
			ArrayList<Symbol> usedRequirements = new ArrayList<>();
			usedSpecialSymbol.add(firstSpecialSymbol);


			Corner fCorner2 = createCorner(CornerState.SPECIALSYMBOL, null, firstSpecialSymbol,true);
			Corner fCorner3 = createCorner(CornerState.EMPTY, null, null, true);
			Corner fCorner4 = createCorner(CornerState.NULL, null, null, true);

			ArrayList<Corner> firstFrontCorners = new ArrayList<>();
			firstFrontCorners.add(fCorner1);
			firstFrontCorners.add(fCorner2);
			firstFrontCorners.add(fCorner3);
			firstFrontCorners.add(fCorner4);

			ArrayList<Corner> backFirstCorners = createBackCorners();

			//add requirements only on gold cards
			ArrayList<Symbol> requirements = new ArrayList<Symbol>();
			Symbol firstSymbol;

			// This do - while help us to don't duplicate the specialSymbol 
			do {
				firstSymbol = Symbol.getRandomSymbol();
			}
			while(firstSymbol.equals(kingdom));
			requirements.add(kingdom);
			requirements.add(kingdom);
			requirements.add(firstSymbol);
			usedRequirements.add(firstSymbol);

			// Create Card which bring it 1 point for each specialSymbol on your board
			Card gCard = new Card(CardType.GOLD, true, firstFrontCorners, backFirstCorners, 1, false, new ArrayList<>(), requirements, firstSpecialSymbol) {

				@Override
				public void play() {
					// Implement the play method for the GoldCard
				}
			};

			GoldCardsDeck.add(gCard);
			// create the remaining 2 cards
			for (int i = 0; i < 2; i++) {

				ArrayList<Corner> backCorners = createBackCorners();
				ArrayList<Corner> frontCorners = new ArrayList<>();
				SpecialSymbol finalSpecialSymbol;
				Symbol finalSymbol;

				do {
					finalSpecialSymbol = SpecialSymbol.getRandomSpecialSymbol();
				}while(usedSpecialSymbol.contains(finalSpecialSymbol));

				usedSpecialSymbol.add(finalSpecialSymbol);

				// Create Corners and overwrite fCorner2 contains different SpecialSymbol
				Corner corner1 = fCorner1;
				Corner corner2 = createCorner(CornerState.SPECIALSYMBOL, null, finalSpecialSymbol, true);
				Corner corner3 = fCorner3;
				Corner corner4 = fCorner4;

				ArrayList<Corner> tempCorners = new ArrayList<>();
				tempCorners.add(corner1);
				tempCorners.add(corner2);
				tempCorners.add(corner3);
				tempCorners.add(corner4);

				Collections.shuffle(tempCorners);
				frontCorners = reorderCorners(tempCorners, corner1, corner2); // ensures that the two different symbols are consecutive
				
				// set requirements
				requirements.remove(2);	//remove firstSymbol using index 
				do {
					finalSymbol = Symbol.getRandomSymbol();
				}while(usedRequirements.contains(finalSymbol));
				requirements.set(2, finalSymbol);
				usedRequirements.add(finalSymbol);

				Card gCard2 = new Card(CardType.GOLD, true, frontCorners, backCorners, 1, false, new ArrayList<>(), requirements, finalSpecialSymbol) {
					@Override
					public void play() {
						// Implement the play method for the ResourceCard
					}
				};

				GoldCardsDeck.add(gCard2);

			}
		}
	}

	private ArrayList<Corner> createBackCorners() {
		ArrayList<Corner> backCorners = new ArrayList<>();
		Corner b = createCorner(CornerState.EMPTY, null, null, false);
		for (int j = 0; j< 4; j++) {
			backCorners.add(b);
		}
		return backCorners;
	}

	/**
	 * Checks if the positions of the Corner enum values in the input ArrayList satisfy the condition.
	 * The condition is that SYMBOLA and SYMBOLB should be consecutive, even if SYMBOLA is in the first
	 * position and SYMBOLB is in the last position.
	 *
	 * @param inputList The input ArrayList of Corner enum values.
	 * @return True if the positions satisfy the condition, false otherwise.
	 */
	public static boolean checkCornerPositions(ArrayList<Corner> inputList, Corner symbolA, Corner symbolB) {
		int symbolAIndex = inputList.indexOf(symbolA);
		int symbolBIndex = inputList.indexOf(symbolB);

		// Check if SYMBOLA and SYMBOLB are consecutive or if SYMBOLA is in the first position and SYMBOLB is in the last position
		if (Math.abs(symbolAIndex - symbolBIndex) == 1 || (symbolAIndex == 0 && symbolBIndex == inputList.size() - 1) || (symbolBIndex == 0 && symbolAIndex == inputList.size() - 1) ) {
			return true;
		}

		return false;
	}

	/**
	 * Reorders the elements in the input ArrayList of Corner enum values and returns a new ArrayList.
	 * The new ArrayList contains the elements from the input ArrayList, with SYMBOLA and SYMBOLB always
	 * appearing consecutively. If SYMBOLA is in the first position and SYMBOLB is in the last position,
	 * they are still considered consecutive.
	 *
	 * @param inputList The input ArrayList of Corner enum values.
	 * @return A new ArrayList with the elements from the input ArrayList, reordered to satisfy the condition.
	 */

	public static ArrayList<Corner> reorderCorners(ArrayList<Corner> inputList, card.Corner symbolA, card.Corner symbolB ) {
		ArrayList<Corner> outputList = new ArrayList<>();

		// Find the index of symbolA and symbolB in the input ArrayList
		int symbolAIndex = inputList.indexOf(symbolA);
		int symbolBIndex = inputList.indexOf(symbolB);
		// If symbolA and symbolB are consecutive in the input ArrayList, add them to the output ArrayList
		if (!checkCornerPositions(inputList, symbolA, symbolB)) {
			if (Math.abs(symbolAIndex - symbolBIndex) == 2 ) {
				outputList.add(symbolA);
				outputList.add(symbolB);
			}
			// Add the remaining elements from the input ArrayList to the output ArrayList
			for (card.Corner corner : inputList) {
				if (corner != symbolA && corner != symbolB) {
					outputList.add(corner);
				}
			}
			return outputList;
		}
		return inputList;     
	}

	private void shuffleDeck() {
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
