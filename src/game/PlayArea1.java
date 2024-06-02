package game;
import java.util.ArrayList;

import initialCard.*;

public class PlayArea1 {

    private InitialCard[][] grid;
    private int size;

    public PlayArea1(int size) {
        this.size = size;
        this.grid = new InitialCard[size][size];
    }

    public boolean placeCard(InitialCard card, int row, int col) {
        if (!isInBounds(row, col)) {
            System.out.println("Position out of bounds.");
            return false;
        }
        
        if (grid[row][col] != null) {
            System.out.println("Position already occupied.");
            return false;
        }

        if (!canPlaceDiagonally(row, col)) {
            System.out.println("Card can only be placed diagonally.");
            return false;
        }

        grid[row][col] = card;
        card.setPlaced(true);
        return true;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private boolean canPlaceDiagonally(int row, int col) {
        if (isEmpty()) {
            return true; // If the grid is empty, the card can be placed anywhere.
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != null) {
                    if (Math.abs(i - row) == Math.abs(j - col)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printPlayArea() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != null) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PlayArea1 playArea = new PlayArea1(20); // Create a 5x5 play area
        InitialCard card1 = new InitialCard1();
        InitialCard card2 = new InitialCard2();

        playArea.placeCard(card1, 2, 2); // Place first card at (2, 2)
        playArea.placeCard(card2, 0, 0); // Attempt to place second card at (0, 0)
        playArea.placeCard(card2, 0, 4); // Attempt to place second card at (0, 4)

        playArea.printPlayArea();
    }
}
