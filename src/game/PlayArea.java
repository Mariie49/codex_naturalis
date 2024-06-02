package game;

import java.util.ArrayList;

import cards.Card;
import cards.CardType;
import cards.Corner;
import cards.CornerState;
import cards.SpecialSymbol;
import cards.Symbol;
import initialCard.InitialCard;

public class PlayArea {
	
	private ArrayList<Card> placedCards;
	private int minRow = Integer.MAX_VALUE;
	private int maxRow = Integer.MIN_VALUE;
	private int minCol = Integer.MAX_VALUE;
	private int maxCol = Integer.MAX_VALUE;
	
    private ArrayList<Symbol> symbols;
    private ArrayList<SpecialSymbol> specialSymbols;
    private Card[][] grid;
    private int rows;
    private int cols;
    
    /**
	 * Constructs an empty play area.
	 */
    public PlayArea() {
    	placedCards = new ArrayList<>();
    }
    
    /**
	 * Returns a copy of this PlayArea object.
	 *
	 * This method creates a new PlayArea instance and copies the references to the placed cards,
	 * as well as the minRow, maxRow, minCol, and maxCol attributes.
	 *
	 * @return A copy of this PlayArea object.
	 */
    public PlayArea getPlayArea() {
		PlayArea copy = new PlayArea();
	    copy.placedCards = new ArrayList<>(placedCards); // Copia la lista delle carte
	    copy.minRow = minRow;
	    copy.maxRow = maxRow;
	    copy.minCol = minCol;
	    copy.maxCol = maxCol;
	    return copy;
	}

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
	
	private void updateBoundaries(InitialCard initialCard) {
		minRow = Integer.MAX_VALUE;
		maxRow = Integer.MIN_VALUE;
		minCol = Integer.MAX_VALUE;
		maxCol = Integer.MIN_VALUE;
		for (Card card : placedCards) {
			updateBoundaries(card); // Richiama il metodo con un singolo parametro per ogni carta
		}
	}

    
    
    
    
//quello che ho in objectivescards
    public Card[][] getGrid() {
        return grid;
    }
	
    public void placeCard(int row, int col, Card card) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = card;
        } else {
            throw new IllegalArgumentException("Invalid position");
        }
    }

    public Card getCard(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return grid[row][col];
        } else {
            return null;
        }
    }
    
    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public ArrayList<SpecialSymbol> getSpecialSymbols() {
        return specialSymbols;
    }

}