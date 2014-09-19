package org.pb.input.diller;

import java.awt.image.BufferedImage;

import org.pb.input_output_util.Coordinates;

public class DillerReader {
	private DillerChecker dillerChecker;
	private DillerState currentDillerState;

	public DillerReader(Coordinates centerOfTheTable) {
		dillerChecker = new DillerChecker(centerOfTheTable);
		currentDillerState = DillerState.ME_DILLER;
	}

	public boolean checkForUpdates(BufferedImage screen) {
		DillerState newDillerSate = dillerChecker.getDillerStateDiller(screen);
		if (newDillerSate != currentDillerState) {
			currentDillerState = newDillerSate;
			return true;
		}
		return false;
	}

	public DillerState getDillerState() {
		return currentDillerState;
	}

}
