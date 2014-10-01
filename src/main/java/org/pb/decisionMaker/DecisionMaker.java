package org.pb.decisionMaker;

import org.pb.decisionImplementation.ActionExecutor;
import org.pb.decisionMaker.data.GameData;
import org.pb.decisionMaker.data.HandData;
import org.pb.input.state.Cards;
import org.pb.inputMessagesAnalyzer.HandResult;
import org.pb.inputMessagesAnalyzer.StartHandData;
import org.pb.inputOutputUtil.Coordinates;

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

	// is called when my new turn comes
	public void makeDecision() {
		System.out.println("hmm... I have to make some decision? hmm...");
	}

	// is called when new hand begins. creates a new hand data
	public void setStartHandData(StartHandData startHandData) {
		gameData.getHandDataList().add(new HandData(startHandData));
		System.out.println(startHandData);
	}

	public void setGameResult(HandResult handResult) {
		System.out.println(handResult);
	}

	// creates a new round
	public void setNewTableCards(Cards cards) {
		System.out.println("new table cards: " + cards);
	}

	public void setEnemyCards(Cards cards) {
		System.out.println("enemy cards: " + cards);
	}

	// player decision handle. creates a new decision

	public void enemyCallsHandle() {
		System.out.println("enemy is calling...");
	}

	public void enemyRaiseHandle(int raiseSize) {
		System.out.println("enemy is rasing (" + raiseSize + ")");
	}

	public void enemyFallsHandle() {
		System.out.println("enemy falls!");
	}

	public void meCallsHandle() {
		System.out.println("I am calling...");
	}

	public void meRaiseHandle(int raiseSize) {
		System.out.println("I am rasing (" + raiseSize + ")");
	}

	public void meFallsHandle() {
		System.out.println("I fall :(");
	}

}
