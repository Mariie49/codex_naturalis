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
	//private ArrayList<ObjectiveCard> commonObjCard;
	private ArrayList<Card> visibleCards;
	private int[] playerScores;
	private int[] playerTurnsPlayed;
	private ArrayList<Card> handPlayerCards;
	private PlayArea matchManuscript;
	private int numPlayers;
	private int currentTurn = 0;
	private boolean lastRound  = false;
	private int maxTurns;
	private final Scanner sc = new Scanner(System.in);


	public ArrayList<Player> getPlayerList() {
		return playerList;
	}



	public Game() {
		playerList = new ArrayList<Player>();
		GoldCard.resetGoldCards();
		ResourceCard.resetResourceCards();
		InitialCard.resetInirialCards();
		//ObjectiveCard.resetObjectiveCards;
		
		visibleCards = new ArrayList<Card>();
		currentTurn = 0;
		maxTurns = 0;
		//matchBoard = new Board();

	}

	/**
	 * the constructor initialize a new game, using command line. 
	 */
	public void startGame() {
		boolean stop = false;
		int x = 0;
		int y = 0;
		
		//int index;
		String name;
		numPlayers = 0; //variabile che conta il numero di giocatori
		stop = false;
		//bisognerebbe impostare una playArea sulla base del numero di giocatori. Da fare
		 do {
	            System.out.print("Quanti giocatori parteciperanno (da 2 a 4)? ");
	            while (!sc.hasNextInt()) {
	                System.out.println("Valore non valido. Inserisci un numero tra 2 e 4.");
	                System.out.println("Errore generico");
	                sc.next(); 
	            }
	            numPlayers = sc.nextInt();
	        } while (numPlayers < 2 || numPlayers > 4);
				
		 if (numPlayers == 2) {
			        x= 40;
			        y= 40;
			    } else if (numPlayers == 3) {
			    	x= 28;
			        y= 28;
			        
			    } else if (numPlayers == 4) {
			    	x= 20;
			        y= 20;
			        
			    }
		 matchManuscript = new PlayArea(x, y);
				
			numPlayers = 0;	
			
		do {

			System.out.println("Inserisci il nome del giocatore: " + (numPlayers + 1));
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
				System.out.println("Nome gi� utilizzato. Per favore, inserisci un nome diverso.");
			} else {
				playerList.add(new Player(numPlayers, name, x, y));
				numPlayers++;
			}
			if (numPlayers > 0 && numPlayers < 4) {
				System.out.println("Vuoi aggiungiure un nuovo giocatore?");
				System.out.println("Si oppure no?");
				if (!checkAnswer() && numPlayers > 1) {
					stop = true;
				}
			}
			else {

				stop = true;
			}

			System.out.println(numPlayers);
		} while ( numPlayers < 4 && !stop);

		playerScores = new int[numPlayers];

		System.out.print("I giocatori sono: " + " ");

		for (Player s : playerList) {
			System.out.print(s.getName() + " ");
		}
		System.out.println();
		for (int i = 0; i < 2; i++) {
			// due carte risorsa visibili
			ResourceCard visibleResourceCard = ResourceCard.drawResourceCard();
			visibleCards.add(visibleResourceCard);


			// due carte oro visibili
			GoldCard visibleGoldCard = GoldCard.drawGoldCard();
			visibleCards.add(visibleGoldCard);



			// due carte obiettivo comuni
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
			
			// Draw two resource cards from the resource deck
			player.setPlayArea(matchManuscript);
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
				System.out.println("\nManoscritto del giocatore " + currentPlayer.getName() + " all'inizio del turno:");
		        currentPlayer.getPlayArea().printManuscript();
		        //da fare
			}
		} else {
			
			System.out.println("Turno " + currentTurn + " - Giocatore " + currentPlayer.getName());
			System.out.println("\nManoscritto del giocatore " + currentPlayer.getName() + " all'inizio del turno:");
	        currentPlayer.getPlayArea().printManuscript();
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


			System.out.println("\nManoscritto del giocatore " + currentPlayer.getName() + " alla fine del turno:");
	        currentPlayer.getPlayArea().printManuscript();
			updatevisibleCards();
			return true;
		}

		//da fare
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
		//da fare
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
			System.out.println("La risposta non � corretta. Rispondi si oppure no. ");
			choice = sc.next();
		}
		flag = choice.startsWith("s") || choice.startsWith("S");

		return flag;
	}

	

	private boolean isGameOver() {
		
		for (Player player : playerList) {
			if (player.getPoints() >= 20 ) {
				return true;
			}
		}
		return false;
	}

	
	//da fare
	private void determineWinner() {
		Player winner = null;
		int maxScore = Integer.MIN_VALUE;

		
		for (Player player : playerList) {
			if (player.getPoints() > maxScore) {
				maxScore = player.getPoints();
				winner = player;
			}
		}

		// pareggio?
		ArrayList <Player> winners = new ArrayList<>();
		for (Player player : playerList) {
			if (player.getPoints() == maxScore) {
				winners.add(player);
			}
		}

		
		if (winners.size() == 1) {
			System.out.println("Il vincitore �: " + winners.get(0).getName());
		} else {
			System.out.println("Pareggio! Il vincitore �:");
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
