package org.pb.input.state;

import java.util.ArrayList;

public class Cards {
	private ArrayList<Card> cardList;
	private int maxCardsCount;

	public Cards(int maxCardsCount) {
		this.maxCardsCount = maxCardsCount;
		cardList = new ArrayList<Card>();
	}

	public Cards(Cards cards) {
		this.maxCardsCount = 5;
		cardList = new ArrayList<Card>();
		for (int i = 0; i < cards.getCardsCount(); i++) {
			cardList.add(new Card(cards.getCardList().get(i)));
		}
	}

	public int getCardsCount() {
		return cardList.size();
	}

	public void removeCards() {
		for (int i = cardList.size(); i > 0; i--) {
			cardList.remove(i - 1);
		}
	}

	@Override
	public String toString() {
		return "" + cardList;
	}

	public void addCard(Card card) {
		if (cardList.size() == maxCardsCount) {
			removeCards();
		}
		cardList.add(new Card(card));
	}

	public ArrayList<Card> getCardList() {
		return cardList;
	}

}
