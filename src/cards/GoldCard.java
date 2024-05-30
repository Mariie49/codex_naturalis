package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GoldCard extends Card {

	private static ArrayList<Card> GoldCardsDeck = new ArrayList<>();
	private static int points;
	private static SpecialSymbol firstSpecialSymbol;
	private ArrayList<Card> board = new ArrayList<>();

	public GoldCard(CardType type, boolean isFront, ArrayList<Corner> frontCorners, ArrayList<Corner> backCorners,
			int score, boolean hasCenterSymbols, ArrayList<Symbol> centerSymbols, ArrayList<Symbol> requirement, SpecialSymbol firstSpecialSymbol) {
		super(type, isFront, frontCorners, backCorners, score, hasCenterSymbols, centerSymbols, requirement, firstSpecialSymbol);
		// TODO Auto-generated constructor stub
	}

	public GoldCard() {
		super(CardType.GOLD, true, new ArrayList<>(), new ArrayList<>(), points, true, new ArrayList<>(), new ArrayList<>(), firstSpecialSymbol);
		createCards();
		shuffleDeck();
	}

	public void createCards() {
		ArrayList<Symbol> kingdoms = new ArrayList<>();
		kingdoms.add(Symbol.PLANT_KINGDOM);
		kingdoms.add(Symbol.ANIMAL_KINGDOM);
		kingdoms.add(Symbol.MUSHROOM_KINGDOM);
		kingdoms.add(Symbol.INSECT_KINGDOM);

		for (Symbol kingdom : kingdoms) {
			createOnePointCards(kingdom);
			createTwoPointsCards(kingdom);
			createThreePointsCards(kingdom);
			createFivePointsCards(kingdom);
		}
	}

	/**
	 * @return the goldCardsDeck
	 */
	public static ArrayList<Card> getGoldCardsDeck() {
		return GoldCardsDeck;
	}

	/**
	 * @return the points
	 */
	public static int getPoints() {
		return points;
	}

	/**
	 * @param goldCardsDeck the goldCardsDeck to set
	 */
	public static void setGoldCardsDeck(ArrayList<Card> goldCardsDeck) {
		GoldCardsDeck = goldCardsDeck;
	}

	/**
	 * @param points the points to set
	 */
	public static void setPoints(int points) {
		GoldCard.points = points;
	}

	private void createOnePointCards(Symbol kingdom) {
		ArrayList<SpecialSymbol> usedSpecialSymbols = new ArrayList<>();
		ArrayList<Symbol> usedRequirements = new ArrayList<>();
		SpecialSymbol firstSpecialSymbol = SpecialSymbol.getRandomSpecialSymbol();
		usedSpecialSymbols.add(firstSpecialSymbol);

		for (int i = 0; i < 3; i++) {
			ArrayList<Corner> frontCorners = createFrontCornersForOnePoint(firstSpecialSymbol, usedSpecialSymbols);
			ArrayList<Corner> backCorners = createBackCorners();
			ArrayList<Symbol> requirements = createRequirementsForOnePoint(kingdom, usedRequirements);

			Card gCard = new Card(CardType.GOLD, true, frontCorners, backCorners, 1, false, new ArrayList<>(), requirements, firstSpecialSymbol) {
				@Override
				public void play() {
					// Implement the play method for the GoldCard
					// Calcola i punti per gli SpecialSymbol sulla board
					int specialSymbolCount = countSpecialSymbolsOnBoard();

					// Aggiungi punti per ogni SpecialSymbol presente sulla board e sulla carta giocata
					int totalPoints = specialSymbolCount + 1;  // +1 per lo SpecialSymbol sulla carta stessa
					this.setScore(totalPoints);
				}
			};

			GoldCardsDeck.add(gCard);
		}
	}

	private ArrayList<Corner> createFrontCornersForOnePoint(SpecialSymbol firstSpecialSymbol, ArrayList<SpecialSymbol> usedSpecialSymbols) {
		// Crea una lista con le configurazioni predefinite
		ArrayList<Corner> frontCorners = new ArrayList<>();
		frontCorners.add(createCorner(CornerState.EMPTY, null, null, true));
		frontCorners.add(createCorner(CornerState.SPECIALSYMBOL, null, firstSpecialSymbol, true));
		frontCorners.add(createCorner(CornerState.EMPTY, null, null, true));
		frontCorners.add(createCorner(CornerState.NULL, null, null, true));

		// Mescola la lista per randomizzare l'ordine
		Collections.shuffle(frontCorners);

		return frontCorners;
	}

	private ArrayList<Symbol> createRequirementsForOnePoint(Symbol kingdom, ArrayList<Symbol> usedRequirements) {
		ArrayList<Symbol> requirements = new ArrayList<>();
		Symbol firstSymbol;
		do {
			firstSymbol = Symbol.getRandomSymbol();
		} while (firstSymbol.equals(kingdom) || usedRequirements.contains(firstSymbol));

		requirements.add(kingdom);
		requirements.add(kingdom);
		requirements.add(firstSymbol);
		usedRequirements.add(firstSymbol);

		return requirements;
	}

	private int countSpecialSymbolsOnBoard() {
		int specialSymbolCount = 0;

		for (Card existingCard : board) {  // Supponiamo che `board` sia una lista di tutte le carte in gioco
			for (Corner corner : existingCard.getFrontCorners()) {
				if (corner.getState() == CornerState.SPECIALSYMBOL) {
					specialSymbolCount++;
				}
			}
		}

		return specialSymbolCount;
	}

	private void createTwoPointsCards(Symbol kingdom) {
		ArrayList<Symbol> usedRequirements = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			ArrayList<Corner> frontCorners = createFrontCornersForTwoPoints();
			ArrayList<Corner> backCorners = createBackCorners();
			ArrayList<Symbol> requirements = createRequirementsForTwoPoints(kingdom, usedRequirements);

			Card gCard = new Card(CardType.GOLD, true, frontCorners, backCorners, 2, false, new ArrayList<>(), requirements, null) {
				@Override
				public void play() {
					// Implement the play method for the GoldCard
					int coveredCorners = calculateCoveredCorners(this);
					this.setScore(coveredCorners * 2);
				}
			};

			GoldCardsDeck.add(gCard);
		}
	}

	private ArrayList<Corner> createFrontCornersForTwoPoints() {
		// Crea una lista con le configurazioni predefinite
		ArrayList<Corner> frontCorners = new ArrayList<>();
		frontCorners.add(createCorner(CornerState.EMPTY, null, null, true));
		frontCorners.add(createCorner(CornerState.EMPTY, null,null, true));
		frontCorners.add(createCorner(CornerState.EMPTY, null, null, true));
		frontCorners.add(createCorner(CornerState.NULL, null, null, true));

		// Mescola la lista per randomizzare l'ordine
		Collections.shuffle(frontCorners);
		return frontCorners;
	}

	private ArrayList<Symbol> createRequirementsForTwoPoints(Symbol kingdom, ArrayList<Symbol> usedRequirements) {
		ArrayList<Symbol> requirements = new ArrayList<>();
		for(int i = 0; i < 3; i ++) {
			requirements.add(kingdom);
		}
		
		usedRequirements.add(kingdom);
		for (int i = 0; i < 3; i++) {
			Symbol symbol;
			do {
				symbol = Symbol.getRandomSymbol();
			} while (usedRequirements.contains(symbol));
			requirements.add(symbol);
			usedRequirements.add(symbol);
		}

		return requirements;
	}

	private int calculateCoveredCorners(Card card) {
		int coveredCorners = 0;

		// Supponiamo di avere accesso al board di gioco tramite una variabile board
		// Esempio semplificato, la logica esatta dipende da come è implementato il board
		for (Corner corner : card.getCorners()) {
			if (isCornerCovered(corner)) {
				coveredCorners++;
			}
		}

		return coveredCorners;
	}

	private boolean isCornerCovered(Corner corner) {
		// Implementa la logica per verificare se l'angolo della carta copre un angolo di un'altra carta
		// La logica esatta dipenderà dalla struttura del board e dal posizionamento delle carte
		// Ecco un esempio semplificato

		// Supponiamo che board sia una lista di tutte le carte in gioco
		for (Card existingCard : board) {
			for (Corner existingCorner : existingCard.getCorners()) {
				if (corner.covers(existingCorner)) {
					return true;
				}
			}
		}

		return false;
	}

	private void createThreePointsCards(Symbol kingdom) {

		for (int i = 0; i < 3; i++) {
			ArrayList<Corner> frontCorners = createFrontCornersForThreePoints();
			ArrayList<Corner> backCorners = createBackCorners();
			ArrayList<Symbol> requirements = createRequirementsForThreePoints(kingdom);

			Card gCard = new Card(CardType.GOLD, true, frontCorners, backCorners, 3, false, new ArrayList<>(), requirements, null) {
				@Override
				public void play() {
					// Implement the play method for the GoldCard
					this.setScore(3);
				}
			};

			GoldCardsDeck.add(gCard);
		}
	}

	private ArrayList<Corner> createFrontCornersForThreePoints() {
		// Crea una lista con le configurazioni predefinite
		ArrayList<Corner> frontCorners = new ArrayList<>();
		frontCorners.add(createCorner(CornerState.SPECIALSYMBOL, null, null, true));
		frontCorners.add(createCorner(CornerState.EMPTY, null,null, true));
		frontCorners.add(createCorner(CornerState.NULL, null, null, true));
		frontCorners.add(createCorner(CornerState.NULL, null, null, true));

		// Mescola la lista per randomizzare l'ordine
		Collections.shuffle(frontCorners);
		return frontCorners;
	}

	private ArrayList<Symbol> createRequirementsForThreePoints(Symbol kingdom) {
		ArrayList<Symbol> requirements = new ArrayList<>();
		
		for(int i = 0; i < 2 ; i++) {
			requirements.add(kingdom);
		}
		
		return requirements;
	}

	private void createFivePointsCards (Symbol kingdom) {
		ArrayList<Corner> frontCorners = createFrontCornersForFivePoints();
		ArrayList<Corner> backCorners = createBackCorners();
		ArrayList<Symbol> requirements = createRequirementsForFivePoints(kingdom);

		Card gCard = new Card(CardType.GOLD, true, frontCorners, backCorners, 5, false, new ArrayList<>(), requirements, null) {
			@Override
			public void play() {
				// Implement the play method for the GoldCard
				this.setScore(5);
			}
		};

		GoldCardsDeck.add(gCard);
	}

	private ArrayList<Symbol> createRequirementsForFivePoints(Symbol kingdom) {
		// TODO Auto-generated method stub
		ArrayList<Symbol> requirements = new ArrayList<>();
		for(int i = 0 ; i < 4 ; i++) {
			requirements.add(kingdom);
		}
		
		return requirements;
	}

	private ArrayList<Corner> createFrontCornersForFivePoints() {
		// TODO Auto-generated method stub
		ArrayList<Corner> frontCorners = new ArrayList<>();
		frontCorners.add(createCorner(CornerState.EMPTY, null, null, true));
		frontCorners.add(createCorner(CornerState.EMPTY, null,null, true));
		frontCorners.add(createCorner(CornerState.NULL, null, null, true));
		frontCorners.add(createCorner(CornerState.NULL, null, null, true));
		
		Collections.shuffle(frontCorners);

		return frontCorners;
	}

	private ArrayList<Corner> createBackCorners() {
		ArrayList<Corner> backCorners = new ArrayList<>();
		Corner b = createCorner(CornerState.EMPTY, null, null, false);
		for (int j = 0; j < 4; j++) {
			backCorners.add(b);
		}
		return backCorners;
	}

	private void shuffleDeck() {
		// TODO Auto-generated method stub

	}

	public static Card drawCard() {

		// Draw a random card from the deck
		Random random = new Random();
		int index = random.nextInt(GoldCardsDeck.size());
		GoldCard card = (GoldCard) GoldCardsDeck.remove(index);

		return card;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

}
