package org.pb.decisionMaker.data;

import java.util.ArrayList;
import java.util.List;

public class GameData {

	private List<HandData> handDataList;

	public GameData() {
		handDataList = new ArrayList<HandData>();
	}

	public void addHandData(HandData handData) {
		handDataList.add(handData);
	}

	public List<HandData> getHandDataList() {
		return handDataList;
	}
}
