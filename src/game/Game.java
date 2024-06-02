package game;
import resourceCard.*;
import cards.*;
import goldCard.*;
import initialCard.*;
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
	    
	    //private ArrayList<Card> handPlayerCards;
	    private PlayArea matchManuscript;
	    private int nPlayers;
	    private int firstEndedPoint;
	    private int currentTurn;
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
	        boolean stop;
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
	        
	        //establishing players
	        do {

	            System.out.println("Inserisci il nome del giocatore: " + (nPlayers + 1));
	            //index = rand.nextInt(resourceCard.size()); //Chooses a random index in resourceCard? Serve?
	            name = sc.next();
	            playerList.add(new Player(nPlayers, name)); //new istance of player added to playerList
	            nPlayers++;
	            if (nPlayers > 0 && nPlayers < 4) {
	                System.out.println("Vuoi aggiungiure un nuovo giocatore?");
	                System.out.println("Si oppure no?");
	                if (!checkAnswer() && nPlayers > 1) {
	                    stop = true;
	                    //System.out.println(nPlayers);
	                }
	            }
	            else {
	            	
	                stop = true;
	            }
	            
	            System.out.println(nPlayers);
	        } while ( nPlayers < 4 && !stop);

	        /*
	         * for(int i = 0; i< nPlayers; i++) {
	        	matchManuscript = new PlayArea();//devo assegnare ogni manoscritto al proprio giocatore
	        }
	         */
	        System.out.print("I giocatori sono: " + " ");
	       
	        for (Player s : playerList) {
	        	System.out.print(s.getName() + " ");
	        }
	        System.out.println();
	        for (int i = 0; i < 2; i++) {
	        	// Draw two visible resource cards from the resource deck
	            ResourceCard visibleResourceCard = ResourceCard.assignResourceCard();
	            visibleCards.add(visibleResourceCard);
	         
	            
	         // Draw two visible gold card from the gold deck
		        GoldCard visibleGoldCard = GoldCard.assignGoldCard();
		        visibleCards.add(visibleGoldCard);
		       
		      
		        
		     // Draw two visible objective card from the objective deck
		     //   ObjectiveCard comObjectiveCard = ObjectiveCard.drawCard();
		     //   commonObjCard.add(comObjectiveCard);
	        }
	        
	        System.out.println("Ecco le prime carte disponibili: ");
            for (Card r : visibleCards) {
            	System.out.println();
            	r.printCard();
            	System.out.println();
            }

	        
	        
	        
	        for (Player player : playerList) {
	        	player.setPlayArea(matchManuscript.getPlayArea());//non sono sicura dell'effettiva correttezza di questa riga
	            // Draw two resource cards from the resource deck
	            for (int i = 0; i < 2; i++) {
	                Card resourceHandCard = ResourceCard.assignResourceCard();
	                player.addCardToHand(resourceHandCard);
	            }

	            // Draw one gold card from the gold deck
	            Card goldHandCard = GoldCard.assignGoldCard();
	            player.addCardToHand(goldHandCard);

	            
	            // Draw one initial card from the initial deck, choose orientation and place initial card
	            InitialCard initialHandCard = InitialCard.assignInitialCard(); //restituisce la carta nel verso scelto 
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
	     * Method to manage the turn of the player with command line
	     *
	     * @param currentPlayer
	     * @return true if the player has placed his card on manuscript area and picked another one
	     */
	    public boolean turn (Player currentPlayer) {
	    	Scanner scanner = new Scanner(System.in);
	        //boolean continuePlaying = true;
	        Corner choosenCorner = null;
	        Card cardToPlay = null;
	        Card cardToTake = null;

	        System.out.println(" " + currentPlayer.getName());
	        System.out.println();
	        
	     // Player plays a card from their hand and places it on the manuscript
	        //while (continuePlaying) {
	        	matchManuscript = currentPlayer.getPlayArea();
	            matchManuscript.displayPlayArea();
	            //System.out.println("Scegli un'azione:");
	            //System.out.println("1. Posiziona una carta");
	            //System.out.println("2. Termina il turno");

	            //int choice = scanner.nextInt();
	            //scanner.nextLine(); 

	            //switch (choice) {
	                //case 1:
	                	cardToPlay = currentPlayer.chooseCardToPlay();
	                	choosenCorner = matchManuscript.chooseCardInPlayArea(scanner); // Interazione per posizionare una carta
	                    scanner.close();
	                    //break;
	                
	                //case 2:
	                    //continuePlaying = false;
	                    //System.out.println("Fine del turno!");
	                    //return true;
	                    //break;
	                //default:
	                    //System.out.println("Scelta non valida.");
	           // }
	        //}

    	   matchManuscript.placeCard(cardToPlay, choosenCorner); // Chiama il metodo placeCard
    	   matchManuscript.displayPlayArea();
    	   
    	   
    	// Player takes a card from either the resource deck or the visible resource cards or the visible gold card or the gold deck
    	   	do {
    	   		cardToTake = currentPlayer.chooseCardToTake(visibleCards);
    	   		
    	   	}while(cardToTake == null);
            
  
            currentPlayer.addCardToHand(cardToTake);
        

        // Update the visible cards for the next turn
            if (visibleCards.size() == 1) {
            		updatevisibleCards();
            }
            
            if (visibleCards.size() == 1) {
            		updatevisibleCards();
            }
	        
	    
	    

	       
	        //DA FINIRE
	        
	            return true;
	        
	        //return false;
	    }

	    /**
	     * Does the basic work to have a valid input from the user . The valid
	     * inputs are: 'Y', 'Yes', 'yes', 'N', 'No', or 'no'
	     *
	     * @return true if the answer is yes, otherwise false
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
	    

	    public void coordinateInput(ArrayList<Integer> area, int index, int maxIndex) {
	        Integer num = 0;
	        boolean inputFlag = false;
	        do {
	            num = integerInput(0, maxIndex);
	            if (index > 0) {
	                if (!area.get(index - 1).equals(num) && !area.get(index - 1).equals(num - 1)) {
	                    System.out.println("The number must be equal or greater than the previous number");
	                    inputFlag = true;
	                } else {
	                    inputFlag = false;
	                    area.add(num);
	                }
	            } else {
	                inputFlag = false;
	                area.add(num);
	            }
	        } while (inputFlag);

	    }

	    /**
	     * Manages the input of an integer
	     *
	     * @param min minimum of the number in input from the user, included.
	     * @param max maximum of the number in input from the user, excluded.
	     * @return
	     */ //non PENSO MI SERVA 
	    public Integer integerInput(int min, int max) {
	        Integer num = 0;
	        boolean inputFlag = false;
	        //new interaction every time the number is too great, too small or wrong format.

	        do {

	            try {
	                System.out.println("Please insert a number between " + min + ", included and " + max + " excluded");
	                num = sc.nextInt();
	                sc.nextLine();

	            } catch (InputMismatchException e) {
	                System.out.println("Error, the number has to be an integer");

	            }
	        } while (num < min || num >= max);
	        return num;
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
	     * @param genericCards
	     */
	    public void printCardArrayList(ArrayList<Card> genericCards) {
	        //print of the index for every element present in the ArrayList
	        for (int i = 0; i < genericCards.size(); i++) {
	            System.out.print(i + "\t");
	        }
	        System.out.println();
	        //print of the elements contained in the ArrayList
	        for (int i = 0; i < genericCards.size(); i++) {
	            System.out.print(genericCards.get(i) + "\t");
	        }
	        System.out.println();
	    }
	    
	    private void updatevisibleCards() {
	            Card visibleCard = ResourceCard.assignResourceCard();
	            visibleCards.add(visibleCard);
	        }
	    
	    
	    

	    public PlayArea getplayArea() {
	        return matchManuscript;
	    }
}
