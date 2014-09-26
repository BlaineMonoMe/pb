package org.pb.input.screens;

import org.pb.inputOutputUtil.Coordinates;

/**
 * Created with IntelliJ IDEA.
 * User: AROS
 * Date: 9/26/14
 * Time: 5:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableRow {

    private Coordinates coordinates;

    public TableRow(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
