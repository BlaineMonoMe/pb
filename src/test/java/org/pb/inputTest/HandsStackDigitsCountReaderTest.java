package org.pb.inputTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pb.input.handsStackReader.HandsStackDigitsCountReader;

import junit.framework.TestCase;

public class HandsStackDigitsCountReaderTest extends TestCase {

	private BufferedImage[] imgList;
	private File folder;
	private File[] listOfFiles;

	private HandsStackDigitsCountReader testObj;

	public HandsStackDigitsCountReaderTest() {
		testObj = new HandsStackDigitsCountReader();
		System.out.println("hello, i'm constructor");
		folder = new File("res\\unit-testing\\handsStackImgs");
		listOfFiles = folder.listFiles();
		// System.out.println(listOfFiles.length);
		imgList = new BufferedImage[listOfFiles.length];
		// System.out.println("hello, i'm constructor");

		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				imgList[i] = ImageIO.read(listOfFiles[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("bue, i'm constructor");

	}

	public void testGetDigitsCount() {

		for (int i = 0; i < listOfFiles.length; i++) {

			int got = testObj.getDigitsCount(imgList[i]);
			int expected = Integer.parseInt(listOfFiles[i].toString()
					.substring(32, 33));

			System.out.println("" + i + "-> " + listOfFiles[i].toString()
					+ ": " + expected + " == " + got);
			System.out.println();

			assertTrue(expected == got);
		}
	}
}
