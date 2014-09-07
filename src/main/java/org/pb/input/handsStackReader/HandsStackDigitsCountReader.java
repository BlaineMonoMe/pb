package org.pb.input.handsStackReader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.pb.input.color.Color;
import org.pb.input_output_util.Coordinates;

public class HandsStackDigitsCountReader {

	private final int digitsAfterCommaCount = 3;

	private ArrayList<Coordinates> commasList = null;

	public HandsStackDigitsCountReader() {
	}

	public ArrayList<Coordinates> getCommasList() {
		ArrayList<Coordinates> listToReturn = commasList;
		commasList = null;
		return listToReturn;
	}

	public boolean isCommasPersent(BufferedImage stackImage) {
		final int commaYCoord = 17;
		final int imageWith = stackImage.getWidth();
		int[] inputArrColor = new int[4];

		for (int currX = 1; currX < imageWith - 1; currX++) {
			stackImage.getData().getPixel(currX - 1, commaYCoord - 1,
					inputArrColor);
			// if (inputArrColor[0] == 161 && inputArrColor[1] == 208
			// && inputArrColor[2] == 152) {
			Color color = new Color(inputArrColor);
			if (color.isGray() == false) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<Coordinates> getCommasCoordinates(BufferedImage stackImage) {
		final int commaYCoord = 17;
		final int imageWith = stackImage.getWidth();
		int[] inputArrColor = new int[4];

		ArrayList<Coordinates> commasList = new ArrayList<Coordinates>();

		for (int currX = 1; currX < imageWith - 1; currX++) {
			stackImage.getData().getPixel(currX - 1, commaYCoord - 1,
					inputArrColor);
			Color color = new Color(inputArrColor);
			if (color.isGray() == false) {
				commasList.add(new Coordinates(currX - 1, commaYCoord - 1));
				currX += 3;
			}
		}
		return commasList;
	}

	public int getDigitsCount(BufferedImage stackImage) {

		int digitsCount = 0;

		if (isCommasPersent(stackImage)) {
			System.out.println("comma exist");
			commasList = new ArrayList<Coordinates>();
			commasList = getCommasCoordinates(stackImage);
			int leftX = getLeftXCoord(stackImage);
			int differance = commasList.get(0).getX() - leftX - 2;
			int digitsBeforeFirstCommaCount = getDigitsCount(differance);
			digitsCount = digitsBeforeFirstCommaCount + commasList.size()
					* digitsAfterCommaCount;
			// System.out.println("diff = " + differance);
			// System.out.println("digitsBeforeFirstCommaCount = "
			// + digitsBeforeFirstCommaCount);
			// System.out.println("diff = " + differance);
			// System.out.println("commasList.size() = " + commasList.size());

		} else {
			System.out.println("no comma");
			int leftX = getLeftXCoord(stackImage);
			int rightX = getRightXCoord(stackImage);
			int differance = rightX - leftX - 2;
			// System.out.println("diff = " + differance);
			digitsCount = getDigitsCount(differance);

		}

		System.out.println("digits count = " + digitsCount);
		return digitsCount;
	}

	/**
	 * finds left coordinate where digits begins
	 */
	public int getLeftXCoord(BufferedImage stackImage) {
		int maxX = stackImage.getWidth();
		int currY = 5;
		int[] inputArrColor = new int[4];

		for (int x = 1; x < maxX - 1; x++) {
			stackImage.getData().getPixel(x, currY, inputArrColor);
			Color rgbColor = new Color(inputArrColor);
			if (rgbColor.isGray() == false) {
				return x;
			}
		}

		throw new NullPointerException();
	}

	/**
	 * finds right coordinate where digits begins
	 */
	public int getRightXCoord(BufferedImage stackImage) {
		int maxX = stackImage.getWidth() - 1;
		int currY = 5;
		int[] inputArrColor = new int[4];

		for (int x = maxX; x > 1; x--) {
			stackImage.getData().getPixel(x, currY, inputArrColor);
			Color rgbColor = new Color(inputArrColor);
			if (rgbColor.isGray() == false) {
				return x;
			}
		}

		throw new NullPointerException();
	}

	/**
	 * Only for digits without commas
	 * 
	 * @param width
	 *            - how many pixels 'uses' number(digits)
	 * @return number of digits (only [1-3])
	 */
	public int getDigitsCount(int width) {
		if (width < 8) {
			return 1;
		}
		if (width < 16) {
			return 2;
		}
		return 3;
	}

}
