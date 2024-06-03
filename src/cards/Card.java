package cards;
import java.util.ArrayList;
public abstract class Card {
	private CardType type;
	private ArrayList <Object> symbols;
	private Symbol kingdom;
	private Corner corner;
	private boolean isPlaced = false;
	private boolean hasCentralSymbol;
	private int number;
	private int row;
    private int column;
	private Card adjacentTop; // Carta adiacente sopra
    private Card adjacentBottom; // Carta adiacente sotto
    private Card adjacentLeft; // Carta adiacente a sinistra
    private Card adjacentRight; // Carta adiacente a destra
    private Card adjacentTopLeft; // Carta adiacente in alto a sinistra
    private Card adjacentTopRight; // Carta adiacente in alto a destra
    private Card adjacentBottomRight; // Carta adiacente in basso a destra
    private Card adjacentBottomLeft; // Carta adiacente in basso a sinistra
	
	public Corner getCorner() {
		return corner;
	}

	public boolean isPlaced() {
		return isPlaced;
	}
	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}
	
	public void setCorner(Corner corner) {
		this.corner = corner;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}

	
	/**
	  * Gets the card that is directly adjacent to the top of this card.
	  *
	  * @return The adjacent card above this card, or null if there is none.
	  */
	 public Card getAdjacentTop() {
	     return adjacentTop;
	 }

	 /**
	  * Gets the card that is directly adjacent to the bottom of this card.
	  *
	  * @return The adjacent card below this card, or null if there is none.
	  */
	 public Card getAdjacentBottom() {
	     return adjacentBottom;
	 }

	 /**
	  * Gets the card that is directly adjacent to the left of this card.
	  *
	  * @return The adjacent card to the left of this card, or null if there is none.
	  */
	 public Card getAdjacentLeft() {
	     return adjacentLeft;
	 }

	 /**
	  * Gets the card that is directly adjacent to the right of this card.
	  *
	  * @return The adjacent card to the right of this card, or null if there is none.
	  */
	 public Card getAdjacentRight() {
	     return adjacentRight;
	 }


	    
	 /**
	  * Sets the card that is directly adjacent to the top of this card.
	  *
	  * @param card The card to set as the adjacent card above.
	  */
	 public void setAdjacentTop(Card card) {
	     this.adjacentTop = card;
	 }

	 /**
	  * Sets the card that is directly adjacent to the bottom of this card.
	  *
	  * @param card The card to set as the adjacent card below.
	  */
	 public void setAdjacentBottom(Card card) {
	     this.adjacentBottom = card;
	 }

	 /**
	  * Sets the card that is directly adjacent to the left of this card.
	  *
	  * @param card The card to set as the adjacent card to the left.
	  */
	 public void setAdjacentLeft(Card card) {
	     this.adjacentLeft = card;
	 }

	 /**
	  * Sets the card that is directly adjacent to the right of this card.
	  *
	  * @param card The card to set as the adjacent card to the right.
	  */
	 public void setAdjacentRight(Card card) {
	     this.adjacentRight = card;
	 }

	    /**
	     * Sets the card adjacent to the top-left corner of this card.
	     *
	     * @param card The adjacent card to set.
	     */
	    public void setAdjacentTopLeft(Card card) {
	        this.adjacentTopLeft = card;
	        if (card != null) {
	            card.adjacentBottomRight = this; // Aggiornamento reciproco dell'adiacenza
	        }
	    }
	    /**
	     * Sets the card adjacent to the top-right corner of this card.
	     *
	     * @param card The adjacent card to set.
	     */
	    public void setAdjacentTopRight(Card card) {
	        this.adjacentTopRight = card;
	        if (card != null) {
	            card.adjacentBottomLeft = this;
	        }
	    }
	    /**
	     * Sets the card adjacent to the bottom-right corner of this card.
	     *
	     * @param card The adjacent card to set.
	     */
	    public void setAdjacentBottomRight(Card card) {
	        this.adjacentBottomRight = card;
	        if (card != null) {
	            card.adjacentTopLeft = this;
	        }
	    }
	    /**
	     * Sets the card adjacent to the bottom-left corner of this card.
	     *
	     * @param card The adjacent card to set.
	     */
	    public void setAdjacentBottomLeft(Card card) {
	        this.adjacentBottomLeft = card;
	        if (card != null) {
	            card.adjacentTopRight = this;
	        }
	    }

	    /**
	    * Gets the row of the card on the play area.
	    *
	    * @return The row of the card.
	    */
	    public int getRow() {
			return row;
		}
	    /**
	     * Sets the row of the card on the play area.
	     *
	     * @param row The new row of the card.
	     */
		public void setRow(int row) {
			this.row = row;
		}
		/**
		 * Gets the column of the card on the play area.
		 *
		 * @return The column of the card.
		 */
		public int getColumn() {
			return column;
		}
		/**
		 * Sets the column of the card on the play area.
		 *
		 * @param column The new column of the card.
		 */
		public void setColumn(int column) {
			this.column = column;
		}
		
		public Symbol getCentralSymbol() {
			return kingdom;
		}
	
	public ArrayList <Corner> addCorners (){
		ArrayList <Corner> corners = new ArrayList<>();
		return corners;
	}
	
	public boolean hasCentralSymbol() {
		return hasCentralSymbol;
	}
	
	public ArrayList <Symbol> addCentralSymbol (){
		ArrayList <Symbol> symbols = new ArrayList<>();
		return symbols;
	}
	public Symbol getKingdom() {
		return this.kingdom;
	}
	
	public ArrayList<Object> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<Object> symbols) {
        this.symbols = symbols;
    }

    public Card ChooseSide (Card d) { 
    	return d;
    }

    public void printCard() {}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
}
