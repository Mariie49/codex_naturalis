package initialCard;

import java.util.ArrayList;
import java.util.Random;

import cards.CardType;
import cards.Corner;
import cards.CornerPosition;
import cards.Symbol;


public abstract class InitialCard {

	private CardType type = CardType.STARTING; // Tipo di carta
	private boolean isFront;
	private int points;
	private int number;
	private Corner corner1;
	private Corner corner2;
	private Corner corner3;
	private Corner corner4;
	private static ArrayList<Integer> assignedInitialCards=new ArrayList<>();
	private boolean isPlaced = false; 
	private boolean hasCentralSymbol;
	private Symbol symbolA;
	public InitialCard () {}

	public int getInitialCardNumber() {
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

	public ArrayList <Symbol> addCentralSymbol (){
		ArrayList <Symbol> centralSymbol = new ArrayList<>();
		centralSymbol.add(symbolA);
		return centralSymbol;
	}




	/**
	 * metodo per assegnare una carta  casuale
	 * @return carta iniziale
	 */
	public static InitialCard assignInitialCard() {
		InitialCard card = null;
		Random r = new Random();
		int n=0;

		do{
			n=r.nextInt(12)+1;	

		}while(assignedInitialCards.contains(n));

		assignedInitialCards.add(n);
		switch(n) {
		case 1:
			card=new InitialCard1();
			break;
		case 2:
			card=new InitialCard2();
			break;
		case 3:
			card=new InitialCard3();
			break;
		case 4:
			card=new InitialCard4();
			break;
		case 5:
			card=new InitialCard5();
			break;
		case 6:
			card=new InitialCard6();
			break;
		case 7:
			card=new InitialCard7();
			break;
		case 8:
			card=new InitialCard8();
			break;
		case 9:
			card=new InitialCard9();
			break;
		case 10:
			card=new InitialCard10();
			break;
		case 11:
			card=new InitialCard11();
			break;
		case 12:
			card=new InitialCard12();
			break;
		}
		return card;
	}

	public CardType getType() {
		return type;
	}

	public int getPoints() {
		return points;
	}

	public boolean isPlaced() {
		return isPlaced;
	}
	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}


	public boolean isFront() {
		return isFront;
	}
	public boolean hasCentralSymbol() {
		return hasCentralSymbol;
	}
	public Symbol getCentralSymbol() {
		return symbolA;
	}

	public String getCornerRepresentation(CornerPosition position) {
		for (Corner corner : addCorners()) {
			if (corner.getPosition().equals(position)) {
				switch (corner.getState()) {
				case NULL:
					return "NULL";
				case EMPTY:
					return "EMPTY";
				case SYMBOL:
					return corner.getSymbol().toString();
				case SPECIALSYMBOL:
					return corner.getSymbol().toString();
				default:
					return "?";
				}
			}
		}
		return "  "; // Angolo non trovato (non dovrebbe accadere)
	}
	/**
	 * Prints the details of the card.
	 * 
	 * This method displays the card's type, the side (front or back),
	 * the corner symbols and center symbols (if present) of that side, and the card's score.
	 * If the card has no center symbols, a blank line is printed to maintain consistent card height.
	 */
	public void printCard() {
		if(this.isFront()) {
			System.out.println("Type: " + getType());
			System.out.println("Score: " + getPoints());
			System.out.println("Front side :");

			System.out.print(getCornerRepresentation(CornerPosition.TOP_LEFT) + "  "+
					getCornerRepresentation(CornerPosition.TOP_RIGHT));
			System.out.println();
			if(this.hasCentralSymbol()) {
				for(Symbol s: addCentralSymbol())
					System.out.println("  " + s + "   ");
			}
			else {
				System.out.println();
				}
			
			System.out.println(getCornerRepresentation(CornerPosition.BOTTOM_RIGHT) + "  "+
					getCornerRepresentation(CornerPosition.BOTTOM_LEFT));	
		}
		else {

			System.out.println("Type: " + getType());
			System.out.println("Score: " + getPoints());
			System.out.println("Back side :");

			System.out.print(getCornerRepresentation(CornerPosition.TOP_LEFT) + "  "+
					getCornerRepresentation(CornerPosition.TOP_RIGHT));
			System.out.println();
			if(this.hasCentralSymbol()) {
				for(Symbol s: addCentralSymbol())
					System.out.print("  " + s + "   ");
			}
			else {
				System.out.println();
				}

			System.out.println(getCornerRepresentation(CornerPosition.BOTTOM_LEFT) + "  "+
					getCornerRepresentation(CornerPosition.BOTTOM_RIGHT));	
		
		}

	}




}
