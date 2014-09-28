package org.pb.input.screens;

import org.pb.inputOutputUtil.Coordinates;
import org.pb.inputOutputUtil.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/26/14
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableListScreen {

    private List<TableRow> tableRowList;
    private Coordinates startingPoint;
    private int tableRowsNumber = 8;  // default value
    private static final int TABLE_ITEM_HEIGHT = 24;
    private static TableListScreen tableListScreen = null;

    private static Randomizer randomizer;

    private TableListScreen() {
        setTableRowList(new ArrayList<TableRow>());
        randomizer = new Randomizer();
    }

    public static TableListScreen getTableListScreen(Coordinates startingPoint) {
        if (tableListScreen == null) {
            tableListScreen = new TableListScreen();
        }
        tableListScreen.startingPoint = startingPoint;
        initializeTable();
        return tableListScreen;
    }

    public static TableListScreen getTableListScreen(Coordinates startingPoint, int tableRowsNumber) {
        if (tableListScreen == null) {
            tableListScreen = new TableListScreen();
        }
        tableListScreen.startingPoint = startingPoint;
        tableListScreen.tableRowsNumber = tableRowsNumber;
        initializeTable();
        return tableListScreen;
    }

    private static void initializeTable() {
        Coordinates coordinates = new Coordinates(tableListScreen.startingPoint);
        for (int i = 0; i < tableListScreen.tableRowsNumber; i++) {
            tableListScreen.getTableRowList().add(new TableRow(coordinates));
            coordinates = new Coordinates(coordinates.getX()
                    + randomizer.getRand(50, 200, 100),
                    coordinates.getY() + (i + 1) * TABLE_ITEM_HEIGHT
                            + randomizer.getRand(3, TABLE_ITEM_HEIGHT - 5));
        }
    }

    public List<TableRow> getTableRowList() {
        return tableRowList;
    }

    public void setTableRowList(List<TableRow> tableRowList) {
        this.tableRowList = tableRowList;
    }
}
