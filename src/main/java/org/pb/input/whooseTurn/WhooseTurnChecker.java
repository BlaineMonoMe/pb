package org.pb.input.whooseTurn;

import java.awt.image.BufferedImage;

import org.pb.input.PixellColorChecker;
import org.pb.input.color.Color;
import org.pb.input_output_util.Coordinates;

public class WhooseTurnChecker {

	private final int X_OFFSET = 34;
	private final int Y_OFFSET = 280;

	private Color color;
	private PixellColorChecker colorChecker;

	public WhooseTurnChecker(Coordinates centerOfTheTable) {
		colorChecker = new PixellColorChecker(centerOfTheTable,
				new Coordinates(X_OFFSET, Y_OFFSET));
		color = new Color(255, 255, 255);
	}
	
	public boolean isMyTurn(BufferedImage screenImage) {
		return colorChecker.checkColor(screenImage, color);
	}
	
}
