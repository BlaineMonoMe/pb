package org.pb.input.dealer;

import org.pb.input.PixelColorChecker;
import org.pb.input.color.Color;
import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

public class DealerChecker {
	private final int X_OFFSET_ENEMY = -75;
	private final int Y_OFFSET_ENEMY = -94;
	private final int X_OFFSET_ME = 81;
	private final int Y_OFFSET_ME = 83;

	private Color color;
	private PixelColorChecker enemyDealerColorChecker;
	private PixelColorChecker meDealerColorChecker;

	public DealerChecker(Coordinates centerOfTheTable) {
		enemyDealerColorChecker = new PixelColorChecker(centerOfTheTable,
				new Coordinates(X_OFFSET_ENEMY, Y_OFFSET_ENEMY));
		meDealerColorChecker = new PixelColorChecker(centerOfTheTable,
				new Coordinates(X_OFFSET_ME, Y_OFFSET_ME));
		color = new Color(255, 255, 255);
	}

	public DealerState getDealerStateDealer(BufferedImage screenImage) {
		if (enemyDealerColorChecker.checkColor(screenImage, color)) {
			return DealerState.ENEMY_DEALER;
		}
		if (meDealerColorChecker.checkColor(screenImage, color)) {
			return DealerState.ME_DEALER;
		}

		return DealerState.NOBODY_DEALER;
	}
}
