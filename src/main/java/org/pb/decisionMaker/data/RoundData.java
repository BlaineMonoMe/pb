package org.pb.decisionMaker.data;

import java.util.ArrayList;

import org.pb.state.Cards;

/**
 * Keeps the list of decisions.
 * 
 * @author Yuriy
 * 
 */
public class RoundData {

	private ArrayList<Decision> decisionList;
	private Cards tableCards;

	public RoundData() {
		decisionList = new ArrayList<Decision>();
		tableCards = new Cards(3);
	}

	public Cards getTableCards() {
		return tableCards;
	}

	public void setTableCards(Cards tableCards) {
		this.tableCards = tableCards;
	}

	public void addNewDecision(Decision decision) {
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

}
