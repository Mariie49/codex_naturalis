package cards;

import java.util.ArrayList;

import cards.Corner;
import cards.CornerState;
import cards.Symbol;



public class ResourceCard14 extends ResourceCard {
	private static CardType type = CardType.RESOURCE;
	private static int points = 0;
	private static boolean isFront= true;
	private static int number= 14;
	private static Symbol symbol = Symbol.FUNGI_KINGDOM;
	//private ArrayList <Corner> corners = new ArrayList <Corner>;
	private Corner corner2 = new Corner (CornerPosition.TOP_RIGHT, CornerState.EMPTY, null);
	private Corner corner3 = new Corner (CornerPosition.BOTTOM_RIGHT, CornerState.NULL, null );
	private Corner corner4 = new Corner (CornerPosition.BOTTOM_LEFT, CornerState.SYMBOL, symbol);
	private Corner corner1 = new Corner (CornerPosition.TOP_LEFT, CornerState.SYMBOL, symbol);
	
	public ResourceCard14() {}
	
	
	
	@Override
	public ArrayList<Corner> addCorners() {
		ArrayList <Corner> frontCorners = new ArrayList<Corner>();
		frontCorners.add(corner1);
		frontCorners.add(corner2);
		frontCorners.add(corner3);
		frontCorners.add(corner4);
		return frontCorners;
	}
	public static CardType getType() {
		return type;
	}



	public static void setType(CardType type) {
		ResourceCard14.type = type;
	}



	public static int getPoints() {
		return points;
	}



	public static void setPoints(int points) {
		ResourceCard14.points = points;
	}



	public static boolean isFront() {
		return isFront;
	}



	public static void setFront(boolean isFront) {
		ResourceCard14.isFront = isFront;
	}



	public static int getNumber() {
		return number;
	}



	public static void setNumber(int number) {
		ResourceCard14.number = number;
	}



	public static Symbol getSymbol() {
		return symbol;
	}



	public static void setSymbol(Symbol symbol) {
		ResourceCard14.symbol = symbol;
	}



	
	
	



	@Override
	public int getResourceCardNumber() {
		return ResourceCard14.number;
	}
	
	
	
	
	
}

