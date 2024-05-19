package cards;

import java.util.ArrayList;

/**
 * Represents a corner of a card, which can be in various states
 * and may contain different types of symbols.
 */

public class Corner {

	private CornerState state; // Stato dell'angolo (nullo, nascosto, simbolo, simbolo speciale)
	private Symbol symbol; // Simbolo dell'angolo (può essere null se lo stato è NULL,HIDDEN o SPECIALSYMBOL)
	private SpecialSymbol specialSymbol; // Simbolo speciale (se lo stato è SPECIAL_SYMBOL)
	private boolean isFront; // True se l'angolo appartiene al fronte della carta, false se al retro

	 /**
     * Default constructor for Corner.
     */
	public Corner () {}
	/**
     * Constructs a Corner with the specified state, symbol, special symbol, and orientation.
     *
     * @param state the state of the corner
     * @param symbol the symbol of the corner, if applicable
     * @param specialSymbol the special symbol of the corner, if applicable
     * @param isFront whether the corner is on the front side of the card
     */
	public Corner(CornerState state, Symbol symbol,SpecialSymbol specialSymbol, boolean isFront) {
		this.state = state;
		this.symbol = symbol;
		this.specialSymbol = specialSymbol;
		this.isFront = isFront;
	}

	/**
     * Gets the state of the corner.
     *
     * @return the state of the corner
     */
	public CornerState getState() {
		return state;
	}
	/**
     * Sets the state of the corner.
     *
     * @param state the new state of the corner
     */
	public void setState(CornerState state) {
		this.state = state;
	}
	/**
     * Gets the symbol of the corner if the state is SYMBOL.
     *
     * @return the symbol of the corner, or null if the state is not SYMBOL
     */
	public Symbol getSymbol() {
		    if (state == CornerState.SYMBOL) {
		    	return symbol;
		    } else {
		      return null;
		    }
		  }
	
	/**
     * Sets the symbol of the corner if the state is SYMBOL.
     *
     * @param symbol the symbol to set
     * @throws IllegalArgumentException if the state is not SYMBOL
     */

	public void setSymbol(Symbol symbol) {
		if (state != CornerState.SYMBOL) {
		      throw new IllegalArgumentException("Impossibile impostare un simbolo normale per uno stato diverso da SYMBOL");
		    }
		this.symbol = symbol;
	}
	
	/**
     * Gets the special symbol of the corner if the state is SPECIALSYMBOL.
     *
     * @return the special symbol of the corner, or null if the state is not SPECIALSYMBOL
     */
	public SpecialSymbol getSpecialSymbol() {
	    if (state == CornerState.SPECIALSYMBOL) {
	      return specialSymbol;
	    } else {
	      return null;
	    }
	  }
	
	/**
     * Sets the special symbol of the corner if the state is SPECIALSYMBOL.
     *
     * @param specialSymbol the special symbol to set
     * @throws IllegalArgumentException if the state is not SPECIALSYMBOL
     */
	
	public void setSpecialSymbol(SpecialSymbol specialSymbol) {
	    if (state != CornerState.SPECIALSYMBOL) {
	      throw new IllegalArgumentException("Impossibile impostare un simbolo speciale per uno stato diverso da SPECIAL_SYMBOL");
	    }
	    this.specialSymbol = specialSymbol;
	  }
	
	/**
     * Checks if the corner is on the front side of the card.
     *
     * @return true if the corner is on the front side, false otherwise
     */

	public boolean isFront() {
		return isFront;
	}
	 /**
     * Sets whether the corner is on the front side of the card.
     *
     * @param isFront true if the corner is on the front side, false otherwise
     */
	public void setFront(boolean isFront) {
		this.isFront = isFront;
	}
	
	// Metodo per stampare le informazioni dell'angolo (utile per il debug)
	/**
     * Prints the details of the corner.
     */
	  public void printCorner() {
	    System.out.println("Stato: " + state);
	    System.out.println("Simbolo: " + symbol);
	    System.out.println("Simbolo speciale: " + specialSymbol);
	    System.out.println("Fronte: " + isFront);
	  }
	  
	  /**
	     * Checks the positions of the variables in the input ArrayList and creates a new ArrayList
	     * where the position of each corner increases by one. The last corner in the input ArrayList
	     * will be moved to the first position in the new ArrayList.
	     *
	     * @param inputList: the input ArrayList of Corner variables.
	     * @return Returns a new ArrayList with positions shifted as described.
	     */
	    public ArrayList<Corner> shiftCornerPositions(ArrayList<Corner> inputList) {
	        ArrayList<Corner> shiftedList = new ArrayList<>();
	        int size = inputList.size();

	        // Move each corner to the next position
	        for (int i = 0; i < size - 1; i++) {
	            shiftedList.add(inputList.get(i + 1));
	        }

	        // Move the last corner to the first position
	        shiftedList.add(inputList.get(0));

	        return shiftedList;
	    }
	// methods for checking the corner status
	/*
	 * isNull(): Returns true if the corner state is CornerState.NULL, false otherwise.
	 * isHidden(): Returns true if the corner's state is CornerState.HIDDEN, false otherwise.
	 * isSymbol(): Returns true if the corner's state is CornerState.SYMBOL, false otherwise.
	 * isSpecialSymbol(): Returns true if the corner state is CornerState.SPECIALSYMBOL, false otherwise.
	 */
	    /**
	     * Checks if the corner state is NULL.
	     *
	     * @return true if the state is NULL, false otherwise
	     */
	public boolean isNull() {
		return state == CornerState.NULL;
	}
	/**
     * Checks if the corner state is EMPTY.
     *
     * @return true if the state is EMPTY, false otherwise
     */

	public boolean isEmpty() {
		return state == CornerState.EMPTY;
	}
	/**
     * Checks if the corner state is SYMBOL.
     *
     * @return true if the state is SYMBOL, false otherwise
     */

	public boolean isSymbol() {
		return state == CornerState.SYMBOL;
	}
	 /**
     * Checks if the corner state is SPECIALSYMBOL.
     *
     * @return true if the state is SPECIALSYMBOL, false otherwise
     */

	public boolean isSpecialSymbol() {
		return state == CornerState.SPECIALSYMBOL;
	}
}
