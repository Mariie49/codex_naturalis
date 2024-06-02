package cards;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
	
	private SpecialSymbol specialSymbol;
	private Symbol kingdom;
    private List<Symbol> symbols;
    private Corner corner;

    public List<Symbol> getSymbols() {
        return symbols;
    }
    
    public ArrayList<Corner> addCorners(){
    	ArrayList<Corner> corners = new ArrayList<>();
		return corners;
		}
    
    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public Corner getCorner() {
        return corner;
    }

    public void setCorner(Corner corner) {
        this.corner = corner;
    }

	public SpecialSymbol getSpecialSymbol() {
		return specialSymbol;
	}
	
	public Symbol getKingdom() {
		return kingdom;
	}
	public void printCard() {}

}
