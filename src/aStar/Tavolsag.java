package aStar;

public class Tavolsag {

	private Pont p1, p2;

	public Pont getP1() {
		return p1;
	}

	public void setP1(Pont p1) {
		this.p1 = p1;
	}

	public Pont getP2() {
		return p2;
	}

	public void setP2(Pont p2) {
		this.p2 = p2;
	}

	public Tavolsag(Pont p1, Pont p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public double tav() {
		return Math.sqrt((double) ((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()))
				+ (double) ((p2.getY() - p1.getY()) * (p2.getY() - p1.getY()))
				+ (double) ((p2.getZ() - p1.getZ()) * (p2.getZ() - p1.getZ())));
	}

	//ha felfele haladok, akkor a magassagok kulonbsege az energia
	//ha lefele menne, akkor nem fogyasztok energiat
	//az m is konstans egy es a g is
	public double energ() {
		if (p1.getZ() < p2.getZ())
			return 0;
		else
			return (p1.getZ() - p2.getZ());
	}
}
