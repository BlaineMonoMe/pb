package org.pb.input.cardReader;

import org.pb.input.state.Card;

import java.awt.image.BufferedImage;

public class CardReader {
	private CardRankReader cardRankReader;
	private CardSuitReader cardSuitReader;

	public CardReader() {
		cardRankReader = new CardRankReader();
		cardSuitReader = new CardSuitReader();
	}

	public Card readCard(BufferedImage cardImage) {
		char level = cardRankReader.getLevel(cardImage);
		char suit = cardSuitReader.getSuit(cardImage);
		return new Card(suit, level);
	}
}
