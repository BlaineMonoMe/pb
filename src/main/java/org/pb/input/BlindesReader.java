package org.pb.input;

import org.pb.input.diller.DillerState;
import org.pb.input.handsStackReader.RealTimeHSR;

public class BlindesReader {

	private RealTimeHSR myHandsStackReader;

	/**
	 * THIS CLASS IS NOT USED
	 */
	private int UNUSEFUL_VAR;

	public BlindesReader(RealTimeHSR myHandsStackReader) {
		this.myHandsStackReader = myHandsStackReader;
	}

	public int calculateBlindes(DillerState dillerState) {
		int koef = 1;
		if (dillerState == DillerState.ME_DILLER) {
			koef = 2;
		}
		System.out
				.println("bbbb " + myHandsStackReader.getStackSizeAtNewTurn());
		System.out.println("bbb " + myHandsStackReader.getStackSize());

		return koef
				* (myHandsStackReader.getStackSizeAtNewTurn() - myHandsStackReader
						.getStackSize());
	}

}
