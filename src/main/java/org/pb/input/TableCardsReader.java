package org.pb.input;

import java.util.ArrayList;

import org.pb.input_output_util.Color;
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.state.Card;

public class TableCardsReader extends CardsReader {

	private int firstCardX;
	private int firstCardY;
	private int deltaX;

	public TableCardsReader(Coordinates centerOfTheTable) {
		super(centerOfTheTable);
		firstCardX = (centerOfTheTable.getX() - 135);
		firstCardY = (centerOfTheTable.getY() - 42);
		deltaX = 54;
	}

	public ArrayList<Card> readCards() {
		ArrayList<Card> cardList = new ArrayList<Card>();

		for (int i = 0; i < 5; i++) {
			if (IOUtil.checkColour(new Coordinates(firstCardX + deltaX * i + 6,
					firstCardY + 25), new Color(236, 168, 168))) {
				cardList.add(new Card('d', 2));
			}
			if (IOUtil.checkColour(new Coordinates(firstCardX + deltaX * i + 6,
					firstCardY + 25), new Color(200, 6, 6))) {
				cardList.add(new Card('h', 2));
			}
			if (IOUtil.checkColour(new Coordinates(firstCardX + deltaX * i + 6,
					firstCardY + 25), new Color(178, 178, 178))) {
				cardList.add(new Card('s', 2));
			}
			if (IOUtil.checkColour(new Coordinates(firstCardX + deltaX * i + 6,
					firstCardY + 25), new Color(231, 231, 231))) {
				cardList.add(new Card('c', 2));
			}
		}
		return cardList;
	}
}
