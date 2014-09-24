package org.pb.inputMessagesAnalyzer;

public class StackSize {
	private int gameBeginStackSize;
	private int currentStackSize;
	private boolean initialized;

	public StackSize() {
		initialized = false;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public int getGameBeginStackSize() {
		return gameBeginStackSize;
	}

	public void setGameBeginStackSize(int gameBeginStackSize) {
		this.gameBeginStackSize = gameBeginStackSize;
	}

	public int getCurrentStackSize() {
		return currentStackSize;
	}

	public void setCurrentStackSize(int currentStackSize) {
		initialized = true;
		this.currentStackSize = currentStackSize;
	}

}
