package org.pb.input;

import java.awt.image.BufferedImage;

import org.pb.input_output_util.Coordinates;
import org.pb.state.CardsOnTableState;

public class CardsOnTableListener extends Thread {

	private CardsOnTableState cardsOnTableState;
	private BufferedImage image;
	private Coordinates centerOfTheTable;
	private ScreenShootMaker screenShootMaker;

	public CardsOnTableListener(CardsOnTableState сardsOnTableState,
			Coordinates coords, ScreenShootMaker screenShootMaker) {
		this.setDaemon(true);
		this.screenShootMaker = screenShootMaker;
		this.cardsOnTableState = сardsOnTableState;
		this.centerOfTheTable = new Coordinates(coords);
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

	private boolean analyze() {
		image = screenShootMaker.getScreenShot();
		if (checkRiver(image) == true) {
			if (cardsOnTableState.getState() != 3) {
				cardsOnTableState.setChanged(true);
				System.out.println("river is coming!");
			}
			cardsOnTableState.setState(3);
			return true;
		}
		if (checkTorn(image) == true) {
			if (cardsOnTableState.getState() != 2) {
				cardsOnTableState.setChanged(true);
				System.out.println("torn is coming!");
			}
			cardsOnTableState.setState(2);
			return true;
		}
		if (checkFlop(image) == true) {
			if (cardsOnTableState.getState() != 1) {
				cardsOnTableState.setChanged(true);
				System.out.println("flop is coming!");
			}
			cardsOnTableState.setState(1);
			return true;
		}
		if (cardsOnTableState.getState() != 0) {
			cardsOnTableState.setChanged(true);
			System.out.println("table becomes empty!");
		}
		cardsOnTableState.setState(0);
		return true;
	}

	private boolean checkRiver(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(centerOfTheTable.getX() + 90,
				centerOfTheTable.getY(), rgb);
		if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
			return true;
		}
		return false;
	}

	private boolean checkTorn(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(centerOfTheTable.getX() + 35,
				centerOfTheTable.getY(), rgb);
		if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
			return true;
		}
		return false;
	}

	private boolean checkFlop(BufferedImage image) {
		int[] rgb = new int[3];
		image.getData().getPixel(centerOfTheTable.getX() - 20,
				centerOfTheTable.getY(), rgb);
		if (rgb[0] == 255 && rgb[1] == 255 && rgb[2] == 255) {
			return true;
		}
		return false;
	}

}
