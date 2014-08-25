package org.pb.input;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.pb.input.color.Color;
import org.pb.input.color.TwoColors;

public class CardLevelReader {

	/**
	 * coordinates of "unique" pixels
	 */
	private final int X1 = 9;
	private final int Y1 = 5;
	private final int X2 = 10;
	private final int Y2 = 5;

	private HashMap<TwoColors, Character> colorLevelMap;

	public CardLevelReader() {
		colorLevelMap = new HashMap<TwoColors, Character>();
		initailize();
	}

	private void initailize() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"res\\data\\CardsDifferences.txt"));
			String line = br.readLine();
			while (line != null) {

				TwoColors twoColors = new TwoColors();
				// System.out.println("string to parse: " + line);
				char charLevel = parseLine(line, twoColors);
				colorLevelMap.put(twoColors, charLevel);

				// System.out.println(line);
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return level of the card in char representation
	 * @return twoColors - output parameter
	 */
	private char parseLine(String line, TwoColors twoColors) {
		String lines[] = line.split(" ");
		/*
		 * for(int i=0;i<lines.length;i++) System.out.println(lines[i]);
		 */
		Color c1 = new Color(lines[1].substring(0, lines[1].length() - 1));
		Color c2 = new Color(lines[2]);
		twoColors.setColor1(c1);
		twoColors.setColor2(c2);
		return lines[0].charAt(0);
	}

	public char getLevel(BufferedImage img) {
		int[] rgb = new int[4];
		img.getData().getPixel(X1, Y1, rgb);
		Color c1 = new Color(rgb[0], rgb[1], rgb[2]);

		img.getData().getPixel(X2, Y2, rgb);
		Color c2 = new Color(rgb[0], rgb[1], rgb[2]);

		TwoColors twoColors = new TwoColors(c1, c2);

		return colorLevelMap.get(twoColors);
	}
}
