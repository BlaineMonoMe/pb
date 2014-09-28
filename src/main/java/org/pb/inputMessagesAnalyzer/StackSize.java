package org.pb.inputMessagesAnalyzer;

public class StackSize {
	private int gameStartStackSize;
	private int currentStackSize;
	private boolean initialized;

	public StackSize() {
		initialized = false;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public int getGameStartStackSize() {
		return gameStartStackSize;
	}

	public void setGameStartStackSize(int gameBeginStackSize) {
		this.gameStartStackSize = gameBeginStackSize;
	}

	public int getCurrentStackSize() {
		return currentStackSize;
	}

	public void setCurrentStackSize(int currentStackSize) {
		initialized = true;
		this.currentStackSize = currentStackSize;
	}

}
