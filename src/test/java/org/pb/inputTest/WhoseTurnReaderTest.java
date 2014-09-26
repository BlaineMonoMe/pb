package org.pb.inputTest;

import junit.framework.TestCase;
import org.pb.input.dealer.DealerChecker;
import org.pb.input.whoseTurn.WhoseTurnChecker;
import org.pb.inputOutputUtil.Coordinates;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WhoseTurnReaderTest extends TestCase {

	private DealerChecker deallerReader;
	private WhoseTurnChecker turnReader;

	private BufferedImage[] imgList;
	private File folder;
	private File[] listOfFiles;

	public WhoseTurnReaderTest() {
		turnReader = new WhoseTurnChecker(new Coordinates(407, 267));
		deallerReader = new DealerChecker(new Coordinates(407, 267));

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
//			boolean got = deallerReader.getDealerStateDealer(imgList[i]);
//			assertTrue(expected == got);
		}

	}

}
