package org.pb.decisionImplementation;

import org.pb.inputOutputUtil.Coordinates;
import org.pb.inputOutputUtil.IOUtil;
import org.pb.inputOutputUtil.Randomizer;
import org.pb.util.NumberFormatter;

public class ActionExecutor {

	private final int BUTTON_WIDTH = 95;
	private final int BUTTON_HEIGHT = 30;
	private final int BUTTON_Y;
	private final int FOLD_BUTTON_X;
	private final int CHECK_CALL_BUTTON_X;
	private final int BET_BUTTON_X;

	private final int BET_FIELD_X;
	private final int BET_FIELD_Y;
	private final int BET_FIELD_WIDTH = 5;
	private final int BET_FIELD_HEIGHT = 9;

	private Randomizer randomizer;

	public ActionExecutor(Coordinates centerOfTheTable) {
		FOLD_BUTTON_X = centerOfTheTable.getX();
		CHECK_CALL_BUTTON_X = centerOfTheTable.getX() + 133;
		BET_BUTTON_X = centerOfTheTable.getX() + 273;
		BUTTON_Y = centerOfTheTable.getY() + 271;
		BET_FIELD_X = centerOfTheTable.getX() + 213;
		BET_FIELD_Y = centerOfTheTable.getY() + 240;
	}

	public void check(int millisWait) {
		IOUtil.wait(millisWait);
		int x = randomizer.getRand(CHECK_CALL_BUTTON_X, CHECK_CALL_BUTTON_X
				+ BUTTON_WIDTH);
		int y = randomizer.getRand(BUTTON_Y, BUTTON_Y + BUTTON_HEIGHT);
		IOUtil.absoluteLeftMouseClick(x, y);
	}

	public void fold(int millisWait) {
		IOUtil.wait(millisWait);
		int x = randomizer.getRand(FOLD_BUTTON_X, FOLD_BUTTON_X + BUTTON_WIDTH);
		int y = randomizer.getRand(BUTTON_Y, BUTTON_Y + BUTTON_HEIGHT);
		IOUtil.absoluteLeftMouseClick(x, y);
	}

	public void call(int millisWait) {
		check(millisWait);
	}

	public void bet(int millisWait, int sizeOfBet) {
		IOUtil.wait(randomizer.getRand(50, 250));

		// dblClick on bet field to activate it
		int x = randomizer.getRand(BET_FIELD_X, BET_FIELD_X + BET_FIELD_WIDTH);
		int y = randomizer.getRand(BET_FIELD_Y, BET_FIELD_Y + BET_FIELD_HEIGHT);
		IOUtil.absoluteLeftMouseDblClick(x, y);

		// enter digits to the field
		enterBetSizeDigits(NumberFormatter.getDigitsFromInt(sizeOfBet));

		IOUtil.wait(randomizer.getRand(50, 250));
		IOUtil.wait(millisWait);

		// enter bet button to accept bet
		x = randomizer.getRand(BET_BUTTON_X, BET_BUTTON_X + BUTTON_WIDTH);
		y = randomizer.getRand(BUTTON_Y, BUTTON_Y + BUTTON_HEIGHT);
		IOUtil.absoluteLeftMouseClick(x, y);
	}

	private void enterBetSizeDigits(int[] digits) {
		for (int currDigit = 0; currDigit < digits.length; currDigit++) {
			IOUtil.wait(randomizer.getRand(50, 250));
			IOUtil.pressDigit(digits[currDigit]);
		}

	}

}
