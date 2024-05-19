package card;

import java.util.Random;

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
