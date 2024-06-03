package game;

import java.util.ArrayList;

import cards.Card;
import cards.CardType;
import cards.Corner;
import cards.CornerPosition;
import cards.CornerState;
import initialCard.InitialCard;

public class PlayArea {
	protected final int rows;
	protected final int columns;
	protected final Cell[][] matrix;
	public ArrayList <Card> placedCards;

	/**
	 * Crea una Griglia con righe e colonne specificate
	 * @param rows
	 * @param columns
	 */
	public PlayArea(int rows, int columns) {

		if(rows < 0)
			throw new IllegalArgumentException("number of rows must be greater than or equal to 0");

		if(columns < 0)
			throw new IllegalArgumentException("number of columns must be greater than or equal to 0");

		this.rows=rows;
		this.columns=columns;
		this.matrix= new Cell[rows][columns];
		placedCards = new ArrayList<>();
		fillMatWithCells();
	}

	private void fillMatWithCells()
	{	
		for(int x = 0; x < this.rows; x++)
		{
			for(int y = 0; y < this.columns; y++)
			{
				Cell Cell = new Cell(x,y);
				matrix[x][y] = Cell;
			}
		}
	}

	/**
	 * Inserisce la carta iniziale al centro della matrice.
	 *
	 * @param initialCard La carta iniziale da piazzare.
	 * @throws IllegalArgumentException Se la carta iniziale non è di tipo starting.
	 */
	public void placeInitialCard(InitialCard initialCard) {
		if (initialCard.getType() != CardType.STARTING) { //penso che non serva
			throw new IllegalArgumentException("The initial card must be of the STARTING type.");
		}
		// Calcola le coordinate del centro della griglia
		int centerX = this.rows / 2;
		int centerY = this.columns / 2;
		System.out.println("La carta iniziale è posizionata in: x = " + centerX);
		System.out.println("La carta iniziale è posizionata in: y = " + centerY);
		Cell centerCell = matrix[centerX][centerY];
		

		// Inserisci la carta al centro
		centerCell.setCard(initialCard);
		centerCell.setState(State.NON_OCCUPABILE);
		initialCard.setPlaced(true);
		placedCards.add(initialCard);

	}

	/**
	 * Posiziona una carta nel manoscritto in diagonale.
	 *
	 * @param card    La carta da posizionare
	 * @param x       La coordinata x 
	 * @param y       La coordinata y 
	 * @throws IllegalArgumentException Se la posizione non è valida, la cella non è occupabile,
	 *                                  o la carta non rispetta la regola di adiacenza diagonale.
	 */
	public void placeCardInManuscript(Card card, int x, int y) {
		// 1. Validazione delle coordinate
		if (x < 0 || x >= this.rows || y < 0 || y >= this.columns) {
			throw new IllegalArgumentException("Posizione non valida nella griglia.");
		}

		Cell cell = matrix[x][y];

		// 2. Verifica se la cella è occupabile
		if (!cell.isNotOccupied()) {
			throw new IllegalArgumentException("La cella selezionata non è occupabile.");
		}


		// 4. Verifica adiacenza diagonale (regola principale)
		if (!hasDiagonalAdjacentCard(x, y)) {
			throw new IllegalArgumentException("La carta deve essere adiacente in diagonale ad un'altra carta.");
		}


		// 5. Posiziona la carta
		cell.setCard(card);
		cell.setState(State.NON_OCCUPABILE);
		card.setPlaced(true);
		// 5. Aggiorna gli stati dei corner
	    updateCornerStates(x, y);
		placedCards.add(card);
		 
	}
	
	/**
     * Restituisce la lista di celle adiacenti in diagonale e occupabili rispetto alle carte già posizionate.
     *
     * @return Una lista di oggetti Cell che rappresentano le celle disponibili.
     */
    public ArrayList<Cell> getAvailableDiagonalCells() {
        ArrayList<Cell> availableCells = new ArrayList<>();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (matrix[x][y].isNotOccupied() && x > 0 && x < rows - 1 && y > 0 && y < columns - 1 &&hasDiagonalAdjacentCard(x, y)) {
                    availableCells.add(matrix[x][y]);
                }
            }
        }
        return availableCells;
    }



	/**
	 * Controlla se una cella ha carte adiacenti in diagonale.
	 *
	 * @param x La coordinata x della cella 
	 * @param y La coordinata y della cella 
	 * @return true se la cella ha almeno una carta adiacente in diagonale, false altrimenti.
	 */
    private boolean hasDiagonalAdjacentCard(int x, int y) {
        Cell cellTopLeft = null;
        Cell cellTopRight = null;
        Cell cellBottomLeft = null;
        Cell cellBottomRight = null;

        if (x > 0 && y > 0) {
            cellTopLeft = matrix[x - 1][y - 1];
            if (!cellTopLeft.isEmpty()) {
                return true;
            }
        }

        if (x > 0 && y < columns - 1) {
            cellTopRight = matrix[x - 1][y + 1];
            if (!cellTopRight.isEmpty()) {
                return true;
            }
        }

        if (x < rows - 1 && y > 0) {
            cellBottomLeft = matrix[x + 1][y - 1];
            if (!cellBottomLeft.isEmpty()) {
                return true;
            }
        }

        if (x < rows - 1 && y < columns - 1) {
            cellBottomRight = matrix[x + 1][y + 1];
            if (!cellBottomRight.isEmpty()) {
                return true;
            }
        }

        return false; 
    }
	/**
     * Aggiorna gli stati dei corner delle carte adiacenti in diagonale alla carta appena posizionata.
     * I corner che sono coperti dalla nuova carta vengono impostati allo stato HIDDEN.
     *
     * @param x La coordinata x della carta appena posizionata.
     * @param y La coordinata y della carta appena posizionata.
     */
	public void updateCornerStates(int x, int y) {
	    for (int dx = -1; dx <= 1; dx += 2) {
	        for (int dy = -1; dy <= 1; dy += 2) {
	            int diagX = x + dx;
	            int diagY = y + dy;

	            // Verifica se le coordinate sono valide e la cella contiene una carta
	            if (diagX >= 0 && diagX < rows &&
	                diagY >= 0 && diagY < columns &&
	                !matrix[diagX][diagY].isEmpty()) {
	                
	                Card inDiagCard = matrix[diagX][diagY].getCard();
	                CornerPosition coveredCorner = getCoveredCorner(dx, dy);

	                // Imposta lo stato del corner a HIDDEN
	                for (Corner corner : inDiagCard.addCorners()) {
	                    if (corner.getPosition() == coveredCorner) {
	                        corner.setState(CornerState.HIDDEN);
	                        corner.setCovered(true);
	                        break; 
	                    }
	                }
	            }
	        }
	    }
	}
	/**
     * Determina quale corner di una carta adiacente viene coperto in base alla posizione relativa
     * rispetto alla carta appena posizionata.
     *
     * @param dx La differenza in x tra le due carte (-1 per sinistra, +1 per destra).
     * @param dy La differenza in y tra le due carte (-1 per sopra, +1 per sotto).
     * @return La posizione del corner coperto (TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT).
     */
    private CornerPosition getCoveredCorner(int dx, int dy) {
        if (dx == -1 && dy == -1) return CornerPosition.BOTTOM_RIGHT;
        if (dx == -1 && dy == 1) return CornerPosition.TOP_RIGHT;
        if (dx == 1 && dy == -1) return CornerPosition.BOTTOM_LEFT;
        return CornerPosition.TOP_LEFT; // dx == 1 && dy == 1
    }

	/**
	 * Restituisce il numero delle righe della Griglia
	 */
	public int getRows() {
		return this.rows;
	}
	/**
	 * Restituisce il numero delle colonne della Griglia
	 */
	public int getColumns() {
		return this.columns;
	}
	/**
	 * Restituisce una copia della matrice di Celle della PlayArea
	 */
	public Cell[][] getPlayArea() {
		Cell[][] copyMatrix = new Cell[this.rows][this.columns];
		for(int row = 0; row < this.rows;row++)
		{
			for(int column = 0; column < this.columns; column++)
			{
				Cell copyCell = new Cell(this.getCell(row, column));
				copyMatrix[row][column] = copyCell;
			}
		}
		return copyMatrix;
	}
	/**
	 * Restituisce la cella avente le coordinate fornite come parametri
	 * @param x
	 * @param y
	 */
	public Cell getCell(int x,int y)
	{
		return matrix[x][y];
	}
	/**
	 * Restituisce la cella in cui si trova una specifica carta
	 * @param Card
	 * @return null se la tessera non si trova nella Griglia
	 */
	public Cell getCell(Card Card)
	{
		if(Card == null)
			throw new NullPointerException("Card is null");

		for(int x = 0; x < this.rows; x++)
		{	
			for(int y = 0; y < this.columns ; y++)
			{
				if(matrix[x][y].isNotOccupied() && !matrix[x][y].isEmpty())
				{
					if(matrix[x][y].getCard().getNumber() == Card.getNumber())
						return matrix[x][y];
				}
			}
		}
		return null;
	}
	/**
	 * Restituisce la carta se contenuta nella Griglia
	 * @param cardNumer della carta
	 * @return null se la carta non è presente
	 */
	public Card contains(int cardNumber, CardType type)

	{
		for(int x = 0; x < this.rows; x++)
		{	
			for(int y = 0; y < this.columns ; y++)
			{
				if(matrix[x][y].isNotOccupied() && !matrix[x][y].isEmpty())
				{
					if(matrix[x][y].getCard().getNumber() == cardNumber && matrix[x][y].getCard().getType() == type)
						return matrix[x][y].getCard();
				}
			}
		}
		return null;
	}
	/**
	 * Restituisce il numero di Celle vuote
	 * @param column
	 */
	public int numberOfEmptyCell(int column)
	{
		if(column < 0 || column >= this.columns) {
			throw new IndexOutOfBoundsException("This column doesn't exit");
		}

		int count = 0;
		for(int i =0; i < this.rows; i++)
		{
			if(matrix[i][column].isEmpty()) {
				count++;
			}
		}
		return count;
	}

	 

	/**
	 * Controlla se la carta specificata ha carte adiacenti in diagonale.
	 *
	 * @param currentCard La carta di cui verificare l'adiacenza diagonale.
	 * @return true se la carta ha almeno una carta adiacente in diagonale, false altrimenti.
	 * @throws NullPointerException se la carta passata è nulla.
	 */
	public boolean hasDiagonalAdjacent(Card currentCard) {
		if (currentCard == null) {
			throw new NullPointerException("La carta è nulla");
		}

		Cell currentCell = this.getCell(currentCard);
		int x = currentCell.getX();
		int y = currentCell.getY();

		// Controllo adiacenze diagonali
		if (x > 0 && y > 0) {
			if (this.matrix[x - 1][y - 1].isNotOccupied() && !this.matrix[x - 1][y - 1].isEmpty()) {
				return true;
			}
		}
		if (x > 0 && y < (this.columns - 1)) {
			if (this.matrix[x - 1][y + 1].isNotOccupied() && !this.matrix[x - 1][y + 1].isEmpty()) {
				return true;
			}
		}
		if (x < (this.rows - 1) && y > 0) {
			if (this.matrix[x + 1][y - 1].isNotOccupied() && !this.matrix[x + 1][y - 1].isEmpty()) {
				return true;
			}
		}
		if (x < (this.rows - 1) && y < (this.columns - 1)) {
			if (this.matrix[x + 1][y + 1].isNotOccupied() && !this.matrix[x + 1][y + 1].isEmpty()) {
				return true;
			}
		}

		return false; 
	}

	/**
	 * Controlla se ci sono carte adiacenti in diagonale nella griglia.
	 *
	 * @return true se esiste almeno una coppia di carte adiacenti in diagonale, false altrimenti.
	 */
	public boolean hasDiagonalAdjacent() {
		for (int row = 0; row < this.rows; row++) {
			for (int column = 0; column < this.columns; column++) {
				if (matrix[row][column].isNotOccupied() && !matrix[row][column].isEmpty()) {
					if (hasDiagonalAdjacent(matrix[row][column].getCard())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Verifica se due carte sono adiacenti in diagonale.
	 *
	 * @param card1 La prima carta da verificare.
	 * @param card2 La seconda carta da verificare.
	 * @return true se le carte sono adiacenti in diagonale, false altrimenti.
	 * @throws NullPointerException se una delle due carte non è presente nella griglia.
	 */
	public boolean areDiagonallyAdjacent(Card card1, Card card2) {
		Cell cell1 = this.getCell(card1);
		Cell cell2 = this.getCell(card2);

		if (cell1 == null) {
			throw new NullPointerException("La griglia non contiene la carta 1");
		}
		if (cell2 == null) {
			throw new NullPointerException("La griglia non contiene la carta 2");
		}

		int deltaX = Math.abs(cell1.getX() - cell2.getX());
		int deltaY = Math.abs(cell1.getY() - cell2.getY());

		return deltaX == 1 && deltaY == 1;
	}

	/**
	 *  verifica se tre carte sono adiacenti in diagonali
	 * @param card1
	 * @param card2
	 * @param card3
	 * @return vero se le 3 carte sono adiacenti in diagonale
	 */
	public boolean areDiagonallyAdjacent (Card card1, Card card2,Card card3)
	{
		Cell cell1 = this.getCell(card1);
		Cell cell2 = this.getCell(card2);
		Cell cell3 = this.getCell(card3);

		if(cell1 == null)
			throw new NullPointerException("La griglia non contiene la carta 1");
		if(cell2 == null)
			throw new NullPointerException("La griglia non contiene la carta 2");
		if(cell3 == null)
			throw new NullPointerException("La griglia non contiene la carta 3");

		int deltaX12 = Math.abs(cell1.getX() - cell2.getX());
		int deltaY12 = Math.abs(cell1.getY() - cell2.getY());
		int deltaX23 = Math.abs(cell2.getX() - cell3.getX());
		int deltaY23 = Math.abs(cell2.getY() - cell3.getY());

		return (deltaX12 == 1 && deltaY12 == 1 && deltaX23 == 1 && deltaY23 == 1) ||
				(deltaX12 == 1 && deltaY12 == 1 && deltaX12 == deltaX23 && deltaY12 == deltaY23);
	}
	/**
	 * Verifica se la carta ha un lato libero
	 * @param currentCard
	 * @return true se ha almeno un lato libero
	 */
	protected boolean hasFreeSide(Card currentCard) {
		Cell currentCell = this.getCell(currentCard);
		if (currentCell == null) {
			throw new NullPointerException("La griglia non contiene la carta");
		}

		int x = currentCell.getX();
		int y = currentCell.getY();


		Cell cell1 = (x > 0) ? matrix[x - 1][y] : null;
		Cell cell2 = (y > 0) ? matrix[x][y - 1] : null;
		Cell cell3 = (x < this.rows - 1) ? matrix[x + 1][y] : null;
		Cell cell4 = (y < this.columns - 1) ? matrix[x][y + 1] : null;


		if (cell1 != null && (cell1.isNotOccupied() || cell1.isEmpty())) {
			return true;
		}
		if (cell2 != null && (cell2.isNotOccupied() || cell2.isEmpty())) {
			return true;
		}
		if (cell3 != null && (cell3.isNotOccupied() || cell3.isEmpty())) {
			return true;
		}
		if (cell4 != null && (cell4.isNotOccupied() || cell4.isEmpty())) {
			return true;
		}

		return false; 
	}

	/**
	 * Verifica se due carte sono posizionate verticalmente l'una rispetto all'altra
	 * @param card1
	 * @param card2
	 * @return true se sono allineate verticalmente
	 */
	protected boolean areVerticallyAligned(Card card1, Card card2) {
		Cell cell1 = this.getCell(card1);
		Cell cell2 = this.getCell(card2);

		if(cell1 == null)
			throw new NullPointerException("La griglia non contiene la carta 1");
		if(cell2 == null)
			throw new NullPointerException("La griglia non contiene la carta 2");

		return cell1.getY() == cell2.getY();
	}
	public void printManuscript() {
	    // Larghezza fissa considerando 3 simboli centrali
	    int maxCardWidth = 21; 

	    // Intestazione con indici di colonna
	    System.out.print("   ");
	    for (int y = 0; y < columns; y++) {
	        System.out.printf("%-" + (maxCardWidth + 2) + "d", y);
	    }
	    System.out.println();

	    // Stampa delle righe con indici di riga
	    for (int x = 0; x < rows; x++) {
	        System.out.printf("%-2d", x); // Indice di riga

	        // Stampa separatore orizzontale superiore
	        System.out.print(" ");
	        for (int y = 0; y < columns; y++) {
	            System.out.print(" ".repeat(maxCardWidth + 1) + " ");
	        }
	        System.out.println(); // A capo

	        // Stampa contenuto delle celle
	        for (int y = 0; y < columns; y++) {
	            Cell cell = matrix[x][y];
	            System.out.print(" "); // Separatore verticale
	            if (cell.isEmpty()) {
	                System.out.print(" ".repeat(maxCardWidth)); 
	            } else {
	                cell.getCard().printCardInCell();
	                int padding = maxCardWidth - 21;
	                System.out.print(" ".repeat(padding));
	            }
	        }
	        System.out.println(" "); // Separatore verticale finale e a capo
	    }

	    // Stampa separatore orizzontale inferiore
	    System.out.print(" ");
	    for (int y = 0; y < columns; y++) {
	        System.out.print(" ".repeat(maxCardWidth + 1) + " ");
	    }
	    System.out.println(); // A capo finale
	}

}
