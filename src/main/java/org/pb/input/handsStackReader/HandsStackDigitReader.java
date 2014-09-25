package org.pb.input.handsStackReader;

import org.pb.input.global.FilePaths;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HandsStackDigitReader {

	/**
	 * coordinates of "unique" pixels
	 */
	private final int X1 = 1;
	private final int Y1 = 1;
	private final int X2 = 2;
	private final int Y2 = 2;

	private HashMap<TwoIntegers, Integer> colorDigitlMap;

	public HandsStackDigitReader() {
		colorDigitlMap = new HashMap<TwoIntegers, Integer>();
		initailize();
	}

	private void initailize() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
                    FilePaths.STACK_DIFFERENCES));
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				TwoIntegers twoInts = new TwoIntegers();
				int digit = parseLine(line, twoInts);
				colorDigitlMap.put(twoInts, new Integer(digit));
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int parseLine(String line, TwoIntegers ints) {

		String[] redVals = line.split(" ");
		ints.setValue1(Integer.parseInt(redVals[1]));
		ints.setValue2(Integer.parseInt(redVals[2]));

		return Integer.parseInt(line.substring(0, 1));
	}

	public int getDigit(BufferedImage img) {
		int[] rgb = new int[4];

		img.getData().getPixel(X1, Y1, rgb);
		int red1 = rgb[0];

		img.getData().getPixel(X2, Y2, rgb);
		int red2 = rgb[0];

		TwoIntegers twoInts = new TwoIntegers(red1, red2);

		if (colorDigitlMap.get(twoInts) == null) {
			System.out.println("shit! " + red1 + " - " + red2);
		}

		return colorDigitlMap.get(twoInts);
	}

}
