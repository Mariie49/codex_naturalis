package game;

import java.util.ArrayList;

import cards.Card;
import cards.SpecialSymbol;
import cards.Symbol;

public class Manuscript {
	
    private ArrayList<Symbol> symbols;
    private ArrayList<SpecialSymbol> specialSymbols;
    private Card[][] grid;
    private int rows;
    private int cols;

    public Manuscript() {}

    public Card[][] getGrid() {
        return grid;
    }

    public void placeCard(int row, int col, Card card) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = card;
        } else {
            throw new IllegalArgumentException("Invalid position");
        }
    }

    public Card getCard(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return grid[row][col];
        } else {
            return null;
        }
    }
    
    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public ArrayList<SpecialSymbol> getSpecialSymbols() {
        return specialSymbols;
    }

}