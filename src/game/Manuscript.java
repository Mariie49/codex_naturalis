package game;

import java.util.ArrayList;
import card.CardType;
import card.Corner;
import card.GoldCard;

public class Manuscript {

    private ArrayList<ArrayList<GoldCard>> matrix;

    public Manuscript(int numRows, int numColumns) {
        // Inizializza la matrice con il numero specificato di righe e colonne
        matrix = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            ArrayList<GoldCard> row = new ArrayList<>(numColumns);
            for (int j = 0; j < numColumns; j++) {
                row.add(null); // Inizialmente nessuna carta è presente nella cella
            }
            matrix.add(row);
        }
    }

    public void addCard(GoldCard card, int rows, int columns, int corner) {
        // Assicurati che la riga e la colonna siano valide
        if (rows >= 0 && rows < matrix.size() && columns >= 0 && columns < matrix.get(rows).size()) {
            // Controlla se l'angolo specificato è adiacente a una carta già piazzata
            if (!nearCardsControl(rows, columns)) {
                // La carta può essere piazzata solo adiacente a una carta già piazzata
                System.out.println("La carta può essere piazzata solo adiacente a una carta già piazzata.");
                return;
            }

            // Controlla se l'angolo specificato è piazzabile
            if (!cornerMatching(card, rows, columns, corner)) {
                // Angolo non piazzabile
                System.out.println("Angolo non piazzabile.");
                return;
            }

            // Controllo dei requisiti delle carte gold
            if (card.getType() == CardType.GOLD && !requirementVerified(card)) {
                // Requisiti della GoldCard non soddisfatti
                System.out.println("Requisiti della GoldCard non soddisfatti.");
                return;
            }

            // Aggiungi la carta alla posizione specificata
            matrix.get(rows).set(columns, card);
        }
    }

    private boolean nearCardsControl(int row, int column) {
        // Controlla se almeno una carta è già piazzata nelle celle adiacenti
        return (row > 0 && matrix.get(row - 1).get(column) != null) || // sopra
               (row < matrix.size() - 1 && matrix.get(row + 1).get(column) != null) || // sotto
               (column > 0 && matrix.get(row).get(column - 1) != null) || // a sinistra
               (column < matrix.get(row).size() - 1 && matrix.get(row).get(column + 1) != null) || // a destra
               (row > 0 && column > 0 && matrix.get(row - 1).get(column - 1) != null) || // diagonale in alto a sinistra
               (row > 0 && column < matrix.get(row - 1).size() - 1 && matrix.get(row - 1).get(column + 1) != null) || // diagonale in alto a destra
               (row < matrix.size() - 1 && column > 0 && matrix.get(row + 1).get(column - 1) != null) || // diagonale in basso a sinistra
               (row < matrix.size() - 1 && column < matrix.get(row + 1).size() - 1 && matrix.get(row + 1).get(column + 1) != null); // diagonale in basso a destra
    }


    private boolean cornerMatching(GoldCard card, int row, int column, int corner) {
        // Ottieni l'angolo specificato
        Corner gCorner = card.getFrontCorners().get(corner);

        // Controlla se l'angolo è vuoto o contiene un simbolo speciale
        if (gCorner.isEmpty() || gCorner.isSpecialSymbol()) {
            // Controlla se l'angolo può essere piazzato sulla matrice
            // Verifica le posizioni adiacenti
            return (row > 0 && matrix.get(row - 1).get(column) != null) || // sopra
                   (row < matrix.size() - 1 && matrix.get(row + 1).get(column) != null) || // sotto
                   (column > 0 && matrix.get(row).get(column - 1) != null) || // a sinistra
                   (column < matrix.get(row).size() - 1 && matrix.get(row).get(column + 1) != null); // a destra
        }
        return false;
    }

    private boolean requirementVerified(GoldCard carta) {
        // Implementa la logica per verificare se ci sono le risorse necessarie per piazzare la carta
        // Attualmente, restituisce sempre true. Questo metodo deve essere esteso per controllare i requisiti effettivi.
        return true;
    }

    public GoldCard getCard(int riga, int colonna) {
        // Assicurati che la riga e la colonna siano valide
        if (riga >= 0 && riga < matrix.size() && colonna >= 0 && colonna < matrix.get(riga).size()) {
            return matrix.get(riga).get(colonna);
        }
        return null;
    }

    public void printMatrix() {
        // Stampa la matrice delle carte
        for (ArrayList<GoldCard> row : matrix) {
            for (GoldCard card : row) {
                if (card != null) {
                    // Stampa le informazioni della carta
                  //  System.out.print(.getSpecialSymbol() + " ");
                } else {
                    System.out.print("NULL ");
                }
            }
            System.out.println();
        }
    }
}

