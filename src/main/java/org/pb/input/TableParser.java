package org.pb.input;

/**
 * Begins to do it's job just after chips count was selected
 */
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.input_output_util.Rectangle;
import org.pb.state.Cards;
import org.pb.state.CardsState;
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
	private CardsState cardsOnTableState;
	private CardsState cardsOnHandsState;
	private CardsOnTableListener cardsOnTableListener;
	private CardsOnHandsListener cardsOnHandsListener;
	private ScreenShootMaker screenShootMaker;
	private TableCardsReader tableCardsReader;
	private HandsCardsReader handsCardsReader;
	private Cards cardsOnTable;
	private Cards cardsOnHands;
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

		cardsOnTableState = new CardsState();
		cardsOnHandsState = new CardsState();

		cardsOnTable = new Cards(5);
		cardsOnHands = new Cards(2);

		cardsOnTableListener = new CardsOnTableListener(cardsOnTableState,
				centerOfTheTable, screenShootMaker);
		cardsOnHandsListener = new CardsOnHandsListener(cardsOnHandsState,
				centerOfTheTable, screenShootMaker, players);

		tableCardsReader = new TableCardsReader(centerOfTheTable, cardsOnTable,
				cardsOnTableState, screenShootMaker);
		handsCardsReader = new HandsCardsReader(centerOfTheTable, cardsOnHands,
				cardsOnHandsState, screenShootMaker, players);

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

	private void setMyPositionAtTheTable(Players players) {
		Coordinates myCoords = IOUtil
				.getCenterCoordinates("res\\images\\MY_NAME.PNG");
		if (myCoords.getY() > centerOfTheTable.getY()) {
			players.setiAmSittingOnTheTop(false);
		} else {
			players.setiAmSittingOnTheTop(true);
		}

		System.out.println("I am sitting at the top: "
				+ players.isiAmSittingOnTheTop());
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
		cardsOnHandsListener.start();
		tableCardsReader.start();
		handsCardsReader.start();
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cardsOnTableState.isChanged()) {
				System.out.println("MAIN table cards: "
						+ cardsOnTableState.getState());
				cardsOnTableState.setChanged(false);
			}
			if (cardsOnHandsState.isChanged()) {
				System.out.println("MAIN hand cards: "
						+ cardsOnHandsState.getState());
				cardsOnHandsState.setChanged(false);
			}
		}

	}

}
