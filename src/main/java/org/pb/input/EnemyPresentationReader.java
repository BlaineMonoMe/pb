package org.pb.input;

import org.pb.input_output_util.IOUtil;
import org.pb.select_table.TableSearcher;

/**
 * 
 * @author Yuriy
 * 
 *         additional thread, which controls if enemy is gone or not present
 * 
 */
public class EnemyPresentationReader extends Thread {

	private boolean enable;
	private boolean isGoingToDie;
	private int freequency;
	private TableSearcher ts;

	public EnemyPresentationReader(int freequency, TableSearcher ts) {
		this.freequency = freequency;
		enable = true;
		isGoingToDie = false;
		this.ts = ts;
		// System.out.println("THREAD!!!");
		this.setDaemon(true);
	}

	public void enable() {
		enable = true;
	}

	public void disable() {
		enable = false;
	}

	public void kill() {
		isGoingToDie = true;
	}

	@Override
	public void run() {
		while (!isGoingToDie) {
			try {
				Thread.sleep(freequency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (enable) {
				if (isEnemyMissing()) {
					closeTable();
				}
			}
		}

	}

	/**
	 * When ps is sitting by the table, but enemy player is missing
	 * 
	 * @return
	 */
	private boolean isEnemyMissing() {
		if (IOUtil.existPicture("res\\images\\IS_NOT_HERE.PNG")) {
			return true;
		}
		return false;
	}

	private void closeTable() {
		IOUtil.closeWindow();
		IOUtil.wait(500);
		IOUtil.leftMouseClickOnComponent("res\\images\\OK_BLACK.PNG", 9, 2);
		IOUtil.wait(1000);
		ts.backOnTableList();
	}

}
