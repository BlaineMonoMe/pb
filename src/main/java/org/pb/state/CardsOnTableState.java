package org.pb.state;

public class CardsOnTableState {
	/**
	 * 0 - empty table, 1 - flop, 2 - tern, 3 - river
	 */
	private int state;
	private boolean changed;

	public CardsOnTableState() {
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
