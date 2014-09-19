package org.pb.input.handsCardsReader;

import java.awt.image.BufferedImage;

import org.pb.input.CardLevelReader;
import org.pb.input_output_util.Coordinates;
import org.pb.state.Card;
import org.pb.state.Cards;

/**
 * WAS A THREAD - BECOMES NOT THREAD ***********
 * 
 * @author Yuriy
 * 
 */
public class HandsCardsReader {
	private Coordinates centerOfTable;
	private Cards cardsOnHands;
	// private CardsState cardsOnHandsState;
	// private ScreenShootMaker screenShootMaker;
	private boolean iAmSittingAtTheTop;
	private CardLevelReader cardsLevelReader;

	public HandsCardsReader(Coordinates centerOfTable,
			boolean amISittingAtTheTop) {
		this.centerOfTable = centerOfTable;
		this.cardsOnHands = new Cards(2);
		// this.cardsOnHandsState = cardsOnHandsState;
		// this.screenShootMaker = screenShootMaker;
		this.iAmSittingAtTheTop = amISittingAtTheTop;
		this.cardsLevelReader = new CardLevelReader();
	}

	public Cards read(BufferedImage image) {
		char lear = 'n';
		char level = 'x';

		cardsOnHands.removeCards();

		if (!iAmSittingAtTheTop) {
			// reading first card
			lear = getLear(image, centerOfTable.getX() - 47,
					centerOfTable.getY() + 127);
			level = cardsLevelReader.getLevel(image.getSubimage(
					centerOfTable.getX() - 52, centerOfTable.getY() + 103, 15,
					15));
			cardsOnHands.addCard(new Card(lear, level));
			// reading second card
			lear = getLear(image, centerOfTable.getX() + 1,
					centerOfTable.getY() + 127);
			level = cardsLevelReader.getLevel(image.getSubimage(
					centerOfTable.getX() - 4, centerOfTable.getY() + 103, 15,
					15));
			cardsOnHands.addCard(new Card(lear, level));
		}

		return cardsOnHands;
	}

	private char getLear(BufferedImage image, int x, int y) {
		int[] rgb = new int[3];
		image.getData().getPixel(x, y, rgb);
		if (rgb[0] == 236 && rgb[1] == 168 && rgb[2] == 168) {
			return 'd';
		}
		if (rgb[0] == 200 && rgb[1] == 6 && rgb[2] == 6) {
			return 'h';
		}
		if (rgb[0] == 178 && rgb[1] == 178 && rgb[2] == 178) {
			return 's';
		}
		if (rgb[0] == 231 && rgb[1] == 231 && rgb[2] == 231) {
			return 'c';
		}
		return 'x';
	}

}
