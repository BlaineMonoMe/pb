package org.pb.input_output_util;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class CursorPositionSaver {
	private static Point coords;

	public static void pushCursorCoords() {
		coords = MouseInfo.getPointerInfo().getLocation();
	}

	public static void popCursorCoords() {
		try {
			Robot r = new Robot();
			r.mouseMove((int) coords.getX(), (int) coords.getY());
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
