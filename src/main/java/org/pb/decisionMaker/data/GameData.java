package org.pb.decisionMaker.data;

import java.util.ArrayList;
import java.util.List;

public class GameData {

	private List<HandData> handDataList;

	public GameData() {
		handDataList = new ArrayList<HandData>();
	}

	public HandData getCurrentHandData() {
		return handDataList.get(handDataList.size() - 1);
	}

	public HandData getPreviousHandData() {
		return handDataList.get(handDataList.size() - 2);
	}

	public void addHandData(HandData handData) {
		handDataList.add(handData);
	}

	public List<HandData> getHandDataList() {
		return handDataList;
	}

	public int getHandsCount() {
		return handDataList.size();
	}
}
