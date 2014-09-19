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
	// private CardTargetManager cardTargetManager;
	//private Players players;

	public SystemManager() {
		tableSearcher = new TableSearcher(0);
		//players = new Players();
		// cardTargetManager = new CardTargetManager();
	}

	public void start() {
		tableSearcher.searchAndSeat();
		tableParser = new TableParser();
		tableParser.start();
	}
}
