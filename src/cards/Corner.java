package cards;

public class Corner {

	private int numCorner;
	private CornerState state;
	private Object symbol;
	
	public Corner() {}
	
	 public Corner(int numCorner, CornerState state, Object symbol) {
		 if (numCorner < 1 || numCorner > 4) {
	            throw new IllegalArgumentException("Il numero del corner deve essere tra 1 e 4");
	        }
	        if (state == CornerState.SYMBOL && !(symbol instanceof Symbol)) {
	            throw new IllegalArgumentException("Quando lo stato è 'SYMBOL', symbol deve essere un'istanza di Symbol");
	        }
	        if (state == CornerState.SPECIALSYMBOL && !(symbol instanceof SpecialSymbol)) {
	            throw new IllegalArgumentException("Quando lo stato è 'SPECIALSYMBOL', symbol deve essere un'istanza di SpecialSymbol");
	        }
	        if ((state == CornerState.EMPTY || state == CornerState.NULL) && symbol != null) {
	            throw new IllegalArgumentException("Quando lo stato è 'EMPTY' o 'NULL', symbol deve essere null");
	        }

	        this.numCorner = numCorner;
	        this.state = state;
	        this.symbol = symbol;
	     
	    }

		/**
	 * @return the numCorner
	 */
	public int getNumCorner() {
		return numCorner;
	}

	/**
	 * @return the state
	 */
	public CornerState getState() {
		return state;
	}

	/**
	 * @return the symbol
	 */
	public Object getSymbol() {
		return symbol;
	}

	/**
	 * @param numCorner the numCorner to set
	 */
	public void setNumCorner(int numCorner) {
		this.numCorner = numCorner;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(CornerState state) {
		this.state = state;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(Object symbol) {
		this.symbol = symbol;
	}

		@Override
	    public String toString() {
	        return "Corner(number=" + numCorner + ", state=" + state + ", symbol=" + symbol + ")";
	    }
}

