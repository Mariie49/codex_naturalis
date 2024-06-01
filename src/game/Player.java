package game;

import java.util.ArrayList;
import java.util.Scanner;
import goldCard.*;
import initialCard.*;
//import ObjectiveCard;
import resourceCard.*;
import cards.Symbol;


public class Player {

	private String name;
	private final int id;
	private int points;
	private boolean isFirst;
	//private CommonGoalCard commonGoalCard;
	private PlayArea personalManuscript;
	private ArrayList <ResourceCard> handResourceCard;
	private ArrayList <GoldCard> handGoldCard;

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
		handResourceCard = new ArrayList<ResourceCard>();
		handGoldCard = new ArrayList <GoldCard> ();

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
		//total += personalGoalPoints();
		total += points;
		//total += personalManuscript.nearbySymbolsScore();
		return total;
	}

	public ArrayList <ResourceCard> addResourceCardToHand(ResourceCard card) {
		handResourceCard.add(card);
		return handResourceCard;
	}
	public ArrayList <GoldCard> addGoldCardToHand(GoldCard card) {
		handGoldCard.add(card);
		return handGoldCard;
	}

	ArrayList<ResourceCard> getResourceCardHand (){
		return handResourceCard;
	}
	ArrayList<GoldCard> getGoldCardHand (){
		return handGoldCard;
	}
	
	
	/**
	 * Allows the player to choose a card to play from their hand.
	 *
	 * @param hand The player's current hand of cards (ArrayList).
	 * @return The chosen Card object, or null if no card was selected.
	 */
	public Object chooseCardToPlay(ArrayList <ResourceCard> handResourceCards, ArrayList <GoldCard> handGoldCards) {
		
		String answer;
		boolean choosen = false;
		Scanner scanner = new Scanner (System.in);
		for (ResourceCard card : handResourceCards) {
			card.printCard();
			System.out.println("Sceglieresti questa carta??");
			answer = scanner.next();
			while (!answer.startsWith("s") && !answer.startsWith("S") && !answer.startsWith("n") && !answer.startsWith("N")) {
				System.out.println("La risposta non è corretta. Rispondi si oppure no.");
				answer = scanner.next();
			}
			choosen = answer.startsWith("s") || answer.startsWith("S");
			if (choosen) {
				scanner.close();
				handResourceCards.remove(card);
				return card;	
			}
			else {
				continue;
			}
		}
		for (GoldCard card : handGoldCards) {
			card.printCard();
			System.out.println("Sceglieresti questa carta??");
			answer = scanner.next();
			while (!answer.startsWith("s") && !answer.startsWith("S") && !answer.startsWith("n") && !answer.startsWith("N")) {
				System.out.println("La risposta non è corretta. Rispondi si oppure no.");
				answer = scanner.next();
			}
			choosen = answer.startsWith("s") || answer.startsWith("S");
			if (choosen) {
				scanner.close();
				handGoldCards.remove(card);
				return card;	
			}
			else {
				continue;
			}
		}
		
		scanner.close();
		return null;	
	}
	/**
	 * Allows the player to choose a card to take from the visible resource or gold cards.
	 *
	 * @param visibleResourceCards The list of visible resource cards available.
	 * @param visibleGoldCards     The list of visible gold cards available.
	 * @return The chosen Card object. If the player doesn't choose any of the visible 
	 *         cards, a new resource or gold card (depending on their choice) is drawn
	 *         and returned. Returns null only if the player enters invalid input for
	 *         the card type.
	 */
	public Object chooseCardToTake(ArrayList <ResourceCard> visibleResourceCards, ArrayList <GoldCard> visibleGoldCards) {
		String answer;
		boolean choosen = false;
		Scanner scanner = new Scanner (System.in);
		Scanner in = new Scanner (System.in);
		int choice;
		System.out.println("Che tipo di carta vorresti? Oro oppure Risorsa?");
		String typeCard = scanner.next();
		if(typeCard.equals("risorsa") || typeCard.equals("Risorsa") || typeCard.equals("RISORSA")) {
			ResourceCard cardTaken;
			System.out.println("Le carte di tipo risorsa visibili sono: ");
			for (ResourceCard rCard : visibleResourceCards) {
				rCard.printCard();
				System.out.println();
			}
			System.out.println();
			System.out.println("Vuoi selezionare una di queste carte? : ");
			answer = scanner.next();
			while (!answer.startsWith("s") && !answer.startsWith("S") && !answer.startsWith("N") && !answer.startsWith("n")) {
				System.out.println("La risposta non è corretta. Rispondi si o no!'");
				answer = scanner.next();
			}
			choosen = answer.startsWith("s") || answer.startsWith("S");
			if (choosen) {
				scanner.close();
				System.out.println("Quale tra queste carte vuoi? Per la prima inserire 1, per la seconda inserire 2 : ");
				choice = in.nextInt();
				cardTaken = visibleResourceCards.remove(choice);
				in.close();
				return cardTaken;	
			}
			else {
				System.out.println("Pesco una nuova carta: ");
				scanner.close();
				in.close();
				return ResourceCard.assignResourceCard();
			}

		}

		else if(typeCard.equals("gold") || typeCard.equals("Gold") || typeCard.equals("GOLD")) {
			GoldCard cardTaken;
			System.out.println("Le carte di tipo oro visibili sono: ");
			for (GoldCard gCard : visibleGoldCards) {
				gCard.printCard();
				System.out.println();
			}
			System.out.println();
			System.out.println("Vuoi selezionare una di queste carte? : ");
			answer = scanner.next();
			while (!answer.startsWith("s") && !answer.startsWith("S") && !answer.startsWith("N") && !answer.startsWith("n")) {
				System.out.println("La risposta non è corretta. Rispondi si o no!'");
				answer = scanner.next();
			}
			choosen = answer.startsWith("s") || answer.startsWith("S");
			if (choosen) {
				scanner.close();
				System.out.println("Quale tra queste carte vuoi? Per la prima inserire 1, per la seconda inserire 2 : ");
				choice = in.nextInt();
				cardTaken = visibleGoldCards.remove(choice);
				in.close();
				return cardTaken;	
			}
			else {
				System.out.println("Pesco una nuova carta: ");
				scanner.close();
				in.close();
				return GoldCard.assignGoldCard();
			}

		}
		scanner.close();
		in.close();	
		return null;
	}


	/**
	 * @return personal goal score 
	 */
	private int commonGoalPoints() {
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


	/*
	 * public ObjectiveCard chooseObjectiveCard(ObjectiveCard firstCard,
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
	 */



}




