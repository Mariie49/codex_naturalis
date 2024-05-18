package main;

import java.util.ArrayList;

import card.Corner;
import card.GoldCard;
import card.InsideCorner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Corner> corners1 = new ArrayList<>();
        corners1.add(new Corner(InsideCorner.ARTEFACT));
        corners1.add(new Corner(InsideCorner.EMPTY));
        corners1.add(new Corner(InsideCorner.EMPTY));
        corners1.add(new Corner(InsideCorner.ARTEFACT));
        GoldCard goldCard1 = new GoldCard(corners1, true);

        ArrayList<Corner> corners2 = new ArrayList<>();
        corners2.add(new Corner(InsideCorner.EMPTY));
        corners2.add(new Corner(InsideCorner.EMPTY));
        corners2.add(new Corner(InsideCorner.ARTEFACT));
        corners2.add(new Corner(InsideCorner.EMPTY));
        GoldCard goldCard2 = new GoldCard(corners2, true);

        // Visualizzazione delle carte oro nel terminale
        System.out.println("Carte Oro:");
        System.out.println("------------");

        System.out.println("Carta Oro 1:\n");
        System.out.println("+------+------+");
        printGoldCard(goldCard1);

        System.out.println("\nCarta Oro 2:\n");
        System.out.println("+------+------+");
        printGoldCard(goldCard2);
    }

    public static void printGoldCard(GoldCard goldCard) {
        // Stampa la rappresentazione grafica della carta oro
        for (int i = 0; i < goldCard.getCorners().size(); i++) {
            Corner corner = goldCard.getCorners().get(i);
            String symbol = (corner.getInsideCorner() == InsideCorner.ARTEFACT) ? "A" : " ";
            System.out.print("   " + symbol + "   ");
            if (i % 2 == 1) {
                System.out.println("|");
                System.out.println("+------+------+");
            }
        }
    }

}
