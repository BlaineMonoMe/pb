package org.pb.input;

/**
 * Begins to do it's job just after chips count was selected
 */
import java.util.ArrayList;

import org.pb.input_output_util.Coordinates;
import org.pb.input_output_util.IOUtil;
import org.pb.state.Players;

public class TableParser {

	/**
	 * 0 - unnown. 1 - waiting for a new game
	 */
	private int status;

	private Coordinates centerOfTheTable;
	private Coordinates chatLastItemCoords;

	private ArrayList<String> chatMessages;

	private ChatReader chatReader;

	public TableParser() {
		initCoordinates();
		chatMessages = new ArrayList<String>();
		chatReader = new ChatReader(chatLastItemCoords);
		status = 0;
	}

	private void initCoordinates() {
		centerOfTheTable = IOUtil.getCenterCoordinates("res\\images\\CHAT.PNG");
		centerOfTheTable.change(194, -152);
		centerOfTheTable.change(173, -64);

		chatLastItemCoords = IOUtil
				.getCenterCoordinates("res\\images\\CHAT.PNG");
		chatLastItemCoords.change(0, 86);
	}

	private String saveLastDillerMessage() {
		String lastDillerMessage = chatReader.readLastDillerMessage();
		chatMessages.add(lastDillerMessage);
		return lastDillerMessage;
	}

	private String loadLastDillerMessage() {
		if (chatMessages.size() == 0) {
			return new String("---");
		}
		return chatMessages.get(chatMessages.size() - 1);
	}

	private boolean isWaitingForGameBegin(String msg) {
		return msg.contains("Нова гра почнеться через");
	}

	private boolean isGameCanceled(String msg) {
		return msg.contains("Роздачу відмінено");
	}

	private void parseMessage(String msg) {
		if (msg.contains("Роздачу відмінено")) {
			status = 0;
		}
		if (msg.contains("Нова гра почнеться через")) {
			status = 1;
		}
		if (msg.contains("блайнд")) {
			tryToSetEnemyName(msg, null);
		}
	}

	// TODO: Players should be not as parameter, but as class member
	private void tryToSetEnemyName(String msg, Players players) {
		String name = msg.split(" ")[1];
		if (!name.equals(players.getMyName())) {
			players.setEnemyName(name);
		}
	}

	public void start() {

		String msg = saveLastDillerMessage();
		

		/*
		 * ChatReader cr = new ChatReader(chatLastItemCoords);
		 * System.out.println(cr.readLastDillerMessage());
		 */
	}

}
