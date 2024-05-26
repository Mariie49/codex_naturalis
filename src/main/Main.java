package main;

import java.util.ArrayList;
import card.ResourceCard;
import card.Card;
import card.GoldCard;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ResourceCard a = new ResourceCard();
		
		GoldCard goldCard = new GoldCard();
		ArrayList<Card> goldCardsDeck = GoldCard.getGoldCardsDeck();
		goldCard= GoldCard.drawCard();
		goldCard.printCard();
		a.printCard();
		
		
	}

}
