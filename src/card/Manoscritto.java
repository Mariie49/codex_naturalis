package card;

import java.util.ArrayList;

public class Manoscritto {

    private ArrayList<ArrayList<GoldCard>> matrice;

    public Manoscritto(int numRows, int numColumns) {
        matrice = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            ArrayList<GoldCard> row = new ArrayList<>(numColumns); //controllo angolo piazzabile e se carta oro controllo se ci sono richieste per piazzare
            for (int j = 0; j < numColumns; j++) {
                row.add(null); // Inizialmente nessuna carta è presente nella cella
            }
            matrice.add(row);
        }
    }

    public void aggiungiCarta(GoldCard carta, int riga, int colonna) {
        // Assicurati che la riga e la colonna siano valide
        if (riga >= 0 && riga < matrice.size() && colonna >= 0 && colonna < matrice.get(riga).size()) {
            // Aggiungi la carta alla posizione specificata
            matrice.get(riga).set(colonna, carta); //quando vengono create le colonne la carta iniziale ha coordinata (0,0) e la matrice si comporta come se fosse un asse cartesiano, andando a sinistra si ha -1, come andando verso il basso
        }
    }

    public GoldCard getCarta(int riga, int colonna) { //si puo togliere
        // Assicurati che la riga e la colonna siano valide
        if (riga >= 0 && riga < matrice.size() && colonna >= 0 && colonna < matrice.get(riga).size()) {
            return matrice.get(riga).get(colonna);
        }
        return null;
    }

    public void stampaMatrice() {
        for (ArrayList<GoldCard> riga : matrice) {
            for (GoldCard carta : riga) {
                if (carta != null) {
                    // Stampa le informazioni della carta
                    System.out.print(carta.getSpecialSymbol() + " "); // Ad esempio, puoi stampare il simbolo speciale
                } else {
                    System.out.print("NULL ");
                }
            }
            System.out.println();
        }
    }
}
