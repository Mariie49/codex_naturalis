package objectivecards;

import java.util.List;

import cards.SpecialSymbol;
import game.Manuscript;

public class ObjectiveCard8 extends ObjectiveCard {

	private static int points = 3;
	private static int number = 8;
	private static SpecialSymbol target1 = SpecialSymbol.QUILL;
	private static SpecialSymbol target2 = SpecialSymbol.INKWELL;
	private static SpecialSymbol target3 = SpecialSymbol.INKWELL;
	
	@Override
	public boolean checkTarget(Manuscript man) {
		 List<SpecialSymbol> symbols = man.getSpecialSymbols();
	        int count = 0;

	        for (SpecialSymbol symbol : symbols) {
	            if (symbol == target1 && symbol == target2 && symbol == target3) {
	                count++;
	            }
	        }

	        if (count == 1) {
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
