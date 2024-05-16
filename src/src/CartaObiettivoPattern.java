import java.util.ArrayList;
import java.util.List;

public class CartaObiettivoPattern {
    public static final int TIPO_PATTERN_ROSSO_BLU = 1;
    public static final int TIPO_PATTERN_VERDE_VIOLA = 2;

    private int id;
    private int tipoPattern;
    private int punti;
    private int rosso;
    private int blu;
    private int verde;
    private int viola;

    public CartaObiettivoPattern(int id, int tipoPattern, int punti, int rosso, int blu, int verde, int viola) {
        this.id = id;
        this.tipoPattern = tipoPattern;
        this.punti = punti;
        this.rosso = rosso;
        this.blu = blu;
        this.verde = verde;
        this.viola = viola;
    }

    public int getId() {
        return id;
    }

    public int getTipoPattern() {
        return tipoPattern;
    }

    public int getPunti() {
        return punti;
    }

    public int getRosso() {
        return rosso;
    }

    public int getBlu() {
        return blu;
    }

    public int getVerde() {
        return verde;
    }

    public int getViola() {
        return viola;
    }

    public static List<CartaObiettivoPattern> creaCarteObiettivoPattern() {
        List<CartaObiettivoPattern> carte = new ArrayList<>();

        // Creazione delle carte obiettivo pattern
        // Il primo pattern ha una disposizione diagonale con due carte che occupano l'angolo in basso a sinistra
        // e quello in alto a destra della carta centrale
        carte.add(new CartaObiettivoPattern(9, TIPO_PATTERN_ROSSO_BLU, 2, 3, 0, 0, 0)); //Richiede 3 carte rosse
        carte.add(new CartaObiettivoPattern(10, TIPO_PATTERN_ROSSO_BLU, 2, 0, 3, 0, 0));//Richiede 3 carte blu
        carte.add(new CartaObiettivoPattern(13, TIPO_PATTERN_ROSSO_BLU, 2, 2, 1, 0, 0)); //Richiede 2 carte rosse e 1 blu
        carte.add(new CartaObiettivoPattern(14, TIPO_PATTERN_ROSSO_BLU, 2, 1, 2, 0, 0));//Richiede 2 carte blu e 1 rossa

        // Il secondo pattern ha una disposizione diagonale con due carte che occupano l'angolo in alto a sinistra
        // e quello in basso a destra della carta centrale
        carte.add(new CartaObiettivoPattern(11, TIPO_PATTERN_VERDE_VIOLA, 2, 3, 0, 3, 0));//Richiede 3 carte verdi
        carte.add(new CartaObiettivoPattern(12, TIPO_PATTERN_VERDE_VIOLA, 2, 0, 0, 0, 3));//Richiede 3 carte viola
        carte.add(new CartaObiettivoPattern(11, TIPO_PATTERN_VERDE_VIOLA, 3, 3, 0, 2, 1));//Richiede 2 carte verdi e 1 viola
        carte.add(new CartaObiettivoPattern(12, TIPO_PATTERN_VERDE_VIOLA, 3, 0, 0, 1, 2));//Richiede 2 carte viola e 1 verde

        return carte;
    }
//manca il controllo per il posizionamento dell'angolo
    public static void main(String[] args) {
        List<CartaObiettivoPattern> carteObiettivoPattern = creaCarteObiettivoPattern();

        for (CartaObiettivoPattern carta : carteObiettivoPattern) {
            String pattern;
            if (carta.getTipoPattern() == TIPO_PATTERN_ROSSO_BLU) {
                pattern = "Rosso e Blu";
            } else {
                pattern = "Verde e Viola";
            }
            System.out.println("Carta " + carta.getId() + ": Pattern: " + pattern +
                    ", Punti: " + carta.getPunti() +
                    ", Rosso: " + carta.getRosso() +
                    ", Blu: " + carta.getBlu() +
                    ", Verde: " + carta.getVerde() +
                    ", Viola: " + carta.getViola());
        }
    }
}
