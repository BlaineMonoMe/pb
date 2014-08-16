package org.pb.system_data;

import java.io.File;
import java.util.ArrayList;

public class CardTargetManager {
	private ArrayList<CardTarget> cardTargets;

	public CardTargetManager() {
		cardTargets = new ArrayList<CardTarget>();
		loadImagesAndMakeTargets();
	}

	private void loadImagesAndMakeTargets() {
		File folder = new File("res\\images\\cards");
		File[] listOfFiles = folder.listFiles();
		CardTarget cardTarget = null;
		for (int i = 0; i < listOfFiles.length; i++) {

			cardTarget = new CardTarget(listOfFiles[i].toString());
			cardTargets.add(cardTarget);

			// System.out.println(listOfFiles[i].toString());
		}
	}

	public ArrayList<CardTarget> getCardTargets() {
		return cardTargets;
	}

}
