package org.pb.input.color;

public class Color {
	private int red;
	private int green;
	private int blue;

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Color(Color color) {
		this.red = color.red;
		this.blue = color.blue;
		this.green = color.green;
	}

	/**
	 * create Color of parsed line like "[12,32,44]"
	 * 
	 * @param line
	 */
	public Color(String line) {
		String substring = line.substring(1, line.length() - 1);
		String[] numbers = substring.split(",");
		this.red = Integer.parseInt(numbers[0]);
		this.green = Integer.parseInt(numbers[1]);
		this.blue = Integer.parseInt(numbers[2]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + red;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (blue != other.blue)
			return false;
		if (green != other.green)
			return false;
		if (red != other.red)
			return false;
		return true;
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
