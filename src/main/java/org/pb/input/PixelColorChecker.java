package org.pb.input;

import org.pb.input.color.Color;
import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

public class PixelColorChecker {

	private Coordinates centerOfTheTable;
	private Coordinates offset;
	private int[] rgb;

	public PixelColorChecker(Coordinates centerOfTheTable, Coordinates offset) {
		this.centerOfTheTable = centerOfTheTable;
		this.offset = new Coordinates(offset);
		rgb = new int[4];
	}

	public boolean checkColor(BufferedImage tableImage, Color color) {
		tableImage.getData().getPixel(centerOfTheTable.getX() + offset.getX(),
				centerOfTheTable.getY() + offset.getY(), rgb);
		Color gotColor = new Color(rgb);
		if (gotColor.equals(color)) {
			return true;
		}
		return false;
	}

}
