package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * Represents a initial cards in the game, which extends the Card class.
 */
public class InitialCard extends Card {
	private static ArrayList<Card> InitialCardsDeck;
	//private static final int NUM_CARDS = 6;
	private boolean hasCenterSymbols = true;
	/**
	 * Constructs an InitialCard and initializes the deck of initial cards.
	 */
	public InitialCard() {
		super(CardType.STARTING, true, new ArrayList<>(), new ArrayList<>(), 0, true, new ArrayList<>());
		createCards();
		shuffleDeck();
	}
	/**
	 * Creates the initial deck of cards for the game.
	 * 
	 * This method generates six specific InitialCards with predefined corner and center symbols.
	 * These cards are:
	 *
	 * - Card 1:
	 *   - Corners: EMPTY, PLANT_KINGDOM, EMPTY, INSECT_KINGDOM
	 *   - Center Symbols: INSECT_KINGDOM
	 *
	 * - Card 2:
	 *   - Corners: ANIMAL_KINGDOM, EMPTY, FUNGI_KINGDOM, EMPTY
	 *   - Center Symbols: FUNGI_KINGDOM
	 *
	 * - Card 3:
	 *   - Corners: 4 EMPTY
	 *   - Center Symbols: FUNGI_KINGDOM, PLANT_KINGDOM
	 *
	 * - Card 4:
	 *   - Corners: 4 EMPTY
	 *   - Center Symbols: INSECT_KINGDOM, ANIMAL_KINGDOM
	 *
	 * - Card 5:
	 *   - Corners: 2 EMPTY, 2 NULL
	 *   - Center Symbols: PLANT_KINGDOM, INSECT_KINGDOM, ANIMAL_KINGDOM
	 *
	 * - Card 6:
	 *   - Corners: 2 EMPTY, 2 NULL
	 *   - Center Symbols: FUNGI_KINGDOM, ANIMAL_KINGDOM, PLANT_KINGDOM
	 *
	 * The cards are added to the InitialCardsDeck ArrayList.
	 */
	private void createCards() {
		InitialCardsDeck = new ArrayList<>();

		//create the first card

		Corner fCorner1 = createCorner(CornerState.EMPTY, null, null, true);
		Corner fCorner2 = createCorner(CornerState.SYMBOL, Symbol.PLANT_KINGDOM, null, true);
		Corner fCorner3 = createCorner(CornerState.EMPTY, null, null, true);
		Corner fCorner4 = createCorner(CornerState.SYMBOL, Symbol.INSECT_KINGDOM, null, true);


		ArrayList<Corner> firstFrontCorners = new ArrayList<>();
		firstFrontCorners.add(fCorner1);
		firstFrontCorners.add(fCorner2);
		firstFrontCorners.add(fCorner3);
		firstFrontCorners.add(fCorner4); 
		
		ArrayList<Symbol> firstCenterSymbol = new ArrayList<>();
		firstCenterSymbol.add(Symbol.INSECT_KINGDOM);
		ArrayList<Corner> backFirstCorners = createBackCorners();
		
		Card firstCard = new Card(CardType.STARTING, true, firstFrontCorners, backFirstCorners, 0, hasCenterSymbols , firstCenterSymbol) {
		};
		InitialCardsDeck.add(firstCard);
		
		//create second card
		Corner sCorner2 = createCorner(CornerState.EMPTY, null, null, true);
		Corner sCorner1 = createCorner(CornerState.SYMBOL, Symbol.ANIMAL_KINGDOM, null, true);
		Corner sCorner4 = createCorner(CornerState.EMPTY, null, null, true);
		Corner sCorner3 = createCorner(CornerState.SYMBOL, Symbol.FUNGI_KINGDOM, null, true);


		ArrayList<Corner> secondFrontCorners = new ArrayList<>();
		secondFrontCorners.add(sCorner1);
		secondFrontCorners.add(sCorner2);
		secondFrontCorners.add(sCorner3);
		secondFrontCorners.add(sCorner4); 
		
		ArrayList<Symbol> secondCenterSymbol = new ArrayList<>();
		secondCenterSymbol.add(Symbol.FUNGI_KINGDOM);
		ArrayList<Corner> SecondbackCorners = createBackCorners();
		
		Card secondCard = new Card(CardType.STARTING, true, secondFrontCorners, SecondbackCorners, 0, hasCenterSymbols , secondCenterSymbol) {
		};
		InitialCardsDeck.add(secondCard);
		
		//create third card
				Corner tCorner = createCorner(CornerState.EMPTY, null, null, true);
				ArrayList<Corner> thirdFrontCorners = new ArrayList<>();
				for (int j = 0; j< 4; j++) {
					thirdFrontCorners.add(tCorner);
				}

				
				ArrayList<Symbol> thirdCenterSymbol = new ArrayList<>();
				thirdCenterSymbol.add(Symbol.FUNGI_KINGDOM);
				thirdCenterSymbol.add(Symbol.PLANT_KINGDOM);
				
				ArrayList<Corner> thirdbackCorners = createBackCorners();
				
				Card thirdCard = new Card(CardType.STARTING, true, thirdFrontCorners, thirdbackCorners, 0, hasCenterSymbols , thirdCenterSymbol) {
				};
				InitialCardsDeck.add(thirdCard);
				
				//create fourth card
				Corner fCorner = createCorner(CornerState.EMPTY, null, null, true);
				ArrayList<Corner> fourthFrontCorners = new ArrayList<>();
				for (int j = 0; j< 4; j++) {
					fourthFrontCorners.add(fCorner);
				}

				
				ArrayList<Symbol> fourthCenterSymbol = new ArrayList<>();
				fourthCenterSymbol.add(Symbol.INSECT_KINGDOM);
				fourthCenterSymbol.add(Symbol.ANIMAL_KINGDOM);
				
				
				ArrayList<Corner> fourthbackCorners = createBackCorners();
				
				Card fourthCard = new Card(CardType.STARTING, true, fourthFrontCorners, fourthbackCorners, 0, hasCenterSymbols , fourthCenterSymbol) {
					
				};
				InitialCardsDeck.add(fourthCard);
				
				//create fifth card
				Corner fifCorner1 = createCorner(CornerState.EMPTY, null, null, true);
				Corner fifCorner2 = createCorner(CornerState.EMPTY, null, null, true);
				Corner fifCorner3 = createCorner(CornerState.NULL, null, null, true);
				Corner fifCorner4 = createCorner(CornerState.NULL, null, null, true);
				


				ArrayList<Corner> fifthFrontCorners = new ArrayList<>();
				fifthFrontCorners.add(fifCorner1);
				fifthFrontCorners.add(fifCorner2);
				fifthFrontCorners.add(fifCorner3);
				fifthFrontCorners.add(fifCorner4); 
				
				ArrayList<Symbol> fifthCenterSymbol = new ArrayList<>();
				fifthCenterSymbol.add(Symbol.PLANT_KINGDOM);
				fifthCenterSymbol.add(Symbol.INSECT_KINGDOM);
				fifthCenterSymbol.add(Symbol.ANIMAL_KINGDOM);
				ArrayList<Corner> fifthbackCorners = createBackCorners();
				
				Card fifthCard = new Card(CardType.STARTING, true, fifthFrontCorners, fifthbackCorners, 0, hasCenterSymbols , fifthCenterSymbol) {
					
				};
				InitialCardsDeck.add(fifthCard);
				
				
				//create sixth card

				ArrayList<Corner> sixthFrontCorners = new ArrayList<>();
				sixthFrontCorners.add(fifCorner1);
				sixthFrontCorners.add(fifCorner2);
				sixthFrontCorners.add(fifCorner3);
				sixthFrontCorners.add(fifCorner4); 
				
				ArrayList<Symbol> sixthCenterSymbol = new ArrayList<>();
				sixthCenterSymbol.add(Symbol.FUNGI_KINGDOM);
				sixthCenterSymbol.add(Symbol.ANIMAL_KINGDOM);
				sixthCenterSymbol.add(Symbol.PLANT_KINGDOM);
				ArrayList<Corner> sixthbackCorners = createBackCorners();
				
				Card sixthCard = new Card(CardType.STARTING, true, sixthFrontCorners, sixthbackCorners, 0, hasCenterSymbols , sixthCenterSymbol) {
				};
				InitialCardsDeck.add(sixthCard);
		
	}


	
	/**
	 * Creates a ArrayList of back corners for a card.
	 *
	 * @return the ArrayList of back corners
	 */

	private ArrayList<Corner> createBackCorners() {
		ArrayList<Corner> backCorners = new ArrayList<>();
		ArrayList<Symbol> kingdoms = new ArrayList<>();
		kingdoms.add(Symbol.PLANT_KINGDOM);
		kingdoms.add(Symbol.ANIMAL_KINGDOM);
		kingdoms.add(Symbol.FUNGI_KINGDOM);
		kingdoms.add(Symbol.INSECT_KINGDOM);

		for (Symbol kingdom : kingdoms) {
			Corner b = createCorner(CornerState.SYMBOL, kingdom, null, false);
			backCorners.add(b);
		}
		Collections.shuffle(backCorners);
		return backCorners;
	}

	/**
	 * Checks if the deck of initial cards is empty.
	 *
	 * @return true if the deck is empty, false otherwise.
	 */
	public static boolean isEmpty() {
        return InitialCardsDeck.isEmpty();
	}
	
	/**
	 * Draws and returns a random InitialCard from the deck.
	 * 
	 * This method randomly selects an InitialCard from the InitialCardsDeck, removes it from the deck, 
	 * and returns it. Before returning the card:
	 * - It prints the details of both the front and back sides of the card.
	 * - It allows the player to choose which side to play the card on using the chooseSide() method.
	 * - If the player chooses the back side, the card is flipped again before being returned.
	 * 
	 * @return The randomly drawn and player-chosen InitialCard.                                   
	 */
	public static InitialCard drawCard() {

		// Draw a random card from the deck
		Random random = new Random();
		int index = random.nextInt(InitialCardsDeck.size());
		InitialCard card = (InitialCard) InitialCardsDeck.remove(index);
		// Print the front and back sides of the card
        System.out.println("Front side of the card: ");
		card.printCard();
		card.flip();
        System.out.println("Back side of the card: ");
		card.printCard(); 
		card.flip(); //back to front
		boolean front = card.chooseSide();
		if(front) {
			return card;
		}
		else {
			card.flip();
			return card;
		}

	}


	
	/**
	 * Flips the card by swapping its front and back corners and toggling the isFront flag.
	 *
	 * @param cornersA the list of corners representing the front side of the card
	 * @param cornersB the list of corners representing the back side of the card
	 * @param centerArray the list of symbols representing the center symbols of the card
	 * @param isFront the boolean flag indicating if the card is currently showing its front side
	 * @param kingdom the symbol representing the kingdom associated with the card
	 * @return a new Card instance with swapped corners and toggled isFront flag
	 */


	public Card flip(ArrayList <Corner> cornersA,ArrayList <Corner> cornersB, ArrayList <Symbol> centerArray, boolean isFront) {

		if (isFront) {
			// Swap front and back corners (front-->back)
			ArrayList<Corner> tempCorners = new ArrayList<>(cornersA);
			cornersA.clear();
			cornersA.addAll(cornersB);

			cornersB.clear();
			cornersB.addAll(tempCorners);

			// Flip the isFront flag
			isFront = !isFront;

			boolean HasCenterSymbol = false;
			ArrayList<Symbol> centerSymbols = null;

			return new Card (CardType.STARTING,isFront ,cornersA, cornersB, 0, HasCenterSymbol, centerSymbols){
			
			};
		}
		else {
			// Swap front and back corners (back-->front)
			ArrayList<Corner> tempCorners = new ArrayList<>(cornersB);
			cornersB.clear();
			cornersB.addAll(cornersA);

			cornersA.clear();
			cornersA.addAll(tempCorners);
			

			// Flip the isFront flag
			isFront = !isFront;

			boolean centerSymbol = true;
			return new Card (CardType.STARTING, isFront ,cornersA, cornersB, 0, centerSymbol, centerArray){
				
			};
		}
	}
	/**
	 * Randomizes the order of cards in the InitialCardsDeck.
	 */
	private void shuffleDeck() {
		Collections.shuffle(InitialCardsDeck);
	}

}
