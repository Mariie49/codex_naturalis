import java.util.ArrayList;
import java.util.List;

// Definizione della classe CartaObiettivoSimboli
public class CartaObiettivoSimboli {
    private int id; // Identificatore univoco della carta
    private int simboliViola; // Numero di simboli viola necessari per completare l'obiettivo
    private int simboliAzzurri; // Numero di simboli azzurri necessari per completare l'obiettivo
    private int simboliVerdi; // Numero di simboli verdi necessari per completare l'obiettivo
    private int simboliRossi; // Numero di simboli rossi necessari per completare l'obiettivo
    private int punti; // Punti assegnati quando l'obiettivo viene completato

    // Costruttore della classe
    public CartaObiettivoSimboli(int id, int simboliViola, int simboliAzzurri, int simboliVerdi, int simboliRossi, int punti) {
        this.id = id;
        this.simboliViola = simboliViola;
        this.simboliAzzurri = simboliAzzurri;
        this.simboliVerdi = simboliVerdi;
        this.simboliRossi = simboliRossi;
        this.punti = punti;
    }

    // Metodi getter per ottenere i valori delle variabili di istanza
    public int getId() {
        return id;
    }

    public int getSimboliViola() {
        return simboliViola;
    }

    public int getSimboliAzzurri() {
        return simboliAzzurri;
    }

    public int getSimboliVerdi() {
        return simboliVerdi;
    }

    public int getSimboliRossi() {
        return simboliRossi;
    }

    public int getPunti() {
        return punti;
    }

    // Metodo statico per creare e restituire una lista di carte obiettivo
    public static List<CartaObiettivoSimboli> creaCarteObiettivo() {
        List<CartaObiettivoSimboli> carte = new ArrayList<>();

        // Creazione delle carte obiettivo con diversi requisiti e punti
        carte.add(new CartaObiettivoSimboli(5, 3, 0, 0, 0, 2)); // Carta viola che richiede 3 simboli viola, assegna 2 punti
        carte.add(new CartaObiettivoSimboli(6, 0, 3, 0, 0, 2)); // Carta azzurra che richiede 3 simboli azzurri, assegna 2 punti
        carte.add(new CartaObiettivoSimboli(7, 0, 0, 3, 0, 2)); // Carta verde che richiede 3 simboli verdi, assegna 2 punti
        carte.add(new CartaObiettivoSimboli(8, 0, 0, 0, 3, 2)); // Carta rossa che richiede 3 simboli rossi, assegna 2 punti

        return carte;
    }

    // Metodo principale del programma
    public static void main(String[] args) {
        // Creazione delle carte obiettivo
        List<CartaObiettivoSimboli> carteObiettivo = creaCarteObiettivo();

        // Stampa dei dettagli di ciascuna carta obiettivo
        for (CartaObiettivoSimboli carta : carteObiettivo) {
            System.out.println("Carta " + carta.getId() + ": Simboli viola: " + carta.getSimboliViola() +
                    ", Simboli azzurri: " + carta.getSimboliAzzurri() +
                    ", Simboli verdi: " + carta.getSimboliVerdi() +
                    ", Simboli rossi: " + carta.getSimboliRossi() +
                    ", Punti: " + carta.getPunti());
        }
    }
}

