package card;

public class Corner {
	
	boolean connected;
	Symbol symbol;
	Artifact artifact;
	cornerState state;
	
	public Corner(boolean connected, Symbol symbol, Artifact artifact, cornerState state) {
		this.connected = connected;
		this.symbol = symbol;
		this.artifact = artifact;
		this.state = state;
	}
	
	public enum cornerState{
		NULL, EMPTY, SYMBOL, ARTIFACT
	}
	
	
}
