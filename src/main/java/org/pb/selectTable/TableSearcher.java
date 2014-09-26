package org.pb.selectTable;

import org.pb.input.screens.HomeScreen;
import org.pb.input.screens.TableRow;
import org.pb.inputOutputUtil.IOUtil;
import org.pb.inputOutputUtil.Randomizer;

public final class TableSearcher {

    private static final int WAIT_TIMEOUT = 400; // milliseconds
    private static final int WAIT_BETWEEN_ACTION_TIMEOUT = 1000; // milliseconds
    private static Randomizer randomizer;

    private static HomeScreen homeScreen;

    public TableSearcher() {
        homeScreen = new HomeScreen();
    }

	/**
	 * Main function, which calls another (private) functions
	 * 
	 * @return O_o
	 */
	public void searchAndSeat() {
        while (true) {
            boolean escape = false;
            for (TableRow row : homeScreen.getTableListScreen().getTableRowList()) {
                IOUtil.absoluteLeftMouseDblClick(row.getCoordinates().getX(), row.getCoordinates().getY());
                if (!homeScreen.isTableEmptyMessage()) {
                    escape = true;
                    break;
                } else {
                    IOUtil.wait(WAIT_TIMEOUT);
                    IOUtil.escape();
                }
            }
            if (escape) {
                break;
            }
        }
		IOUtil.wait(WAIT_TIMEOUT);
		IOUtil.enter();
		IOUtil.wait(WAIT_BETWEEN_ACTION_TIMEOUT);
		IOUtil.wait(WAIT_TIMEOUT);
		IOUtil.enter();
		IOUtil.wait(WAIT_BETWEEN_ACTION_TIMEOUT);

	}



}
