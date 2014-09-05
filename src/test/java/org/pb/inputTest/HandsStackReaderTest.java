package org.pb.inputTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import org.pb.input.handsStackReader.HandsStackReader;

public class HandsStackReaderTest extends TestCase {

	private HandsStackReader shr = new HandsStackReader();
	BufferedImage fourDigitsImage;
	BufferedImage threeDigitsImage;

	public BufferedImage[] imgList;
	public File folder;
	File[] listOfFiles;

	public HandsStackReaderTest() {

		folder = new File("res\\unit-testing");
		listOfFiles = folder.listFiles();
		imgList = new BufferedImage[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				imgList[i] = ImageIO.read(listOfFiles[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * try { fourDigitsImage = ImageIO.read(new File(
		 * "res\\unit-testing\\fourDigits.PNG")); threeDigitsImage =
		 * ImageIO.read(new File( "res\\unit-testing\\threeDigits.PNG")); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */
	}

	public void testGetHandsStack() {

		int digitsCount = 0;
		for (int i = 0; i < imgList.length; i++) {
			digitsCount = shr.getHandsStack(imgList[i], false);
			String digits = listOfFiles[i].toString().substring(17, 18);
			int expected = Integer.parseInt(digits);
			System.out.println("i=" + listOfFiles[i].toString() + " -> "
					+ digitsCount);
			assertTrue(digitsCount == expected);
		}

		/*
		 * digitsCount = shr.getHandsStack(fourDigitsImage, false);
		 * System.out.println("fout digits size: " + digitsCount);
		 * assertTrue(digitsCount == 4);
		 * 
		 * digitsCount = shr.getHandsStack(threeDigitsImage, false);
		 * System.out.println("three digits size: " + digitsCount);
		 * assertTrue(digitsCount == 3);
		 */
	}

	/*
	 * public void oldTestGetCommasCoordinates() {
	 * 
	 * ArrayList<Coordinates> coord1 = shr
	 * .getCommasCoordinates(fourDigitsImage); assertFalse(coord1.size() != 1);
	 * 
	 * ArrayList<Coordinates> coord2 = shr
	 * .getCommasCoordinates(threeDigitsImage); assertTrue(coord2.size() == 0);
	 * 
	 * }
	 * 
	 * public void oldTestGetLeftXCoord() { int leftX =
	 * shr.getLeftXCoord(fourDigitsImage); System.out.println("l f: " + leftX);
	 * assertTrue(35 < leftX && leftX < 41);
	 * 
	 * leftX = shr.getLeftXCoord(threeDigitsImage); System.out.println("l t: " +
	 * leftX); assertTrue(41 < leftX && leftX < 46); }
	 * 
	 * public void oldTestGetRightXCoord() { int rightX =
	 * shr.getRightXCoord(fourDigitsImage); System.out.println("r f: " +
	 * rightX); assertTrue(72 < rightX && rightX < 76);
	 * 
	 * rightX = shr.getRightXCoord(threeDigitsImage); System.out.println("r t: "
	 * + rightX); assertTrue(64 < rightX && rightX < 70); }
	 * 
	 * public void oldTestGetHandsStack() { int digitsCount =
	 * shr.getHandsStack(fourDigitsImage, false);
	 * System.out.println("fout digits size: " + digitsCount);
	 * assertTrue(digitsCount == 4);
	 * 
	 * digitsCount = shr.getHandsStack(threeDigitsImage, false);
	 * System.out.println("three digits size: " + digitsCount);
	 * assertTrue(digitsCount == 3); }
	 */

}
