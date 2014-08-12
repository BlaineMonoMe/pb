package org.pb.input;

import org.pb.input_output_util.ClipboardCommunicator;
import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;

public class ChatReader {
	/**
	 * Position of the last item in chat box
	 */
	private Coordinates lastItemCoords;

	private final int ITEM_HEIGHT = 13;

	public ChatReader(Coordinates lastItemCoords) {
		this.lastItemCoords = new Coordinates(lastItemCoords);
	}

	public void readLastTwoItems() {
		int currentItem = 0;
		for (int i = 0; i < 20; i++) {
			String str1 = null;
			String str2 = readItem(currentItem);
			// System.out.println(str2);
			if (str2.equals(new String("null"))) {
				currentItem++;
			} else {
				str1 = readItem(currentItem + 1);
				System.out.println("   got string: " + str1 + " " + str2);
				break;
			}
			//System.out.println("   got string: " + str1 + " " + str2);
		}
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
		String result = ClipboardCommunicator.getClipboardText().trim();
		IOUtil.wait(100);
		// System.out.println("read: " + result);
		return result;
	}

}
