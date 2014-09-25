package org.pb.appl;

import org.pb.input.TableParser;
import org.pb.inputMessagesAnalyzer.TableMessagesParser;
import org.pb.selectTable.TableSearcher;

/**
 * 
 * @author Yuriy
 * 
 *         Manages all system. This class will know what happening now, launch
 *         threads, activate sybsystems
 */
public class SystemManager {
	private TableSearcher tableSearcher;
	private TableParser tableParser;
	private TableMessagesParser tableMessagesParser;

	public SystemManager() {
		tableSearcher = new TableSearcher(0);
	}

	public void start() {
		tableSearcher.searchAndSeat();

		tableMessagesParser = new TableMessagesParser();
		tableParser = new TableParser();
		tableParser.start(tableMessagesParser);
	}
}
