package cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Resource card in the game, 
 * which has different types and properties.
 */
public abstract class Card {
	/**
     * Enum representing the type of the card.
     */
	// Enumerazione per i tipi di carta
	public enum CardType {
		RESOURCE, GOLD, OBJECTIVE, STARTING
	}
	/**
     * Enum representing the different symbols that a card can have.
     */
	// Enumerazione per i simboli normali (animale, fungo, insetto, vegetale)
	public enum Symbol {
		PLANT_KINGDOM,
		ANIMAL_KINGDOM,
		FUNGI_KINGDOM,
		INSECT_KINGDOM;
		
		 /**
         * Returns a random symbol from the enum.
         *
         * @return a random symbol
         */
		public static Symbol getRandomSymbol() {
	        Symbol[] Symbols = Symbol.values();
	        Random random = new Random();
	        int i = random.nextInt(Symbols.length);
	        return Symbols[i];
	    }
		
	}
	/**
     * Enum representing the special symbols that a card can have.
     */
	// Enumerazione per i simboli speciali
	public enum SpecialSymbol {
		INKWELL, MANUSCRIPT, QUILL;
		/**
         * Returns a random special symbol from the enum.
         *
         * @return a random special symbol
         */
		public static SpecialSymbol getRandomSpecialSymbol() {
	        SpecialSymbol[] specialSymbols = SpecialSymbol.values();
	        Random random = new Random();
	        int index = random.nextInt(specialSymbols.length);
	        return specialSymbols[index];
	    }
	}
	// Enumerazione per gli stati degli angoli
	/**
     * Enum representing the state of a corner of the card.
     */
	public enum CornerState {
		NULL,
		EMPTY,
		SYMBOL,
		SPECIALSYMBOL
	}
	


	private CardType type; // Tipo di carta
	private boolean isFront; // True se la carta è giocata a faccia in su, false se a faccia in giù
	protected ArrayList<Corner> frontCorners;// Angoli della carta (lista di oggetti Corner)
	private ArrayList<Corner> backCorners;// 
	private int score; // Punteggio della carta
	private ArrayList<Symbol> centerSymbols; // Simboli al centro della carta (massimo 3)
	private boolean hasCenterSymbols; // Flag per indicare la presenza di simboli al centro

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
		return new Corner(state, symbol, specialSymbol, isFront); // Fronte per impostazione predefinita
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

	// Getters e setters per type, isFront, corners, score e centerSymbols
	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public boolean isFront() {
		return isFront;
	}

	public void setFront(boolean Front) {
		this.isFront = Front;
	}

	public ArrayList<Corner> getCorners() {
		return isFront ? frontCorners : backCorners;
	}

	public void setCorners(ArrayList<Corner> corners) {
		this.frontCorners = corners;
	}
	
	public ArrayList<Corner> getFrontCorners() {
	    return frontCorners;
	}

	public ArrayList<Corner> getBackCorners() {
	    return backCorners;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Symbol> getCenterSymbols() {
		return centerSymbols;
	}

	public void setCenterSymbols(ArrayList<Symbol> centerSymbols) {
		this.centerSymbols = centerSymbols;
	}

	public boolean hasCenterSymbols() {
		return hasCenterSymbols;
	}
	public void setHasCenterSymbols(boolean hasCenterSymbols) {
		this.hasCenterSymbols = hasCenterSymbols;
	}

	// Metodo astratto da implementare nelle sottoclassi
	/**
     * Abstract method that defines how the card is played.
     * This should be implemented by subclasses.
     */
	public abstract void play();
	
	public void flip() {
        isFront = !isFront;
    }

	// Altri metodi utili (opzionali)
	public void printCard() {

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

	}
}

