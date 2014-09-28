package org.pb.decisionMaker;

import org.pb.decisionImplementation.ActionExecutor;
import org.pb.decisionMaker.data.GameData;
import org.pb.decisionMaker.data.HandData;
import org.pb.inputMessagesAnalyzer.BeginHandData;
import org.pb.inputOutputUtil.Coordinates;
import org.pb.state.Cards;

/**
 * Top-level class for DM subsystem
 * 
 * @author Yuriy
 * 
 */
public class DecisionMaker {

	// for executing decisions
	private ActionExecutor actionExecutor;

	private GameData gameData;

	public DecisionMaker(Coordinates centerOfTheTable) {
		actionExecutor = new ActionExecutor(centerOfTheTable);
		gameData = new GameData();
	}

	// is called when my mew turn comes
	public void makeDecision() {
	}

	// is called when new hand begins
	public void setBeginHandData(BeginHandData beginHandData) {
		gameData.getHandDataList().add(new HandData(beginHandData));
		System.out.println(gameData.getHandDataList().size());
	}

	public void setNewTableCards(Cards cards) {
	}

	public void setEnemyCards(Cards cards) {
	}

	public void enemyCallsHandle() {
	}

	public void enemyRaiseHandle(int raiseSize) {
	}

	public void meCallsHandle() {
	}

	public void meRaiseHandle(int raiseSize) {
	}

	public void setGameResult(boolean amIWinner, int winStackSize) {
	}

}
