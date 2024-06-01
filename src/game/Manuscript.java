package game;

import java.util.ArrayList;

import cards.SpecialSymbol;
import cards.Symbol;

public class Manuscript {
	
    private ArrayList<Symbol> symbols;
    private ArrayList<SpecialSymbol> specialSymbols;

    public Manuscript(ArrayList<Symbol> symbols, ArrayList<SpecialSymbol> specialSymbols) {
        this.symbols = symbols;
        this.specialSymbols = specialSymbols;
    }

    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public ArrayList<SpecialSymbol> getSpecialSymbols() {
        return specialSymbols;
    }
}