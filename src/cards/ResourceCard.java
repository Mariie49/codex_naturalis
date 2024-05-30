package cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * classe astratta per creazione delle carte di tipo risorsa
 * 
 *
 */
public abstract class ResourceCard {
	private CardType type = CardType.RESOURCE; // Tipo di carta
	private boolean isFront;
	private int points;
	private int number;
	private Corner corner1;
	private Corner corner2;
	private Corner corner3;
	private Corner corner4;
	private static ArrayList<Integer> assignedResourceCards=new ArrayList<>();
	private boolean isPlaced = false; 
	
	
	
	
	public ResourceCard () {
		
		}
	public int getResourceCardNumber() {
		return this.number;
	}
	
	public ArrayList <Corner> addCorners (){
		ArrayList <Corner> corners = new ArrayList<>();
		corners.add(corner1);
		corners.add(corner2);
		corners.add(corner3);
		corners.add(corner4);
		for (int i = 0; i < corners.size(); i++) {
			corners.get(i).setPosition(CornerPosition.values()[i]);
		}
		return corners;
	}
	
	/**
	 * metodo per assegnare una carta  casuale
	 * @return carta obiettivo personale
	 */
	public static ResourceCard assignResourceCard() {
		ResourceCard card = null;
		Random r = new Random();
		int n=0;
		
		do{
		 n=r.nextInt(12)+1;	
		 
		}while(assignedResourceCards.contains(n));
		
		assignedResourceCards.add(n);
		switch(n) {
		case 1:
			card=new ResourceCard1();
			break;
		case 2:
			card=new ResourceCard2();
			break;
		case 3:
			card=new ResourceCard3();
			break;
		case 4:
			card=new ResourceCard4();
			break;
		case 5:
			card=new ResourceCard5();
			break;
		case 6:
			card=new ResourceCard6();
			break;
		case 7:
			card=new ResourceCard7();
			break;
		case 8:
			card=new ResourceCard8();
			break;
		case 9:
			card=new ResourceCard9();
			break;
		case 10:
			card=new ResourceCard10();
			break;
		case 11:
			card=new ResourceCard11();
			break;
		case 12:
			card=new ResourceCard12();
			break;
		case 13:
			card=new ResourceCard13();
			break;
		case 14:
			card=new ResourceCard14();
			break;
		case 15:
			card=new ResourceCard15();
			break;
		case 16:
			card=new ResourceCard16();
			break;
		case 17:
			card=new ResourceCard17();
			break;
		case 18:
			card=new ResourceCard18();
			break;
		case 19:
			card=new ResourceCard19();
			break;
		case 20:
			card=new ResourceCard20();
			break;
		case 21:
			card=new ResourceCard21();
			break;
		case 22:
			card=new ResourceCard22();
			break;
		case 23:
			card=new ResourceCard23();
			break;
		case 24:
			card=new ResourceCard24();
			break;
		case 25:
			card=new ResourceCard25();
			break;
		case 26:
			card=new ResourceCard27();
			break;
		case 27:
			card=new ResourceCard28();
			break;
		case 29:
			card=new ResourceCard29();
			break;
		case 30:
			card=new ResourceCard30();
			break;
		case 31:
			card=new ResourceCard31();
			break;
		case 32:
			card=new ResourceCard32();
			break;
		case 33:
			card=new ResourceCard33();
			break;
		case 34:
			card=new ResourceCard34();
			break;
		case 35:
			card=new ResourceCard35();
			break;
		case 36:
			card=new ResourceCard36();
			break;
		case 37:
			card=new ResourceCard37();
			break;
		case 38:
			card=new ResourceCard38();
			break;
		case 39:
			card=new ResourceCard39();
			break;
		case 40:
			card=new ResourceCard40();
			break;
		case 41:
			card=new ResourceCardBack();
			break;
				
		}
		return card;
	}
	

}
