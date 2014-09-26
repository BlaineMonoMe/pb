package org.pb.state;

import java.util.ArrayList;
import java.util.List;

public class Cards {
	private List<Card> cardList;
	private int maxCardsCount;

	public Cards(int maxCardsCount) {
		this.maxCardsCount = maxCardsCount;
		cardList = new ArrayList<Card>();
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
		return "[cards] : " + cardList;
	}

	public void addCard(Card card) {
		if (cardList.size() == maxCardsCount) {
			removeCards();
		}
		cardList.add(new Card(card));
	}

	public List<Card> getCardList() {
		return cardList;
	}

}
