package card;

import java.util.ArrayList;

public class Manoscritto {

    private ArrayList<ArrayList<GoldCard>> matrice;

    public Manoscritto(int numRows, int numColumns) {
        // Inizializza la matrice con il numero specificato di righe e colonne
        matrice = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            ArrayList<GoldCard> row = new ArrayList<>(numColumns);
            for (int j = 0; j < numColumns; j++) {
                row.add(null); // Inizialmente nessuna carta è presente nella cella
            }
            matrice.add(row);
        }
    }

    public void aggiungiCarta(GoldCard carta, int riga, int colonna, int angolo) {
        // Assicurati che la riga e la colonna siano valide
        if (riga >= 0 && riga < matrice.size() && colonna >= 0 && colonna < matrice.get(riga).size()) {
            // Controlla se l'angolo specificato è adiacente a una carta già piazzata
            if (!isAdiacenteACartaPiazzata(riga, colonna)) {
                // La carta può essere piazzata solo adiacente a una carta già piazzata
                System.out.println("La carta può essere piazzata solo adiacente a una carta già piazzata.");
                return;
            }

            // Controlla se l'angolo specificato è piazzabile
            if (!isAngoloPiazzabile(carta, riga, colonna, angolo)) {
                // Angolo non piazzabile
                System.out.println("Angolo non piazzabile.");
                return;
            }

            // Controllo dei requisiti delle carte gold
            if (carta.getType() == CardType.GOLD && !verificaRequisiti(carta)) {
                // Requisiti della GoldCard non soddisfatti
                System.out.println("Requisiti della GoldCard non soddisfatti.");
                return;
            }

            // Aggiungi la carta alla posizione specificata
            matrice.get(riga).set(colonna, carta);
        }
    }

    private boolean isAdiacenteACartaPiazzata(int riga, int colonna) {
        // Controlla se almeno una carta è già piazzata nelle celle adiacenti
        return (riga > 0 && matrice.get(riga - 1).get(colonna) != null) || // sopra
               (riga < matrice.size() - 1 && matrice.get(riga + 1).get(colonna) != null) || // sotto
               (colonna > 0 && matrice.get(riga).get(colonna - 1) != null) || // a sinistra
               (colonna < matrice.get(riga).size() - 1 && matrice.get(riga).get(colonna + 1) != null) || // a destra
               (riga > 0 && colonna > 0 && matrice.get(riga - 1).get(colonna - 1) != null) || // diagonale in alto a sinistra
               (riga > 0 && colonna < matrice.get(riga - 1).size() - 1 && matrice.get(riga - 1).get(colonna + 1) != null) || // diagonale in alto a destra
               (riga < matrice.size() - 1 && colonna > 0 && matrice.get(riga + 1).get(colonna - 1) != null) || // diagonale in basso a sinistra
               (riga < matrice.size() - 1 && colonna < matrice.get(riga + 1).size() - 1 && matrice.get(riga + 1).get(colonna + 1) != null); // diagonale in basso a destra
    }


    private boolean isAngoloPiazzabile(GoldCard carta, int riga, int colonna, int angolo) {
        // Ottieni l'angolo specificato
        Corner corner = carta.getFrontCorners().get(angolo);

        // Controlla se l'angolo è vuoto o contiene un simbolo speciale
        if (corner.isEmpty() || corner.isSpecialSymbol()) {
            // Controlla se l'angolo può essere piazzato sulla matrice
            // Verifica le posizioni adiacenti
            return (riga > 0 && matrice.get(riga - 1).get(colonna) != null) || // sopra
                   (riga < matrice.size() - 1 && matrice.get(riga + 1).get(colonna) != null) || // sotto
                   (colonna > 0 && matrice.get(riga).get(colonna - 1) != null) || // a sinistra
                   (colonna < matrice.get(riga).size() - 1 && matrice.get(riga).get(colonna + 1) != null); // a destra
        }
        return false;
    }

    private boolean verificaRequisiti(GoldCard carta) {
        // Implementa la logica per verificare se ci sono le risorse necessarie per piazzare la carta
        // Attualmente, restituisce sempre true. Questo metodo deve essere esteso per controllare i requisiti effettivi.
        return true;
    }

    public GoldCard getCarta(int riga, int colonna) {
        // Assicurati che la riga e la colonna siano valide
        if (riga >= 0 && riga < matrice.size() && colonna >= 0 && colonna < matrice.get(riga).size()) {
            return matrice.get(riga).get(colonna);
        }
        return null;
    }

    public void stampaMatrice() {
        // Stampa la matrice delle carte
        for (ArrayList<GoldCard> riga : matrice) {
            for (GoldCard carta : riga) {
                if (carta != null) {
                    // Stampa le informazioni della carta
                    System.out.print(carta.getSpecialSymbol() + " ");
                } else {
                    System.out.print("NULL ");
                }
            }
            System.out.println();
        }
    }
}

