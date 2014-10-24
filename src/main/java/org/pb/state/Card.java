package org.pb.state;

import java.util.LinkedHashMap;
import java.util.Map;

public class Card {

    private static Map<String, Integer> cardSymbolicValueToIntMap = new LinkedHashMap<String, Integer>() {
        {
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("T", 10);
            put("J", 11);
            put("Q", 12);
            put("K", 13);
            put("A", 14);
        }
    };

    private static Map<Integer, String> cardIntValueToSymbolicMap = new LinkedHashMap<Integer, String>() {
        {
            put(2, "2");
            put(3, "3");
            put(4, "4");
            put(5, "5");
            put(6, "6");
            put(7, "7");
            put(8, "8");
            put(9, "9");
            put(10, "T");
            put(11, "J");
            put(12, "Q");
            put(13, "K");
            put(14, "A");
        }
    };

	// mast'
	private char suit;
	// from 2 to Ace
	private int rank;
    private String rankStringValue;

	public Card(Card card) {
		this.suit = card.getSuit();
		this.rank = card.getRank();
        rankStringValue = cardIntValueToSymbolicMap.get(rank);
	}

	public Card(char suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Card(char suit, char rank) {
		this.suit = suit;
        rankStringValue = String.valueOf(rank);
		this.rank = cardSymbolicValueToIntMap.get(rankStringValue);

	}

	public Card() {
	}

	@Override
	public String toString() {
		return "[" + rank + suit + "]";
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

    public String getRankStringValue() {
        return rankStringValue;
    }

    public void setRankStringValue(String rankStringValue) {
        this.rankStringValue = rankStringValue;
    }

    public String getCardStringValue() {
        return rankStringValue + suit;
    }
}
