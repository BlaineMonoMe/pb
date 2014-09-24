package org.pb.inputMessagesAnalyzer;

public class TableStack {
	private int myPart;
	private int enemyPart;

	@Override
	public String toString() {
		return "table stack. my part = " + myPart + ", enemy part = "
				+ enemyPart;
	}

	public boolean isPalyerCalling() {
		return (myPart == enemyPart);
	}

	public boolean isPlayerRaiseing() {
		return (myPart != enemyPart);
	}

	public void incrementMyPart(int value) {
		myPart += value;
	}

	public void incrementEnemyPart(int value) {
		enemyPart += value;
	}

	public int getMyPart() {
		return myPart;
	}

	public void setMyPart(int myPart) {
		this.myPart = myPart;
	}

	public int getEnemyPart() {
		return enemyPart;
	}

	public void setEnemyPart(int enemyPart) {
		this.enemyPart = enemyPart;
	}

}
