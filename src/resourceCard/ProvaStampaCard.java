package resourceCard;

import java.util.ArrayList;

import initialCard.InitialCard3;
import initialCard.InitialCard4;


public class ProvaStampaCard {

	public static void main(String[] args) {
		//ArrayList<ResourceCard> card= new ArrayList<ResourceCard>();
		ResourceCard23 card = new ResourceCard23();
		ResourceCardBackPlant cardiB  =  new ResourceCardBackPlant();
		InitialCard3 cardI = new InitialCard3 ();
		InitialCard4 cardIB = new InitialCard4 ();
		 cardI.printCard();
		 System.out.println();
		 cardIB.printCard();
		 
		
		 /*
		  * System.out.println(card.getSymbol());
		 System.out.println(card.getPoints());
		 System.out.println(card.getResourceCardNumber());
		 System.out.println(card.isFront());
		 System.out.println(card.getType());
		 card.setPlaced(true);
		 System.out.println(card.isPlaced());
		  */
	}

}
