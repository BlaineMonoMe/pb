package org.pb.input;

import java.awt.image.BufferedImage;

import org.pb.input_output_util.Coordinates;
import org.pb.state.CardsState;
import org.pb.state.Players;

public class CardsOnHandsListener extends Thread {
	private CardsState cardsOnHandsState;
	private BufferedImage image;
	private Coordinates centerOfTheTable;
	private ScreenShootMaker screenShootMaker;
	private boolean isSittingAtTheTop;

	public CardsOnHandsListener(CardsState cardsOnHandsState,
			Coordinates coords, ScreenShootMaker screenShootMaker,
			Players players) {
		this.setDaemon(true);
		this.screenShootMaker = screenShootMaker;
		this.cardsOnHandsState = cardsOnHandsState;
		this.centerOfTheTable = new Coordinates(coords);
		isSittingAtTheTop = players.isiAmSittingOnTheTop();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			analyze();
		}
	}

	private void analyze() {
		if (!isSittingAtTheTop) {
			image = screenShootMaker.getScreenShot();
			int[] rgb = new int[3];
			image.getData().getPixel(centerOfTheTable.getX() + 25,
					centerOfTheTable.getY() + 108, rgb);
			if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
				if (cardsOnHandsState.getState() != 1) {
					cardsOnHandsState.setChanged(true);
					//System.out.println("hand cards appears!");
				}
				cardsOnHandsState.setState(1);
			} else {
				if (cardsOnHandsState.getState() != 0) {
					cardsOnHandsState.setChanged(true);
					//System.out.println("hand cards disappears!");
				}
				cardsOnHandsState.setState(0);
			}
		}
	}
}
