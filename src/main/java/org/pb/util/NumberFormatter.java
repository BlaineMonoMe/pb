package org.pb.util;

public class NumberFormatter {

	public static int getIntFromDigits(int[] digits) {
		int result = 0;
		for (int i = 0; i < digits.length; i++) {
			int ten = 1;
			for (int j = i; j < digits.length - 1; j++) {
				ten *= 10;
			}
			result += digits[i] * ten;
		}
		return result;
	}

	public static int[] getDigitsFromInt(int value) {
		String strNumber = new Integer(value).toString();
		int[] digits = new int[strNumber.length()];

		for (int i = 0; i < strNumber.length(); i++) {
			digits[i] = Integer.parseInt(strNumber.substring(i, i + 1));
		}

		return digits;
	}

}
