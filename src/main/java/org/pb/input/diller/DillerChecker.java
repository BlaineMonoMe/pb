package org.pb.input.diller;

import java.awt.image.BufferedImage;

import org.pb.input.PixellColorChecker;
import org.pb.input.color.Color;
import org.pb.input_output_util.Coordinates;

public class DillerChecker {
	private final int X_OFFSET_ENEMY = -75;
	private final int Y_OFFSET_ENEMY = -94;
	private final int X_OFFSET_ME = 81;
	private final int Y_OFFSET_ME = 83;

	private Color color;
	private PixellColorChecker enemyDillerColorChecker;
	private PixellColorChecker meDillerColorChecker;

	public DillerChecker(Coordinates centerOfTheTable) {
		enemyDillerColorChecker = new PixellColorChecker(centerOfTheTable,
				new Coordinates(X_OFFSET_ENEMY, Y_OFFSET_ENEMY));
		meDillerColorChecker = new PixellColorChecker(centerOfTheTable,
				new Coordinates(X_OFFSET_ME, Y_OFFSET_ME));
		color = new Color(255, 255, 255);
	}

	public DillerState getDillerStateDiller(BufferedImage screenImage) {
		if (enemyDillerColorChecker.checkColor(screenImage, color)) {
			return DillerState.ENEMY_DILLER;
		}
		if (meDillerColorChecker.checkColor(screenImage, color)) {
			return DillerState.ME_DILLER;
		}

		return DillerState.NOBODY_DILLER;
	}
}
