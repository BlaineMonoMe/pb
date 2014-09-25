package org.pb.input.myCardsReader;

import java.awt.image.BufferedImage;

import org.pb.input.state.Cards;
import org.pb.input_output_util.Coordinates;

/**
 * WAS A THREAD - BECOMES NOT THREAD***********
 * 
 * @author Yuriy
 * 
 */

public class CardsOnHandsListener {
	private Coordinates centerOfTheTable;
	private HandsCardsReader handsCardsReader;
	private boolean isSittingAtTheTop;
	private boolean isUpdated;

	public CardsOnHandsListener(Coordinates coords, boolean amISittingAtTheTop) {
		this.centerOfTheTable = new Coordinates(coords);
		handsCardsReader = new HandsCardsReader(coords, amISittingAtTheTop);
		isSittingAtTheTop = amISittingAtTheTop;
		isUpdated = false;
	}

	public boolean isNewCards(BufferedImage image) {
		if (!isSittingAtTheTop) {
			int[] rgb = new int[3];
			image.getData().getPixel(centerOfTheTable.getX() + 25,
					centerOfTheTable.getY() + 108, rgb);

			boolean currUpdate = true;
			if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
				currUpdate = true;
			} else {
				currUpdate = false;
			}

			if (currUpdate != isUpdated) {
				isUpdated = currUpdate;
				return isUpdated;
			} else {
				return false;
			}
		}
		return false;
	}

	public Cards getCards(BufferedImage image) {
		return handsCardsReader.read(image);
	}

}
