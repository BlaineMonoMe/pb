package org.pb.input.screens;

import org.pb.input.global.FilePaths;
import org.pb.inputOutputUtil.Coordinates;
import org.pb.inputOutputUtil.IOUtil;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/26/14
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeScreen {

    private TableListScreen tableListScreen;
    private static Coordinates windowCoords;

    public void clickPokerStarsIcon() {
        if(!IOUtil.leftMouseClickOnComponent(FilePaths.ICON, 3, 3)) {
            throw new RuntimeException("The Poker Stars application isn't started or the icon has changed");
        }
        IOUtil.wait(1000);
        updateWindowCoordinates();
        tableListScreen = TableListScreen.getTableListScreen(windowCoords);
    }

    public boolean isTableEmptyMessage() {
        if (IOUtil.existPicture(FilePaths.EMPTY_TABLE_MASSAGE)) {
            return true;
        }
        return false;
    }

    public TableListScreen getTableListScreen() {
        return tableListScreen;
    }

    public void setTableListScreen(TableListScreen tableListScreen) {
        this.tableListScreen = tableListScreen;
    }

    private void updateWindowCoordinates() {
        windowCoords = IOUtil
                .getCenterCoordinates(FilePaths.LITTLE_ICON);
        windowCoords.changeX(-15);
        windowCoords.changeY(32);
        System.out.println(windowCoords);
    }
}
