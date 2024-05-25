package cards;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Resource card in the game, 
 * which has different types and properties.
 */
public abstract class Card {
	private CardType type; // Tipo di carta
	private boolean isFront; // True se la carta è giocata a faccia in su, false se a faccia in giù
	protected ArrayList<Corner> frontCorners;// Angoli della carta (lista di oggetti Corner)
	private ArrayList<Corner> backCorners;// 
	private int score; // Punteggio della carta
	private ArrayList<Symbol> centerSymbols; // Simboli al centro della carta (massimo 3)
	private boolean hasCenterSymbols; // Flag per indicare la presenza di simboli al centro
	private boolean isPlaced = false; // Inizialmente non posizionata
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
    protected ArrayList<Corner> corners;

	// Costruttore
	 /**
     * Constructs a Card with the specified properties.
     *
     * @param type the type of the card
     * @param isFront whether the card is front-side up
     * @param frontCorners the list of corners on the front side of the card
     * @param backCorners the list of corners on the back side of the card
     * @param score the score of the card
     * @param hasCenterSymbols whether the card has center symbols
     * @param centerSymbols the list of center symbols on the card
     */
	public Card(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners, int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols) {
		this.type = type;
		this.isFront = isFront;
		this.frontCorners = frontCorners;
	    this.backCorners = backCorners;
		this.score = score;
		this.centerSymbols = centerSymbols;
		this.hasCenterSymbols = hasCenterSymbols;
		this.corners = isFront ? frontCorners : backCorners;
	}



	// Metodo privato per creare un angolo 
	 /**
     * Creates a new Corner with the specified properties.
     *
     * @param state the state of the corner
     * @param symbol the symbol of the corner, if applicable
     * @param specialSymbol the special symbol of the corner, if applicable
     * @param isFront whether the corner is on the front side of the card
     * @return the created Corner
     */
	protected Corner createCorner(CornerState state, Symbol symbol, SpecialSymbol specialSymbol, boolean isFront) {
		return new Corner(state, symbol, specialSymbol, isFront, this); // Fronte per impostazione predefinita
	}

	// Metodo per aggiungere un angolo alla carta (modificato per utilizzare la nuova classe Corner)
	/**
     * Adds a corner to the frontCorners list with the specified properties.
     *
     * @param state the state of the corner
     * @param symbol the symbol of the corner, if applicable
     * @param specialSymbol the special symbol of the corner, if applicable
     */
	public void addCorner(CornerState state, Symbol symbol, SpecialSymbol specialSymbol) {
		frontCorners.add(createCorner(state, symbol, specialSymbol, true)); // Fronte per impostazione predefinita
	}

	/**
	 * Gets the type of the card.
	 *
	 * @return The type of the card.
	 */
	public CardType getType() {
		return type;
	}
	/**
	 * Sets the type of the card.
	 *
	 * @param type The new type of the card.
	 */
	public void setType(CardType type) {
		this.type = type;
	}
	/**
	 * Checks if the card is currently facing front-side up.
	 *
	 * @return true if the card is front-side up, false otherwise.
	 */
	public boolean isFront() {
		return isFront;
	}
	/**
	 * Gets the list of corners for the currently side of the card.
	 *
	 * @return The list of corners if the card is front-side up (frontCorners), 
	 *         or the list of back corners (backCorners) if the card is back-side up.
	 */
	public void setFront(boolean Front) {
		this.isFront = Front;
	}
	/**
	 * Gets the list of corners for the currently active side of the card.
	 *
	 * @return The list of corners if the card is front-side up (`frontCorners`), 
	 *         or the list of back corners (`backCorners`) if the card is back-side up.
	 */
	public ArrayList<Corner> getCorners() {
		return isFront ? frontCorners : backCorners;
	} 

	/*
	 * Il metodo getCorner itera sulla lista di corner della carta (corners).
Per ogni corner, controlla se la sua posizione (corner.getPosition()) corrisponde alla posizione desiderata (position).
Se trova un corner corrispondente, lo restituisce.
Se nessun corner corrispondente viene trovato, restituisce null (questo non dovrebbe accadere se la carta è stata creata correttamente).
	 */
	/**
	 * Gets the corner at the specified position on the card.
	 *
	 * @param position The position of the corner (TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT).
	 * @return The Corner at the specified position, or null if not found.
	 */
	public Corner getCorner (CornerPosition position) {
	    for (Corner corner : corners) {
	        if (corner.getPosition() == position) {
	            return corner;
	        }
	    }
	    return null; // Corner non trovato 
	}
	/**
	 * Sets the corners of the card's front side.
	 *
	 * @param corners The list of Corner objects representing the front corners of the card.
	 */
	public void setCorners(ArrayList<Corner> corners) {
		this.frontCorners = corners;
	}
	/**
	 * Gets the list of corners on the front side of the card.
	 *
	 * @return The list of Corner objects representing the front corners.
	 */
	public ArrayList<Corner> getFrontCorners() {
	    return frontCorners;
	}
	/**
	 * Gets the list of corners on the back side of the card.
	 *
	 * @return The list of Corner objects representing the back corners.
	 */
	public ArrayList<Corner> getBackCorners() {
	    return backCorners;
	}
	/**
	 * Gets the score associated with the card.
	 *
	 * @return The score of the card.
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Sets the score associated with the card.
	 *
	 * @param score The new score for the card.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Gets the list of center symbols on the card.
	 *
	 * @return The list of Symbol objects representing the center symbols.
	 */
	public ArrayList<Symbol> getCenterSymbols() {
		return centerSymbols;
	}
	/**
	 * Sets the list of center symbols on the card.
	 *
	 * @param centerSymbols The new list of Symbol objects for the center symbols.
	 */
	public void setCenterSymbols(ArrayList<Symbol> centerSymbols) {
		this.centerSymbols = centerSymbols;
	}
	/**
	 * Checks if the card has center symbols.
	 *
	 * @return true if the card has center symbols, false otherwise.
	 */
	public boolean hasCenterSymbols() {
		return hasCenterSymbols;
	}
	/**
	 * Sets whether the card has center symbols.
	 *
	 * @param hasCenterSymbols true if the card has center symbols, false otherwise.
	 */
	public void setHasCenterSymbols(boolean hasCenterSymbols) {
		this.hasCenterSymbols = hasCenterSymbols;
	}
	/**
	 * Checks if the card is placed on the play area.
	 *
	 * @return true if the card is placed, false otherwise.
	 */
	public boolean isPlaced() {
	    return isPlaced;
	}
	
	/**
	 * Sets whether the card is placed on the play area.
	 *
	 * @param placed true if the card is placed, false otherwise.
	 */
	 public void setPlaced(boolean placed) {
	        isPlaced = placed;
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



	
		/**
		 * Flips the card.
		 *
		 * This method toggles the isFront property of the card, changing its orientation 
		 * from front-side up to back-side up, or vice versa.
		 */
	public void flip() {
        isFront = !isFront;
    }
	/**
	 * This method displays a menu asking the user to select the front (1) or back (2) side.
	 * It reads the user's input and sets the isFront property of the card accordingly.
	 * If the user enters an invalid choice, it defaults to the front side.
	 *
	 * @return true if the user chose the front side, false if the user chose the back side.
	 */
	public boolean chooseSide() {
	    System.out.println("Do you want to choose the front or back side of the card?");
	    System.out.println("1. Front");
	    System.out.println("2. Back");

	    Scanner scanner = new Scanner(System.in);
	    int choice = scanner.nextInt();

	    switch (choice) {
	        case 1:
	            setFront(true);
	            break;
	        case 2:
	            setFront(false);
	            break;
	        default:
	            System.out.println("Invalid choice. Defaulting to front side.");
	            setFront(true);
	            break;
	    }

	    System.out.println("You have chosen the " + (isFront() ? "front" : "back") + " side of the card.");
	    scanner.close();
	    return isFront();
	}
	

	// Altri metodi utili (opzionali)
	/*
	 * public void printCard() {

		// Stampa le informazioni della carta (tipo, angoli, punteggio, simboli centrali, flag hasCenterSymbols)
		System.out.println("Tipo: " + type);
		System.out.println("Fronte in su: " + isFront);
		System.out.println("Angoli:");
		for (Corner corner : frontCorners) {
			corner.printCorner(); // Stampa le informazioni di ogni angolo utilizzando il metodo printCorner() della classe Corner
		}
		System.out.println("Punteggio: " + score);
		System.out.println("Simboli centrali: " + centerSymbols);
		System.out.println("Simboli centrali presenti: " + hasCenterSymbols);
		
	 */// Metodo di supporto per ottenere la rappresentazione testuale di un corner
	/**
	 * Gets the textual representation of the card corner at the specified position.
	 *
	 * @param position The position of the corner to get (TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT).
	 * @return The textual representation of the corner. It can be:
	 *     - The name of the Symbol enum if the corner state is SYMBOL.
	 *     - The name of the SpecialSymbol enum if the corner state is SPECIALSYMBOL.
	 *     - "EMPTY" if the corner state is EMPTY.
	 *     - "NULL" if the corner state is NULL.
	 *     - "?" if the corner state is invalid.
	 *     - " " (a blank space) if the corner is not found (should not happen under normal conditions).
	 */
	private String getCornerRepresentation(CornerPosition position) {
	    for (Corner corner : getCorners()) {
	        if (corner.getPosition() == position) {
	            switch (corner.getState()) {
	                case NULL:
	                    return "NULL";
	                case EMPTY:
	                    return "EMPTY";
	                case SYMBOL:
	                    return corner.getSymbol().name();
	                case SPECIALSYMBOL:
	                    return corner.getSpecialSymbol().name();
	                default:
	                    return "?";
	            }
	        }
	    }
	    return " "; // Angolo non trovato (non dovrebbe accadere)
	}
	
	/**
	 * Prints the corners and center symbols of the card.
	 *
	 * This method calculates the maximum width needed to display the card's
	 * symbols correctly, then prints the top corners, center symbols (if present),
	 * and bottom corners in their respective positions. If the card does not have
	 * center symbols, a blank line is printed to maintain consistent card height.
	 */
		public void printCornersAndCenterSymbols() {
		    // Calcola la larghezza massima della carta
		    int dimMaxCard = 9; // Spazio minimo per i corner
		    if (hasCenterSymbols()) {
		        for (Symbol symbol : getCenterSymbols()) {
		            dimMaxCard = Math.max(dimMaxCard, symbol.name().length() + 1); // +1 per lo spazio
		        }
		    }

		    // Stampa i corner superiori
		    System.out.printf("%-" + (dimMaxCard / 2) + "s%-" + (dimMaxCard / 2) + "s\n",
		            getCornerRepresentation(CornerPosition.TOP_LEFT),
		            getCornerRepresentation(CornerPosition.TOP_RIGHT));

		    // Stampa i simboli centrali (se presenti) o una riga vuota
		    if (hasCenterSymbols()) {
		        String centerSymbols = "";
		        for (Symbol symbol : getCenterSymbols()) {
		            centerSymbols += symbol.name() + " ";
		        }
		        System.out.printf("%-" + dimMaxCard + "s\n", centerSymbols.trim());
		    } else {
		        System.out.println(" ".repeat(dimMaxCard)); // Riga vuota
		    }

		    // Stampa i corner inferiori
		    System.out.printf("%-" + (dimMaxCard / 2) + "s%-" + (dimMaxCard / 2) + "s\n",
		            getCornerRepresentation(CornerPosition.BOTTOM_LEFT),
		            getCornerRepresentation(CornerPosition.BOTTOM_RIGHT));
		
		}
		/**
		 * Prints the details of the card.
		 * 
		 * This method displays the card's type, the side (front or back),
		 * the corner symbols and center symbols (if present) of that side, and the card's score.
		 * If the card has no center symbols, a blank line is printed to maintain consistent card height.
		 */
		public void printCard() {
		    System.out.println("Type: " + getType());

		    if (isFront()) {
		        System.out.println("Front side:");
		            printCornersAndCenterSymbols();
		        
		    } else {
		        System.out.println("Back side:");
		            printCornersAndCenterSymbols();
		        }
		    
		    System.out.println("Score: " + getScore());
		    
		    System.out.println("Has center symbols: " + hasCenterSymbols());

		    /*if (hasCenterSymbols()) {
		        System.out.println("Center symbols: " + getCenterSymbols());
		    } else {
		        System.out.println("No center symbols");
		    }
		    */
		}
}

