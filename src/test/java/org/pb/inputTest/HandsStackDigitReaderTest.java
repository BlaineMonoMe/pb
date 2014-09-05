package org.pb.inputTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import org.pb.input.handsStackReader.HandsStackDigitReader;

public class HandsStackDigitReaderTest extends TestCase {

	private HandsStackDigitReader hsdr;

	public BufferedImage[] imgList;
	public File folder;
	File[] listOfFiles;

	{
		System.out.println("hello, i'm constructor");
	}

	public HandsStackDigitReaderTest() {

		hsdr = new HandsStackDigitReader();
		System.out.println("hello, i'm constructor");
		folder = new File("res\\unit-testing\\handsStackDigits");
		listOfFiles = folder.listFiles();
		System.out.println(listOfFiles.length);
		imgList = new BufferedImage[listOfFiles.length];
		System.out.println("hello, i'm constructor");

		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				imgList[i] = ImageIO.read(listOfFiles[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("bue, i'm constructor");
	}

	public void testGetDigit() {
		System.out.println("begin testing");
		for (int i = 0; i < imgList.length; i++) {
			System.out.println("testing " + i);
			int digit = hsdr.getDigit(imgList[i]);
			assertTrue(digit == i / 2);
		}
	}

}
