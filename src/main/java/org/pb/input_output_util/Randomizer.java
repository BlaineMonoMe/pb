package org.pb.input_output_util;

import java.util.Random;

public class Randomizer {

	private Random random;

	public Randomizer() {
		random = new Random();
	}

	/**
	 * 
	 * @param minValue
	 * @param maxValue
	 * @param avgValue
	 *            - "gravitation" - rand numbers will be closer to this value
	 * @return rand value
	 */
	public int getRand(int minValue, int maxValue, int avgValue) {
		int randValue = random.nextInt(maxValue - minValue) + minValue;
		int delta = randValue - avgValue;
		if (delta == 0) {
			delta = 1;
		}
		int correctionValue = random.nextInt(Math.abs(delta));

		if (randValue > avgValue) {
			randValue += correctionValue;
		} else {
			randValue -= correctionValue;
		}

		return correctValue(randValue, minValue, maxValue);
	}

	public int getRand(int minValue, int maxValue) {
		int avgValue = (maxValue - minValue) / 2;
		int randValue = random.nextInt(maxValue - minValue) + minValue;
		int delta = randValue - avgValue;
		int correctionValue = random.nextInt(Math.abs(delta));

		if (randValue > avgValue) {
			randValue += correctionValue;
		} else {
			randValue -= correctionValue;
		}

		return correctValue(randValue, minValue, maxValue);
	}

	private int correctValue(int value, int minValue, int maxValue) {
		if (value < minValue) {
			value = minValue;
		}
		if (value > maxValue) {
			value = maxValue;
		}
		return value;
	}

}
