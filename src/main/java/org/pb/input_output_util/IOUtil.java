package org.pb.input_output_util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopMouse;

/**
 * 
 * @author Yuriy
 * 
 *         Input-Output Util
 */
public class IOUtil {

	public static void absoluteLeftMouseClick(int x, int y) {
		try {
			Robot robot = new Robot();
			CursorPositionSaver.pushCursorCoords();
			robot.mouseMove(x, y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			CursorPositionSaver.popCursorCoords();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void absoluteLeftMouseDblClick(int x, int y) {
		try {
			Robot robot = new Robot();
			robot.mouseMove(x, y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(100);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static boolean leftMouseClickOnComponent(String file,
			int leftOffset, int topOffset) {
		ScreenRegion screen = new DesktopScreenRegion();
		File image = new File(file);
		Target target = new ImageTarget(image);
		ScreenRegion r = screen.wait(target, 1);
		if (r == null) {
			return false;
		}
		// System.out.println(r.getCenter().getX());
		Mouse mouse = new DesktopMouse();
		mouse.click(r.getRelativeScreenLocation(leftOffset, topOffset));
		return true;
	}

	public static boolean leftMouseDblClickOnComponent(String file,
			int leftOffset, int topOffset) {
		ScreenRegion screen = new DesktopScreenRegion();
		File image = new File(file);
		Target target = new ImageTarget(image);
		ScreenRegion r = screen.wait(target, 1);
		if (r == null) {
			return false;
		}
		Mouse mouse = new DesktopMouse();
		mouse.doubleClick(r.getRelativeScreenLocation(leftOffset, topOffset));
		return true;
	}

	public static boolean existPicture(String file) {
		ScreenRegion screen = new DesktopScreenRegion();
		File image = new File(file);
		Target target = new ImageTarget(image);
		target.setMinScore(0.99);
		ScreenRegion r = screen.wait(target, 1);
		// System.out.println("min score: " + target.getMinScore());
		if (r == null) {
			return false;
		}
		return true;
	}

	/**
	 * SUPER-FAST method!!! (shit, no!)
	 * 
	 * @param target
	 * @param rectangle
	 * @return
	 */
	public static boolean existPicture(Target target, Rectangle rectangle) {
		ScreenRegion screen = new DesktopScreenRegion(rectangle.getLeft(),
				rectangle.getTop(), rectangle.getWidth(), rectangle.getHeight());
		ScreenRegion r = screen.wait(target, 1);
		if (r == null) {
			return false;
		}
		return true;
	}

	public static boolean existPicture(String file, Rectangle rectangle) {
		ScreenRegion screen = new DesktopScreenRegion(rectangle.getLeft(),
				rectangle.getTop(), rectangle.getWidth(), rectangle.getHeight());
		File image = new File(file);
		Target target = new ImageTarget(image);
		target.setMinScore(0.99);
		ScreenRegion r = screen.wait(target, 1);
		// System.out.println("min score: " + target.getMinScore());
		if (r == null) {
			return false;
		}
		return true;
	}

	public static boolean checkColour(Coordinates coords, Color color) {
		ScreenRegion screen = new DesktopScreenRegion();
		int[] rgb = new int[3];
		screen.capture().getData().getPixel(coords.getX(), coords.getY(), rgb);
		System.out.println(rgb[0]);
		System.out.println(rgb[1]);
		System.out.println(rgb[2]);
		Color gotColor = new Color(rgb);
		if (gotColor.equals(color)) {
			return true;
		}
		return false;
	}

	public static Coordinates getCenterCoordinates(String file) {
		ScreenRegion screen = new DesktopScreenRegion();
		File image = new File(file);
		Target target = new ImageTarget(image);
		target.setMinScore(0.95);
		ScreenRegion r = screen.wait(target, 1);
		return new Coordinates(r.getCenter().getX(), r.getCenter().getY());
	}

	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void closeWindow() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_ALT);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static void ctrlC() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
