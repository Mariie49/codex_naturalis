package game;

import java.util.ArrayList;

public class MainGame {

	public static void main(String[] args) {
		 Game match = new Game();
	        ArrayList<Player> players;
	        boolean gameNotEnded = true;
	        match.initGame();
	        

	        players = match.getPlayerList();

	        do {
	            for (Player currentPlayer : players) {
	                if (match.turn(currentPlayer)) {
	                    gameNotEnded = false;

	                }
	            }
	            
	        } while (gameNotEnded);
	        players = match.rankings();
	        for (Player currentPlayer : players) {
	            System.out.println("The player " + currentPlayer.getName() + " with a score of " + currentPlayer.totalPoints());
	        }
	        
	        System.out.println("The winner is " + players.get(0).getName() + " with a score of " + players.get(0).totalPoints());


	    }

	}


