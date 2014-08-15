package org.pb.appl;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello ");

		SystemManager sm = new SystemManager();
		sm.start();

		System.out.println("Bue ");
	}
}
