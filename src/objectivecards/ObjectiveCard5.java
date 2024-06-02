package objectivecards;

import java.util.List;
import cards.SpecialSymbol;
import game.PlayArea;

public class ObjectiveCard5 extends ObjectiveCards {

	private static int points = 2;
	private static int number = 5;
	private static SpecialSymbol target = SpecialSymbol.QUILL;
	
	@Override
	public boolean checkTarget(PlayArea man) {
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
