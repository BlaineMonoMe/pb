package org.pb.input.whooseTurn;

import java.awt.image.BufferedImage;

import org.pb.input_output_util.Coordinates;

public class MyTurnReader {
	private WhooseTurnChecker whooseTurnChecker;
	private boolean isMyTurn;

	public MyTurnReader(Coordinates centerOfTheTable) {
		whooseTurnChecker = new WhooseTurnChecker(centerOfTheTable);
		isMyTurn = false;
	}

	public boolean isMyNewTurn(BufferedImage screen) {
		boolean currentMyTurn = whooseTurnChecker.isMyTurn(screen);
		if (currentMyTurn != isMyTurn) {
			isMyTurn = currentMyTurn;
			return isMyTurn;
		}
		return false;
	}

}
