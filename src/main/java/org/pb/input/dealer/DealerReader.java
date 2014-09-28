package org.pb.input.dealer;

import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

public class DealerReader {
	private DealerChecker dealerChecker;
	private DealerState currentDealerState;

	public DealerReader(Coordinates centerOfTheTable) {
		dealerChecker = new DealerChecker(centerOfTheTable);
		currentDealerState = DealerState.ME_DEALER;
	}

	public boolean checkForUpdates(BufferedImage screen) {
		DealerState newDealerState = dealerChecker.getDealerState(screen);
		if (newDealerState != currentDealerState) {
			currentDealerState = newDealerState;
			return true;
		}
		return false;
	}

	public DealerState getDealerState() {
		return currentDealerState;
	}

}
