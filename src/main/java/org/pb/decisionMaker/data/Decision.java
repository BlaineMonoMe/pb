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
	private final Player player;

	// what decision
	private final Action action;

	// if it is rise or call - how big(in chips) is this rise/call
	private int stackSize;

	public Decision(Player player, Action action) {
		this.player = player;
		this.action = action;
		System.out.println("    creating new DECISION: " + player + " "
				+ action);
	}

	public Decision(Player player, Action action, int stackSize) {
		this.player = player;
		this.action = action;
		this.stackSize = stackSize;
		System.out.println("    creating new DECISION: " + player + " "
				+ action + " " + stackSize);
	}

	@Override
	public String toString() {
		return "|decision| " + player + " " + action + "(" + stackSize + ")";
	}

	public void setStackSize(int stackSize) {
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
