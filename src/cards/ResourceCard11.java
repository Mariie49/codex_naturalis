package cards;
import java.util.ArrayList;

import cards.Corner;
import cards.CornerState;
import cards.Symbol;
import cards.SpecialSymbol;


public class ResourceCard11 extends ResourceCard {
	private static CardType type = CardType.RESOURCE;
	private static int points = 0;
	private static boolean isFront= true;
	private static int number= 1;
	private static Symbol symbol = Symbol.FUNGI_KINGDOM;
	//private ArrayList <Corner> corners = new ArrayList <Corner>;
	private Corner corner1 = new Corner (CornerPosition.TOP_LEFT, CornerState.SYMBOL, symbol);
	private Corner corner2 = new Corner (CornerPosition.TOP_RIGHT, CornerState.SYMBOL, symbol);
	private Corner corner3 = new Corner (CornerPosition.BOTTOM_RIGHT, CornerState.EMPTY, null);
	private Corner corner4 = new Corner (CornerPosition.BOTTOM_LEFT, CornerState.NULL, null);
	
	public ResourceCard11() {}
	
	
	
	@Override
	public ArrayList<Corner> addCorners() {
		ArrayList <Corner> frontCorners = new ArrayList<Corner>();
		frontCorners.add(corner1);
		frontCorners.add(corner2);
		frontCorners.add(corner3);
		frontCorners.add(corner4);
		return frontCorners;
	}
	
	public int getPoint () {
		return this.points;
	}



	@Override
	public int getResourceCardNumber() {
		return this.number;
	}
	
	
	
	
	
}
