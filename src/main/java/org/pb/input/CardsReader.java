package org.pb.input;

import org.pb.input_output_util.Coordinates;

public abstract class CardsReader {

	/**
	 * UNUSEFULL CLASS
	 */
	private Coordinates centerOfTable;
	private ScreenShootMaker screenShootMaker;

	public CardsReader(Coordinates centerOfTable,
			ScreenShootMaker screenShootMaker) {
		this.centerOfTable = centerOfTable;
		this.screenShootMaker = screenShootMaker;
	}

}
