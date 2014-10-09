package org.pb.decisionMaker;

import org.apache.log4j.Logger;
import org.pb.appl.App;
import org.pb.decisionImplementation.ActionExecutor;
import org.pb.decisionMaker.data.Decision;
import org.pb.decisionMaker.data.GameData;
import org.pb.decisionMaker.data.HandData;
import org.pb.decisionMaker.data.RoundData;
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

	final static Logger logger = Logger.getLogger(DecisionMaker.class);

	// for executing decisions
	private ActionExecutor actionExecutor;

	private GameData gameData;

	public DecisionMaker(Coordinates centerOfTheTable) {
		actionExecutor = new ActionExecutor(centerOfTheTable);
		gameData = new GameData();
	}

	// is called when my new turn comes
	public void makeDecision() {
		System.out.println("MYTURN");
	}

	// is called when new hand begins. creates a new hand data
	public void setStartHandData(StartHandData startHandData) {
		gameData.getHandDataList().add(new HandData(startHandData));
		// System.out.println(startHandData);
	}

	public void setGameResult(HandResult handResult) {
		// maybe, we will set not to current, but to current-1
		gameData.getCurrentHandData().setHandResult(handResult);
		logger.info(gameData.getCurrentHandData());

		// System.out.println(handResult);
		// System.out.println(gameData.getCurrentHandData());
	}

	// creates a new round
	public void setNewTableCards(Cards cards) {
		HandData currHandData = gameData.getCurrentHandData();
		// System.out.println("crrr " + currHandData.getRoundData());
		currHandData.addNextRoundData(cards);
		// currHandData
		//System.out.println("new table cards: " + cards);
	}

	public void setEnemyCards(Cards cards) {
		HandData currHandData = gameData.getCurrentHandData();
		currHandData.setEnemysCards(cards);
		// System.out.println("enemy cards: " + cards);
	}

	// player decision handle. creates a new decision

	public void enemyCallsHandle() {

		RoundData currRound = gameData.getCurrentHandData()
				.getCurrentRoundData();
		currRound.addNewDecision(new Decision(Player.ENEMY, Action.CALL));
		// System.out.println("enemy is calling...");

	}

	public void enemyRaiseHandle(int raiseSize) {

		RoundData currRound = gameData.getCurrentHandData()
				.getCurrentRoundData();
		currRound.addNewDecision(new Decision(Player.ENEMY, Action.RAISE,
				raiseSize));

		// System.out.println("enemy is rasing (" + raiseSize + ")");
	}

	public void enemyFallsHandle() {

		RoundData currRound = gameData.getCurrentHandData()
				.getCurrentRoundData();
		currRound.addNewDecision(new Decision(Player.ENEMY, Action.FOLD));

		// System.out.println("enemy falls!");
	}

	public void meCallsHandle() {

		RoundData currRound = gameData.getCurrentHandData()
				.getCurrentRoundData();
		currRound.addNewDecision(new Decision(Player.ME, Action.CALL));

		// System.out.println("I am calling...");
	}

	public void meRaiseHandle(int raiseSize) {

		RoundData currRound = gameData.getCurrentHandData()
				.getCurrentRoundData();
		currRound.addNewDecision(new Decision(Player.ME, Action.RAISE,
				raiseSize));

		// System.out.println("I am rasing (" + raiseSize + ")");
	}

	public void meFallsHandle() {

		RoundData currRound = gameData.getCurrentHandData()
				.getCurrentRoundData();
		currRound.addNewDecision(new Decision(Player.ME, Action.FOLD));

		// System.out.println("I fall :(");
	}

}
