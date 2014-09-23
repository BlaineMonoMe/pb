package org.pb.input.state;

public class Card {
	// mast'
	private char lear;
	// from 2 to Ace
	private int level;

	public Card(Card card) {
		this.lear = card.getLear();
		this.level = card.getLevel();
	}

	public Card(char lear, int level) {
		this.lear = lear;
		this.level = level;
	}

	public Card(char lear, char level) {
		this.lear = lear;
		if (level == '2')
			this.level = 2;
		if (level == '3')
			this.level = 3;
		if (level == '4')
			this.level = 4;
		if (level == '5')
			this.level = 5;
		if (level == '6')
			this.level = 6;
		if (level == '7')
			this.level = 7;
		if (level == '8')
			this.level = 8;
		if (level == '9')
			this.level = 9;
		if (level == 'T')
			this.level = 10;
		if (level == 'J')
			this.level = 11;
		if (level == 'Q')
			this.level = 12;
		if (level == 'K')
			this.level = 13;
		if (level == 'A')
			this.level = 14;
	}

	public Card() {
	}

	@Override
	public String toString() {
		return "[" + level + lear + "]";
	}

	public char getLear() {
		return lear;
	}

	public void setLear(char lear) {
		this.lear = lear;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
