package objectivecards;

import cards.Card;
import cards.Symbol;
import game.PlayArea;

public class ObjectiveCard9 extends ObjectiveCards {

	private static int points = 2;
	private static int number = 9;
	private static Symbol target = Symbol.FUNGI_KINGDOM;
	private Card[][] pattern;
	
	@Override
	protected boolean checkTarget(PlayArea man) {
		Card[][] grid = man.getGrid();
        for (int i = 0; i <= grid.length - pattern.length; i++) {
            for (int j = 0; j <= grid[0].length - pattern[0].length; j++) {
                if (matchesPattern(grid, i, j)) {
                    setPoints(points);
                    return true;
                }
            }
        }
		return false;
	}
	 private boolean matchesPattern(Card[][] grid, int row, int col) {
	        for (int i = 0; i < pattern.length; i++) {
	            for (int j = 0; j < pattern[i].length; j++) {
	            	Symbol patternSymbol = target;	            	
	            	 Card gridCard = grid[row + i][col + j];

	                 if (patternSymbol != null && gridCard != null) {
	                     if (!gridCard.getKingdom().equals(patternSymbol)) {
	                         return false;
	                     }
	                     // Verifica se le tre carte sono disposte diagonalmente e collegate tramite un corner
	                     // Carta 1: [row][col]
	                     // Carta 2: [row + 1][col + 1]
	                     // Carta 3: [row + 2][col + 2]
	                     if (grid[row][col] == null || grid[row + 1][col + 1] == null || grid[row + 2][col + 2] == null) {
	                         return false;
	                     }
	                     // Verifica sei simboli dei regni corrispondono
	                     if (!grid[row][col].getKingdom().equals(pattern[0][0]) ||
	                         !grid[row + 1][col + 1].getKingdom().equals(pattern[1][1]) ||
	                         !grid[row + 2][col + 2].getKingdom().equals(pattern[2][2])) {
	                         return false;
	                     }
	                     
	                 } else if (patternSymbol != null || gridCard != null) {
	                     return false;
	                 }	                 
	            }
	        }
	        return true;
	 }
	/**
	 * @return the number
	 */
	public static int getNumber() {
		return number;
	}
	 
}