package org.pb.system_data;

import java.io.File;

import org.pb.state.Card;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.Target;

public class CardTarget {

	private Target target;
	private Card card;

	public CardTarget(String file) {
		init(file);
	}

	public CardTarget() {
	}

	public void init(String file) {
		File image = new File(file);
		target = new ImageTarget(image);
		target.setMinScore(0.99);

		String name = file;
		//System.out.println(name);
		// String name = file.split(".")[0];
		char lear = name.charAt(name.length() - 5);
		char level = name.charAt(name.length() - 6);
		//System.out.println("..." + level + " " + lear);
		card = new Card(lear, level);
		//System.out.println(card);
	}

	public Target getTarget() {
		return target;
	}

	public Card getCard() {
		return card;
	}

}
