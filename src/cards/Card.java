package cards;

import java.util.ArrayList;

public abstract class Card {
	
		private CardType type;
		private ArrayList <Object> symbols;
		private Symbol kingdom;
		private Corner corner;
		private int number;
		
		public ArrayList <Corner> addCorners (){
			ArrayList <Corner> corners = new ArrayList<>();
			return corners;
		}
		
		public ArrayList <Symbol> addCentralSymbol (){
			ArrayList <Symbol> symbols = new ArrayList<>();
			return symbols;
		}

		/**
		 * @return the type
		 */
		public CardType getType() {
			return type;
		}

		/**
		 * @return the symbols
		 */
		public ArrayList<Object> getSymbols() {
			return symbols;
		}

		/**
		 * @return the kingdom
		 */
		public Symbol getKingdom() {
			return kingdom;
		}

		/**
		 * @return the corner
		 */
		public Corner getCorner() {
			return corner;
		}

		/**
		 * @return the number
		 */
		public int getNumber() {
			return number;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(CardType type) {
			this.type = type;
		}

		/**
		 * @param symbols the symbols to set
		 */
		public void setSymbols(ArrayList<Object> symbols) {
			this.symbols = symbols;
		}

		/**
		 * @param corner the corner to set
		 */
		public void setCorner(Corner corner) {
			this.corner = corner;
		}

		/**
		 * @param number the number to set
		 */
		public void setNumber(int number) {
			this.number = number;
		}

		/**
		 * Prints the details of the card.
		 * 
		 * This method displays the card's type, the side (front or back),
		 * the corner symbols and center symbols (if present) of that side, and the card's score.
		 * If the card has no center symbols, a blank line is printed to maintain consistent card height.
		 */
		public void printCard() {
			// TODO Auto-generated method stub
		}

}
