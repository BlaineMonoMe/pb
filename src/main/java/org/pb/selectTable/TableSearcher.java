package org.pb.selectTable;

import org.pb.input.screens.*;
import org.pb.inputOutputUtil.IOUtil;
import org.pb.inputOutputUtil.Randomizer;
import org.pb.util.ExpectedCondition;
import org.pb.util.ScreenWait;

public final class TableSearcher {

    private static final int WAIT_TIMEOUT = 400; // milliseconds
    private static final long WAIT_FOR_SCREEN_TIMEOUT = 4000;
    private static final long WAIT_BETWEEN_ACTION_TIMEOUT = 4000; // milliseconds

    private static ScreenWait screenWait;
    private static Randomizer randomizer;

    private static HomeScreen homeScreen;

    public TableSearcher() {
        homeScreen = new HomeScreen();
        screenWait = new ScreenWait(WAIT_FOR_SCREEN_TIMEOUT);
    }

	/**
	 * Main function, which calls another (private) functions
	 * 
	 * @return O_o
	 */
	public void searchAndSeat() {
        homeScreen.clickPokerStarsIcon();
        while (true) {
            boolean escape = false;
            for (TableRow row : homeScreen.getTableListScreen().getTableRowList()) {
                IOUtil.absoluteLeftMouseDblClick(row.getCoordinates().getX(), row.getCoordinates().getY());
                IOUtil.waitPatiently(WAIT_FOR_SCREEN_TIMEOUT);
                if (!homeScreen.isTableEmptyMessage() && BuyInAlertMessageConfirmWindow.exists()) {
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
        screenWait.waitUntil(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean until() {
                        return BuyInAlertMessageConfirmWindow.exists();
                    }
                }
        );
        IOUtil.enter();
        screenWait.waitUntil(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean until() {
                        return BuyInStackSizePromptWindow.exists();
                    }
                }
        );
        IOUtil.enter();
        screenWait.waitUntil(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean until() {
                        return PokerTableScreen.exists();
                    }
                }
        );
	}



}
