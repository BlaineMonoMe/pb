package org.pb.input;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.Rectangle;

public class TableCardsReader extends CardsReader {

	public TableCardsReader(Coordinates centerOfTheTable) {
		super(centerOfTheTable);

		TABLE_CARDS_WIDTH_AREA = 450;
		TABLE_CARDS_HEIGHT_AREA = 170;
		TABLE_CARDS_Y_OFFSET = -20;

		// TODO: remove code 'dublicating'
		cardsArea = new Rectangle(centerOfTheTable.getX()
				- TABLE_CARDS_WIDTH_AREA / 2, centerOfTheTable.getY()
				- TABLE_CARDS_HEIGHT_AREA / 2 + TABLE_CARDS_Y_OFFSET,
				TABLE_CARDS_WIDTH_AREA, TABLE_CARDS_HEIGHT_AREA);
	}
}
