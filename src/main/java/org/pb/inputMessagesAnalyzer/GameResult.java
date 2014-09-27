package org.pb.inputMessagesAnalyzer;

public class GameResult {
	private int winningStack;
	private GameWinner winner;

	public GameResult() {

	}

	public GameResult(int winningStack, GameWinner winner) {
		this.winningStack = winningStack;
		this.winner = winner;
	}

	public void reset() {
		winningStack = 0;
		winner = GameWinner.BOTH;
	}

	public void incrementWinningStack(int value) {
		winningStack += value;
	}

	public int getWinningStack() {
		if (winner != GameWinner.BOTH) {
			return winningStack;
		} else {
			return winningStack / 2;
		}

	}

	public void setWinningStack(int winningStack) {
		this.winningStack = winningStack;
	}

	public GameWinner getWinner() {
		return winner;
	}

	/**
	 * cool setter, yeah?))
	 * 
	 * @param winner
	 */
	public void setWinner(GameWinner winner) {
		if (this.winner == GameWinner.BOTH) {
			this.winner = winner;
		} else {
			if (this.winner != winner) {
				this.winner = GameWinner.BOTH;
			}
		}
	}

	@Override
	public String toString() {
		return "" + winner + " wins " + winningStack;
	}

}
