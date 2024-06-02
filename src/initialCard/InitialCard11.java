package initialCard;

import java.util.ArrayList;

import cards.Corner;
import cards.CornerPosition;
import cards.CornerState;
import cards.Symbol;

public class InitialCard11 extends InitialCard {


	private boolean isPlaced = false;
	private static boolean isFront = true;
	private static int points = 0;
	private static boolean hasCentralSymbol = true;
	private static int number = 11;
	private static Symbol symbol2 = Symbol.ANIMAL_KINGDOM;
	private static Symbol symbol1 = Symbol.PLANT_KINGDOM;
	private static Symbol symbol3 = Symbol.INSECT_KINGDOM;
	private Corner corner1 = new Corner (CornerPosition.TOP_LEFT, CornerState.EMPTY, null);
	private Corner corner2 = new Corner (CornerPosition.TOP_RIGHT,CornerState.EMPTY, null);
	private Corner corner3 = new Corner (CornerPosition.BOTTOM_RIGHT, CornerState.NULL, null);
	private Corner corner4 = new Corner (CornerPosition.BOTTOM_LEFT, CornerState.NULL, null);
	
	public InitialCard11() {}
	
	@Override
	public ArrayList<Corner> addCorners() {
		ArrayList <Corner> frontCorners = new ArrayList<Corner>();
		frontCorners.add(corner1);
		frontCorners.add(corner2);
		frontCorners.add(corner3);
		frontCorners.add(corner4);
		return frontCorners;
	}
	@Override
	public ArrayList <Symbol> addCentralSymbol (){
		ArrayList <Symbol> centralSymbol = new ArrayList<>();
		centralSymbol.add(symbol2);
		centralSymbol.add(symbol3);
		centralSymbol.add(symbol1);
		return centralSymbol;
	}
	
	@Override
	public int getPoints() {
		return InitialCard11.points;
	}
	@Override
	public boolean isPlaced() {
		return isPlaced;
	}
	@Override
	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}
	@Override
	public boolean hasCentralSymbol() {
		return InitialCard11.hasCentralSymbol;
	}
	
	
	@Override
	public boolean isFront() {
		return InitialCard11.isFront;
	}
	@Override
	public int getInitialCardNumber() {
		return InitialCard11.number;
	}



}
