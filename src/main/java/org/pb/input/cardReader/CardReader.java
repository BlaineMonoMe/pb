package org.pb.input.cardReader;

import java.awt.image.BufferedImage;

import org.pb.input.state.Card;

public class CardReader {
	private CardLevelReader cardLevelReader;
	private CardLearReader cardLearReader;

	public CardReader() {
		cardLevelReader = new CardLevelReader();
		cardLearReader = new CardLearReader();
	}

	public Card readCard(BufferedImage cardImage) {
		char level = cardLevelReader.getLevel(cardImage);
		char lear = cardLearReader.getLear(cardImage);
		return new Card(lear, level);
	}
}
