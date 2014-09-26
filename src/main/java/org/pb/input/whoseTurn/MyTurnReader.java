package org.pb.input.whoseTurn;

import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

public class MyTurnReader {
	private WhoseTurnChecker whoseTurnChecker;
	private boolean isMyTurn;

	public MyTurnReader(Coordinates centerOfTheTable) {
		whoseTurnChecker = new WhoseTurnChecker(centerOfTheTable);
		isMyTurn = false;
	}

	public boolean isMyNewTurn(BufferedImage screen) {
		boolean currentMyTurn = whoseTurnChecker.isMyTurn(screen);
		if (currentMyTurn != isMyTurn) {
			isMyTurn = currentMyTurn;
			return isMyTurn;
		}
		return false;
	}

}
