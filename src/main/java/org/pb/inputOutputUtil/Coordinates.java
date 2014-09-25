package org.pb.inputOutputUtil;

public class Coordinates {
	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}

	private int x;
	private int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates(Coordinates toCopy) {
		this.x = toCopy.getX();
		this.y = toCopy.getY();
	}

	public void changeX(int value) {
		x += value;
	}

	public void changeY(int value) {
		y += value;
	}

	public void change(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
