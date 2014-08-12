package org.pb.input_output_util;

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

	public void changeX(int value) {
		x += value;
	}

	public void changeY(int value) {
		y += value;
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
