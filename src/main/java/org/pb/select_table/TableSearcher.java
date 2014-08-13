package org.pb.select_table;

import org.pb.input.ChatReader;
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;

import javax.swing.*;

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
	private Coordinates windowCoords = null;

	public TableSearcher(int status) {
		this.status = status;
	}

	public void backOnTableList() {
		updateWindowCoorditates();
		status = 1;
	}

	/**
	 * Main function, which calls another (private) functions
	 * 
	 * @return O_o
	 */
	public boolean searchAndSeat() {

		// EnemyPresentionReader epr = new EnemyPresentionReader(1000, this);
		// epr.start();

		while (status < 5) {

			// epr.disable();
			switch (status) {
			case 0:
				activateWindow();
                IOUtil.wait(1000);
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
				// epr.enable();
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

	private void updateWindowCoorditates() {
		windowCoords = IOUtil
				.getCenterCoordinates("res\\images\\LITTLE_ICON.PNG");
		windowCoords.changeX(-22);
		windowCoords.changeY(32);
		System.out.println(windowCoords);
	}

	private void selectTable() {
		while (true) {
//            new Thread() {
//                public void run() {
//                    IOUtil.absoluteLeftMouseDblClick(windowCoords.getX() + 50,
//                            windowCoords.getY() + currentTable * TABLE_ITEM_HEIGHT
//                                    + TABLE_ITEM_HEIGHT / 2);
//                }
//            }.start();
			IOUtil.absoluteLeftMouseDblClick(windowCoords.getX() + 50,
					windowCoords.getY() + currentTable * TABLE_ITEM_HEIGHT
							+ TABLE_ITEM_HEIGHT / 2);
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
