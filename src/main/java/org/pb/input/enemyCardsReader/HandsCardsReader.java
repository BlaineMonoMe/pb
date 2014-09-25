package org.pb.input.enemyCardsReader;

import java.awt.image.BufferedImage;

import org.pb.input.cardReader.CardReader;
import org.pb.input.state.Cards;
import org.pb.input_output_util.Coordinates;

/**
 * WAS A THREAD - BECOMES NOT THREAD ***********
 * 
 * @author Yuriy
 * 
 */
public class HandsCardsReader {

	private final int LEFT_CARD_X;
	private final int RIGHT_CARD_X;
	private final int CARD_Y;
	private final int CARD_WIDTH = 20;
	private final int CARD_HEIGHT = 40;

	// private Coordinates centerOfTable;
	private Cards cardsOnHands;
	// private CardsState cardsOnHandsState;
	// private ScreenShootMaker screenShootMaker;
	private boolean iAmSittingAtTheTop;
	// private CardLevelReader cardsLevelReader;
	// private CardLearReader cardsLearReader;
	private CardReader cardReader;

	public HandsCardsReader(Coordinates centerOfTable,
			boolean amISittingAtTheTop) {
		// this.centerOfTable = centerOfTable;
		this.cardsOnHands = new Cards(2);
		// this.cardsOnHandsState = cardsOnHandsState;
		// this.screenShootMaker = screenShootMaker;
		this.iAmSittingAtTheTop = amISittingAtTheTop;
		// cardsLevelReader = new CardLevelReader();
		// cardsLearReader = new CardLearReader();
		cardReader = new CardReader();

		LEFT_CARD_X = centerOfTable.getX() - 52;
		RIGHT_CARD_X = centerOfTable.getX() - 4;
		CARD_Y = centerOfTable.getY() -211;
	}

	public Cards read(BufferedImage image) {
		// char lear = 'n';
		// char level = 'x';

		cardsOnHands.removeCards();

		if (!iAmSittingAtTheTop) {
			// reading first card
			/*
			 * lear = getLear(image, centerOfTable.getX() - 47,
			 * centerOfTable.getY() + 127);
			 */
			/*
			 * lear = cardsLearReader.getLear(image.getSubimage(LEFT_CARD_X,
			 * CARD_Y, CARD_WIDTH, CARD_HEIGHT)); level =
			 * cardsLevelReader.getLevel(image.getSubimage( centerOfTable.getX()
			 * - 52, centerOfTable.getY() + 103, 15, 15));
			 */
			// cardsOnHands.addCard(new Card(lear, level));

			cardsOnHands.addCard(cardReader.readCard(image.getSubimage(
					LEFT_CARD_X, CARD_Y, CARD_WIDTH, CARD_HEIGHT)));

			// reading second card
			/*
			 * lear = getLear(image, centerOfTable.getX() + 1,
			 * centerOfTable.getY() + 127);
			 */
			/*
			 * lear = cardsLearReader.getLear(image.getSubimage(RIGHT_CARD_X,
			 * CARD_Y, CARD_WIDTH, CARD_HEIGHT)); level =
			 * cardsLevelReader.getLevel(image.getSubimage( centerOfTable.getX()
			 * - 4, centerOfTable.getY() + 103, 15, 15));
			 * cardsOnHands.addCard(new Card(lear, level));
			 */
			cardsOnHands.addCard(cardReader.readCard(image.getSubimage(
					RIGHT_CARD_X, CARD_Y, CARD_WIDTH, CARD_HEIGHT)));
		}

		return cardsOnHands;
	}
	/*
	 * private char getLear(BufferedImage image, int x, int y) { int[] rgb = new
	 * int[3]; image.getData().getPixel(x, y, rgb); if (rgb[0] == 236 && rgb[1]
	 * == 168 && rgb[2] == 168) { return 'd'; } if (rgb[0] == 200 && rgb[1] == 6
	 * && rgb[2] == 6) { return 'h'; } if (rgb[0] == 178 && rgb[1] == 178 &&
	 * rgb[2] == 178) { return 's'; } if (rgb[0] == 231 && rgb[1] == 231 &&
	 * rgb[2] == 231) { return 'c'; } return 'x'; }
	 */

}
