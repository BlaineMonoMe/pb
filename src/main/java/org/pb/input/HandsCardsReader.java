package org.pb.input;

import java.awt.image.BufferedImage;

import org.pb.input_output_util.Coordinates;
import org.pb.state.Card;
import org.pb.state.Cards;
import org.pb.state.CardsState;
import org.pb.state.Players;

public class HandsCardsReader extends Thread {
	private Coordinates centerOfTable;
	private Cards cardsOnHands;
	private CardsState cardsOnHandsState;
	private ScreenShootMaker screenShootMaker;
	private boolean iAdSittingAtTheTop;

	public HandsCardsReader(Coordinates centerOfTable, Cards cardsOnHands,
			CardsState cardsOnHandsState, ScreenShootMaker screenShootMaker,
			Players players) {
		this.setDaemon(true);
		this.centerOfTable = centerOfTable;
		this.cardsOnHands = cardsOnHands;
		this.cardsOnHandsState = cardsOnHandsState;
		this.screenShootMaker = screenShootMaker;
		this.iAdSittingAtTheTop = players.isiAmSittingOnTheTop();
	}

	public void run() {
		while (true) {
			if (cardsOnHandsState.isChanged()) {
				int state = cardsOnHandsState.getState();
				switch (state) {
				case 1:
					read();
					System.out.println("HANDS: " + cardsOnHands);
					break;
				case 0:
					cardsOnHands.removeCards();
					System.out.println("HANDS: " + cardsOnHands);
					break;
				}
				cardsOnHandsState.setChanged(false);
			}
		}
	}

	private void read() {
		BufferedImage image = screenShootMaker.getScreenShot();
		char lear = 'n';

		if (!iAdSittingAtTheTop) {
			// reading first card
			lear = getLear(image, centerOfTable.getX() - 47,
					centerOfTable.getY() + 127);
			cardsOnHands.addCard(new Card(lear, 2));
			// reading second card
			lear = getLear(image, centerOfTable.getX() + 1,
					centerOfTable.getY() + 127);
			cardsOnHands.addCard(new Card(lear, 2));
		}
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
