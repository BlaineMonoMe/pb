package org.pb.input;

import java.awt.image.BufferedImage;

import org.pb.input_output_util.Coordinates;
import org.pb.state.Card;
import org.pb.state.Cards;
import org.pb.state.CardsState;

public class TableCardsReader extends Thread {
	private Coordinates centerOfTable;
	private Cards cardsOnTable;
	private CardsState cardsOnTableState;
	private ScreenShootMaker screenShootMaker;

	public TableCardsReader(Coordinates centerOfTable,
			Cards cardsOnTable, CardsState cardsOnTableState,
			ScreenShootMaker screenShootMaker) {
		this.setDaemon(true);
		this.cardsOnTable = cardsOnTable;
		this.centerOfTable = centerOfTable;
		this.cardsOnTableState = cardsOnTableState;
		this.screenShootMaker = screenShootMaker;
	}

	public void run() {
		while (true) {
			if (cardsOnTableState.isChanged()) {
				int state = cardsOnTableState.getState();
				switch (state) {
				case 3:
					readRiver();
					System.out.println("TABLE: "+cardsOnTable);
					break;
				case 2:
					readTorn();
					System.out.println("TABLE: "+cardsOnTable);
					break;
				case 1:
					readFlop();
					System.out.println("TABLE: "+cardsOnTable);
					break;
				case 0:
					cardsOnTable.removeCards();
					System.out.println("TABLE: "+cardsOnTable);
					break;
				}
				cardsOnTableState.setChanged(false);
			}
		}
	}

	private void readFlop() {
		BufferedImage image = screenShootMaker.getScreenShot();
		char lear = 'n';

		// reading first card
		lear = getLear(image, centerOfTable.getX() - 130,
				centerOfTable.getY() - 18);
		cardsOnTable.addCard(new Card(lear, 2));

		// reading second card
		lear = getLear(image, centerOfTable.getX() - 76,
				centerOfTable.getY() - 18);
		cardsOnTable.addCard(new Card(lear, 2));

		// reading third card
		lear = getLear(image, centerOfTable.getX() - 22,
				centerOfTable.getY() - 18);
		cardsOnTable.addCard(new Card(lear, 2));
	}

	private void readTorn() {
		BufferedImage image = screenShootMaker.getScreenShot();
		char lear = 'n';
		lear = getLear(image, centerOfTable.getX() + 32,
				centerOfTable.getY() - 18);
		cardsOnTable.addCard(new Card(lear, 2));
	}

	private void readRiver() {
		BufferedImage image = screenShootMaker.getScreenShot();
		char lear = 'n';
		lear = getLear(image, centerOfTable.getX() + 86,
				centerOfTable.getY() - 18);
		cardsOnTable.addCard(new Card(lear, 2));
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
