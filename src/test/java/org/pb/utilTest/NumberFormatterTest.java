package org.pb.utilTest;

import junit.framework.TestCase;

import org.pb.util.NumberFormatter;

public class NumberFormatterTest extends TestCase {

	public void testGetIntFromDigits() {
		int[] a = { 1, 2, 3 };
		int result = NumberFormatter.getIntFromDigits(a);
		System.out.println(result);
		assertTrue(123 == result);

		int[] b = { 1 };
		int result2 = NumberFormatter.getIntFromDigits(b);
		System.out.println(result2);
		assertTrue(1 == result2);

	}
}
