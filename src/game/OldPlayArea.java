package game;

import java.util.ArrayList;
import java.util.Scanner;

import cards.Card;
import cards.CardType;
import cards.Corner;
import cards.CornerPosition;
import cards.CornerState;
import cards.Symbol;
import initialCard.InitialCard;

/**
 * Represents the play area where cards are placed during the game.
 *
 * The play area is a virtual grid with no fixed dimensions, allowing cards to be placed
 * in any position as long as the cards observe the placement rules of the game..
 */
public class OldPlayArea {
	private ArrayList<Card> placedCards;
	private int minRow = Integer.MAX_VALUE;
	private int maxRow = Integer.MIN_VALUE;
	private int minCol = Integer.MAX_VALUE;
	private int maxCol = Integer.MAX_VALUE;
	/**
	 * Constructs an empty play area.
	 */
	public OldPlayArea() {
		placedCards = new ArrayList<>();
	}
	/*
	 * /**
     * Returns a reference to this PlayArea.
     *
     * This method allows other parts of the game to access and interact with the play area.
     * @return A reference to this PlayArea object.
    
    public PlayArea getPlayArea() {
        return this;
    }
	 */
	/**
	 * Returns a copy of this PlayArea object.
	 *
	 * This method creates a new PlayArea instance and copies the references to the placed cards,
	 * as well as the minRow, maxRow, minCol, and maxCol attributes.
	 *
	 * @return A copy of this PlayArea object.
	 */
	public OldPlayArea getPlayArea() {
		OldPlayArea copy = new OldPlayArea();
	    copy.placedCards = new ArrayList<>(placedCards); // Copia la lista delle carte
	    copy.minRow = minRow;
	    copy.maxRow = maxRow;
	    copy.minCol = minCol;
	    copy.maxCol = maxCol;
	    return copy;
	}
	//verificare se funziona
	/**
     * Places the initial card in the play area.
     *
     * @param initialCard The initial card to be placed.
     * @throws IllegalArgumentException If the initial card is not of type STARTING.
     */
	public void placeInitialCard(InitialCard initialCard) {
		if (initialCard.getType() != CardType.STARTING) { //penso che non serva
			throw new IllegalArgumentException("The initial card must be of the STARTING type.");
		}
		initialCard.setPlaced(true);
		placedCards.add(initialCard);
		updateBoundaries(initialCard);
	}
	/**
     * Places a card in the play area, adhering to the game's placement rules.
     *
     * @param card The card to be placed.
     * @param targetCorner The corner of an existing card to be covered by the new card.
     * @throws IllegalArgumentException If the target corner is invalid (NULL state) or if the placement violates the rules.
     */
	public void placeCard(Card card, Corner targetCorner) {
		// Controlla se l'angolo è NULL.
		if (targetCorner.getState() == CornerState.NULL) {
			throw new IllegalArgumentException("Invalid target corner.");
		}

		// Controlla se la nuova carta copre più angoli della stessa carta.
		if (coversMultipleCorners(card, targetCorner)) {
			throw new IllegalArgumentException("The card cannot cover more than one corner of the same card.");
		}
		//Recupera la carta a cui appartiene l'angolo da coprire.
		Card existingCard = targetCorner.getCard();
		// Determina la posizione della nuova carta e controlla la validità dell'angolo opposto
		//Ottiene la posizione dell'angolo di destinazione (targetPosition).
		//Calcola la posizione opposta (oppositePosition).
		//Ottiene il corner opposto della nuova carta (oppositeCorner).
		//CornerPosition oppositeCornerPosition = targetCorner.getPosition().getOpposite();
		//Corner oppositeCorner = card.getCorner(oppositeCornerPosition); // Ottiene il corner opposto della nuova carta

		// Determina la posizione della nuova carta
		switch (targetCorner.getPosition()) {
		case TOP_LEFT:
			card.setRow(existingCard.getRow() - 1);
			card.setColumn(existingCard.getColumn() - 1);
			break;
		case TOP_RIGHT:
			card.setRow(existingCard.getRow() - 1);
			card.setColumn(existingCard.getColumn() + 1);
			break;
		case BOTTOM_RIGHT:
			card.setRow(existingCard.getRow() + 1);
			card.setColumn(existingCard.getColumn() + 1);
			break;
		case BOTTOM_LEFT:
			card.setRow(existingCard.getRow() + 1);
			card.setColumn(existingCard.getColumn() - 1);
			break;
		}

		// Aggiorna le adiacenze della nuova carta e delle carte circostanti
		updateCardAdjacencies(card);
		for (Card placedCard : placedCards) {
			if (placedCard != card && areCardsAdjacent(card, placedCard)) {
				updateCardAdjacencies(placedCard);
			}
		}

		// Aggiorna lo stato del corner di destinazione (solo se non è NULL) a HIDDEN e imposta isCovered a true
		if (targetCorner.getState() != CornerState.NULL) {
			targetCorner.setState(CornerState.HIDDEN);
			targetCorner.setCovered(true);
		}


		card.setPlaced(true);
		placedCards.add(card);
		updateBoundaries(card);
		updateCoveredCorners(card);//AGGIORNA I CORNER COPERTI
	}
	/**
     * Displays the play area in the console.
     *
     * This method prints a textual representation of the play area, showing the symbols
     * on the corners and Central of each card. Cards are displayed in their relative positions,
     * taking into account any overlapping corners.
     */
	public void displayPlayArea() {
		 // Aggiorna i limiti della griglia per determinare l'intervallo di righe e colonne da visualizzare.
		updateBoundaries();
		
		// Calcola la larghezza massima per un corner
	    int minCornerWidth = 0;
	    for (CornerPosition pos : CornerPosition.values()) {
	        minCornerWidth = Math.max(minCornerWidth, pos.name().length());
	    }

		// Calcola la larghezza massima di una carta per visualizzare la carta più larga, considerando sia i simboli centrali che i corner.
		int dimMaxCard = (minCornerWidth * 2 ) + 3; // Spazio per i due corner di una riga + spazi
		for (Card card : placedCards) {
			int cardWidth = 0;
			if (card.hasCentralSymbols()) {
				for (Symbol symbol : card.getCentralSymbols()) {
					cardWidth += symbol.name().length() + 1; // +1 per lo spazio
				}
			}
			cardWidth = Math.max(cardWidth, 9); // Considera anche la larghezza dei corner
			dimMaxCard = Math.max(dimMaxCard, cardWidth);
		}

		for (int row = minRow; row <= maxRow; row++) {
			for (int i = 0; i < 3; i++) { // Tre righe per ogni carta
				for (int col = minCol; col <= maxCol; col++) {
					String symbolToPrint = " ".repeat(dimMaxCard); 

					// Itera su tutte le carte per trovare quella che copre la cella corrente
					for (Card card : placedCards) {
						if (card.getRow() == row && card.getColumn() == col) {
							if (i == 0) {
								symbolToPrint = String.format("%-" + (dimMaxCard / 2) + "s%-" + (dimMaxCard / 2) + "s",
										getCornerRepresentation(card, CornerPosition.TOP_LEFT),
										getCornerRepresentation(card, CornerPosition.TOP_RIGHT));
							} else if (i == 1) {
								String CentralSymbols = "";
								if (card.hasCentralSymbols()) {
									for (Symbol symbol : card.getCentralSymbols()) {
										CentralSymbols += symbol.name() + " ";
									}
									symbolToPrint = String.format("%-" + dimMaxCard + "s", CentralSymbols.trim());
								} else if (!card.hasCentralSymbols()) {
									symbolToPrint = " ".repeat(dimMaxCard); 
								}
							} else {
								symbolToPrint = String.format("%-" + (dimMaxCard / 2) + "s%-" + (dimMaxCard / 2) + "s",
										getCornerRepresentation(card, CornerPosition.BOTTOM_LEFT),
										getCornerRepresentation(card, CornerPosition.BOTTOM_RIGHT));
							}
							break; 
						}
					}
					System.out.print(symbolToPrint); 
				}
				System.out.println(); 
			}
			System.out.println(); // Aggiungi una riga vuota tra le righe di carte
		}
	}

	 /**
     * Gets the list of placed cards in the play area.
     *
     * @return An ArrayList containing the placed Card objects.
     */
    public ArrayList<Card> getPlacedCards() {
        return new ArrayList<>(placedCards); // Restituisce una copia per evitare modifiche esterne
    }

	/**
	 * Gets the textual representation of a corner of the given card, based on its position.
	 *
	 * This method iterates through the card's corners and returns the textual representation
	 * of the corner that matches the given position. If the corner is covered by another card,
	 * it is not included in the representation.
	 *
	 * @param card The card from which to extract the corner representation.
	 * @param position The position of the corner on the card (TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT).
	 * @return The textual representation of the corner
	 * */
	private String getCornerRepresentation(Card card, CornerPosition position) {
		for (Corner corner : card.addCorners()) {
			if (corner.getPosition() == position && !corner.isCovered()) { // Controlla se il corner è coperto
				switch (corner.getState()) {
				case NULL:
					return "NULL";
				case EMPTY:
					return "EMPTY";
				case SYMBOL:
					return corner.getSymbol().toString();
				case SPECIALSYMBOL:
					return corner.getSymbol().toString();
				default:
					return "?";
				}
			}
		}
		return " "; // Angolo non trovato (non dovrebbe accadere)
	}

	/**
     * Checks if a card covers multiple corners of the same card.
     *
     * @param newCard The new card being placed.
     * @param targetCorner The target corner of an existing card.
     * @return true if the new card covers multiple corners of the existing card, false otherwise.
     */
	private boolean coversMultipleCorners(Card newCard, Corner targetCorner) {
		Card existingCard = targetCorner.getCard();
		int coveredCorners = 0;
		for (Corner corner : newCard.addCorners()) {
			if (corner.getCard() == existingCard && corner.getState() != CornerState.NULL) { // Controlla tutti gli stati tranne NULL
				coveredCorners++;
				if (coveredCorners > 1) {
					return true; // Più di un corner della stessa carta è coperto
				}
			}
		}
		return false; // Solo un corner o nessuno è coperto
	}
	
	/**
     * Counts the number of different cards whose corners are covered by a new card.
     *
     * @param newCard The new card being placed.
     * @return The number of different cards covered by the new card.
     */
    private int countCoveredCards(Card newCard) {
        ArrayList<Card> coveredCards = new ArrayList<>(); // Lista per tenere traccia delle carte coperte
        for (Corner corner : newCard.addCorners()) {
            if (corner.getState() != CornerState.NULL) { // Considera solo gli angoli validi
                int cornerRow = newCard.getRow() + corner.getPosition().getRowOffset();
                int cornerCol = newCard.getColumn() + corner.getPosition().getColOffset();

                Card coveredCard = getCardAt(cornerRow, cornerCol); // Trova la carta coperta (se esiste)
                if (coveredCard != null && coveredCard != newCard && !coveredCards.contains(coveredCard)) { 
                    // Escludi la nuova carta stessa e i duplicati
                    coveredCards.add(coveredCard); 
                }
            }
        }

        return coveredCards.size(); // Restituisci il numero di carte coperte
    }
	/**
     * Gets the card at the specified row and column.
     *
     * @param row The row of the card.
     * @param col The column of the card.
     * @return The card at the specified position, or null if no card is found.
     */
	private Card getCardAt(int row, int col) {
		for (Card card : placedCards) {
			if (card.getRow() == row && card.getColumn() == col) {
				return card;
			}
		}
		return null;
	}

	 public void interactWithPlayArea() {
		Scanner scanner = new Scanner(System.in);
		boolean continuePlaying = true;

		while (continuePlaying) {
			displayPlayArea(); // Mostra l'area di gioco

			System.out.println("Scegli un'azione:");
			System.out.println("1. Posiziona una carta");
			System.out.println("2. Termina il turno");

			int choice = scanner.nextInt();
			scanner.nextLine(); 

			switch (choice) {
			case 1:
				chooseCardInPlayArea(scanner); // Interazione per posizionare una carta
				scanner.close();
				break;

			case 2:
				continuePlaying = false;
				System.out.println("Fine del turno!");
				break;
			default:
				System.out.println("Scelta non valida.");
			}
		}
	}

	 
	
	/**
	 * This method asks the user to enter the row and column coordinates of a card in the play area,
	 * and then to choose a corner position of that card (TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT).
	 * @param scanner A Scanner object for reading user input.
	 * @return The selected Corner object.
	 */
	public Corner chooseCardInPlayArea(Scanner scanner) {
		Card existingCard = null;
		Corner targetCorner = null;

		do {
			// Selezione della carta esistente nell'area di gioco
			System.out.println("Choose an existing card in the game area ( insert its row,column coordinates):");
			int existingCardRow = scanner.nextInt();
			int existingCardCol = scanner.nextInt();
			scanner.nextLine(); 

			existingCard = getCardAt(existingCardRow, existingCardCol);
			if (existingCard == null || !existingCard.isPlaced()) {
				System.out.println("No valid cards found in that position.");
			} else {
				// Selezione del corner della carta esistente
				System.out.println("Choose a corner of the existing card  (TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT):");
				String cornerPositionStr = scanner.nextLine();
				try {
					CornerPosition cornerPosition = CornerPosition.valueOf(cornerPositionStr.toUpperCase());
					targetCorner = existingCard.getCorner(cornerPosition);
					if (targetCorner == null) {
						System.out.println("Invalid corner");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid corner position.");
					targetCorner = null; // Resetta targetCorner in caso di errore
				}
			}
		} while (existingCard == null || !existingCard.isPlaced() || targetCorner == null); // Continua finché non sono valide

		return targetCorner;
	}


	/**
     * Updates the boundaries of the play area based on a specific card.
     *
     * @param card The card to be considered for updating the boundaries.
     */
	private void updateBoundaries(Card card) {
		minRow = Math.min(minRow, card.getRow());
		maxRow = Math.max(maxRow, card.getRow());
		minCol = Math.min(minCol, card.getColumn());
		maxCol = Math.max(maxCol, card.getColumn());
	}
	 /**
     * Updates the boundaries of the play area based on the placed cards.
     *
     * This method calculates the minimum and maximum row and column values based on the
     * positions of the placed cards. It is used to determine the area of the grid that
     * needs to be displayed.
     */
	private void updateBoundaries() {
		minRow = Integer.MAX_VALUE;
		maxRow = Integer.MIN_VALUE;
		minCol = Integer.MAX_VALUE;
		maxCol = Integer.MIN_VALUE;
		for (Card card : placedCards) {
			updateBoundaries(card); // Richiama il metodo con un singolo parametro per ogni carta
		}
	}

	

	/**
     * Updates the adjacency relationships of a card.
     *
     * @param card The card whose adjacencies need to be updated.
     */
	private void updateCardAdjacencies(Card card) {
		int row = card.getRow();
		int col = card.getColumn();

		// Azzera le adiacenze prima di ricalcolarle
		card.setAdjacentTop(null);
		card.setAdjacentBottom(null);
		card.setAdjacentLeft(null);
		card.setAdjacentRight(null);
		card.setAdjacentTopLeft(null);
		card.setAdjacentTopRight(null);
		card.setAdjacentBottomRight(null);
		card.setAdjacentBottomLeft(null);

		for (Card otherCard : placedCards) {
			if (otherCard != card) { 
				int otherRow = otherCard.getRow();
				int otherCol = otherCard.getColumn();

				// Controlla le adiacenze orizzontali e verticali
				if (otherRow == row && otherCol == col - 1) {
					card.setAdjacentLeft(otherCard);
					otherCard.setAdjacentRight(card);
				} else if (otherRow == row && otherCol == col + 1) {
					card.setAdjacentRight(otherCard);
					otherCard.setAdjacentLeft(card);
				} else if (otherRow == row - 1 && otherCol == col) {
					card.setAdjacentTop(otherCard);
					otherCard.setAdjacentBottom(card);
				} else if (otherRow == row + 1 && otherCol == col) {
					card.setAdjacentBottom(otherCard);
					otherCard.setAdjacentTop(card);
				}

				// Controlla le adiacenze diagonali
				for (Corner corner : card.addCorners()) {
					for (Corner otherCorner : otherCard.addCorners()) {
						CornerPosition cornerPos = corner.getPosition();
						CornerPosition otherCornerPos = otherCorner.getPosition();

						// Calcola le coordinate assolute dei corner
						int cornerRow = card.getRow() + cornerPos.getRowOffset();
						int cornerCol = card.getColumn() + cornerPos.getColOffset();
						int otherCornerRow = otherCard.getRow() + otherCornerPos.getRowOffset();
						int otherCornerCol = otherCard.getColumn() + otherCornerPos.getColOffset();

						if (cornerPos == otherCornerPos.getOpposite() && cornerRow == otherCornerRow && cornerCol == otherCornerCol) {
							// Le carte sono adiacenti diagonalmente
							switch (cornerPos) {
							case TOP_LEFT:
								card.setAdjacentTopLeft(otherCard);
								otherCard.setAdjacentBottomRight(card);
								break;
							case TOP_RIGHT:
								card.setAdjacentTopRight(otherCard);
								otherCard.setAdjacentBottomLeft(card);
								break;
							case BOTTOM_RIGHT:
								card.setAdjacentBottomRight(otherCard);
								otherCard.setAdjacentTopLeft(card);
								break;
							case BOTTOM_LEFT:
								card.setAdjacentBottomLeft(otherCard);
								otherCard.setAdjacentTopRight(card);
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
     * Checks if two cards are adjacent (horizontally, vertically or diagonally).
     *
     * @param card1 The first card.
     * @param card2 The second card.
     * @return true if the cards are adjacent, false otherwise.
     */
	private boolean areCardsAdjacent(Card card1, Card card2) {
		int rowDiff = Math.abs(card1.getRow() - card2.getRow());
		int colDiff = Math.abs(card1.getColumn() - card2.getColumn());
		return (rowDiff <= 1 && colDiff <= 1); // Adiacenza orizzontale, verticale e diagonale
	}
	/**
	 * Count how many times a given symbol appears
     * how many times it appears in the corners and Centrals of the cards in the play area. 
	 * @param symbol
	 * @return The total number of times the symbol appears in playArea.
	 */
	public int countsSymbolInPlayArea(Object symbol) {
		int totalCount = 0;
        for (Card card : placedCards) { // Itera su tutte le carte nella PlayArea
        	for (Corner corner : card.addCorners()) {
                if (corner.getState() == CornerState.SYMBOL && corner.getSymbol() == symbol) {
                    totalCount++;
                }
        	}
        	if (card.hasCentralSymbol()) {
                for (Symbol CentralSymbol : card.addCentralSymbol()) {
                    if (CentralSymbol == symbol) {
                        totalCount++;
                    }
                }
        	}
                
        }
        return totalCount;
    }
	 /**
     * Updates the 'covered' status of corners that are overlapped by the new card.
     *
     * @param newCard The newly placed card.
     */
	private void updateCoveredCorners(Card newCard) {
		for (Corner corner : newCard.addCorners()) {
			if (corner.getState() != CornerState.NULL) {
				// Ottieni la posizione del corner rispetto alla carta
				CornerPosition cornerPos = corner.getPosition();

				// Calcola le coordinate assolute del corner sulla griglia
				int cornerRow = newCard.getRow() + cornerPos.getRowOffset();
				int cornerCol = newCard.getColumn() + cornerPos.getColOffset();

				// Trova la carta che copre il corner (se esiste)
				Card coveredCard = getCardAt(cornerRow, cornerCol);
				if (coveredCard != null && coveredCard != newCard) {
					// Trova il corner corrispondente sulla carta coperta e impostalo come coperto
					for (Corner c : coveredCard.addCorners()) {
						if (c.getPosition() == cornerPos.getOpposite()) { // Controlla la posizione opposta
							c.setCovered(true);
							break;
						}
					}
				}
			}
		}
	}

}
