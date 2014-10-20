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

	private boolean isFirstHand = true;

	private HandResult handResult;
	private StartHandData startHandData;

	private DecisionMaker decisionMaker;

	public TableMessagesParser(Coordinates centerOfTheTable) {
		myStackSize = new StackSize();
		enemyStackSize = new StackSize();
		cardsOnTable = new Cards(5);
		cardsOnHands = new Cards(2);
		enemyCards = new Cards(2);
		tableStack = new TableStack();
		handResult = new HandResult();

		startHandData = new StartHandData();
		decisionMaker = new DecisionMaker(centerOfTheTable);
	}

	/**
	 * is called when PS is waiting for your move
	 */
	public void myNewTurn() {
		decisionMaker.makeDecision();
	}

	/**
	 * is called when dealer changes
	 * 
	 * @param dealerState
	 */
	public void newDealerState(DealerState dealerState) {
		this.dealerState = dealerState;
		startHandData.setDealer(dealerState);
		tryToSendAndNullNewHandData();
	}

	private void checkForFall() {
		if (tableStack.isPlayerCalling() == false) {
			if (tableStack.isMyPartBigger()) {
				decisionMaker.enemyFallsHandle();
			} else {
				decisionMaker.meFallsHandle();
			}
		}
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
			myStackSize.setGameStartStackSize(stackSize);
		} else {
			int myStackDifferance = myStackSize.getCurrentStackSize()
					- stackSize;

			// if my stack becomes bigger
			if (myStackDifferance < 0) {
				handResult.incrementWinningStack(-myStackDifferance);
				handResult.setWinner(HandWinner.ME);

				isStackUp = 2;
			}
			// if my stack becomes smaller
			else {
				if (isStackUp > 0) {
					isStackUp--;
					int blindes = getMyBlindes(myStackDifferance);

					// calculate only once (my or enemy blindes are calculating)
					if (isStackUp == 1) {
						checkForFall();
					}

					tableStack.setMyPart(getMyAutoBlindes(blindes));

					startHandData.setMyStackSize(stackSize
							+ getMyAutoBlindes(blindes));
					tryToSendAndNullNewHandData();

				} else {
					tableStack.incrementMyPart(myStackDifferance);
					if (tableStack.isPlayerCalling()) {
						decisionMaker.meCallsHandle(myStackDifferance);
					} else {
						decisionMaker.meRaiseHandle(myStackDifferance);
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
			enemyStackSize.setGameStartStackSize(stackSize);
		} else {
			int enemyStackDifference = enemyStackSize.getCurrentStackSize()
					- stackSize;

			// if enemy stack becomes bigger
			if (enemyStackDifference < 0) {
				handResult.incrementWinningStack(-enemyStackDifference);
				handResult.setWinner(HandWinner.ENEMY);

				isStackUp = 2;
			}
			// if enemy stack becomes smaller
			else {
				// if it is auto blindes
				if (isStackUp > 0) {
					isStackUp--;
					int blindes = getEnemyBlindes(enemyStackDifference);

					// calculate only once (my or enemy blindes are calculating)
					if (isStackUp == 1) {
						checkForFall();
					}

					tableStack.setEnemyPart(getEnemyAutoBlindes(blindes));

					startHandData.setEnemyStackSize(stackSize
							+ getEnemyAutoBlindes(blindes));
					startHandData.setBigBlindes(blindes);

					tryToSendAndNullNewHandData();

				} else {
					tableStack.incrementEnemyPart(enemyStackDifference);
					if (tableStack.isPlayerCalling()) {
						decisionMaker.enemyCallsHandle(enemyStackDifference);
					} else {
						decisionMaker.enemyRaiseHandle(enemyStackDifference);
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
	 * is called when new cards appears(or disappear) in your hands
	 * 
	 * @param handCards
	 */
	public void myNewHandsCards(Cards cards) {
		if (cards.getCardList().size() == 0) {
			cardsOnHands.removeCards();
		} else {
			cardsOnHands = cards;

			if (isFirstHand == false) {
				decisionMaker.setGameResult(handResult);
			} else {
				isFirstHand = false;
			}
			handResult.reset();

			/**
			 * 
			 */
			startHandData.setMyCards(cardsOnHands);
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
		} else {
			enemyCards = cards;
			decisionMaker.setEnemyCards(cards);
		}
	}

	/**
	 * is called when new cards appears(or disappear) on table
	 * 
	 * @param cards
	 */
	public void newTableCards(Cards tableCards) {
		if (tableCards.getCardList().size() == 0) {
			cardsOnTable.removeCards();
		} else {
			cardsOnTable = tableCards;
			// System.out.println("+++" + tableCards.getCardsCount());
			decisionMaker.setNewTableCards(tableCards);
		}
	}

	private boolean isNewHandDataReady(StartHandData newHandData) {

		if (newHandData.getBigBlindes() == 0) {
			return false;
		}
		if (newHandData.getEnemyStackSize() == 0) {
			return false;
		}
		if (newHandData.getMyStackSize() == 0) {
			return false;
		}
		if (newHandData.getDealer() == null) {
			return false;
		}
		if (newHandData.getMyCards() == null) {
			return false;
		}

		return true;
	}

	private void tryToSendAndNullNewHandData() {
		if (isNewHandDataReady(startHandData)) {
			decisionMaker.setStartHandData(startHandData);
			startHandData = new StartHandData();
		}
	}

}
