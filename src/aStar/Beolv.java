package aStar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Beolv {

	Pont[][] matr;
	BufferedReader br;

	public Beolv(File f) {
		String s;
		matr = new Pont[520][520]; //az egyszeruseg kedveert 520x520 ponttal fogok dolgozni
		init();
		try {
			this.br = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {

			System.out.println("Nem talaltam a file-t");
			e.printStackTrace();
		}
		try {
			while ((s = br.readLine()) != null) {

				String[] strr = s.split(" ");
				int x = (int) Double.parseDouble(strr[0]);
				int y = (int) Double.parseDouble(strr[1]);
				double z = Double.parseDouble(strr[2]);
				int d = Integer.parseInt(strr[3]);
				matr[x][y] = new Pont(x, y, z, d);

			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}

	}

	public void init() {
		// a peremen csak 0-k lesznek, hogy konnyebb legyen szomszedot talalni
		for (int i = 0; i < 519; i++)
			for (int j = 0; j < 519; j++) {
				matr[i][j] = new Pont(0, 0, 0, 1);
			}
	}

	public Pont[][] getMatr() {
		return matr;
	}

	public void setMatr(Pont[][] matr) {
		this.matr = matr;
	}

}
