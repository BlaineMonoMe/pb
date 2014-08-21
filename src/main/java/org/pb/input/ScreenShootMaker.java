package org.pb.input;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ScreenShootMaker extends Thread {
	private BufferedImage screen;
	private Robot robot;
	private Rectangle rectangle;

	public ScreenShootMaker() {
		this.setDaemon(true);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rectangle = new Rectangle(0, 0, 1000, 800);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (screen) {
				screen = robot.createScreenCapture(rectangle);
			}
		}
	}

	public synchronized BufferedImage getScreenShot() {
		return screen;
	}

}
