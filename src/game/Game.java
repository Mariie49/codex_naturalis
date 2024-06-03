package game;
import resourceCard.*;
import cards.*;
import goldCard.*;
import initialCard.*;
import objectivecards.ObjectiveCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class Game {

	private ArrayList<Player> playerList;
	private ArrayList<ResourceCard> resourceCards;
	private ArrayList<Card> handPlayerCards;
	private ArrayList<GoldCard> goldCards;
	//private ArrayList<ObjectiveCard> commonObjCard;
	private ArrayList <InitialCard> initCards;
	private ArrayList<Card> visibleCards;
	private int[] playerScores;
	private int[] playerTurnsPlayed;
	//private ArrayList<Card> handPlayerCards;
	private PlayArea matchManuscript;
	private int nPlayers;
	private int firstEndedPoint;
	private int currentTurn = 0;
	private boolean lastRound  = false;
	private int maxTurns;
	private final Scanner sc = new Scanner(System.in);


	public ArrayList<Player> getPlayerList() {
		return playerList;
	}



	public Game() {
		playerList = new ArrayList<Player>();
		resourceCards = new ArrayList<ResourceCard>();
		goldCards = new ArrayList<GoldCard>();
		//commonObjCard = new ArrayList<ObjectiveCard>();
		initCards = new ArrayList<InitialCard>();
		visibleCards = new ArrayList<Card>();

		handPlayerCards= new ArrayList <Card>();

		firstEndedPoint  = 1;
		currentTurn = 0;
		maxTurns = 0;
		//matchBoard = new Board();

	}

	/**
	 * the constructor initialize a new game, using command line. 
	 */
	public void initGame() {
		ArrayList<ResourceCard> deckResourceCard;
		ArrayList<GoldCard> deckGoldCard;
		ArrayList<InitialCard> deckInitialCard;
		boolean stop = false;
		Random rand;
		int index;
		String name;
		//ArrayList<ObjectiveCard> allCommonGoals;
		deckResourceCard = new ArrayList<ResourceCard>();

		nPlayers = 0; //variabile che conta il numero di giocatori
		stop = false;
		rand = new Random();

		//deckCommonGoals = new ArrayList<ObjectiveCard>();
		deckResourceCard = new ArrayList<ResourceCard>();
		deckGoldCard = new ArrayList <GoldCard>();
		deckInitialCard = new ArrayList <InitialCard>();
		//Collections.addAll(resourceCard);

		do {

			System.out.println("Inserisci il nome del giocatore: " + (nPlayers + 1));
			//index = rand.nextInt(resourceCard.size()); //Chooses a random index in resourceCard? Serve?
			name = sc.next();
			boolean isDuplicated = false;
			for (Player p : playerList) {
				if (p.getName().equalsIgnoreCase(name)) { 
					isDuplicated = true;
					break; 
				}
			}

			if (isDuplicated) {
				System.out.println("Nome già utilizzato. Per favore, inserisci un nome diverso.");
			} else {
				playerList.add(new Player(nPlayers, name));
				nPlayers++;
			}
			if (nPlayers > 0 && nPlayers < 4) {
				System.out.println("Vuoi aggiungiure un nuovo giocatore?");
				System.out.println("Si oppure no?");
				if (!checkAnswer() && nPlayers > 1) {
					stop = true;
				}
			}
			else {

				stop = true;
			}

			System.out.println(nPlayers);
		} while ( nPlayers < 4 && !stop);

		playerScores = new int[nPlayers];

		System.out.print("I giocatori sono: " + " ");

		for (Player s : playerList) {
			System.out.print(s.getName() + " ");
		}
		System.out.println();
		for (int i = 0; i < 2; i++) {
			// Draw two visible resource cards from the resource deck
			ResourceCard visibleResourceCard = ResourceCard.drawResourceCard();
			visibleCards.add(visibleResourceCard);


			// Draw two visible gold card from the gold deck
			GoldCard visibleGoldCard = GoldCard.drawGoldCard();
			visibleCards.add(visibleGoldCard);



			// Draw two visible objective card from the objective deck
			//   ObjectiveCard comObjectiveCard = ObjectiveCard.drawCard();
			//   commonObjCard.add(comObjectiveCard);
		}

		System.out.println("Ecco le prime carte disponibili sul tavolo: 2 carte oro e 2 carte risorsa. ");
		for (Card r : visibleCards) {
			System.out.println();
			r.printCard();
			System.out.println();
		}


		for (Player player : playerList) {
			//player.setPlayArea(matchManuscript.getMatrGrid());//non sono sicura dell'effettiva correttezza di questa riga
			// Draw two resource cards from the resource deck
			System.out.println(" " + player.getName());
			System.out.println("Pesco 2 carte risorsa e una carta oro. ");
			for (int i = 0; i < 2; i++) {
				Card resourceHandCard = ResourceCard.drawResourceCard();
				resourceHandCard.printCard();
				player.addCardToHand(resourceHandCard);
			}

			// Draw one gold card from the gold deck
			Card goldHandCard = GoldCard.drawGoldCard();
			goldHandCard.printCard();
			player.addCardToHand(goldHandCard);


			// Draw one initial card from the initial deck, choose orientation and place initial card
			InitialCard initialHandCard = InitialCard.drawInitialCard(); 
			//restituisce la carta nel verso scelto 

			player.chooseOrientationAndPlaceInitialCard(initialHandCard); 



			// Draw two objective cards from the objective deck and choose one
			/*
			 *  ObjectiveCard objectiveFirstHandCards = ObjectiveCard.drawCard();
			 *  ObjectiveCard objectiveSecondHandCards = ObjectiveCard.drawCard();

	                ObjectiveCard objectiveHandCards = player.chooseObjectiveCard(objectiveFirstHandCards, objectiveSecondHandCards);
			 */

		}

		Collections.shuffle(playerList);
		playerList.get(0).setIsFirst(true);



	}


	/**
	 * Metodo per gestire il turno dei giocatori 
	 *
	 * @param currentPlayer
	 * @return vero se il giocatore ha piazzato la sua carta sul manoscritto e ne ha scelto un'altra
	 */
	public boolean turn (Player currentPlayer) {
		//Corner choosenCorner = null;
		Card cardToPlay = null;
		Card cardToTake = null;
		ArrayList<Cell> availableCells;
		boolean cardPlaced = false;
		currentTurn++;
		playerTurnsPlayed[currentPlayer.getId()]++;

		if ( !lastRound  || (playerScores[currentPlayer.getId()]  >= 20 && !lastRound)) {
			lastRound = true; 
		}

		if (lastRound) {

			if (playerTurnsPlayed[currentPlayer.getId()] < getMaxTurnsPlayed()) {

				System.out.println("Ultimo Round - Giocatore " + currentPlayer.getName());

			}
		} else {
			// Logica del turno normale
			System.out.println("Turno " + currentTurn + " - Giocatore " + currentPlayer.getName());
			cardToPlay = currentPlayer.chooseCardToPlay();

			availableCells = matchManuscript.getAvailableDiagonalCells();
			System.out.println("Celle disponibili:");
			for (Cell cell : availableCells) {
				System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
			}

			while (!cardPlaced) {
				try {
					System.out.print("Inserisci la coordinata x: ");
					int x = sc.nextInt(); 

					System.out.print("Inserisci la coordinata y: ");
					int y = sc.nextInt(); 

					matchManuscript.placeCardInManuscript(cardToPlay, x, y);
					cardPlaced = true; 

				} catch (IllegalArgumentException e) {
					if (e.getMessage().contains("Posizione non valida nel manoscritto")) {
						System.out.println("Coordinate non valide. Riprova.");
						availableCells = matchManuscript.getAvailableDiagonalCells();
						System.out.println("Celle disponibili:");
						for (Cell cell : availableCells) {
							System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
						}
					} else {
						System.out.println("Errore: " + e.getMessage());
						System.out.println("Riprova a inserire la carta.");
					}
				}
			}
			playerScores[currentPlayer.getId()] += currentPlayer.getPoints();
			if ( isGameOver() || (lastRound && allPlayersHadLastTurn())) {
				endGame();
			}





			matchManuscript = currentPlayer.getPlayArea();

			cardToPlay = currentPlayer.chooseCardToPlay();




			do {
				cardToTake = currentPlayer.chooseCardToTake(visibleCards);

			}while(cardToTake == null);


			currentPlayer.addCardToHand(cardToTake);



			updatevisibleCards();
			return true;
		}


		if (lastRound && allPlayersHadLastTurn()) {
			endGame();
		}



		return false;
	}




	private int getMaxTurnsPlayed() {
		int maxTurns = 0;
		for (int turns : playerTurnsPlayed) {
			maxTurns = Math.max(maxTurns, turns);
		}
		return maxTurns;
	}

	private boolean allPlayersHadLastTurn() {
		for (int turns : playerTurnsPlayed) {
			if (turns < getMaxTurnsPlayed()) {
				return false;
			}
		}
		return true;
	}

	private void endGame() {
		// Logica di fine partita (determinazione vincitore, ecc.)
		System.out.println("Partita terminata!");
	}

	/**
	 * Verifica se l'utente risponde si oppure no. Le valide risposte sono : 'Y', 'Yes', 'yes', 'N', 'No', or 'no'
	 *
	 * @return true is the answer is yes
	 */
	public boolean checkAnswer() {
		String choice;
		boolean flag;
		choice = sc.next();
		//while(!choice.contains("y") || !choice.equals("Yes") || !choice.equals("yes") || !choice.equals("N") || !choice.equals("No") || !choice.equals("no")) 
		while (!choice.startsWith("s") && !choice.startsWith("S") && !choice.startsWith("n") && !choice.startsWith("N")) {
			System.out.println("La risposta non è corretta. Rispondi si oppure no. ");
			choice = sc.next();
		}
		flag = choice.startsWith("s") || choice.startsWith("S");

		return flag;
	}

	private void playTurns() {
		while (!isGameOver()) {

		}
	}

	private boolean isGameOver() {
		// Check if any player has reached 20 points
		for (Player player : playerList) {
			if (player.getPoints() >= 20 ) {
				return true;
			}
		}
		return false;
	}

	private void calculateScores() {
		for (Player player : playerList) {
			player.totalPoints();
		}
	}

	private void determineWinner() {
		Player winner = null;
		int maxScore = Integer.MIN_VALUE;

		// Find the player with the highest score
		for (Player player : playerList) {
			if (player.getPoints() > maxScore) {
				maxScore = player.getPoints();
				winner = player;
			}
		}

		// Check for ties
		ArrayList <Player> winners = new ArrayList<>();
		for (Player player : playerList) {
			if (player.getPoints() == maxScore) {
				winners.add(player);
			}
		}

		// Declare the winner(s)
		if (winners.size() == 1) {
			System.out.println("Il vincitore è: " + winners.get(0).getName());
		} else {
			System.out.println("Pareggio! Il vincitore è:");
			for (Player player : winners) {
				System.out.println(player.getName());
			}
		}
	}







	public ArrayList<Player> rankings(){
		ArrayList<Player> sortedRanking;
		sortedRanking = playerList;
		// Setting up the comparator, the reversed flag is to have a decrescent order
		Comparator <Player> scoreComparator = Comparator.comparingInt(Player :: totalPoints).reversed();

		// Sorting the ArrayList with the Comparator
		Collections.sort(sortedRanking, scoreComparator);
		return sortedRanking;
	}

	/**
	 * Fast way to print an ArrayList of Cards with an index on top of the row
	 *
	 * @param cards
	 */
	public void printCardArrayList(ArrayList<Card> genericCards) {

		for (int i = 0; i < genericCards.size(); i++) {
			System.out.print(i + "\t");
		}
		System.out.println();

		for (int i = 0; i < genericCards.size(); i++) {
			System.out.print(genericCards.get(i) + "\t");
		}
		System.out.println();
	}

	private void updatevisibleCards() {
		int goldCount = 0;
		int resourceCount = 0;


		// Analisi delle carte visibili
		for (Card card : visibleCards) {
			if (card.getType() == CardType.GOLD) {
				goldCount++;
			} else if (card.getType() == CardType.RESOURCE) {
				resourceCount++;
			}
		}

		// Pesca delle nuove carte (se necessario)
		while (goldCount < 2) {
			Card gold = GoldCard.drawGoldCard(); // Metodo ipotetico per pescare una carta specifica
			if (gold != null) {
				visibleCards.add(gold);
				goldCount++;
			} else {
				lastRound = true;
				System.out.println("Carte oro sono finite. Fine gioco.");
				break; 
			}
		}

		while (resourceCount < 2) {
			Card resource = ResourceCard.drawResourceCard();
			if (resource != null) {
				visibleCards.add(resource);
				resourceCount++;
			} else {
				lastRound = true;
				System.out.println("Carte risorsa sono finite. Fine gioco. ");
				break;
			}
		}

	}




	public PlayArea getplayArea() {
		return matchManuscript;
	}
}
