package org.pb.input;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.Rectangle;
import org.pb.state.Players;

public class PlayerCardsReader extends CardsReader {

	public PlayerCardsReader(Coordinates centerOfTheTable, Players players) {
		super(centerOfTheTable);
		TABLE_CARDS_WIDTH_AREA = 150;
		TABLE_CARDS_HEIGHT_AREA = 150;
		if (players.isiAmSittingOnTheTop()) {
			TABLE_CARDS_Y_OFFSET = -210;
		} else {
			TABLE_CARDS_Y_OFFSET = 80;
		}

		// TODO: remove code 'dublicating'
		cardsArea = new Rectangle(centerOfTheTable.getX()
				- TABLE_CARDS_WIDTH_AREA / 2, centerOfTheTable.getY()
				- TABLE_CARDS_HEIGHT_AREA / 2 + TABLE_CARDS_Y_OFFSET,
				TABLE_CARDS_WIDTH_AREA, TABLE_CARDS_HEIGHT_AREA);
	}

}
