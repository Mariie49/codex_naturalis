package cards.goldcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cards.CardType;
import cards.Corner;

public abstract class GoldCard {

	private CardType type = CardType.GOLD;
	private boolean isFront;
	private boolean isPlaced = false;
	private int points;
	private int number;
	private Corner corner1;
	private Corner corner2;
	private Corner corner3;
	private Corner corner4;
	private static List<Integer> addGoldCards= new ArrayList<>();
	
	public GoldCard () {}

	public static GoldCard addGoldCard() {
		GoldCard card = null;
		Random r = new Random();
		int n = 0;
		
		do{
		 n=r.nextInt(40);		 
		}while(addGoldCards.contains(n));
		
		addGoldCards.add(n);
		switch(n) {
		case 1:
			card=new GoldCard1();
			break;
		case 2:
			card=new GoldCard2();
			break;
		case 3:
			card=new GoldCard3();
			break;
		case 4:
			card=new GoldCard4();
			break;
		case 5:
			card=new GoldCard5());
			break;
		case 6:
			card=new GoldCard6();
			break;
		case 7:
			card=new GoldCard7();
			break;
		case 8:
			card=new GoldCard8();
			break;
		case 9:
			card=new GoldCard9();
			break;
		case 10:
			card=new GoldCard10();
			break;
		case 11:
			card=new GoldCard11();
			break;
		case 12:
			card=new GoldCard12();
			break;
		case 13:
			card=new GoldCard13();
			break;
		case 14:
			card=new GoldCard14();
			break;
		case 15:
			card=new GoldCard15());
			break;
		case 16:
			card=new GoldCard16();
			break;
		case 17:
			card=new GoldCard17();
			break;
		case 18:
			card=new GoldCard18();
			break;
		case 19:
			card=new GoldCard19();
			break;
		case 20:
			card=new GoldCard20();
			break;
		case 21:
			card=new GoldCard21();
			break;
		case 22:
			card=new GoldCard22();
			break;
		case 23:
			card=new GoldCard23();
			break;
		case 24:
			card=new GoldCard24();
			break;
		case 25:
			card=new GoldCard25());
			break;
		case 26:
			card=new GoldCard26();
			break;
		case 27:
			card=new GoldCard27();
			break;
		case 28:
			card=new GoldCard28();
			break;
		case 29:
			card=new GoldCard29();
			break;
		case 30:
			card=new GoldCard30();
			break;
		case 31:
			card=new GoldCard31();
			break;
		case 32:
			card=new GoldCard32();
			break;
		case 33:
			card=new GoldCard33();
			break;
		case 34:
			card=new GoldCard34();
			break;
		case 35:
			card=new GoldCard35());
			break;
		case 36:
			card=new GoldCard36();
			break;
		case 37:
			card=new GoldCard37();
			break;
		case 38:
			card=new GoldCard38();
			break;
		case 39:
			card=new GoldCard39();
			break;
		case 40:
			card=new GoldCard40();
			break;
		case 41:
			card=new GoldCardBack();
			break;
		}
	}
}
