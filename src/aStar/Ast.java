package aStar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

public class Ast {

	private ArrayList<Pont> closedL;
	private ArrayList<Pont> kimenet;
	private ArrayList<Pont> queue;
	private Pont[][] matr;
	private double newF, newG, newH;
	private Tavolsag t;
	private int sx,sy,gx,gy; //start, goal coordianates

	public Ast(Pont[][] matr) {
		this.matr = new Pont[520][520];
		this.matr = matr;
		queue = new ArrayList<Pont>();
		closedL = new ArrayList<Pont>();
		kimenet = new ArrayList<Pont>();
		
		BeolvStartGoal beOlv2 = new BeolvStartGoal();
		sx = beOlv2.getSx(); sy = beOlv2.getSy(); gx = beOlv2.getGx(); gy = beOlv2.getGy();
	}

	public void aStarAlg() {
		Pont pont;
		int i, j;

		i = sx; //elindulok a starttol
		j = sy;

		
		newF = 0.0; 
		newG = 0.0;
		newH = 0.0;

		updateDetails(i, j, newF, newG, newH); //a start kiertekelo, koltsegfg. inicializalasa 0-ra
		queue.add(matr[i][j]);
		closedL.add(matr[i][j]);
		t = new Tavolsag(matr[i - 1][j], matr[i][j]);

		while (queue.size() != 0) {

			pont = getMinFromQueue(); // kivalasztjuk a legkisebb f kiertekelofuggvenyt

			if (pont.getX() == gx && pont.getY() == gy) { // ha elertuk a celt, leallunk
				break;
			}

			int ujI, ujJ;
			ujI = i = pont.getX();
			ujJ = j = pont.getY();
	
			neighborTraverse(i, j, ujI, ujJ); // bejarjuk az osszes szomszedjat a kivalasztott pontnak
		}

		int xx,yy;
		xx = gx;
		yy = gy;
		int xxx, yyy;
		xxx = matr[xx][yy].getElozoX();
		yyy = matr[xx][yy].getElozoY();

		kimenet.add(matr[xx][yy]);
		
		//az utvonalat fogjuk elmenteni a kovetkezokben
		//kiindulunk a celtol, fogjuk a cel elozo csomopontjat stb. es ezt a lepest folytatva majd eljutunk a startig
		int k = 0;
		double dist = 0.0; // 332 171
		while (xxx != sx || yyy != sy) {
			kimenet.add(matr[xxx][yyy]);
			xxx = matr[xx][yy].getElozoX();
			yyy = matr[xx][yy].getElozoY();

			t.setP1(matr[xx][yy]);
			t.setP2(matr[xxx][yyy]);
			dist = dist + t.tav();

			xx = xxx;
			yy = yyy;
			k++;
		}
		kimenet.add(matr[xxx][yyy]);
		System.out.println("Min tavolsag: "+ dist + ", ennyi lepessel: "+k);
		Collections.reverse(kimenet);
		String ss;

		//beirjuk allomanyba
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("kimenet.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Pont ppp : kimenet) {
			ss = Integer.toString(ppp.getX()) + " " + Integer.toString(ppp.getY()) + " " + Double.toString(ppp.getZ())+"\n";
			writer.write(ss);
		}
		writer.close();
	}

	public Pont getMinFromQueue() {
		Pont pontt;
		double ujF;
		ujF = queue.get(0).getF();
		int ii = 0;
		int selectedItem = 0;
		for (Pont pp : queue) {
			if (pp.getF() < ujF) {
				selectedItem = ii;
			}
			++ii;
		}

		pontt = queue.get(selectedItem);
		queue.remove(selectedItem);
		return pontt;
	}

	public void neighborTraverse(int i, int j, int ujI, int ujJ) {
		// 1. szomszed

		ujI = i - 1;
		ujJ = j;
		neighbor(i, j, ujI, ujJ);

		// 2. szomszed

		ujI = i - 1;
		ujJ = j - 1;
		neighbor(i, j, ujI, ujJ);

		// 3. szomszed
		ujI = i - 1;
		ujJ = j + 1;
		neighbor(i, j, ujI, ujJ);

		// 4. szomszed

		ujI = i;
		ujJ = j - 1;
		neighbor(i, j, ujI, ujJ);

		// 5. szomszed
		ujI = i;
		ujJ = j + 1;
		neighbor(i, j, ujI, ujJ);

		// 6. szomszed
		ujI = i + 1;
		ujJ = j;
		neighbor(i, j, ujI, ujJ);

		// 7. szomszed
		ujI = i + 1;
		ujJ = j + 1;
		neighbor(i, j, ujI, ujJ);

		// 8. szomszed
		ujI = i + 1;
		ujJ = j - 1;
		neighbor(i, j, ujI, ujJ);

	}

	public void neighbor(int i, int j, int ii, int jj) {
		if (matr[ii][jj].getD() == 0) { // ha nem akadaly

			t.setP1(matr[i][j]);
			t.setP2(matr[ii][jj]);
			newG = t.tav() + matr[i][j].getG(); // tavolsag starttol a szomszedig a jelenlegin keresztul

			// ha meg nem lattuk ezt a szomszedot vagy az uj G rovidebb, frissitunk
			if (!(closedL.contains(matr[ii][jj])) || newG < matr[ii][jj].getG()) {
				matr[ii][jj].setElozoX(i);
				matr[ii][jj].setElozoY(j);
				t.setP1(matr[ii][jj]);
				t.setP2(matr[476][380]);
				newH = t.tav(); // jelenlegi csomoponttol a vegpontig megtett kozelito tavolsag
				newF = newG + newH;

				updateDetails(ii, jj, newF, newG, newH); // beallitjuk az uj f,g,h ertekeket
				queue.add(matr[ii][jj]);

				if (!(closedL.contains(matr[ii][jj]))) { // ha meg nem tettuk volna be a nyilt listaba
					closedL.add(matr[ii][jj]);
				}
			}
		}
	}

	public void updateDetails(int i, int j, double newF, double newG, double newH) {
		matr[i][j].setF(newF);
		matr[i][j].setG(newG);
		matr[i][j].setH(newH);
	}

}
