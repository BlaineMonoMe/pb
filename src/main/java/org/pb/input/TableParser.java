package org.pb.input;

/**
 * Begins to do it's job just after chips count was selected
 */
import java.awt.image.BufferedImage;

import org.pb.input.diller.DillerReader;
import org.pb.input.handsCardsReader.CardsOnHandsListener;
import org.pb.input.handsCardsReader.HandsCardsReader;
import org.pb.input.handsStackReader.RealTimeHSR;
import org.pb.input.tableCardsReader.CardsOnTableListener;
import org.pb.input.whooseTurn.MyTurnReader;
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;

public class TableParser {

	// private final int CARDS_ON_TABLE_WIDTH = 400;
	// private final int CARDS_ON_TABLE_HEIGHT

	// private CardTargetManager cardTargetManager;
	// private CardsState cardsOnTableState;
	// private CardsState cardsOnHandsState;

	private boolean amISittingAtTheTop;

	private Coordinates centerOfTheTable;

	private ScreenShootMaker screenShootMaker;

	private CardsOnTableListener cardsOnTableListener;
	private CardsOnHandsListener cardsOnHandsListener;

	private RealTimeHSR myHandsStackReader;
	private RealTimeHSR enemyHandsStackReader;

	private DillerReader dillerReader;
	private MyTurnReader myTurnReader;

	// private Cards cardsOnTable;
	// private Cards cardsOnHands;
	// private Coordinates chatLastItemCoords;

	// private Rectangle tableCardsArea;
	// private Rectangle myCardsArea;

	// private Players players;

	public TableParser() {
		initCoordinates();
		setMyPositionAtTheTable();
		// status = 0;
		// this.players = players;
		// this.cardTargetManager = cardTargetManager;

		screenShootMaker = new ScreenShootMaker();

		dillerReader = new DillerReader(centerOfTheTable);

		myTurnReader = new MyTurnReader(centerOfTheTable);

		myHandsStackReader = new RealTimeHSR(centerOfTheTable,
				amISittingAtTheTop);
		enemyHandsStackReader = new RealTimeHSR(centerOfTheTable,
				!amISittingAtTheTop);

		// cardsOnTableState = new CardsState();
		// cardsOnHandsState = new CardsState();

		// cardsOnTable = new Cards(5);
		// cardsOnHands = new Cards(2);

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

	public void start() {
		// setMyPositionAtTheTable();

		// cardsOnTableListener.start();
		// cardsOnHandsListener.start();
		// tableCardsReader.start();
		// handsCardsReader.start();
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			BufferedImage screen = screenShootMaker.makeScreenShot();

			if (dillerReader.checkForUpdates(screen)) {
				System.out.println(dillerReader.getDillerState());
			}

			if (cardsOnTableListener.isUpdated(screen)) {
				System.out.println("table: "
						+ cardsOnTableListener.getCards(screen));
			}

			if (cardsOnHandsListener.isNewCards(screen)) {
				System.out.println("NEW_GAME");
				System.out.println("hands: "
						+ cardsOnHandsListener.getCards(screen));
			}

			// depends on cardsOnTableListener, so must be after it
			if (myHandsStackReader.isNewTurn(screen, cardsOnTableListener)) {
				System.out.println("  My stack at new game: "
						+ myHandsStackReader.getStackSize());
			}
			if (enemyHandsStackReader.isNewTurn(screen, cardsOnTableListener)) {
				System.out.println("  Enemy stack at new game: "
						+ enemyHandsStackReader.getStackSize());
			}

			if (myTurnReader.isMyNewTurn(screen)) {
				System.out.println("   MY_NEW_TURN");
			}

			/*
			 * if (cardsOnTableState.isChanged()) {
			 * System.out.println("MAIN table cards: " +
			 * cardsOnTableState.getState());
			 * cardsOnTableState.setChanged(false); } if
			 * (cardsOnHandsState.isChanged()) {
			 * System.out.println("MAIN hand cards: " +
			 * cardsOnHandsState.getState());
			 * cardsOnHandsState.setChanged(false); }
			 */
		}

	}

}
