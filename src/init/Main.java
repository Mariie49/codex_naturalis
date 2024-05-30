package init;

import cards.Card;
import cards.Corner;
import cards.CornerState;
import cards.Symbol;
import cards.SpecialSymbol;
import java.util.Random;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Corner> corners = new ArrayList<>();
		ArrayList<Card> deck = new ArrayList<>();
		Random random = new Random();

        // Creare e distribuire i Corner alle 80 carte
        for (int i = 0; i <2; i++) {
      
            
            for (int j = 1; j <= 4; j++) {
                CornerState state;
                Object symbol = null;

                int statePicker = random.nextInt(4); // Scegliere uno stato casualmente
                switch (statePicker) {
                    case 0:
                        state = CornerState.EMPTY;
                        break;
                    case 1:
                        state = CornerState.SYMBOL;
                        symbol = Symbol.values()[random.nextInt(Symbol.values().length)];
                        break;
                    case 2:
                        state = CornerState.SPECIALSYMBOL;
                        symbol = SpecialSymbol.values()[random.nextInt(SpecialSymbol.values().length)];
                        break;
                    case 3:
                    	default:
                    		state = CornerState.NULL;
                    		break;
                    	
                }                
                corners.add(new Corner(j, state, symbol));
            }
            
        }

        deck.add(new Card(corners));

        // Stampa le carte per verifica
        for (Card card : deck) {
            System.out.println(deck);
        }
		
		/*PRIMA PROVA
		Corner corner1 = new Corner(1, CornerState.SYMBOL, Symbol.LEAF);
		Corner corner2 = new Corner(2, CornerState.EMPTY,);
		Corner corner3 = new Corner(3, CornerState.SPECIALSYMBOL,SpecialSymbol.INKWELL);
		Corner corner4 = new Corner(3, CornerState.NULL,);
		
		
		System.out.println("I corner sono:\n\n"+ corner1.toString() + "\n" + corner2.toString() + "\n" +corner3.toString() + "\n" +corner4.toString());
		*/
	}

}
