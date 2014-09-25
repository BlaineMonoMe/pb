package org.pb.inputMessagesAnalyzer;

import org.pb.input.diller.DillerState;
import org.pb.input.state.Cards;

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
	private DillerState dillerState;
	private TableStack tableStack;

	/**
	 * if my or enemy's stack is up. positive = true, zero = false; 2 - because
	 * two methods must check it and make it false (this var becomes false (or
	 * 0) after each of those methods check it)
	 */
	private int isStackUp = 2;

	public TableMessagesParser() {
		myStackSize = new StackSize();
		enemyStackSize = new StackSize();
		cardsOnTable = new Cards(5);
		cardsOnHands = new Cards(2);
		tableStack = new TableStack();
	}

	/**
	 * is called when PS is waiting for your move
	 */
	public void myNewTurn() {
		System.out.println("my new turn");
	}

	/**
	 * is called when diller changes
	 * 
	 * @param dillerState
	 */
	public void newDillerState(DillerState dillerState) {
		this.dillerState = dillerState;
		System.out.println("new diller state " + dillerState);
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
			System.out.println("my begin stack size " + stackSize);
		} else {
			int myStackDifferance = myStackSize.getCurrentStackSize()
					- stackSize;

			// if my stack becomes bigger
			if (myStackDifferance < 0) {
				System.out.println("i win " + (-myStackDifferance));
				isStackUp = 2;
			}
			// if my stack becomes smaller
			else {
				if (isStackUp > 0) {
					isStackUp--;
					int blindes = getMyBlindes(myStackDifferance);
					System.out.println("blindes are " + (blindes / 2) + "/"
							+ blindes);
					System.out.println("my auto blindes "
							+ getMyAutoBlindes(blindes));
					tableStack.setMyPart(getMyAutoBlindes(blindes));
				} else {
					tableStack.incrementMyPart(myStackDifferance);
					System.out.println("   " + tableStack);
					if (tableStack.isPalyerCalling()) {
						System.out.println("i am calling");
					} else {
						System.out.println("i am raiseing to "
								+ myStackDifferance);
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
			System.out.println("enemy begin stack size " + stackSize);
		} else {
			int enemyStackDifferance = enemyStackSize.getCurrentStackSize()
					- stackSize;

			// if enemy stack becomes bigger
			if (enemyStackDifferance < 0) {
				System.out.println("enemy wins " + (-enemyStackDifferance));
				isStackUp = 2;
			}
			// if enemy stack becomes smaller
			else {
				// if it is auto blindes
				if (isStackUp > 0) {
					isStackUp--;
					int blindes = getEnemyBlindes(enemyStackDifferance);
					System.out.println("enemy auto blindes "
							+ getEnemyAutoBlindes(blindes));
					tableStack.setEnemyPart(getEnemyAutoBlindes(blindes));
				} else {
					tableStack.incrementEnemyPart(enemyStackDifferance);
					System.out.println("   " + tableStack);
					if (tableStack.isPalyerCalling()) {
						System.out.println("enemy is calling");
					} else {
						System.out.println("enemy is raiseing to "
								+ enemyStackDifferance);
					}
				}
			}
			enemyStackSize.setCurrentStackSize(stackSize);
		}
	}

	private int getMyBlindes(int stackDifferance) {
		if (dillerState == DillerState.ME_DILLER) {
			return stackDifferance * 2;
		} else {
			return stackDifferance;
		}
	}

	private int getEnemyBlindes(int stackDifferance) {
		if (dillerState == DillerState.ME_DILLER) {
			return stackDifferance;
		} else {
			return stackDifferance * 2;
		}
	}

	private int getMyAutoBlindes(int blindes) {
		if (dillerState == DillerState.ME_DILLER) {
			return blindes / 2;
		} else {
			return blindes;
		}
	}

	private int getEnemyAutoBlindes(int blindes) {
		if (dillerState == DillerState.ME_DILLER) {
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
	public void myNewHandsCards(Cards handCards) {
		if (handCards.getCardList().size() == 0) {
			cardsOnHands.removeCards();
			System.out.println("hands cards disappears");
		} else {
			cardsOnHands = handCards;
			System.out.println("new turn");
			System.out.println("my new hands cards " + handCards);
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
			System.out.println("table cards disappears");
		} else {
			cardsOnTable = tableCards;
			System.out.println("my new hands cards " + tableCards);
		}
	}

}
