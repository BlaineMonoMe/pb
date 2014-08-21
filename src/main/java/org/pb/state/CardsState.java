package org.pb.state;

public class CardsState {
	/**
	 * 0 - empty table, 1 - flop, 2 - tern, 3 - river; 0 - hands without cards,
	 * 0 - hands with cards
	 */
	private int state;
	private boolean changed;

	public CardsState() {
		state = 0;
		changed = false;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

}
