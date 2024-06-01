package objectivecards;

import java.util.Random;
import game.Manuscript;
import game.Player;

public abstract class ObjectiveCards {

	private int points = 0;
	private int repeats = 1;
	private static int assignedCard = 0;

	public ObjectiveCards() {}

	protected abstract boolean checkTarget(Manuscript manuscript);

	public static ObjectiveCards assignObjectiveCards() {
		ObjectiveCards card = null;
		Random random = new Random();
		int n;

		do {
			n = random.nextInt(16) + 1;
		}while(assignedCard == n);
		assignedCard = n;

		switch(n) {
		case 1 : card = new ObjectiveCard1(); 
		break;
		case 2 : card = new ObjectiveCard2(); 
		break;
		case 3 : card = new ObjectiveCard3(); 
		break;
		case 4 : card = new ObjectiveCard4(); 
		break;
		case 5 : card = new ObjectiveCard5(); 
		break;
		case 6 : card = new ObjectiveCard6(); 
		break;
		case 7 : card = new ObjectiveCard7();
		break;
		case 8 : card = new ObjectiveCard8();
		break;
		case 9 : card = new ObjectiveCard9(); 
		break;
		case 10 : card = new ObjectiveCard10(); 
		break;
		case 11 : card = new ObjectiveCard11(); 
		break;
		case 12 : card = new ObjectiveCard12(); 
		break;
		case 13 : card = new ObjectiveCard13(); 
		break;
		case 14 : card = new ObjectiveCard14(); 
		break;
		case 15 : card = new ObjectiveCard15();
		break;
		case 16 : card = new ObjectiveCard16();
		break;
		default: throw new IllegalStateException("Unexpected value: "+ n);
		}
		return card;
	}
	
	public int counter(Player a ) {
		if(checkTarget(a.getManuscript())) {
			repeats++;
		}
		return repeats;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

}
