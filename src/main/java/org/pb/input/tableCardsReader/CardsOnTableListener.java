package org.pb.input.tableCardsReader;

import org.pb.input.state.Cards;
import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

/**
 * WAS A THREAD - BECOMES NOT THREAD ************
 * 
 * @author Yuriy
 * 
 */

public class CardsOnTableListener {

	private Coordinates centerOfTheTable;

	private boolean isUpdated;
	private TableCardsState currentState;
	private TableCardsReader tableCardsReader;
	private int currentCardsCount = 100;

	public CardsOnTableListener(Coordinates coords) {
		this.centerOfTheTable = new Coordinates(coords);
		tableCardsReader = new TableCardsReader(coords);
		isUpdated = false;
		currentState = TableCardsState.TURN;
	}

	public boolean isUpdated(BufferedImage image) {

		TableCardsState state = TableCardsState.NONE;

		if (checkFlop(image) == true) {
			state = TableCardsState.FLOP;
			// System.out.println("flop");
		}
		if (checkTorn(image) == true) {
			state = TableCardsState.TURN;
			// System.out.println("turn");
		}
		if (checkRiver(image) == true) {
			state = TableCardsState.RIVER;
			// System.out.println("river");
		}

		// System.out.println(state);

		if (state != currentState) {
			isUpdated = true;
			currentState = state;
		} else {
			isUpdated = false;
		}

		return isUpdated;
	}

	public Cards getCards(BufferedImage image) {
		Cards cards = tableCardsReader.read(image, currentState);
		currentCardsCount = cards.getCardsCount();
		return cards;
	}

	public int getCurrentCardsCount() {
		return currentCardsCount;
	}

	private boolean checkRiver(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(centerOfTheTable.getX() + 90,
				centerOfTheTable.getY(), rgb);
		if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
			return true;
		}
		return false;
	}

	private boolean checkTorn(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(centerOfTheTable.getX() + 35,
				centerOfTheTable.getY(), rgb);
		if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
			return true;
		}
		return false;
	}

	private boolean checkFlop(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(centerOfTheTable.getX() - 20,
				centerOfTheTable.getY(), rgb);
		if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
			return true;
		}
		return false;
	}

}
