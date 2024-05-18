package Deck;

import java.util.ArrayList;

import card.GoldCard;
import card.ResourceCard;
import goal.Goal;

public class Deck {

	ArrayList<ResourceCard> resourcesCards;
	ArrayList<GoldCard> goldCards;
	ArrayList<Goal> goals;
	
	public Deck(ArrayList<ResourceCard> resourcesCards, ArrayList<GoldCard> goldCards, ArrayList<Goal> goals) {
		super();
		this.resourcesCards = resourcesCards;
		this.goldCards = goldCards;
		this.goals = goals;
	}
	
}
