package org.pb.inputMessagesAnalyzer;

import org.pb.decisionMaker.DecisionMaker;
import org.pb.input.dealer.DealerState;
import org.pb.input.state.Cards;
import org.pb.inputOutputUtil.Coordinates;

/**
 * As a field-member Decision Making subsystem class (reference on it) must be
 * present.
 * 
 * @author Yuriy
 * 
 */

public class TableMessagesParser {

	private StackSize myStackSize;
	private StackSize enemyStackSize;
	private Cards cardsOnTable;
	private Cards cardsOnHands;
	private Cards enemyCards;
	private DealerState dealerState;
	private TableStack tableStack;

	/**
	 * if my or enemy's stack is up. positive = true, zero = false; 2 - because
	 * two methods must check it and make it false (this var becomes false (or
	 * 0) after each of those methods check it)
	 */
	private int isStackUp = 2;

	/**
	 * if one of players raises this variable contains the value of raise (in
	 * chips). In this case we can watch when enemy falls and this value returns
	 * to players stack
	 */
	/*
	 * private int raiseStackSize = 0;
	 * 
	 * private int winningStack = 0;
	 */
	private HandResult gameResult;
	private BeginHandData beginHandData;

	private DecisionMaker decisionMaker;

	public TableMessagesParser(Coordinates centerOfTheTable) {
		myStackSize = new StackSize();
		enemyStackSize = new StackSize();
		cardsOnTable = new Cards(5);
		cardsOnHands = new Cards(2);
		enemyCards = new Cards(2);
		tableStack = new TableStack();
		gameResult = new HandResult();

		beginHandData = new BeginHandData();
		decisionMaker = new DecisionMaker(centerOfTheTable);
	}

	/**
	 * is called when PS is waiting for your move
	 */
	public void myNewTurn() {
		System.out.println("my new turn");
	}

	/**
	 * is called when dealer changes
	 * 
	 * @param dealerState
	 */
	public void newDealerState(DealerState dealerState) {
		this.dealerState = dealerState;
		beginHandData.setDealer(dealerState);
		// System.out.println("new dealer state " + dealerState);
		tryToSendAndNullNewHandData();
	}

	/**
	 * is called when my stack size was changed
	 * 
	 * @param stackSize
	 *            - new stack size
	 */
	public void myNewStackSize(int stackSize) {
		if (myStackSize.isInitialized() == false) {
			myStackSize.setCurrentStackSize(stackSize);
			myStackSize.setGameBeginStackSize(stackSize);
			// System.out.println("my begin stack size " + stackSize);
		} else {
			int myStackDifferance = myStackSize.getCurrentStackSize()
					- stackSize;

			// if my stack becomes bigger
			if (myStackDifferance < 0) {
				// if (raiseStackSize == 0) {
				gameResult.incrementWinningStack(-myStackDifferance);
				gameResult.setWinner(HandWinner.MY);

				// System.out.println("i win " + (-myStackDifferance));
				isStackUp = 2;
				// }
			}
			// if my stack becomes smaller
			else {
				if (isStackUp > 0) {
					isStackUp--;
					int blindes = getMyBlindes(myStackDifferance);
					// System.out.println("blindes are " + (blindes / 2) + "/"
					// + blindes);
					// System.out.println("my auto blindes "
					// + getMyAutoBlindes(blindes));
					tableStack.setMyPart(getMyAutoBlindes(blindes));

					beginHandData.setMyStackSize(stackSize
							+ getMyAutoBlindes(blindes));
					tryToSendAndNullNewHandData();

				} else {
					tableStack.incrementMyPart(myStackDifferance);
					// System.out.println("   " + tableStack);
					if (tableStack.isPlayerCalling()) {
						System.out.println("i am calling");
						// raiseStackSize = 0;
					} else {
						System.out.println("i am raising to "
								+ myStackDifferance);
						// raiseStackSize = myStackDifferance;

					}
				}
			}
			myStackSize.setCurrentStackSize(stackSize);
		}
	}

	/**
	 * is called when enemy stack size was changed
	 * 
	 * @param stackSize
	 *            - new stack size
	 */
	public void enemyNewStackSize(int stackSize) {
		if (enemyStackSize.isInitialized() == false) {
			enemyStackSize.setCurrentStackSize(stackSize);
			enemyStackSize.setGameBeginStackSize(stackSize);
			// System.out.println("enemy begin stack size " + stackSize);
		} else {
			int enemyStackDifference = enemyStackSize.getCurrentStackSize()
					- stackSize;

			// if enemy stack becomes bigger
			if (enemyStackDifference < 0) {
				// if (raiseStackSize == 0) {
				gameResult.incrementWinningStack(-enemyStackDifference);
				gameResult.setWinner(HandWinner.ENEMY);

				// System.out.println("enemy wins " + (-enemyStackDifference));
				isStackUp = 2;
				// }
			}
			// if enemy stack becomes smaller
			else {
				// if it is auto blindes
				if (isStackUp > 0) {
					isStackUp--;
					int blindes = getEnemyBlindes(enemyStackDifference);
					System.out.println("enemy auto blindes "
							+ getEnemyAutoBlindes(blindes));
					tableStack.setEnemyPart(getEnemyAutoBlindes(blindes));

					beginHandData.setEnemyStackSize(stackSize
							+ getEnemyAutoBlindes(blindes));
					beginHandData.setBigBlindes(blindes);

					tryToSendAndNullNewHandData();

				} else {
					tableStack.incrementEnemyPart(enemyStackDifference);
					// System.out.println("   " + tableStack);
					if (tableStack.isPlayerCalling()) {
						System.out.println("enemy is calling");
						// raiseStackSize = 0;
					} else {
						System.out.println("enemy is raising to "
								+ enemyStackDifference);
						// raiseStackSize = enemyStackDifferance;
					}
				}
			}
			enemyStackSize.setCurrentStackSize(stackSize);
		}
	}

	private int getMyBlindes(int stackDifferance) {
		if (dealerState == DealerState.ME_DEALER) {
			return stackDifferance * 2;
		} else {
			return stackDifferance;
		}
	}

	private int getEnemyBlindes(int stackDifference) {
		if (dealerState == DealerState.ME_DEALER) {
			return stackDifference;
		} else {
			return stackDifference * 2;
		}
	}

	private int getMyAutoBlindes(int blindes) {
		if (dealerState == DealerState.ME_DEALER) {
			return blindes / 2;
		} else {
			return blindes;
		}
	}

	private int getEnemyAutoBlindes(int blindes) {
		if (dealerState == DealerState.ME_DEALER) {
			return blindes;
		} else {
			return blindes / 2;
		}
	}

	/**
	 * is called when new cards appears(or disappears) in your hands
	 * 
	 * @param handCards
	 */
	public void myNewHandsCards(Cards cards) {
		if (cards.getCardList().size() == 0) {
			cardsOnHands.removeCards();
			System.out.println("hands cards disappears");
		} else {
			cardsOnHands = cards;
			System.out.println("new turn");
			// System.out.println("my new hands cards " + cardsOnHands);
			// System.out.print("   ");
			System.out.println(gameResult);
			gameResult.reset();

			/**
			 * 
			 */
			beginHandData.setMyCards(cardsOnHands);
			tryToSendAndNullNewHandData();

		}
	}

	/**
	 * is called when enemy shows his cards
	 * 
	 * @param handCards
	 */
	public void enemyNewCards(Cards cards) {
		if (cards.getCardList().size() == 0) {
			enemyCards.removeCards();
			// System.out.println("enemy cards disappears");
		} else {
			enemyCards = cards;
			System.out.println("enemy hands cards " + enemyCards);
		}
	}

	/**
	 * is called when new cards appears(or disappears) on table
	 * 
	 * @param cards
	 */
	public void newTableCards(Cards tableCards) {
		if (tableCards.getCardList().size() == 0) {
			cardsOnTable.removeCards();
			// System.out.println("table cards disappears");
		} else {
			cardsOnTable = tableCards;
			// System.out.println("my new hands cards " + tableCards);
		}
	}

	private boolean isNewHandDataReady(BeginHandData newHandData) {

		if (newHandData.getBigBlindes() == 0) {
			System.out.println("big blindes = 0");
			return false;
		}
		if (newHandData.getEnemyStackSize() == 0) {
			System.out.println("enemy stack = 0");
			return false;
		}
		if (newHandData.getMyStackSize() == 0) {
			System.out.println("bmy stack = 0");
			return false;
		}
		if (newHandData.getDealer() == null) {
			System.out.println("diller = null");
			return false;
		}
		if (newHandData.getMyCards() == null) {
			System.out.println("my cards = null");
			return false;
		}

		return true;
	}

	private void tryToSendAndNullNewHandData() {
		if (isNewHandDataReady(beginHandData)) {
			System.out.println(beginHandData);
			decisionMaker.setBeginHandData(beginHandData);
			beginHandData = new BeginHandData();
		}
	}

}
