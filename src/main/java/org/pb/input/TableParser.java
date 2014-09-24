package org.pb.input;

/**
 * Begins to do it's job just after chips count was selected
 */
import java.awt.image.BufferedImage;

import org.pb.input.diller.DillerReader;
import org.pb.input.diller.DillerState;
import org.pb.input.handsCardsReader.CardsOnHandsListener;
import org.pb.input.handsStackReader.RealTimeHSR;
import org.pb.input.tableCardsReader.CardsOnTableListener;
import org.pb.input.whooseTurn.MyTurnReader;
import org.pb.inputMessagesAnalyzer.TableMessagesParser;
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;


public class TableParser {

	private boolean amISittingAtTheTop;

	private Coordinates centerOfTheTable;

	private ScreenShootMaker screenShootMaker;

	private CardsOnTableListener cardsOnTableListener;
	private CardsOnHandsListener cardsOnHandsListener;

	private RealTimeHSR myHandsStackReader;
	private RealTimeHSR enemyHandsStackReader;

	private DillerReader dillerReader;

	private MyTurnReader myTurnReader;

	public TableParser() {
		initCoordinates();
		setMyPositionAtTheTable();

		screenShootMaker = new ScreenShootMaker();

		dillerReader = new DillerReader(centerOfTheTable);

		myTurnReader = new MyTurnReader(centerOfTheTable);

		myHandsStackReader = new RealTimeHSR(centerOfTheTable,
				amISittingAtTheTop);
		enemyHandsStackReader = new RealTimeHSR(centerOfTheTable,
				!amISittingAtTheTop);

		cardsOnTableListener = new CardsOnTableListener(centerOfTheTable);
		cardsOnHandsListener = new CardsOnHandsListener(centerOfTheTable,
				amISittingAtTheTop);

		System.out.println("center of the table: " + centerOfTheTable);
	}

	private void initCoordinates() {
		centerOfTheTable = IOUtil.getCenterCoordinates("res\\images\\CHAT.PNG");
		centerOfTheTable.change(194, -152);
		centerOfTheTable.change(173, -64);
	}

	private void setMyPositionAtTheTable() {
		Coordinates myCoords = IOUtil
				.getCenterCoordinates("res\\images\\MY_NAME.PNG");
		if (myCoords.getY() > centerOfTheTable.getY()) {
			amISittingAtTheTop = false;
		} else {
			amISittingAtTheTop = true;
		}

		System.out.println("I am sitting at the top: " + amISittingAtTheTop);
	}

	public void start(TableMessagesParser tableMessagesParser) {

		while (true) {

			BufferedImage screen = screenShootMaker.makeScreenShot();

			// who is diller
			if (dillerReader.checkForUpdates(screen)) {
				DillerState dillerState = dillerReader.getDillerState();
				/*
				 * String dillerMsg = null; switch (dillerState) { case
				 * ME_DILLER: dillerMsg = "diller: me"; break; case
				 * ENEMY_DILLER: dillerMsg = "diller: enemy"; break; case
				 * NOBODY_DILLER: dillerMsg = "diller: nobody"; break; }
				 * System.out.println(dillerMsg);
				 */
				tableMessagesParser.newDillerState(dillerState);
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
