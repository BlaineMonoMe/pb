package org.pb.input;

/**
 * Begins to do it's job just after chips count was selected
 */
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.input_output_util.Rectangle;
import org.pb.state.CardsOnTableState;
import org.pb.state.Players;
import org.pb.system_data.CardTargetManager;

public class TableParser {

	private final int CARDS_ON_TABLE_WIDTH = 400;
	// private final int CARDS_ON_TABLE_HEIGHT

	/**
	 * 0 - unnown. 1 - waiting for a new game
	 */
	private int status;

	private Coordinates centerOfTheTable;
	private CardTargetManager cardTargetManager;
	private CardsOnTableState cardsOnTableState;
	private CardsOnTableListener cardsOnTableListener;
	private ScreenShootMaker screenShootMaker;
	// private Coordinates chatLastItemCoords;

	private Rectangle tableCardsArea;
	private Rectangle myCardsArea;

	private Players players;

	public TableParser(Players players, CardTargetManager cardTargetManager) {
		initCoordinates();
		status = 0;
		this.players = players;
		this.cardTargetManager = cardTargetManager;
		screenShootMaker = new ScreenShootMaker();
		cardsOnTableState = new CardsOnTableState();
		cardsOnTableListener = new CardsOnTableListener(cardsOnTableState,
				centerOfTheTable, screenShootMaker);

		// cardsOnTableState = CardsOnTableState.EMPTY;
		System.out.println("center of the table: " + centerOfTheTable);
	}

	private void initCoordinates() {
		centerOfTheTable = IOUtil.getCenterCoordinates("res\\images\\CHAT.PNG");
		centerOfTheTable.change(194, -152);
		centerOfTheTable.change(173, -64);

		/*
		 * chatLastItemCoords = IOUtil
		 * .getCenterCoordinates("res\\images\\CHAT.PNG");
		 * chatLastItemCoords.change(0, 86);
		 */
	}

	private void initTableCardsArea() {

	}

	/*
	 * private void saveLastDillerMessage() { String msg =
	 * chatReader.readLastDillerMessage(); if (msg != null) { if
	 * (!chatMessages.get(chatMessages.size() - 1).equals(msg)) {
	 * chatMessages.add(msg); } } }
	 */

	/*
	 * private void saveLastDillerMessages() { ArrayList<String> chatList =
	 * chatReader.readLastDillerMessages(); ArrayList<String> reversedList = new
	 * ArrayList<String>(); for (int i = 0; i < chatList.size(); i++) {
	 * reversedList.add(chatList.get(chatList.size() - i - 1)); }
	 * 
	 * outer: for (int i = reversedList.size(); i > 0; i--) { for (int j = 0; j
	 * < i; j++) { if (!chatMessages.get(chatMessages.size() - 1 - j).equals(
	 * reversedList.get(reversedList.size() - 1 - j))) { continue outer; } } for
	 * (int k = i; k < reversedList.size(); k++) {
	 * chatMessages.add(reversedList.get(k)); } } }
	 */

	private void setMyPositionAtTheTable(Players players) {
		Coordinates myCoords = IOUtil
				.getCenterCoordinates("res\\images\\MY_NAME.PNG");
		if (myCoords.getY() > centerOfTheTable.getY()) {
			players.setiAmSittingOnTheTop(false);
		} else {
			players.setiAmSittingOnTheTop(true);
		}

		System.out.println(players.isiAmSittingOnTheTop());
	}

	public void start() {
		setMyPositionAtTheTable(players);
		screenShootMaker.start();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		cardsOnTableListener.start();
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cardsOnTableState.isChanged()) {
				System.out.println("--->" + cardsOnTableState.getState());
				cardsOnTableState.setChanged(false);
			}
		}

	}

}
