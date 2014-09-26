package org.pb.inputOutputUtil;

public class Color {
	private int red;
	private int green;
	private int blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color(int[] rgb) {
		this.red = rgb[0];
		this.green = rgb[1];
		this.blue = rgb[2];
	}

	@Override
	public boolean equals(Object arg0) {
		Color that = (Color) arg0;
		if (this.blue != that.blue) {
			return false;
		}
		if (this.green != that.green) {
			return false;
		}
		if (this.red != that.red) {
			return false;
		}
		return true;
	}

	public Color() {
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

}
