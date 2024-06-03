package game;
import cards.Card;


public class Cell {
	private final int x;
	private final int y;
	private State state;
	private Card Card;
	
	/**
	 * Crea una Cella. 
	 * @param x riga nella griglia
	 * @param y colonna nella griglia
	 */
	public Cell( int x, int y)
	{
		if(x<0) {
			throw new IllegalArgumentException("wrong coordinates: x coordinate is less than 0");
		}
		if(y<0) {
			throw new IllegalArgumentException("wrong coordinates: y coordinate is less than 0");
		}
		
		this.x = x;
		this.y = y;
		this.state = State.OCCUPABILE;
		this.Card = null;
	}
	/**
	 * Costruttore Copia
	 * @param Cell
	 */
	public Cell(Cell cella)
	{	
		this.x = cella.x;
		this.y = cella.y;
		this.state = cella.state;
		if(cella.isNotOccupied() && !cella.isEmpty()){
		this.Card = cella.Card;
		}
		else {
			this.Card = null;
		}
	}
	/**
	 * Verifica se la Cella è vuota.
	 * @return vero se lo Cella è vuota
	 */
	public boolean isEmpty()
	{
		if(!this.isNotOccupied()) {
			System.out.println("La cella non è occupabile");
		}
		if(this.Card == null){
			return true;
		}
		return false;
	}
	
	/**
	 * Imposta lo stato della Cella.
	 * @param enum State: Occupabile o non occupabile
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * Se la cella è occupabile, inserisce la carta.
	 * @param Card carta da inserire all'interno della Cella.
	 */
	public void setCard(Card Card) {
		if(!this.isNotOccupied()) {
			System.out.println("La cella non è occupabile");
		}
		this.Card = Card;
	}
	
	/**
	 * Se la cella è occupata restituisce la carta in essa contenuta.
	 */
	public Card getCard() {
		if(!this.isNotOccupied()) {
			System.out.println("La cella non è occupabile");
		}
		if(this.isEmpty()) {
			throw new NullPointerException("Non c'è nessuna carta nella cella");
		}
		return Card;	
	}
	
	/**
	 * Verifica se la cella è occupabile.
	 * @return vero se la Cella è occupabile
	 */
	public boolean isNotOccupied() {
		if(this.state.equals(State.OCCUPABILE)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Restituisce la riga in cui è posizionata la cella nella Griglia
	 */
	public int getX() {
		return x;
	}
	
	/**
	 *	Restituisce la colonna in cui è posizionata la cella nella Griglia 
	 */
	public int getY() {
		return y;
	}
}
