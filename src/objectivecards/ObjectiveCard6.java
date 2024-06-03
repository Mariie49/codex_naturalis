package objectivecards;

import java.util.List;

import cards.SpecialSymbol;
import game.Manuscript;

public class ObjectiveCard6 extends ObjectiveCard {

	private static int points = 2;
	private static int number = 6;
	private static SpecialSymbol target = SpecialSymbol.INKWELL;
	
	@Override
	public boolean checkTarget(Manuscript man) {
		 List<SpecialSymbol> symbols = man.getSpecialSymbols();
	        int count = 0;

	        for (SpecialSymbol symbol : symbols) {
	            if (symbol == target) {
	                count++;
	            }
	        }

	        if (count == 2) {
	            setPoints(points);
	            return true;
	        }
	        return false;
	    }

	    /**
	     * @return the number
	     */
	    public static int getNumber() {
	        return number;
	    }

}
