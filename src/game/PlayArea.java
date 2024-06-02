package game;

import java.util.ArrayList;

import cards.Card;
import cards.SpecialSymbol;
import cards.Symbol;

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
    
//quello che ho in objectivescards
    public Card[][] getGrid() {
        return grid;
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