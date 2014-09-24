package org.pb.select_table;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.input_output_util.Randomizer;

public final class TableSearcher {

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
	private Coordinates windowCoords;

	private Randomizer randomizer;

	public TableSearcher(int status) {
		this.status = status;
		randomizer = new Randomizer();
	}

	public void backOnTableList() {
		updateWindowCoordinates();
		status = 1;
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
				IOUtil.wait(1000);
				updateWindowCoordinates();
				break;
			case 1:
				selectTable();
				break;
			case 3:
				IOUtil.wait(randomizer.getRand(100, 400));
				IOUtil.enter();
				IOUtil.wait(1000);
				status = 4;
				break;
			case 4:
				IOUtil.wait(randomizer.getRand(100, 400));
				IOUtil.enter();
				IOUtil.wait(1000);
				return true;
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

	private void updateWindowCoordinates() {
		windowCoords = IOUtil
				.getCenterCoordinates("res\\images\\LITTLE_ICON.PNG");
		windowCoords.changeX(-22);
		windowCoords.changeY(32);
		System.out.println(windowCoords);
	}

	private void selectTable() {
		while (true) {
			synchronized (this) {

				IOUtil.absoluteLeftMouseDblClick(windowCoords.getX()
						+ randomizer.getRand(50, 200, 100),
						windowCoords.getY() + currentTable * TABLE_ITEM_HEIGHT
								+ randomizer.getRand(3, TABLE_ITEM_HEIGHT - 5));

			}

			IOUtil.wait(1000);
			if (isTableEmptyMessage()) {
				IOUtil.wait(randomizer.getRand(0, 300));
				IOUtil.escape();
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
