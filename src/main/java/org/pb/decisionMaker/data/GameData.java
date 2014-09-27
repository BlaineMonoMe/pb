package org.pb.decisionMaker.data;

import java.util.ArrayList;

public class GameData {

	private ArrayList<HandData> handDataList;

	public GameData() {
		handDataList = new ArrayList<HandData>();
	}

	public void addHandData(HandData handData) {
		handDataList.add(handData);
	}

	public ArrayList<HandData> getHandDataList() {
		return handDataList;
	}
}
