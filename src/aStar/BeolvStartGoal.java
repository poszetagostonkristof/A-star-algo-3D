package aStar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BeolvStartGoal {

	private int sx, gx, sy, gy; //start x,y and goal x,y coordinates

	public int getSx() {
		return sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public int getGx() {
		return gx;
	}

	public void setGx(int gx) {
		this.gx = gx;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public int getGy() {
		return gy;
	}

	public void setGy(int gy) {
		this.gy = gy;
	}

	public BeolvStartGoal() {

		File f2 = new File("points.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f2));
		} catch (FileNotFoundException e) {

			System.out.println("Nem talaltam a file-t");
			e.printStackTrace();
		}
		try {
			String s;

			s = br.readLine();
			String[] strr = s.split(" ");
			sx = (int) Double.parseDouble(strr[0]);
			sy = (int) Double.parseDouble(strr[1]);

			s = br.readLine();
			strr = s.split(" ");
			gx = (int) Double.parseDouble(strr[0]);
			gy = (int) Double.parseDouble(strr[1]);

		} catch (IOException e1) {

			e1.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
