package resourceCard;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import cards.Card;
import cards.CardType;
import cards.Corner;
import cards.CornerPosition;
import cards.SpecialSymbol;
import cards.Symbol;

/**
 * classe astratta per creazione delle carte di tipo risorsa
 * 
 *
 */
public abstract class ResourceCard extends Card {
	private CardType type = CardType.RESOURCE; // Tipo di carta
	private static boolean isFront;
	private boolean hasCentralSymbol;
	private int points;
	private int number;
	private Corner corner1;
	private Corner corner2;
	private Corner corner3;
	private Corner corner4;
	private static ArrayList<Integer> assignedResourceCards=new ArrayList<>();
	private boolean isPlaced = false; 
	private static Symbol symbol; 

	public ResourceCard () {}

	public int getResourceCardNumber() {
		return this.number;
	}
	@Override
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
	 * @return carta risorsa
	 */
	public static ResourceCard drawResourceCard() {
		ResourceCard card = null;
		Random r = new Random();
		int n=0;

		do{
			n=r.nextInt(40)+1;	

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
			card=new ResourceCardBackPlant();
			break;
		case 42:
			card=new ResourceCardBackFungi();
			break;
		case 43:
			card=new ResourceCardBackAnimal();
			break;
		case 44:
			card=new ResourceCardBackInsect();
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
	@Override
	public boolean isPlaced() {
		return isPlaced;
	}
	@Override
	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}
	
	@Override
	public Symbol getKingdom() {
		return this.symbol;
	}

	public boolean isFront() {
		return isFront;
	}
	@Override
	public boolean hasCentralSymbol() {
		return hasCentralSymbol;
	}
	@Override
	public Symbol getCentralSymbol() {
		return symbol;
	}

	public String getCornerRepresentation(CornerPosition position) {
		for (Corner corner : addCorners()) {
			if (corner.getPosition().equals(position)) {
				switch (corner.getState()) {
				case NULL:
					return "NULL";
				case EMPTY:
					return "EMPTY";
				case HIDDEN:
					return "HIDDEN";
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
	public String getAbbreviatedCorner(CornerPosition position) {
	    for (Corner corner : addCorners()) {
	        if (corner.getPosition().equals(position)) {
	            switch (corner.getState()) {
	                case NULL:
	                    return "NUL";
	                case EMPTY:
	                    return "EMP";
	                case HIDDEN:
	                    return "HID";
	                case SYMBOL:
	                	if (corner.getSymbol() instanceof Symbol) {
	                        Symbol symbol = (Symbol) corner.getSymbol();
	                        return symbol.getAbbreviation();
	                	} else {
	                        
	                        return corner.getSymbol().toString();
	                    }
	                case SPECIALSYMBOL:
	                	if (corner.getSymbol() instanceof SpecialSymbol) {
	                        SpecialSymbol symbol = (SpecialSymbol) corner.getSymbol();
	                        return symbol.getAbbreviation();
	                	} else {
	                        
	                        return corner.getSymbol().toString();
	                    }
	                default:
	                    return "?";
	            }
	        }
	    }
	    return "  "; 
	}
	@Override
	public void printCardInCell() {
		if(this.isFront())
			{
				System.out.print(getAbbreviatedCorner(CornerPosition.TOP_LEFT) + "        "+
						getAbbreviatedCorner(CornerPosition.TOP_RIGHT));
				System.out.println("\n");
				System.out.println(getAbbreviatedCorner(CornerPosition.BOTTOM_LEFT) + "        "+
						getAbbreviatedCorner(CornerPosition.BOTTOM_RIGHT)+"\n");
			}else {
				
				System.out.print(getAbbreviatedCorner(CornerPosition.TOP_LEFT) + "        "+
						getAbbreviatedCorner(CornerPosition.TOP_RIGHT));
				System.out.println("\n\n    " + getKingdom().getAbbreviation() + "    \n");
				System.out.println(getAbbreviatedCorner(CornerPosition.BOTTOM_RIGHT) + "        "+
						getAbbreviatedCorner(CornerPosition.BOTTOM_LEFT)+"\n");
		}
	}
	/**
	 * Prints the details of the card.
	 * 
	 * This method displays the card's type, the side (front or back),
	 * the corner symbols and Central symbols (if present) of that side, and the card's score.
	 * If the card has no Central symbols, a blank line is printed to maintain consistent card height.
	 */
	@Override
	public void printCard() {
		if(this.isFront()) {
			System.out.println("Type: " + getType());
			System.out.println("Score: " + getPoints());
			System.out.println("Kingdom: " + getKingdom());
			System.out.println("Front side : \n");

			System.out.print(getCornerRepresentation(CornerPosition.TOP_LEFT) + "        "+
					getCornerRepresentation(CornerPosition.TOP_RIGHT));
			System.out.println();
			System.out.println();


			System.out.println(getCornerRepresentation(CornerPosition.BOTTOM_RIGHT) + "        "+
					getCornerRepresentation(CornerPosition.BOTTOM_LEFT));	
		}
		else {

			System.out.println("Type: " + getType());
			System.out.println("Score: " + getPoints());
			System.out.println("Kingdom: " + getKingdom());
			System.out.println("Back side :\n");

			System.out.print(getCornerRepresentation(CornerPosition.TOP_LEFT) + "        "+
					getCornerRepresentation(CornerPosition.TOP_RIGHT));
			System.out.println();
			System.out.print("    " + getKingdom() + "    ");
			System.out.println();
			System.out.println(getCornerRepresentation(CornerPosition.BOTTOM_LEFT) + "        "+
					getCornerRepresentation(CornerPosition.BOTTOM_RIGHT));	

		}}
	/**
	 * This method displays a menu asking the user to select the front (1) or back (2) side.
	 * It reads the user's input and sets the isFront property of the card accordingly.
	 * If the user enters an invalid choice, it defaults to the front side.
	 */
	@Override
	public Card ChooseSide(Card d) {
			ResourceCard card = null;
		    System.out.println("Do you want to choose the front or back side of the card?");
		    System.out.println("1. Front");
		    System.out.println("2. Back");

		    Scanner scanner = new Scanner(System.in);
		    int choice = scanner.nextInt();
		    int val = d.getNumber();
		    switch (choice) {
		        case 1:
		        	card = (ResourceCard)d;
		            break;
		        case 2:
		            if (val > 0 && val < 11 ) 
		            	card=new ResourceCardBackPlant();
		            else if (val > 10 && val < 21 ) 
		            	card=new ResourceCardBackFungi();
		            else if ( val > 20 && val < 31 )
		            	card=new ResourceCardBackAnimal();
		            else if (val > 30 && val < 41 )
		            	card=new ResourceCardBackInsect();
		            break; 
		        default:
		            System.out.println("Invalid choice. Defaulting to front side.");
		            card = (ResourceCard)d;
		            break;
		    }

		    System.out.println("You have chosen the " + (isFront() ? "front" : "back") + " side of the card.");
		    scanner.close();
		    return card;
		
	}
	
	


}
