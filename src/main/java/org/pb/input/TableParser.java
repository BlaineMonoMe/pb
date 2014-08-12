package org.pb.input;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;

public class TableParser {
	private Coordinates centerOfTheTable;
	private Coordinates chatLastItemCoords;

	public TableParser() {
		initCenterOfTheTable();
	}

	private void initCenterOfTheTable() {
		centerOfTheTable = IOUtil.getCenterCoordinates("res\\images\\CHAT.PNG");
		centerOfTheTable.change(194, -152);
		centerOfTheTable.change(173, -64);

		chatLastItemCoords = IOUtil
				.getCenterCoordinates("res\\images\\CHAT.PNG");
		chatLastItemCoords.change(0, 86);
	}

	public void start() {
		ChatReader cr = new ChatReader(chatLastItemCoords);
		cr.readLastTwoItems();
	}

}
