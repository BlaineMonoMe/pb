package org.pb.input.color;

public class TwoColors {
	private Color color1;
	private Color color2;

	public TwoColors() {
	}

	public TwoColors(Color color1, Color color2) {
		this.color1 = new Color(color1);
		this.color2 = new Color(color2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color1 == null) ? 0 : color1.hashCode());
		result = prime * result + ((color2 == null) ? 0 : color2.hashCode());
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
		TwoColors other = (TwoColors) obj;
		if (color1 == null) {
			if (other.color1 != null)
				return false;
		} else if (!color1.equals(other.color1))
			return false;
		if (color2 == null) {
			if (other.color2 != null)
				return false;
		} else if (!color2.equals(other.color2))
			return false;
		return true;
	}

	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = new Color(color1);
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = new Color(color2);
	}

}
