package org.pb.input.cardReader;

import java.awt.image.BufferedImage;

public class CardSuitReader {

	// "magic" - pixel, by which the lear will be get
	private final int MAGIC_PIXELL_X_OFFSET = 5;
	private final int MAGIC_PIXELL_Y_OFFSET = 24;

	public char getSuit(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(MAGIC_PIXELL_X_OFFSET, MAGIC_PIXELL_Y_OFFSET,
				rgb);
		if (rgb[0] == 236 && rgb[1] == 168 && rgb[2] == 168) {
			return 'd';
		}
		if (rgb[0] == 200 && rgb[1] == 6 && rgb[2] == 6) {
			return 'h';
		}
		if (rgb[0] == 178 && rgb[1] == 178 && rgb[2] == 178) {
			return 's';
		}
		if (rgb[0] == 231 && rgb[1] == 231 && rgb[2] == 231) {
			return 'c';
		}
		return 'x';
	}
}
