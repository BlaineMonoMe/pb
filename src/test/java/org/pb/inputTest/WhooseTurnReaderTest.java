package org.pb.inputTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

import org.pb.input.PixellColorChecker;
import org.pb.input.color.Color;
import org.pb.input.diller.DillerChecker;
import org.pb.input.whooseTurn.WhooseTurnChecker;
import org.pb.input_output_util.Coordinates;

public class WhooseTurnReaderTest extends TestCase {

	private DillerChecker deallerReader;
	private WhooseTurnChecker turnReader;

	private BufferedImage[] imgList;
	private File folder;
	private File[] listOfFiles;

	public WhooseTurnReaderTest() {
		turnReader = new WhooseTurnChecker(new Coordinates(407, 267));
		deallerReader = new DillerChecker(new Coordinates(407, 267));

		folder = new File("res\\unit-testing\\screens");
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
	}

	public void testIsMyTurn() {

		for (int i = 0; i < imgList.length; i++) {
			boolean expected = listOfFiles[i].toString().contains("my-turn");
			// System.out.println(expected);
			boolean got = turnReader.isMyTurn(imgList[i]);
			assertTrue(expected == got);
		}

		for (int i = 0; i < imgList.length; i++) {
			boolean expected = listOfFiles[i].toString().contains("me-duller");
			// System.out.println(expected);
			boolean got = deallerReader.amIDiller(imgList[i]);
			assertTrue(expected == got);
		}

	}

}
