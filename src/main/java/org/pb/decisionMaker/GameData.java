package org.pb.decisionMaker;

import org.pb.input.dealer.DealerState;
import org.pb.state.Cards;

public class GameData {

	/**
	 * this fields can't change. Every new game should be created new dataGame
	 * object
	 */
	private final int myStackSize;
	private final int enemyStackSize;
	private final int bigBlindes;
	private final Cards myCards;
	private final DealerState dealer;

	public GameData(int myStackSize, int enemyStackSize, int bigBlindes,
			Cards myCards, DealerState dealer) {
		this.myStackSize = myStackSize;
		this.enemyStackSize = enemyStackSize;
		this.bigBlindes = bigBlindes;
		this.myCards = myCards;
		this.dealer = dealer;
	}

	public int getMyStackSize() {
		return myStackSize;
	}

	public int getEnemyStackSize() {
		return enemyStackSize;
	}

	public int getBigBlindes() {
		return bigBlindes;
	}

	public Cards getMyCards() {
		return myCards;
	}

	public DealerState getDealer() {
		return dealer;
	}

}
