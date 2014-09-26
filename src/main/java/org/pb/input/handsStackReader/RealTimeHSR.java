package org.pb.input.handsStackReader;

import org.pb.input.tableCardsReader.CardsOnTableListener;
import org.pb.inputOutputUtil.Coordinates;

import java.awt.image.BufferedImage;

public class RealTimeHSR {

	private final int DOWN_X_OFFSET = -77;
	private final int DOWN_Y_OFFSET = 170;
	private final int UP_X_OFFSET = -31;
	private final int UP_Y_OFFSET = -140;
	private final int IMG_WITH = 105;
	private final int IMG_HEIGHT = 20;

	private int xOffset;
	private int yOffset;

	private HandsStackReader handsStackReader;
	private boolean isPlayerSittingAtTheTop;

	private int currentStackSize;
	private int stackSizeAtNewTurn;

	private boolean isNewTurn = true;

	public RealTimeHSR(Coordinates centerOfTheTable,
			boolean isPlayerSittingAtTheTop) {
		this.isPlayerSittingAtTheTop = isPlayerSittingAtTheTop;
		handsStackReader = new HandsStackReader();

		if (isPlayerSittingAtTheTop) {
			xOffset = centerOfTheTable.getX() + UP_X_OFFSET;
			yOffset = centerOfTheTable.getY() + UP_Y_OFFSET;
		} else {
			xOffset = centerOfTheTable.getX() + DOWN_X_OFFSET;
			yOffset = centerOfTheTable.getY() + DOWN_Y_OFFSET;
		}

	}

	public boolean isNewTurn(BufferedImage screenImage,
			CardsOnTableListener cardsOnTableListener) {

		if (cardsOnTableListener.getCurrentCardsCount() == 0) {
			if (isNewTurn == true) {
				isNewTurn = false;

				BufferedImage stackImage = screenImage.getSubimage(xOffset,
						yOffset, IMG_WITH, IMG_HEIGHT);
				int newStckSize = handsStackReader.getHandsStackSize(
						stackImage, isPlayerSittingAtTheTop);
				stackSizeAtNewTurn = newStckSize;
				if (newStckSize != currentStackSize) {
					currentStackSize = newStckSize;
				}
				return true;
			}
		} else {
			isNewTurn = true;
		}

		return false;
	}

	public boolean isUpdated(BufferedImage screenImage) {

		BufferedImage stackImage = screenImage.getSubimage(xOffset, yOffset,
				IMG_WITH, IMG_HEIGHT);

		int newStckSize = handsStackReader.getHandsStackSize(stackImage,
				isPlayerSittingAtTheTop);
		if (newStckSize != currentStackSize) {
			currentStackSize = newStckSize;
			return true;
		}
		return false;
	}

	public int getStackSize() {
		return currentStackSize;
	}

	public int getStackSizeAtNewTurn() {
		return stackSizeAtNewTurn;
	}

}
