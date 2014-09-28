package org.pb.decisionMaker.data;

import java.util.ArrayList;
import java.util.List;

import org.pb.input.dealer.DealerState;
import org.pb.input.state.Cards;
import org.pb.inputMessagesAnalyzer.HandResult;
import org.pb.inputMessagesAnalyzer.StartHandData;

public class HandData {

	/**
	 * this fields can't change. Every new game should be created new dataGame
	 * object
	 */
	private final int myStackSize;
	private final int enemyStackSize;
	private final int bigBlindes;
	private final Cards myCards;
	private final DealerState dealer;

	private Cards enemysCards;
	private HandResult handResult;

	private List<RoundData> roundDataList;

	public HandData(int myStackSize, int enemyStackSize, int bigBlindes,
			Cards myCards, DealerState dealer) {
		this.myStackSize = myStackSize;
		this.enemyStackSize = enemyStackSize;
		this.bigBlindes = bigBlindes;
		this.myCards = myCards;
		this.dealer = dealer;

		roundDataList = new ArrayList<RoundData>();
	}

	public HandData(StartHandData newHandData) {
		this.myStackSize = newHandData.getMyStackSize();
		this.enemyStackSize = newHandData.getEnemyStackSize();
		this.bigBlindes = newHandData.getBigBlindes();
		this.myCards = newHandData.getMyCards();
		this.dealer = newHandData.getDealer();

		roundDataList = new ArrayList<RoundData>();
	}

	public void addRoundData(RoundData roundData) {
		roundDataList.add(roundData);
	}

	public List<RoundData> getRoundData() {
		return roundDataList;
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
