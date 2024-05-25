package game;

import java.util.ArrayList;
import java.util.Scanner;

import cards.Card;
import cards.GoldCard;
import cards.InitialCard;
import cards.ObjectiveCard;
import cards.ResourceCard;
import cards.Symbol;


public class Player {

	private String name;
	private final int id;
	private int points;
	private boolean isFirst;
	private PlayArea personalManuscript;
	private ArrayList <Card> hand;

	/**
	 * The constructor define player
	 * @param id player
	 * @param name player

	 */
	
	public Player(int id, String name) {
		this.id = id;
		this.personalManuscript = new PlayArea();
		this.name = name;
		points = 0;
		hand = new ArrayList<Card>();
	}

	/**
	 * @return player's Id
	 */
	public int getId() {
		return this.id;
	}
	
	public PlayArea getPlayArea() {
        return personalManuscript;
    }

    public void setPlayArea(PlayArea playArea) {
        personalManuscript = playArea;
    }
	/**
	 * @return player's name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param isFirst
	 */
	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	/**
	 * @return if player is first
	 */
	public boolean isFirst() {
		return isFirst;
	}
	/**
	 * @return player's points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param commonGoalPoints
	 */
	public void setPoints(int commonGoalPoints) {
		this.points = commonGoalPoints;
	}

	public int totalPoints() {
		int total = 0;
		total += personalGoalPoints();
		total += points;
		//total += personalManuscript.nearbySymbolsScore();
		return total;
	}
	
	public ArrayList<Card> addCardToHand(Card card) {
        hand.add(card);
        return hand;
    }
	
	ArrayList<Card> getHand (){
		return this.hand;
	}
	
	
	public Card chooseCardToPlay(ArrayList <Card> hand) {
		this.hand = hand;
		String answer;
		boolean choosen = false;
		Scanner scanner = new Scanner (System.in);
		for (Card card : hand) {
			System.out.println("Would you choose: " + card.toString() + " ?");
			answer = scanner.next();
	        while (!answer.startsWith("y") && !answer.startsWith("Y") && !answer.startsWith("n") && !answer.startsWith("N")) {
	            System.out.println("The answer is incorrect, please reply with: 'Y', 'Yes', 'yes', 'N', 'No', or 'no'");
	            answer = scanner.next();
	        }
	        choosen = answer.startsWith("y") || answer.startsWith("Y");
	        if (choosen) {
	        	scanner.close();
	        	hand.remove(card);
	        	return card;	
	        }
	        else {
	        	continue;
	        	}
		}
		scanner.close();
		return null;	
	}

	public Card chooseCardToTake(ArrayList <ResourceCard> visibleResourceCards, ArrayList <GoldCard> visibleGoldCards) {
		String answer;
		boolean choosen = false;
		Scanner scanner = new Scanner (System.in);
		//2 for-each in cui stampi le carte visibili
		System.out.println("What type of card do you need ? answer resource or gold");
		String typeCard = scanner.next();
		if(typeCard.equals("resource") || typeCard.equals("Resource") || typeCard.equals("RESOURCE")) {
			for (ResourceCard rCard : visibleResourceCards) {
				System.out.println("Would you choose: " + rCard.toString() + " ?");
				answer = scanner.next();
		        while (!answer.startsWith("y") && !answer.startsWith("Y") && !answer.startsWith("n") && !answer.startsWith("N")) {
		            System.out.println("The answer is incorrect, please reply with: 'Y', 'Yes', 'yes', 'N', 'No', or 'no'");
		            answer = scanner.next();
		        }
		        choosen = answer.startsWith("y") || answer.startsWith("Y");
		        if (choosen) {
		        	scanner.close();
		        	visibleResourceCards.remove(rCard);
		        	return (Card)rCard;	
		        }
			}
			scanner.close();
			return ResourceCard.drawCard();
		}
		
		else if(typeCard.equals("gold") || typeCard.equals("Gold") || typeCard.equals("GOLD")) {
		for (GoldCard gCard : visibleGoldCards) {
			System.out.println("Would you choose: " + gCard.toString() + " ?");
			answer = scanner.next();
	        while (!answer.startsWith("y") && !answer.startsWith("Y") && !answer.startsWith("n") && !answer.startsWith("N")) {
	            System.out.println("The answer is incorrect, please reply with: 'Y', 'Yes', 'yes', 'N', 'No', or 'no'");
	            answer = scanner.next();
	        }
	        choosen = answer.startsWith("y") || answer.startsWith("Y");
	        if (choosen) {
	        	scanner.close();
	        	visibleGoldCards.remove(gCard);
	        	return (Card)gCard;	
	        }
		}
		scanner.close();
		return GoldCard.drawCard();
		}
		scanner.close();
		return null;	
	}
	
	
	/**
	 * @return personal goal score 
	 */
	private int personalGoalPoints() {
		//DA FARE
		return points;
	}

	
	/**
	 * @return an array of two positions, in the first element we have the column with the maxinum number of empty cells. In the second element we have the number of symbols
	 */
	//public int[] nMaxSymbols() {
		//return personalManuscript.nMaxSimbols();
	//}

	
	/**
	 * @return player's Manuscript
	 */
	public PlayArea getManuscript() { //no array
		return personalManuscript.getPlayArea();
	}
	/**
	 * @return print player's Manuscript
	 */
	public String printManuscript() {
		return personalManuscript.toString();
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void calculateScore() {
		// TODO Auto-generated method stub
		
	}

	public void chooseOrientationAndPlaceInitialCard(InitialCard initialCard) {
            personalManuscript.placeInitialCard(initialCard); 
    }


	public ObjectiveCard chooseObjectiveCard(ObjectiveCard firstCard,
			ObjectiveCard secondCard) {
		Scanner scanner = new Scanner(System.in);
        System.out.println("Choose one of the following cards:");
        System.out.println("1. " + firstCard);
        System.out.println("2. " + secondCard);
        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.print("Enter your choice (1 or 2): ");
            choice = scanner.nextInt();
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
        scanner.close();
        return choice == 1 ? firstCard : secondCard;
	}

	
		
	}

	


