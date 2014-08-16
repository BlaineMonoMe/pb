package org.pb.state;

public class Card {
	// mast'
	private char lear;
	// from 2 to Ace
	private int level;

	public Card(char lear, int level) {
		this.lear = lear;
		this.level = level;
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
