package org.pb.decisionMaker.data;

import java.util.ArrayList;
import java.util.List;

import org.pb.decisionMaker.Player;
import org.pb.decisionMaker.Round;
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

	/*
	 * public HandData() { roundDataList = new ArrayList<RoundData>();
	 * roundDataList.add(new RoundData(bigBlindes, dealer)); }
	 */

	public HandData(StartHandData newHandData) {
		this.myStackSize = newHandData.getMyStackSize();
		this.enemyStackSize = newHandData.getEnemyStackSize();
		this.bigBlindes = newHandData.getBigBlindes();
		this.myCards = newHandData.getMyCards();
		this.dealer = newHandData.getDealer();

		roundDataList = new ArrayList<RoundData>();
		roundDataList.add(new RoundData(bigBlindes, dealer));

		System.out.println("    creating new HAND_DATA: " + newHandData
				+ ", class=");
	}

	@Override
	public String toString() {
		String info = "|hand data| [my stack=" + myStackSize + ", enemy stack="
				+ enemyStackSize + ", big blindes=" + bigBlindes + ", dealer="
				+ dealer + "]\n";
		String myC = "my cards = " + myCards.toString() + "\n";
		String eC = "enemy cards = poof";// + enemysCards.toString() + "\n";
		String handR = "";// "hand result = " + handResult.toString() + "\n";
		String list = roundDataList.toString() + "\n";

		return info + myC + eC + handR + list;
	}

	public int getCurrentPlayerStack(Player player) {
		int playerChipsOnTable = 0;
		for (int i = 0; i < roundDataList.size(); i++) {
			playerChipsOnTable += roundDataList.get(i).getPlayerPartTableBank(
					player);
		}
		if (player == Player.ME) {
			return myStackSize - playerChipsOnTable;
		} else {
			return enemyStackSize - playerChipsOnTable;
		}
	}

	public Cards getCurrentTableCards() {
		Cards currentCards = new Cards(5);
		for (int round = 0; round < roundDataList.size(); round++) {
			for (int card = 0; card < roundDataList.get(round).getTableCards()
					.getCardsCount(); card++) {
				currentCards.addCard(roundDataList.get(round).getTableCards()
						.getCardList().get(card));
			}
		}
		return currentCards;
	}

	public RoundData getCurrentRoundData() {
		return roundDataList.get(roundDataList.size() - 1);
	}

	public void addRoundData(RoundData roundData) {
		roundDataList.add(roundData);
		/*if (roundDataList.size() > 1) {
			System.out.println("=====================");
			System.out.println(roundDataList.get(roundDataList.size() - 2));
			System.out.println("=====================");
		}*/
	}

	public void addNextRoundData(Cards cards) {
		RoundData currRoundData = getCurrentRoundData();
		Round nextRound = null;
		try {
			nextRound = currRoundData.getNextRound();
		} catch (Exception e) {
			e.printStackTrace();
		}
		roundDataList.add(new RoundData(nextRound, cards));
	}

	/**
	 * HandResult getter & setter
	 */
	public HandResult getHandResult() {
		return handResult;
	}

	public void setHandResult(HandResult handResult) {
		this.handResult = handResult;
		System.out.println("    setting game result: " + handResult);

	}

	/**
	 * Enemy cards getter & setter
	 */
	public Cards getEnemysCards() {
		return enemysCards;
	}

	public void setEnemysCards(Cards enemysCards) {
		this.enemysCards = enemysCards;
	}

	/**
	 * Final fields getters
	 */
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
