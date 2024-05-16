import java.util.ArrayList;
import java.util.List;

// Definizione della classe CartaObiettivoArtefatti
public class CartaObiettivoArtefatti {
    private int id; // Identificatore univoco della carta
    private int piumeNecessarie; // Numero di piume necessarie per completare l'obiettivo
    private int boccetteNecessarie; // Numero di boccette necessarie per completare l'obiettivo
    private int pergameneNecessarie; // Numero di pergamene necessarie per completare l'obiettivo
    private int punti; // Punti assegnati quando l'obiettivo viene completato

    // Costruttore della classe
    public CartaObiettivoArtefatti(int id, int piumeNecessarie, int boccetteNecessarie, int pergameneNecessarie, int punti) {
        this.id = id;
        this.piumeNecessarie = piumeNecessarie;
        this.boccetteNecessarie = boccetteNecessarie;
        this.pergameneNecessarie = pergameneNecessarie;
        this.punti = punti;
    }

    // Metodi getter per ottenere i valori delle variabili di istanza
    public int getId() {
        return id;
    }

    public int getPiumeNecessarie() {
        return piumeNecessarie;
    }

    public int getBoccetteNecessarie() {
        return boccetteNecessarie;
    }

    public int getPergameneNecessarie() {
        return pergameneNecessarie;
    }

    public int getPunti() {
        return punti;
    }

    // Metodo statico per creare e restituire una lista di carte obiettivo
    public static List<CartaObiettivoArtefatti> creaCarteObiettivo() {
        List<CartaObiettivoArtefatti> carte = new ArrayList<>();

        // Creazione delle carte obiettivo con diversi requisiti e punti
        carte.add(new CartaObiettivoArtefatti(1, 2, 0, 0, 2)); // Carta che richiede 2 piume e assegna 2 punti
        carte.add(new CartaObiettivoArtefatti(2, 0, 2, 0, 2)); // Carta che richiede 2 boccette e assegna 2 punti
        carte.add(new CartaObiettivoArtefatti(3, 0, 0, 2, 2)); // Carta che richiede 2 pergamene e assegna 2 punti
        carte.add(new CartaObiettivoArtefatti(4, 1, 1, 1, 3)); // Carta che richiede una piuma, una boccetta, una pergamena e assegna 3 punti

        return carte;
    }

    // Metodo principale del programma
    public static void main(String[] args) {
        // Creazione delle carte obiettivo
        List<CartaObiettivoArtefatti> CartaObiettivoArtefatti = creaCarteObiettivo();

        // Stampa dei dettagli di ciascuna carta obiettivo
        for (CartaObiettivoArtefatti carta : CartaObiettivoArtefatti) {
            System.out.println("Carta " + carta.getId() + ": Piume necessarie: " + carta.getPiumeNecessarie() +
                    ", Boccette necessarie: " + carta.getBoccetteNecessarie() +
                    ", Pergamene necessarie: " + carta.getPergameneNecessarie() +
                    ", Punti: " + carta.getPunti());
        }
    }
}

