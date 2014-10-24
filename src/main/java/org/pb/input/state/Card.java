package org.pb.input.state;

public class Card {
	// mast'
	private char suit;
	// from 2 to Ace
	private int rank;
    private String rankStringValue;

	public Card(Card card) {
		this.suit = card.getSuit();
		this.rank = card.getRank();
	}

	public Card(char suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public Card(char suit, char rank) {
		this.suit = suit;
        rankStringValue = String.valueOf(rank);
		if (rank == '2')
			this.rank = 2;
		if (rank == '3')
			this.rank = 3;
		if (rank == '4')
			this.rank = 4;
		if (rank == '5')
			this.rank = 5;
		if (rank == '6')
			this.rank = 6;
		if (rank == '7')
			this.rank = 7;
		if (rank == '8')
			this.rank = 8;
		if (rank == '9')
			this.rank = 9;
		if (rank == 'T')
			this.rank = 10;
		if (rank == 'J')
			this.rank = 11;
		if (rank == 'Q')
			this.rank = 12;
		if (rank == 'K')
			this.rank = 13;
		if (rank == 'A')
			this.rank = 14;
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
