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
	            System.out.println("Il giocatore " + currentPlayer.getName() + " ha raggiunto un punteggio di  " + currentPlayer.totalPoints());
	        }
	        
	        System.out.println("Il vincitore " + players.get(0).getName() + " con un punteggio di  " + players.get(0).totalPoints());


	    }

	}


