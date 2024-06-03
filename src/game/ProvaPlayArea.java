package game;
import cards.Corner;
import initialCard.*;
import initialCard.InitialCard3;
import initialCard.InitialCard4;
import resourceCard.ResourceCard23;
import resourceCard.ResourceCard7;

import java.util.ArrayList;

import cards.Card;
import game.*;
import goldCard.GoldCard4;
public class ProvaPlayArea {

	public static void main(String[] args) {
		PlayArea playArea = new PlayArea(5, 5);
		ArrayList<Cell> availableCells;
        // Carta iniziale (fittizia)
		System.out.println(playArea.toString());
        /*
         * InitialCard initialCard = new InitialCard3();
        playArea.placeInitialCard(initialCard);
        availableCells = playArea.getAvailableDiagonalCells();
        System.out.println("Celle disponibili:");
        for (Cell cell : availableCells) {
            System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
        }
         */
        /*
		Card card1 = new ResourceCard7();
		playArea.placeCardInManuscript(card1, 3, 3);
         */
        /*
         * // Carte normali (fittizie)
        Card card1 = new ResourceCard7();
        Card card2 = new GoldCavrd4();
        Card card3 = new ResourceCard23();

        // Posizionamento delle carte (fittizio)
        playArea.placeCardInManuscript(card1, 1, 0);
        playArea.placeCardInManuscript(card2, 2, 1);
        playArea.placeCardInManuscript(card3, 0, 2);
         */

        // Stampa del manoscritto
        playArea.printManuscript();
    }

	

}
