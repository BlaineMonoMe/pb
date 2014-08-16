package org.pb.input;

import java.util.ArrayList;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.input_output_util.Rectangle;
import org.pb.state.Card;

/**
 * 
 * @author Yuriy
 * 
 *         subclasses defines area on the table where to do search cards this
 *         class do search of the cards
 */
public abstract class CardsReader {

	protected int TABLE_CARDS_WIDTH_AREA;
	protected int TABLE_CARDS_HEIGHT_AREA;
	protected int TABLE_CARDS_Y_OFFSET;

	// where to find cards
	protected Rectangle cardsArea;
	protected Coordinates centerOfTheTable;

	public CardsReader(Coordinates centerOfTheTable) {
		this.centerOfTheTable = centerOfTheTable;
	}

	public ArrayList<Card> readCards() {
		ArrayList<Card> cardList = new ArrayList<Card>();
		if (IOUtil.existPicture("res\\images\\cards\\4s.PNG", cardsArea)) {
			cardList.add(new Card('s', 4));
		}
		if (IOUtil.existPicture("res\\images\\cards\\6c.PNG", cardsArea)) {
			cardList.add(new Card('c', 6));
		}
		if (IOUtil.existPicture("res\\images\\cards\\6s.PNG", cardsArea)) {
			cardList.add(new Card('s', 6));
		}
		if (IOUtil.existPicture("res\\images\\cards\\3s.PNG", cardsArea)) {
			cardList.add(new Card('s', 3));
		}
		if (IOUtil.existPicture("res\\images\\cards\\7d.PNG", cardsArea)) {
			cardList.add(new Card('d', 7));
		}
		if (IOUtil.existPicture("res\\images\\cards\\7h.PNG", cardsArea)) {
			cardList.add(new Card('h', 7));
		}
		if (IOUtil.existPicture("res\\images\\cards\\9c.PNG", cardsArea)) {
			cardList.add(new Card('c', 9));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Td.PNG", cardsArea)) {
			cardList.add(new Card('d', 10));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Th.PNG", cardsArea)) {
			cardList.add(new Card('h', 10));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Js.PNG", cardsArea)) {
			cardList.add(new Card('s', 11));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Qh.PNG", cardsArea)) {
			cardList.add(new Card('h', 12));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Kc.PNG", cardsArea)) {
			cardList.add(new Card('c', 13));
		}
		if (IOUtil.existPicture("res\\images\\cards\\2d.PNG", cardsArea)) {
			cardList.add(new Card('d', 2));
		}
		if (IOUtil.existPicture("res\\images\\cards\\3h.PNG", cardsArea)) {
			cardList.add(new Card('h', 3));
		}
		if (IOUtil.existPicture("res\\images\\cards\\4c.PNG", cardsArea)) {
			cardList.add(new Card('c', 4));
		}
		if (IOUtil.existPicture("res\\images\\cards\\5c.PNG", cardsArea)) {
			cardList.add(new Card('c', 5));
		}
		if (IOUtil.existPicture("res\\images\\cards\\6d.PNG", cardsArea)) {
			cardList.add(new Card('d', 6));
		}
		if (IOUtil.existPicture("res\\images\\cards\\6h.PNG", cardsArea)) {
			cardList.add(new Card('h', 6));
		}
		if (IOUtil.existPicture("res\\images\\cards\\7s.PNG", cardsArea)) {
			cardList.add(new Card('s', 7));
		}
		if (IOUtil.existPicture("res\\images\\cards\\8d.PNG", cardsArea)) {
			cardList.add(new Card('d', 8));
		}
		if (IOUtil.existPicture("res\\images\\cards\\9s.PNG", cardsArea)) {
			cardList.add(new Card('s', 9));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Jd.PNG", cardsArea)) {
			cardList.add(new Card('d', 11));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Qc.PNG", cardsArea)) {
			cardList.add(new Card('c', 12));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Kh.PNG", cardsArea)) {
			cardList.add(new Card('h', 13));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Ks.PNG", cardsArea)) {
			cardList.add(new Card('s', 13));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Ah.PNG", cardsArea)) {
			cardList.add(new Card('h', 14));
		}
		if (IOUtil.existPicture("res\\images\\cards\\Ac.PNG", cardsArea)) {
			cardList.add(new Card('c', 14));
		}
		if (IOUtil.existPicture("res\\images\\cards\\As.PNG", cardsArea)) {
			cardList.add(new Card('s', 14));
		}
		return cardList;
	}

}
