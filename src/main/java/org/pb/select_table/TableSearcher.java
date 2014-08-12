package org.pb.select_table;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;

public final class TableSearcher {

	/**
	 * offset from windowCoords.y
	 */
	private final int FIRST_TABLE_Y_OFFSET = 262;

	private final int TABLE_ITEM_HEIGHT = 24;

	private final int TABLE_COUNT = 8;

	/**
	 * 0 - PS window is not activated; 1 - list of tables we can see; 2 - empty
	 * table warning; 3 - "bay-in" warning; 4 - chips count selection window.
	 */
	private int status = 0;

	/**
	 * table, which will be trying to access to.
	 */
	private int currentTable = 0;

	/**
	 * Left up co0rdinates of PS window
	 */
	private Coordinates windowCoords = null;

	public TableSearcher() {
	}

	/**
	 * Main function, which calls another (private) functions
	 * 
	 * @return O_o
	 */
	public boolean searchAndSeat() {
		while (status < 5) {

			switch (status) {
			case 0:
				activateWindow();
				updateWindowCoorditates();
				break;
			case 1:
				selectTable();
				break;
			case 3:
				IOUtil.leftMouseClickOnComponent("res\\images\\OK_BLACK.PNG",
						30, 4);
				IOUtil.wait(1000);
				status = 4;
				break;
			case 4:
				IOUtil.leftMouseClickOnComponent("res\\images\\OK_GREEN.PNG",
						30, 4);
				IOUtil.wait(1000);
				break;
			}

		}
		return true;
	}

	private boolean activateWindow() {
		if (IOUtil.leftMouseClickOnComponent("res\\images\\ICON.PNG", 3, 3)) {
			status = 1;
			return true;
		}
		System.out.println("PS is not launched, or I can't find it...");
		return false;
	}

	private void updateWindowCoorditates() {
		windowCoords = IOUtil
				.getCenterCoordinates("res\\images\\LITTLE_ICON.PNG");
		windowCoords.changeX(-11);
		windowCoords.changeY(+18);
		// now, windowCoords refers to left up point of main menu
	}

	private void selectTable() {
		while (true) {
			IOUtil.absoluteLeftMouseDblClick(windowCoords.getX() + 50,
					(windowCoords.getY() + FIRST_TABLE_Y_OFFSET) + currentTable
							* TABLE_ITEM_HEIGHT + TABLE_ITEM_HEIGHT / 2);
			IOUtil.wait(1000);
			if (isTableEmptyMessage()) {
				IOUtil.leftMouseClickOnComponent(
						"res\\images\\CANCEL_EMPTY_TABLE.PNG", 30, 10);
				currentTable++;
				if (currentTable == TABLE_COUNT) {
					currentTable = 0;
				}
				IOUtil.wait(1000);
			} else {
				status = 3;
				return;
			}
		}
	}

	private boolean isTableEmptyMessage() {
		if (IOUtil.existPicture("res\\images\\CANCEL_EMPTY_TABLE.PNG")) {
			return true;
		}
		return false;
	}

}
