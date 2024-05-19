package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
	/**
	 * Represents a resource card in the game, which extends the Card class.
	 */
	public class ResourceCard extends Card {
		
		private static final int NUM_CARDS_PER_KINGDOM = 10;
		private static ArrayList<Card> resourceCardsDeck;
		/**
	     * Constructs a ResourceCard with default properties and initializes the deck with resource cards.
	     */
		public ResourceCard() {
			super(CardType.RESOURCE, true, new ArrayList<>(), new ArrayList<>(), 0, true, new ArrayList<>());
			createCards();
			shuffleDeck();
		}
		
		/**
	     * Creates the resource cards and adds them to the deck.
	     */
		private void createCards() {
			ArrayList<Symbol> kingdoms = new ArrayList<>();
			kingdoms.add(Symbol.PLANT_KINGDOM);
			kingdoms.add(Symbol.ANIMAL_KINGDOM);
			kingdoms.add(Symbol.FUNGI_KINGDOM);
			kingdoms.add(Symbol.INSECT_KINGDOM); 
			Corner corn = new Corner();
			resourceCardsDeck = new ArrayList<>();

			for (Symbol kingdom : kingdoms) {
				
				// Create 4 cards with 2 identical symbols on consecutive corners,an empty corner and a null corner
				//create the first card
				
				Corner fCorner1 = createCorner(CornerState.SYMBOL, kingdom, null, true);
	            Corner fCorner2 = createCorner(CornerState.SYMBOL, kingdom, null, true);
	            Corner fCorner3 = createCorner(CornerState.NULL, null, null, true);
	            Corner fCorner4 = createCorner(CornerState.EMPTY, null, null, true);
	            
	            ArrayList<Corner> firstFrontCorners = new ArrayList<>();
	            frontCorners.add(fCorner1);
	            frontCorners.add(fCorner2);
	            frontCorners.add(fCorner3);
	            frontCorners.add(fCorner4); 

	            ArrayList<Corner> backFirstCorners = createBackCorners();
	       

	            Card fCard = new Card(CardType.RESOURCE, true, firstFrontCorners, backFirstCorners, 1, false, new ArrayList<>()) {
	                @Override
	                public void play() {
	                    // Implement the play method for the ResourceCard
	                }
	            };

	            resourceCardsDeck.add(fCard);
				// create the remaining 3 cards
				for (int i = 0; i < 3; i++) {
					
	                ArrayList<Corner> backCorners = createBackCorners();
	                ArrayList<Corner> frontCorners = corn.shiftCornerPositions(firstFrontCorners);
	                
	                Card card = new Card(CardType.RESOURCE, true, frontCorners, backCorners, 0, false, new ArrayList<>()) {
	                    @Override
	                    public void play() {
	                        // Implement the play method for the ResourceCard
	                    }
	                };

	                resourceCardsDeck.add(card);
	                firstFrontCorners.clear();
	                firstFrontCorners.addAll(frontCorners);
	                frontCorners.clear();
	                
				} 

				// Create 2 cards with 2 different symbols (one kingdom's symbol) on consecutive corners and a special symbol
				for (int i = 0; i < 2; i++) {
					
					Corner corner1 = createCorner(CornerState.SYMBOL, kingdom, null, true);
					Symbol differentKingdomSymbol = Symbol.getRandomSymbol();
					//check that the two symbols are different
					do {									
						differentKingdomSymbol = Symbol.getRandomSymbol();
					}while (differentKingdomSymbol.equals(kingdom));
					
					Corner corner2 = createCorner(CornerState.SYMBOL, differentKingdomSymbol, null, true);
					Corner corner3 = createCorner(CornerState.SPECIALSYMBOL, null, SpecialSymbol.getRandomSpecialSymbol(), true);
					Corner corner4 = createCorner(CornerState.NULL, null, null, true);
					
					ArrayList<Corner> tempCorners = new ArrayList<>();
					tempCorners.add(corner1);
					tempCorners.add(corner2);
					tempCorners.add(corner3);
					tempCorners.add(corner4);
					
					Collections.shuffle(tempCorners); //the same 4 Corner enums are shuffled
					ArrayList <Corner> frontCorners = reorderCorners(tempCorners, corner1, corner2); // ensures that the two different symbols are consecutive
					
					
					//ArrayList<Corner> frontCorners = corn.shiftCornerPositions(tempCorners);
					ArrayList<Corner> backCorners = createBackCorners();

					Card card = new Card(CardType.RESOURCE, true, frontCorners, backCorners, 0, false, new ArrayList<>()) {
						@Override
						public void play() {
							// Implement the play method for the ResourceCard
						}
					};
					resourceCardsDeck.add(card);
				}

				// Create 1 cards with 2 different symbols on consecutive corners, 1 special symbol and an empty corner
				for (int i = 0; i < 2; i++) {
					Corner corner1 = createCorner(CornerState.SYMBOL, kingdom, null, true);
					Corner corner2 = createCorner(CornerState.SYMBOL, kingdom, null, true);
					Corner corner3 = createCorner(CornerState.SPECIALSYMBOL, null, SpecialSymbol.getRandomSpecialSymbol(), true);
					Corner corner4 = createCorner(CornerState.EMPTY, null, null, true);

					ArrayList<Corner> frontCorners = new ArrayList<>();
					frontCorners.add(corner1);
					frontCorners.add(corner2);
					frontCorners.add(corner3);
					frontCorners.add(corner4);

					ArrayList<Corner> backCorners = createBackCorners();


					Card card = new Card(CardType.RESOURCE, true, frontCorners, backCorners, 0, false, new ArrayList<>()) {
						@Override
						public void play() {
							// Implement the play method for the ResourceCard
						}
					};
					resourceCardsDeck.add(card);
				}

				// Create 3 cards with 1 symbol on a corner and 2 empty corners (1 hidden)
				//create the first card
				
				Corner firstCorner1 = createCorner(CornerState.SYMBOL, kingdom, null, true);
			    Corner firstCorner2 = createCorner(CornerState.EMPTY, null , null, true);
			    Corner firstCorner3 = createCorner(CornerState.EMPTY, null, null, true);
			    Corner firstCorner4 = createCorner(CornerState.NULL, null, null, true);
			    
			    ArrayList<Corner> firStFrontCorners = new ArrayList<>();
			    frontCorners.add(firstCorner1);
			    frontCorners.add(firstCorner2);
			    frontCorners.add(firstCorner3);
			    frontCorners.add(firstCorner4); 

			    ArrayList<Corner> backFirStCorners = createBackCorners();


			    Card firstCard = new Card(CardType.RESOURCE, true, firStFrontCorners, backFirStCorners, 1, false, new ArrayList<>()) {
			        @Override
			        public void play() {
			            // Implement the play method for the ResourceCard
			        }
			    };

			    resourceCardsDeck.add(firstCard);
				// create the remaining 2 cards
				for (int i = 0; i < 2; i++) {
					
			        ArrayList<Corner> backCorners = createBackCorners();
			        
			        ArrayList<Corner> frontCorners = corn.shiftCornerPositions(firstFrontCorners);
			        
			        Card card = new Card(CardType.RESOURCE, true, frontCorners, backCorners, 1, false, new ArrayList<>()) {
			            @Override
			            public void play() {
			                // Implement the play method for the ResourceCard
			            }
			        };

			        resourceCardsDeck.add(card);
			        firstFrontCorners.clear();
			        firstFrontCorners.addAll(frontCorners);
			        frontCorners.clear();
			        
				} 

				// Shuffle the cards to randomize their order
				Collections.shuffle(resourceCardsDeck);

				// Add the cards to the deck
				for (int i = 0; i < NUM_CARDS_PER_KINGDOM; i++) {
					addCard(resourceCardsDeck.get(i));
				}
			}
		}
		
		/**
	     * Creates a list of back corners for a card.
	     *
	     * @return the list of back corners
	     */

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
	        Collections.shuffle(resourceCardsDeck);
	    }
	    public static ResourceCard drawCard() {

	        // Draw a random card from the deck
	        Random random = new Random();
	        int index = random.nextInt(resourceCardsDeck.size());
	        ResourceCard card = (ResourceCard) resourceCardsDeck.remove(index);

	        return card;
	    }
		
	    //non sono convinta di questo metodo
		private void addCard(Card card) {
			if (isFront()) {
				getFrontCorners().addAll(card.getFrontCorners());
				getBackCorners().addAll(card.getBackCorners());
			} else {
				getBackCorners().addAll(card.getFrontCorners());
				getFrontCorners().addAll(card.getBackCorners());
			}
		}
		/**
		 * Flips the card by swapping its front and back corners and toggling the isFront flag.
		 *
		 * @param cornersA the list of corners representing the front side of the card
		 * @param cornersB the list of corners representing the back side of the card
		 * @param isFront the boolean flag indicating if the card is currently showing its front side
		 * @param kingdom the symbol representing the kingdom associated with the card
		 * @return a new Card instance with swapped corners and toggled isFront flag
		 */

		public Card flip(ArrayList <Corner> cornersA,ArrayList <Corner> cornersB, boolean isFront, Symbol kingdom) {

			// Swap front and back corners
			ArrayList<Corner> tempCorners = new ArrayList<>(cornersA);
			cornersA.clear();
			cornersA.addAll(cornersB);

			cornersB.clear();
			cornersB.addAll(tempCorners);

			// Flip the isFront flag
			isFront = !isFront;

			boolean centerSymbol = true;
			ArrayList<Symbol> centerSymbols = new ArrayList <Symbol> ();
			centerSymbols.add(kingdom);

			return new Card (CardType.RESOURCE,isFront ,cornersA, cornersB, 0, centerSymbol, centerSymbols){
				@Override
				public void play() {
					// Implement the play method for the ResourceCard
				}
				

			};

			
		} 
		
		
		@Override
	    public void printCard() {
	        super.printCard();
	        // Display information based on isFront flag
	    }

		@Override
		public void play() {
			// TODO Auto-generated method stub

		}
}
