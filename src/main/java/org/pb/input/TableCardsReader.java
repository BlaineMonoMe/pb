package org.pb.input;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.Rectangle;

public class TableCardsReader extends CardsReader {

	public TableCardsReader(Coordinates centerOfTheTable) {
		super(centerOfTheTable);
		cardsAreas.add(new Rectangle(273, 229, 16, 30));
		cardsAreas.add(new Rectangle(328, 229, 16, 30));
		cardsAreas.add(new Rectangle(382, 229, 16, 30));
		cardsAreas.add(new Rectangle(436, 229, 16, 30));
		cardsAreas.add(new Rectangle(490, 229, 16, 30));
	}
}
