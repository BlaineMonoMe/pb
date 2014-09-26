package org.pb.input.whoseTurn;

import java.awt.image.BufferedImage;

import org.pb.input.PixelColorChecker;
import org.pb.input.color.Color;
import org.pb.inputOutputUtil.Coordinates;

public class WhoseTurnChecker {

	private final int X_OFFSET = 34;
	private final int Y_OFFSET = 280;

	private Color color;
	private PixelColorChecker colorChecker;

	public WhoseTurnChecker(Coordinates centerOfTheTable) {
		colorChecker = new PixelColorChecker(centerOfTheTable,
				new Coordinates(X_OFFSET, Y_OFFSET));
		color = new Color(255, 255, 255);
	}
	
	public boolean isMyTurn(BufferedImage screenImage) {
		return colorChecker.checkColor(screenImage, color);
	}
	
}
