package org.pb.input;

import java.awt.Robot;

import org.pb.input_output_util.ClipboardCommunicator;
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;

public class ChatReader {
	/**
	 * Position of the last item in chat box
	 */
	private Coordinates lastItemCoords;

	private final int ITEM_HEIGHT = 13;

	/**
	 * item count, which is shown in screen
	 */
	private final int ITEM_COUNT = 4;

	public ChatReader(Coordinates lastItemCoords) {
		this.lastItemCoords = new Coordinates(lastItemCoords);
	}

	/**
	 * this method hopes, that players will not write messages, which will be
	 * contained in two items
	 * 
	 * @return
	 */
	public String readLastDillerMessage() {
		int currentItem = 0;
		String gotItem = null;
		String addtitionalItem = null;
		for (int i = 0; i < ITEM_COUNT; i++) {
			gotItem = readItem(currentItem);

			// if Robot pressed at empty line
			if (gotItem.trim().length() == 0) {
				currentItem++;
			} else {
				// if message contains of two items
				if (MessageParser.beginsWithSpace(gotItem)) {
					addtitionalItem = readItem(currentItem + 1);
					return (addtitionalItem.trim() + " " + gotItem.trim());
				} else {
					if (MessageParser.isDealerMessage(gotItem)) {
						return gotItem.trim();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Items numeration begins from 0. Item with index 0 is the last (numeration
	 * from bottom)
	 * 
	 * @param itemNumber
	 * @return
	 */
	private String readItem(int itemNumber) {
		// System.out.println(lastItemCoords);
		ClipboardCommunicator.setClipboardText("null");
		IOUtil.absoluteLeftMouseClick(lastItemCoords.getX(),
				lastItemCoords.getY() - itemNumber * ITEM_HEIGHT);
		IOUtil.wait(100);
		IOUtil.ctrlC();
		IOUtil.wait(100);
		String result = ClipboardCommunicator.getClipboardText();
		IOUtil.wait(100);
		// System.out.println("read: " + result);
		return result;
	}

}
