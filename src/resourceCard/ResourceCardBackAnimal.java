package resourceCard;

import java.util.ArrayList;

import cards.Corner;
import cards.CornerPosition;
import cards.CornerState;
import cards.Symbol;

public class ResourceCardBackAnimal extends ResourceCard {

	private static int points = 0;
	private static boolean isFront= false;
	private static int number= 43;
	private static Symbol symbol = Symbol.PLANT_KINGDOM;
	private boolean hasCenterSymbol = true;
	//private ArrayList <Corner> corners = new ArrayList <Corner>;
	private Corner corner2 = new Corner (CornerPosition.TOP_RIGHT, CornerState.EMPTY, null);
	private Corner corner3 = new Corner (CornerPosition.BOTTOM_RIGHT, CornerState.EMPTY, null );
	private Corner corner4 = new Corner (CornerPosition.BOTTOM_LEFT, CornerState.EMPTY, null);
	private Corner corner1 = new Corner (CornerPosition.TOP_LEFT, CornerState.EMPTY, null);
	private boolean isPlaced = false;
	
	public ResourceCardBackAnimal() {}
	
	
	
	@Override
	public ArrayList<Corner> addCorners() {
		ArrayList <Corner> backCorners = new ArrayList<Corner>();
		backCorners.add(corner1);
		backCorners.add(corner2);
		backCorners.add(corner3);
		backCorners.add(corner4);
		return backCorners;
	}
	@Override
	public int getPoints() {
		return points;
	}
	@Override
	public boolean isPlaced() {
		return isPlaced;
	}
	@Override
	public void setPlaced(boolean isPlaced) {
		this.isPlaced   = isPlaced;
	}
	@Override
	public Symbol getSymbol() {
		return ResourceCardBackAnimal.symbol;
	}
	
	@Override
	public boolean isFront() {
		return ResourceCardBackAnimal.isFront;
	}
	@Override
	public int getResourceCardNumber() {
		return ResourceCardBackAnimal.number;
	}
	public boolean hasCenterSymbol() {
		return hasCenterSymbol;
	}
	@Override
	public Symbol getCentralSymbol() {
		return ResourceCardBackAnimal.symbol;
	}
	
	


}
