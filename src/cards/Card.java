package cards;
import java.util.ArrayList;
public abstract class Card {
	private CardType type;
	private ArrayList <Object> symbols;
	private Symbol kingdom;
	private Corner corner; 
	
	public Corner getCorner() {
		return corner;
	}

	
	public void setCorner(Corner corner) {
		this.corner = corner;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}

	private int number;
	
	public ArrayList <Corner> addCorners (){
		ArrayList <Corner> corners = new ArrayList<>();
		return corners;
	}
	
	public ArrayList <Symbol> addCentralSymbol (){
		ArrayList <Symbol> symbols = new ArrayList<>();
		return symbols;
	}
	public Symbol getKingdom() {
		return this.kingdom;
	}
	
	public ArrayList<Object> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<Object> symbols) {
        this.symbols = symbols;
    }

    public Card ChooseSide (Card d) { 
    	return d;
    }

    public void printCard() {}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
}
