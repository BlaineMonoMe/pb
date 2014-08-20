package org.pb.appl;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ScreenRegion;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello ");

		// assert false : "oh, shit!";

		// CardTargetManager ctm = new CardTargetManager();
		/*
		 * ArrayList<CardTarget> ct = ctm.getCardTargets(); for (int i = 0; i <
		 * ct.size(); i++) { System.out.println(ct.get(i).getCard()); }
		 */

		// IOUtil.checkColour(new Coordinates(0, 0), new Color(0, 0, 0));

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
