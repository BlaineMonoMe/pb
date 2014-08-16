package org.pb.input;

import java.util.ArrayList;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.input_output_util.Rectangle;
import org.pb.state.Card;
import org.pb.system_data.CardTarget;
import org.pb.system_data.CardTargetManager;

/**
 * 
 * @author Yuriy
 * 
 *         subclasses defines area on the table where to do search cards this
 *         class do search of the cards
 */
public abstract class CardsReader {

	// where to find cards
	protected ArrayList<Rectangle> cardsAreas;
	protected Coordinates centerOfTheTable;

	public CardsReader(Coordinates centerOfTheTable) {
		cardsAreas = new ArrayList<Rectangle>();
		this.centerOfTheTable = centerOfTheTable;
	}

	public ArrayList<Card> readCards(CardTargetManager cardTargetManager) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		// CardTargetManager cardTargetManager = new CardTargetManager();
		System.out.println("in CardsReader: begin search cards");
		ArrayList<CardTarget> cardTargetList = cardTargetManager
				.getCardTargets();
		outer: for (int i = 0; i < cardsAreas.size(); i++) {
			for (int j = 0; j < cardTargetList.size(); j++) {
				System.out.println("j = " + j);
				if (IOUtil.existPicture(cardTargetList.get(j).getTarget(),
						cardsAreas.get(i))) {
					cardList.add(cardTargetList.get(j).getCard());
					continue outer;
				}
			}
		}
		System.out.println("in CardsReader: end search cards");
		return cardList;
	}

}
