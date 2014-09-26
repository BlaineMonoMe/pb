package org.pb.input;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 * WAS A THREAD - BECOMES NOT THREAD! **********
 * 
 * @author Yuriy
 * 
 */

public class ScreenshotMaker {
	private BufferedImage screen;
	private Robot robot;
	private Rectangle rectangle;

	public ScreenshotMaker() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rectangle = new Rectangle(0, 0, 1000, 800);
	}

	public BufferedImage makeScreenShot() {
		screen = robot.createScreenCapture(rectangle);
		return screen;
	}

	public BufferedImage getScreenShot() {
		return screen;
	}

}
