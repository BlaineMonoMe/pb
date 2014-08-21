package org.pb.input;

import org.pb.input_output_util.Coordinates;

public abstract class CardsReader {

	private Coordinates centerOfTable;
	private ScreenShootMaker screenShootMaker;

	public CardsReader(Coordinates centerOfTable,
			ScreenShootMaker screenShootMaker) {
		this.centerOfTable = centerOfTable;
		this.screenShootMaker = screenShootMaker;
	}
	
	

}
