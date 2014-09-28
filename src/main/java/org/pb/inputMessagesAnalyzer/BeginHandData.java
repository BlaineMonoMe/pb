package org.pb.inputMessagesAnalyzer;

import org.pb.input.dealer.DealerState;
import org.pb.input.state.Cards;

public class BeginHandData {

	private int myStackSize;
	private int enemyStackSize;
	private int bigBlindes;
	private Cards myCards;

	@Override
	public String toString() {
		return "NewHandData [myStackSize=" + myStackSize + ", enemyStackSize="
				+ enemyStackSize + ", bigBlindes=" + bigBlindes + ", myCards="
				+ myCards + ", dealer=" + dealer + "]";
	}

	private DealerState dealer;

	public int getMyStackSize() {
		return myStackSize;
	}

	public void setMyStackSize(int myStackSize) {
		this.myStackSize = myStackSize;
	}

	public int getEnemyStackSize() {
		return enemyStackSize;
	}

	public void setEnemyStackSize(int enemyStackSize) {
		this.enemyStackSize = enemyStackSize;
	}

	public int getBigBlindes() {
		return bigBlindes;
	}

	public void setBigBlindes(int bigBlindes) {
		this.bigBlindes = bigBlindes;
	}

	public Cards getMyCards() {
		return myCards;
	}

	public void setMyCards(Cards cardsOnHands) {
		this.myCards = cardsOnHands;
	}

	public DealerState getDealer() {
		return dealer;
	}

	public void setDealer(DealerState dealer) {
		this.dealer = dealer;
	}

}
