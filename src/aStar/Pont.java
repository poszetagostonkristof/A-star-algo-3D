package aStar;

public class Pont {

	//Pont osztaly, amely tartalmazza az x,y,z koordinatakat, hogy akadaly-e vagy sem, illetve minden pontnak tarolni fogom az elozojet, ez az
	//utvonal megtalalasaban fog segiteni, mivel igy nem kell egy tombben taroljam kulon az utvonalat
	private double z, elozoZ; 
	private double f = 500000.0;
	private double g = 500000.0;
	private double h = 500000.0;
	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	private int x,y,d, elozoX, elozoY;
	
	public double getElozoZ() {
		return elozoZ;
	}

	public void setElozoZ(double elozoZ) {
		this.elozoZ = elozoZ;
	}

	public int getElozoX() {
		return elozoX;
	}

	public void setElozoX(int elozoX) {
		this.elozoX = elozoX;
	}

	public int getElozoY() {
		return elozoY;
	}

	public void setElozoY(int elozoY) {
		this.elozoY = elozoY;
	}

	public Pont(int x, int y, double z, int d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.d = d;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
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

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}
	
	public String toString() {
		return x + " " + y + " " + z;
	}
}
