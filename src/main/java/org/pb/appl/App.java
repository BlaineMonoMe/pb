package org.pb.appl;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello ");

		Robot r = null;
		Rectangle rr = new Rectangle(0, 0, 1400, 800);

		try {
			r = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		long before, after, delta;
		try {
			before = System.currentTimeMillis();
			BufferedImage bi = r.createScreenCapture(rr);

			int[] rgb = new int[3];
			bi.getData().getPixel(5, 5, rgb);
			after = System.currentTimeMillis();
			System.out.println(after);
			System.out.println(before);
			delta = after - before;

			System.out.println(delta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SystemManager sm = new SystemManager();
		sm.start();

		System.out.println("Bue ");
	}
}
