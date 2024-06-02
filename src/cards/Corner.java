package cards;

public class Corner {

	private CornerPosition position;
	private CornerState state;
	private Object symbol;
	private Card card;
	
	public Corner() {}
	
	 public Corner(CornerPosition position, CornerState state, Object symbol) {
	        if (state == CornerState.SYMBOL && !(symbol instanceof Symbol)) {
	            throw new IllegalArgumentException("Quando lo stato è 'SYMBOL', symbol deve essere un'istanza di Symbol");
	        }
	        if (state == CornerState.SPECIALSYMBOL && !(symbol instanceof SpecialSymbol)) {
	            throw new IllegalArgumentException("Quando lo stato è 'SPECIALSYMBOL', symbol deve essere un'istanza di SpecialSymbol");
	        }
	        if ((state == CornerState.EMPTY || state == CornerState.NULL) && symbol != null) {
	            throw new IllegalArgumentException("Quando lo stato è 'EMPTY' o 'NULL', symbol deve essere null");
	        }

	        this.position = position;
	        this.state = state;
	        this.symbol = symbol;
	    }


		/**
	 * @return the position
	 */
	public CornerPosition getPosition() {
		return position;
	}

	/**
	 * @return the state
	 */
	public CornerState getState() {
		return state;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(CornerPosition position) {
		this.position = position;
	}

	/**
	 * @return the symbol
	 */
	public Object getSymbol() {
		return symbol;
	}

		@Override
	    public String toString() {
	        return "Corner(position="+ position  + ", state=" + state + ", symbol=" + symbol + ")";
	    }

}

