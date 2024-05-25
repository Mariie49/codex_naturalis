package main;

import java.util.ArrayList;

import card.Card;
import card.GoldCard;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoldCard goldCard = new GoldCard();
		ArrayList<Card> goldCardsDeck = GoldCard.getGoldCardsDeck();

        // Stampa le carte del mazzo
        for (Card card : goldCardsDeck) {
            System.out.println(card);
        }
	}

}
