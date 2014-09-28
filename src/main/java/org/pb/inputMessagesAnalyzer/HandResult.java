package org.pb.inputMessagesAnalyzer;

public class HandResult {
	private int winningStack;
	private HandWinner winner;

	public HandResult() {

	}

	public HandResult(int winningStack, HandWinner winner) {
		this.winningStack = winningStack;
		this.winner = winner;
	}

	public void reset() {
		winningStack = 0;
		winner = HandWinner.BOTH;
	}

	public void incrementWinningStack(int value) {
		winningStack += value;
	}

	public int getWinningStack() {
		if (winner != HandWinner.BOTH) {
			return winningStack;
		} else {
			return winningStack / 2;
		}

	}

	public void setWinningStack(int winningStack) {
		this.winningStack = winningStack;
	}

	public HandWinner getWinner() {
		return winner;
	}

	/**
	 * cool setter, yeah?))
	 * 
	 * @param winner
	 */
	public void setWinner(HandWinner winner) {
		if (this.winner == HandWinner.BOTH) {
			this.winner = winner;
		} else {
			if (this.winner != winner) {
				this.winner = HandWinner.BOTH;
			}
		}
	}

	@Override
	public String toString() {
		return "" + winner + " wins " + winningStack;
	}

}
