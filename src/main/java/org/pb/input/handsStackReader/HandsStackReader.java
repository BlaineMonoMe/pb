package org.pb.input.handsStackReader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.pb.input_output_util.Coordinates;
import org.pb.util.NumberFormatter;

public class HandsStackReader {

	private final int downYOffset = 4;
	private final int downXOffset = 43;
	private final int upYOffset = 4;
	private final int upXOffset = 38;

	private final int digitsAfterCommaCount = 3;
	private final int leftDigitXDifference = 4;
	private final int digitWidth = 8;
	private final int digitHeight = 11;
	private final int digitWithSpaceWidth = 9;

	private HandsStackDigitReader digitReader;
	private HandsStackDigitsCountReader digitCountReader;
	private AllInChecker allInChecker;

	public HandsStackReader() {
		digitReader = new HandsStackDigitReader();
		digitCountReader = new HandsStackDigitsCountReader();
		allInChecker = new AllInChecker();
	}

	public int getHandsStackSize(BufferedImage stackImage,
			boolean isPlayerSittingAtTheTop) {

		// checking for all-in
		if (allInChecker.checkForAllIn(stackImage, isPlayerSittingAtTheTop)) {
			return 0;
		}

		int digitsCount = digitCountReader.getDigitsCount(stackImage);
		ArrayList<Coordinates> commasList = digitCountReader.getCommasList();

		int result = 0;

		// if digit count is 3 or less
		if (digitsCount <= 3) {
			result = getHandsStackSize(stackImage, digitsCount,
					isPlayerSittingAtTheTop);
		}
		// if digit count is 4 or more
		else {
			result = getHandsStackSize(stackImage, commasList, digitsCount);
		}

		return result;
	}

	/**
	 * for 1 - 3 digits
	 * 
	 * @param stackImage
	 * @param digitsCount
	 * @param isPlayerSittingAtTheTop
	 * @return
	 */
	private int getHandsStackSize(BufferedImage stackImage, int digitsCount,
			boolean isPlayerSittingAtTheTop) {

		int[] stackSize = new int[digitsCount];
		int currXOffset = 0;
		int currYOffset = 0;

		// TODO: change this stupid if
		if (digitsCount < 2) {
			return 5;
		}

		if (isPlayerSittingAtTheTop) {
			currXOffset = upXOffset + (digitsAfterCommaCount - digitsCount)
					* leftDigitXDifference;
			currYOffset = upYOffset;
		} else {
			currXOffset = downXOffset + (digitsAfterCommaCount - digitsCount)
					* leftDigitXDifference;
			currYOffset = downYOffset;
		}

		for (int i = 0; i < digitsCount; i++) {

			// System.out.println("SUBIMAGE: x="
			// + (currXOffset + (digitWithSpaceWidth * i)) + ", y="
			// + currYOffset);

			stackSize[i] = digitReader.getDigit(stackImage.getSubimage(
					currXOffset + (digitWithSpaceWidth * i), currYOffset,
					digitWidth, digitHeight));

		}

		return NumberFormatter.getIntFromDigits(stackSize);
	}

	/**
	 * for 4 and more digits
	 * 
	 * @param stackImage
	 * @param commasCoordinates
	 * @param digitsCount
	 * @return
	 */
	private int getHandsStackSize(BufferedImage stackImage,
			ArrayList<Coordinates> commasCoordinates, int digitsCount) {
		int[] digits = new int[digitsCount];
		int digitIndex = 0;

		int[] digitsBeforeComma = getNumberBeforeComma(stackImage,
				commasCoordinates.get(0), digitsCount);
		for (int i = 0; i < digitsBeforeComma.length; i++) {
			digits[digitIndex] = digitsBeforeComma[i];
			++digitIndex;
		}

		int commasCount = commasCoordinates.size();
		for (int currComma = 0; currComma < commasCount; currComma++) {
			int[] currCommaDigits = getNumberAfterComma(stackImage,
					commasCoordinates.get(currComma));
			for (int currDigit = 0; currDigit < 3; currDigit++) {
				digits[digitIndex] = currCommaDigits[currDigit];
				++digitIndex;
			}
		}

		return NumberFormatter.getIntFromDigits(digits);
	}

	private int[] getNumberAfterComma(BufferedImage stackImage,
			Coordinates commaCoords) {

		int[] digits = new int[3];
		for (int i = 0; i < 3; i++) {
			digits[i] = digitReader.getDigit(stackImage.getSubimage(
					commaCoords.getX() + 4 + digitWithSpaceWidth * i,
					commaCoords.getY() - 12, digitWidth, digitHeight));
		}
		return digits;
	}

	private int[] getNumberBeforeComma(BufferedImage stackImage,
			Coordinates commaCoords, int digitsCount) {

		int digitsBeforeComma = getDigitsBeforeCommaCount(digitsCount);

		int[] digits = new int[digitsBeforeComma];
		int digitIndex = digitsBeforeComma - 1;

		for (int i = 0; i < digitsBeforeComma; i++) {
			digits[digitIndex] = digitReader.getDigit(stackImage.getSubimage(
					commaCoords.getX() - 8 - digitWithSpaceWidth * i,
					commaCoords.getY() - 12, digitWidth, digitHeight));
			--digitIndex;
		}

		return digits;
	}

	private int getDigitsBeforeCommaCount(int digitsCount) {
		int digitsBeforeComma = digitsCount % 3;
		if (digitsBeforeComma == 0) {
			digitsBeforeComma = 3;
		}
		return digitsBeforeComma;
	}

}
