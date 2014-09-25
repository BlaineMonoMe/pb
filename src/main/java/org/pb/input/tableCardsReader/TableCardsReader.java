package org.pb.input.tableCardsReader;

import org.pb.input.cardReader.CardReader;
import org.pb.input.state.Cards;
import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

/**
 * WAS A THREAD _ BECOMES NOT THREAD! *******
 * 
 * @author Yuriy
 * 
 */
public class TableCardsReader {

	private final int CARD1_X;
	private final int CARD2_X;
	private final int CARD3_X;
	private final int CARD4_X;
	private final int CARD5_X;
	private final int CARD_Y;
	private final int CARD_WIDTH = 20;
	private final int CARD_HEIGHT = 40;

	// private Coordinates centerOfTable;
	private Cards cardsOnTable;
	// private CardsState cardsOnTableState;
	// private ScreenshotMaker screenShootMaker;
	// private CardRankReader cardLevelReader;
	// private CardSuitReader cardLearReader;

	private CardReader cardReader;

	public TableCardsReader(Coordinates centerOfTable) {
		this.cardsOnTable = new Cards(5);
		// this.centerOfTable = centerOfTable;
		// this.cardsOnTableState = cardsOnTableState;
		// this.screenShootMaker = screenShootMaker;
		// cardLevelReader = new CardRankReader();
		// cardLearReader = new CardSuitReader();
		cardReader = new CardReader();

		CARD_Y = centerOfTable.getY() - 42;
		CARD1_X = centerOfTable.getX() - 135;
		CARD2_X = centerOfTable.getX() - 81;
		CARD3_X = centerOfTable.getX() - 27;
		CARD4_X = centerOfTable.getX() + 27;
		CARD5_X = centerOfTable.getX() + 81;

	}

	public Cards read(BufferedImage image, TableCardsState tableCardsState) {

		// if (cardsOnTableState.isChanged()) {
		// int state = cardsOnTableState.getState();
		switch (tableCardsState) {
		case RIVER:
			readRiver(image);
			// System.out.println("TABLE: " + cardsOnTable);
			break;
		case TURN:
			readTorn(image);
			// System.out.println("TABLE: " + cardsOnTable);
			break;
		case FLOP:
			readFlop(image);
			// System.out.println("TABLE: " + cardsOnTable);
			break;
		case NONE:
			cardsOnTable.removeCards();
			// System.out.println("TABLE: " + cardsOnTable);
			break;
		}

		return cardsOnTable;
		// cardsOnTableState.setChanged(false);
		// }

	}

	private void readFlop(BufferedImage image) {
		// char lear = 'n';
		// char level = 'x';

		cardsOnTable.removeCards();

		// reading first card
		/*
		 * lear = getSuit(image, centerOfTable.getX() - 130,
		 * centerOfTable.getY() - 18);
		 */
		/*
		 * lear = cardLearReader.getSuit(image.getSubimage(CARD1_X, CARD_Y,
		 * CARD_WIDTH, CARD_HEIGHT)); level =
		 * cardLevelReader.getRank(image.getSubimage( centerOfTable.getX() -
		 * 135, centerOfTable.getY() - 42, 15, 15));
		 */
		// cardsOnTable.addCard(new Card(lear, level));
		cardsOnTable.addCard(cardReader.readCard(image.getSubimage(CARD1_X,
				CARD_Y, CARD_WIDTH, CARD_HEIGHT)));

		// reading second card
		/*
		 * lear = getSuit(image, centerOfTable.getX() - 76, centerOfTable.getY()
		 * - 18);
		 */
		/*
		 * lear = cardLearReader.getSuit(image.getSubimage(CARD2_X, CARD_Y,
		 * CARD_WIDTH, CARD_HEIGHT)); level =
		 * cardLevelReader.getRank(image.getSubimage( centerOfTable.getX() -
		 * 81, centerOfTable.getY() - 42, 15, 15));
		 */
		// cardsOnTable.addCard(new Card(lear, level));
		cardsOnTable.addCard(cardReader.readCard(image.getSubimage(CARD2_X,
				CARD_Y, CARD_WIDTH, CARD_HEIGHT)));

		// reading third card
		/*
		 * lear = getSuit(image, centerOfTable.getX() - 22, centerOfTable.getY()
		 * - 18);
		 */
		/*
		 * lear = cardLearReader.getSuit(image.getSubimage(CARD3_X, CARD_Y,
		 * CARD_WIDTH, CARD_HEIGHT)); level =
		 * cardLevelReader.getRank(image.getSubimage( centerOfTable.getX() -
		 * 27, centerOfTable.getY() - 42, 15, 15));
		 */
		// cardsOnTable.addCard(new Card(lear, level));
		cardsOnTable.addCard(cardReader.readCard(image.getSubimage(CARD3_X,
				CARD_Y, CARD_WIDTH, CARD_HEIGHT)));
	}

	private void readTorn(BufferedImage image) {
		// char lear = 'n';
		// char level = 'x';

		// Cards cardsOnTable = new Cards(5);

		cardsOnTable.removeCards();

		/*
		 * lear = getSuit(image, centerOfTable.getX() + 32, centerOfTable.getY()
		 * - 18);
		 */
		/*
		 * lear = cardLearReader.getSuit(image.getSubimage(CARD4_X, CARD_Y,
		 * CARD_WIDTH, CARD_HEIGHT)); level =
		 * cardLevelReader.getRank(image.getSubimage( centerOfTable.getX() +
		 * 27, centerOfTable.getY() - 42, 15, 15));
		 */
		// cardsOnTable.addCard(new Card(lear, level));
		cardsOnTable.addCard(cardReader.readCard(image.getSubimage(CARD4_X,
				CARD_Y, CARD_WIDTH, CARD_HEIGHT)));
	}

	private void readRiver(BufferedImage image) {
		// char level = 'n';
		// char lear = 'n';

		// Cards cardsOnTable = new Cards(5);

		cardsOnTable.removeCards();

		/*
		 * lear = getSuit(image, centerOfTable.getX() + 86, centerOfTable.getY()
		 * - 18);
		 */
		/*
		 * lear = cardLearReader.getSuit(image.getSubimage(CARD5_X, CARD_Y,
		 * CARD_WIDTH, CARD_HEIGHT)); level =
		 * cardLevelReader.getRank(image.getSubimage( centerOfTable.getX() +
		 * 81, centerOfTable.getY() - 42, 15, 15));
		 */
		// cardsOnTable.addCard(new Card(lear, level));
		cardsOnTable.addCard(cardReader.readCard(image.getSubimage(CARD5_X,
				CARD_Y, CARD_WIDTH, CARD_HEIGHT)));
	}

	/*
	 * private char getSuit(BufferedImage image, int x, int y) { int[] rgb = new
	 * int[3]; image.getData().getPixel(x, y, rgb); if (rgb[0] == 236 && rgb[1]
	 * == 168 && rgb[2] == 168) { return 'd'; } if (rgb[0] == 200 && rgb[1] == 6
	 * && rgb[2] == 6) { return 'h'; } if (rgb[0] == 178 && rgb[1] == 178 &&
	 * rgb[2] == 178) { return 's'; } if (rgb[0] == 231 && rgb[1] == 231 &&
	 * rgb[2] == 231) { return 'c'; } return 'x'; }
	 */

}
