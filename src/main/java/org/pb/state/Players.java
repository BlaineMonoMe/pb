package org.pb.state;

public class Players {
	private String myName = "KIZOY";
	private String enemyName = null;
	private boolean iAmSittingOnTheTop;
	private int enemyStackSize;
	private int myStackSize;

	public Players() {
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getEnemyName() {
		return enemyName;
	}

	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}

	public boolean isiAmSittingOnTheTop() {
		return iAmSittingOnTheTop;
	}

	public void setiAmSittingOnTheTop(boolean iAmSittingOnTheTop) {
		this.iAmSittingOnTheTop = iAmSittingOnTheTop;
	}

	public int getEnemyStackSize() {
		return enemyStackSize;
	}

	public void setEnemyStackSize(int enemyStackSize) {
		this.enemyStackSize = enemyStackSize;
	}

	public int getMyStackSize() {
		return myStackSize;
	}

	public void setMyStackSize(int myStackSize) {
		this.myStackSize = myStackSize;
	}

}
