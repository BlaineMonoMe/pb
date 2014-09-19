package org.pb.input.handsStackReader;

import java.awt.image.BufferedImage;

import org.pb.input.color.Color;

public class AllInChecker {

	/**
	 * for player, who is sitting at the top of the table
	 */
	private final int GRAY_X1 = 42;
	private final int GRAY_Y1 = 9;
	private final int GRAY_X2 = 58;
	private final int GRAY_Y2 = 12;
	private final int NOT_GRAY_X3 = 27;
	private final int NOT_GRAY_Y3 = 9;
	private final int NOT_GRAY_X4 = 73;
	private final int NOT_GRAY_Y4 = 7;

	private final int BOTTOM_PLAYER_X_OFFSET = 5;

	private int[] rgb;
	private Color color;

	public AllInChecker() {
		rgb = new int[4];
	}

	public boolean checkForAllIn(BufferedImage stackImage,
			boolean isPlayerAtTheTop) {

		int xOffset = 0;
		if (isPlayerAtTheTop == false) {
			xOffset = BOTTOM_PLAYER_X_OFFSET;
		}

		/*
		 * for (int i = 20; i < 80; i++) { stackImage.getData().getPixel(GRAY_X1
		 * + xOffset + i, GRAY_Y1 + 2, rgb); color = new Color(rgb);
		 * System.out.println(color.isGray()); }
		 */

		stackImage.getData().getPixel(GRAY_X1 + xOffset, GRAY_Y1, rgb);
		color = new Color(rgb);
		if (color.isGray() == false) {
			return false;
		}

		stackImage.getData().getPixel(GRAY_X2 + xOffset, GRAY_Y2, rgb);
		color = new Color(rgb);
		if (color.isGray() == false) {
			return false;
		}

		stackImage.getData().getPixel(NOT_GRAY_X4 + xOffset, NOT_GRAY_Y4, rgb);
		color = new Color(rgb);
		if (color.isGray() == true) {
			return false;
		}

		stackImage.getData().getPixel(NOT_GRAY_X3 + xOffset, NOT_GRAY_Y3, rgb);
		color = new Color(rgb);
		if (color.isGray() == true) {
			return false;
		}

		return true;
	}

}
