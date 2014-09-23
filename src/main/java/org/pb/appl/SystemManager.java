package org.pb.appl;

import org.pb.input.TableParser;
import org.pb.select_table.TableSearcher;

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

	public SystemManager() {
		tableSearcher = new TableSearcher(0);
	}

	public void start() {
		tableSearcher.searchAndSeat();
		tableParser = new TableParser();
		tableParser.start();
	}
}
