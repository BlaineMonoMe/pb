package org.pb.inputTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import org.pb.input.handsStackReader.HandsStackReader;

public class HandsStackReaderTest extends TestCase {

	private BufferedImage[] imgList;
	private File folder;
	private File[] listOfFiles;

	private HandsStackReader testObj;

	public HandsStackReaderTest() {
		testObj = new HandsStackReader();
		// System.out.println("hello, i'm constructor");
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
		// System.out.println("bue, i'm constructor");
	}

	public void testGetHandsStackSizeBufferedImageBoolean() {
		System.out.println("begin testing");

		for (int i = 0; i < listOfFiles.length; i++) {

			boolean up = false;
			if (listOfFiles[i].toString().lastIndexOf("u") > 33) {
				up = true;
			}

			int expected = Integer.parseInt(listOfFiles[i].toString()
					.split("_")[1]);
			if (expected < 10) {
				expected = 5;
			}

			int result = testObj.getHandsStackSize(imgList[i], up);
			System.out.println("testing " + listOfFiles[i].toString());
			System.out.println("result = " + result);
			System.out.println();

			assertTrue(expected == result);

		}
		System.out.println("end testing");
	}

}
