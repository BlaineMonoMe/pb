package org.pb.input;

/**
 * Begins to do it's job just after chips count was selected
 */
import org.pb.input.dealer.DealerReader;
import org.pb.input.dealer.DealerState;
import org.pb.input.enemyCardsReader.EnemyCardsListener;
import org.pb.input.global.FilePaths;
import org.pb.input.handsStackReader.RealTimeHSR;
import org.pb.input.myCardsReader.CardsOnHandsListener;
import org.pb.input.tableCardsReader.CardsOnTableListener;
import org.pb.input.whoseTurn.MyTurnReader;
import org.pb.inputMessagesAnalyzer.TableMessagesParser;
import org.pb.inputOutputUtil.Coordinates;
import org.pb.inputOutputUtil.IOUtil;

import java.awt.image.BufferedImage;

public class TableParser {

	private boolean amISittingAtTheTop;

	private Coordinates centerOfTheTable;

	private ScreenshotMaker screenshotMaker;

	private CardsOnTableListener cardsOnTableListener;
	private CardsOnHandsListener cardsOnHandsListener;
	private EnemyCardsListener enemyCardsListener;

	private RealTimeHSR myHandsStackReader;
	private RealTimeHSR enemyHandsStackReader;

	private DealerReader dealerReader;

	private MyTurnReader myTurnReader;

	public TableParser() {
		initCoordinates();
		setMyPositionAtTheTable();

		screenshotMaker = new ScreenshotMaker();

		dealerReader = new DealerReader(centerOfTheTable);

		myTurnReader = new MyTurnReader(centerOfTheTable);

		myHandsStackReader = new RealTimeHSR(centerOfTheTable,
				amISittingAtTheTop);
		enemyHandsStackReader = new RealTimeHSR(centerOfTheTable,
				!amISittingAtTheTop);

		cardsOnTableListener = new CardsOnTableListener(centerOfTheTable);
		cardsOnHandsListener = new CardsOnHandsListener(centerOfTheTable,
				amISittingAtTheTop);
		enemyCardsListener = new EnemyCardsListener(centerOfTheTable,
				amISittingAtTheTop);

		System.out.println("center of the table: " + centerOfTheTable);
	}

	private void initCoordinates() {
		centerOfTheTable = IOUtil.getCenterCoordinates(FilePaths.CHAT);
		centerOfTheTable.change(194, -152);
		centerOfTheTable.change(173, -64);
	}

	private void setMyPositionAtTheTable() {
		Coordinates myCoords = IOUtil
				.getCenterCoordinates(FilePaths.MY_NAME);
		if (myCoords.getY() > centerOfTheTable.getY()) {
			amISittingAtTheTop = false;
		} else {
			amISittingAtTheTop = true;
		}

		System.out.println("I am sitting at the top: " + amISittingAtTheTop);
	}

	public void start(TableMessagesParser tableMessagesParser) {

		while (true) {

			BufferedImage screen = screenshotMaker.makeScreenShot();

			// who is dealer
			if (dealerReader.checkForUpdates(screen)) {
				DealerState dealerState = dealerReader.getDealerState();
				/*
				 * String dealerMsg = null; switch (dealerState) { case
				 * ME_DEALER: dealerMsg = "dealer: me"; break; case
				 * ENEMY_DEALER: dealerMsg = "dealer: enemy"; break; case
				 * NOBODY_DEALER: dealerMsg = "dealer: nobody"; break; }
				 * System.out.println(dealerMsg);
				 */
				tableMessagesParser.newDealerState(dealerState);
			}

			// table cards reading
			if (cardsOnTableListener.isUpdated(screen)) {
				/*
				 * System.out.println("table: " +
				 * cardsOnTableListener.getCards(screen));
				 */
				tableMessagesParser.newTableCards(cardsOnTableListener
						.getCards(screen));
			}
			// TODO
			// enemy cards reader
			if (enemyCardsListener.isNewCards(screen)) {
				// System.out.println("NEW_GAME");
				/*
				 * System.out.println("hands: " +
				 * cardsOnHandsListener.getCards(screen));
				 */
				tableMessagesParser.enemyNewCards(enemyCardsListener
						.getCards(screen));

			}

			// hands cards reading
			if (cardsOnHandsListener.isNewCards(screen)) {
				// System.out.println("NEW_GAME");
				/*
				 * System.out.println("hands: " +
				 * cardsOnHandsListener.getCards(screen));
				 */
				tableMessagesParser.myNewHandsCards(cardsOnHandsListener
						.getCards(screen));
			}

			// my stack reading
			if (myHandsStackReader.isUpdated(screen)) {
				/*
				 * System.out.println("  my stack : " +
				 * myHandsStackReader.getStackSize());
				 */
				tableMessagesParser.myNewStackSize(myHandsStackReader
						.getStackSize());
			}

			// enemy stack reading
			if (enemyHandsStackReader.isUpdated(screen)) {
				/*
				 * System.out.println("  enemy stack : " +
				 * enemyHandsStackReader.getStackSize());
				 */
				tableMessagesParser.enemyNewStackSize(enemyHandsStackReader
						.getStackSize());
			}

			// checking if it is my turn
			if (myTurnReader.isMyNewTurn(screen)) { //
				// System.out.println("my turn");
				tableMessagesParser.myNewTurn();
			}

		}

	}
}
