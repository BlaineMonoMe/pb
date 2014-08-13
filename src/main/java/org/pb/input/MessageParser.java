package org.pb.input;

public class MessageParser {

	public static boolean beginsWithSpace(String data) {
		if (data.indexOf(" ") == 0) {
			return true;
		}
		return false;
	}

	public static boolean isDealerMessage(String data) {
		return data.contains("Дилер: ");
	}
}
