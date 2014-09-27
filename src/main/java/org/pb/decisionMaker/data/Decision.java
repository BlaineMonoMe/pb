package org.pb.decisionMaker.data;

import org.pb.decisionMaker.Action;
import org.pb.decisionMaker.Player;

/**
 * This class represents players decision.
 * 
 * @author Yuriy
 * 
 */
public class Decision {

	// who make decision
	private Player player;

	// what decision
	private Action action;

	// if it is rise or call - how big(in chips) is this rise/call
	private int stackSize;

	public Decision(Player player, Action action) {
		this.player = player;
		this.action = action;
	}

	public Decision(Player player, Action action, int stackSize) {
		this.player = player;
		this.action = action;
		this.stackSize = stackSize;
	}

	public Player getPlayer() {
		return player;
	}

	public Action getAction() {
		return action;
	}

	public int getStackSize() {
		return stackSize;
	}

}
