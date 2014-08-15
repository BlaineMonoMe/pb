package org.pb.appl;

import org.pb.input.TableParser;
import org.pb.select_table.TableSearcher;
import org.pb.state.Players;

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
	private Players players;

	public SystemManager() {
		tableSearcher = new TableSearcher(0);
		players = new Players();
	}

	public void start() {
		tableSearcher.searchAndSeat();
		tableParser = new TableParser(players);
		tableParser.start();
	}
}
