package org.pb.decisionMaker.data;

import java.util.ArrayList;
import java.util.List;

import org.pb.decisionMaker.Action;
import org.pb.decisionMaker.Player;
import org.pb.decisionMaker.Round;
import org.pb.input.dealer.DealerState;
import org.pb.input.state.Cards;

/**
 * Keeps the list of decisions.
 * 
 * @author Yuriy
 * 
 */
public class RoundData {

	private final Round round;
	private Cards tableCards;
	private List<Decision> decisionList;

	/**
	 * For "pre-flop" round constructor only
	 * 
	 * @param round
	 * @param cards
	 */
	public RoundData(int bigBlindes, DealerState dealer) {
		decisionList = new ArrayList<Decision>();
		tableCards = new Cards(1);
		this.round = Round.PRE_FLOP;

		if (dealer == DealerState.ME_DEALER) {
			decisionList.add(new Decision(Player.ME, Action.RAISE,
					bigBlindes / 2));
			decisionList.add(new Decision(Player.ENEMY, Action.RAISE,
					bigBlindes));
		} else {
			decisionList.add(new Decision(Player.ENEMY, Action.RAISE,
					bigBlindes / 2));
			decisionList.add(new Decision(Player.ME, Action.RAISE, bigBlindes));
		}
		System.out.println("    creating new ROUND_DATA: round=" + round
				+ ", no cards, class=");
	}

	/**
	 * For any round except "pre-flop"
	 * 
	 * @param round
	 * @param cards
	 */
	public RoundData(Round round, Cards cards) {
		decisionList = new ArrayList<Decision>();
		tableCards = new Cards(cards);
		// System.out.println("___" + tableCards);
		this.round = round;
		System.out.println("    creating new ROUND_DATA: round=" + round
				+ ", cards=" + tableCards);
		// System.out.println("===" + this);
	}

	@Override
	public String toString() {
		String info = "  |round data| " + " [round=" + round
				+ ",  table cards=" + tableCards + "\n";
		String list = decisionList.toString() + "\n";
		return "\n" + info + list + "\n";
	}

	public int getPlayerPartTableBank(Player Player) {
		int playerBankPart = 0;
		for (int i = 0; i < decisionList.size(); i++) {
			if (decisionList.get(i).getPlayer() == Player) {
				playerBankPart += decisionList.get(i).getStackSize();
			}
		}
		return playerBankPart;
	}

	public int getCurrentTableBank() {
		int currBankSize = 0;
		for (int i = 0; i < decisionList.size(); i++) {
			currBankSize += decisionList.get(i).getStackSize();
		}
		return currBankSize;
	}

	public Decision getLastEnemyDecision() throws Exception {
		for (int i = decisionList.size() - 1; i > -1; i--) {
			if (decisionList.get(i).getPlayer() == Player.ENEMY) {
				return decisionList.get(i);
			}
		}
		throw new Exception("there are no any eneme's decisions");
	}

	public Cards getTableCards() {
		return tableCards;
	}

	public void addNewDecision(Decision decision) {
		System.out.println("            decisions = " + decisionList.size());
		/*
		 * if (decision.getAction() == Action.CALL) { //
		 * System.out.println("decision list size+++" + // decisionList.size());
		 * Decision beforeDecision = decisionList.get(decisionList.size() - 1);
		 * int beforeRaiseSize = beforeDecision.getStackSize();
		 * decision.setStackSize(beforeRaiseSize); }
		 */
		decisionList.add(decision);
	}

	public Decision getDecision(int arg0) {
		return decisionList.get(arg0);
	}

	public boolean isNoDecisions() {
		return decisionList.isEmpty();
	}

	public int getDecisiosCount() {
		return decisionList.size();
	}

	public Round getRound() {
		return round;
	}

	public Round getNextRound() throws Exception {
		switch (round) {
		case PRE_FLOP:
			return Round.FLOP;
		case FLOP:
			return Round.TURN;
		case TURN:
			return Round.RIVER;
		case RIVER:
			throw new Exception("there is no round after river");
		}
		return null;
	}

}
