package card;

import java.util.ArrayList;

public class GoldCard extends Card{
	
    private ArrayList<Symbol> requiredSymbols;
    
    public GoldCard(ArrayList<Corner> corners, boolean hasPoint) {
		super(corners, hasPoint);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Corner> getCorners() {
        return corners;
    }

    public ArrayList<Symbol> getRequiredSymbols() {
        return requiredSymbols;
    }

    // Metodo per verificare se il giocatore ha accumulato i simboli necessari per utilizzare la carta oro
    public boolean canBeUsed(ArrayList<Symbol> playerSymbols) {
        return playerSymbols.containsAll(requiredSymbols);
    }
}
