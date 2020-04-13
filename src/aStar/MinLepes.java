package aStar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MinLepes {
	private Pont[][] matr;
	private ArrayList<Pont> queue;
	private ArrayList<Pont> visited;
	private int[][] tav;
	private int sx, sy, gx, gy;
	private ArrayList<Pont> kimenet;

	public MinLepes(Pont[][] matr) {
		this.matr = new Pont[520][520];
		this.matr = matr;
		tav = new int[520][520];
		initTav();
		queue = new ArrayList<Pont>();
		visited = new ArrayList<Pont>();

		BeolvStartGoal beOlv2 = new BeolvStartGoal();
		sx = beOlv2.getSx();
		sy = beOlv2.getSy();
		gx = beOlv2.getGx();
		gy = beOlv2.getGy();
		kimenet = new ArrayList<Pont>();
	}

	public void bfs() {
		queue.add(matr[sx][sy]);
		visited.add(matr[sx][sy]);
		tav[sx][sy] = 1;
		Pont temp;
		int x, y;
		// amig ki nem urult a sor vagy el nem ertem a celig
		while (queue.size() != 0 && (queue.get(0).getX() != gx || queue.get(0).getY() != gy)) {
			temp = queue.get(0);
			queue.remove(0);
			x = temp.getX();
			y = temp.getY();

			// megnezem az adott pont osszes 8 szomszedjat
			// akkor veszem figyelembe, ha nem akadaly
			if (!(visited.contains(matr[x - 1][y])) && matr[x - 1][y].getD() == 0) {
				visited.add(matr[x - 1][y]);
				queue.add(matr[x - 1][y]); // ha meg nem dolgoztam fel, beteszem a varakozasi sorba
				tav[x - 1][y] = 1 + tav[x][y]; // a lepes a starttol a szomszedos pontig = lepes a pontig + 1
				matr[x - 1][y].setElozoX(x);
				matr[x - 1][y].setElozoY(y);
			}

			if (!(visited.contains(matr[x + 1][y])) && matr[x + 1][y].getD() == 0) {
				visited.add(matr[x + 1][y]);
				queue.add(matr[x + 1][y]);
				tav[x + 1][y] = 1 + tav[x][y];
				matr[x + 1][y].setElozoX(x);
				matr[x + 1][y].setElozoY(y);
			}

			if (!(visited.contains(matr[x - 1][y - 1])) && matr[x - 1][y - 1].getD() == 0) {
				visited.add(matr[x - 1][y - 1]);
				queue.add(matr[x - 1][y - 1]);
				tav[x - 1][y - 1] = 1 + tav[x][y];
				matr[x - 1][y - 1].setElozoX(x);
				matr[x - 1][y - 1].setElozoY(y);
			}
			if (!(visited.contains(matr[x - 1][y + 1])) && matr[x - 1][y + 1].getD() == 0) {
				visited.add(matr[x - 1][y + 1]);
				queue.add(matr[x - 1][y + 1]);
				tav[x - 1][y + 1] = 1 + tav[x][y];
				matr[x - 1][y + 1].setElozoX(x);
				matr[x - 1][y + 1].setElozoY(y);
			}
			if (!(visited.contains(matr[x + 1][y + 1])) && matr[x + 1][y + 1].getD() == 0) {
				visited.add(matr[x + 1][y + 1]);
				queue.add(matr[x + 1][y + 1]);
				tav[x + 1][y + 1] = 1 + tav[x][y];
				matr[x + 1][y + 1].setElozoX(x);
				matr[x + 1][y + 1].setElozoY(y);
			}
			if (!(visited.contains(matr[x + 1][y - 1])) && matr[x + 1][y - 1].getD() == 0) {
				visited.add(matr[x + 1][y - 1]);
				queue.add(matr[x + 1][y - 1]);
				tav[x + 1][y - 1] = 1 + tav[x][y];
				matr[x + 1][y - 1].setElozoX(x);
				matr[x + 1][y - 1].setElozoY(y);
			}
			if (!(visited.contains(matr[x][y - 1])) && matr[x][y - 1].getD() == 0) {
				visited.add(matr[x][y - 1]);
				queue.add(matr[x][y - 1]);
				tav[x][y - 1] = 1 + tav[x][y];
				matr[x][y - 1].setElozoX(x);
				matr[x][y - 1].setElozoY(y);
			}
			if (!(visited.contains(matr[x][y + 1])) && matr[x][y + 1].getD() == 0) {
				visited.add(matr[x][y + 1]);
				queue.add(matr[x][y + 1]);
				tav[x][y + 1] = 1 + tav[x][y];
				matr[x][y + 1].setElozoX(x);
				matr[x][y + 1].setElozoY(y);
			}
		}

		System.out.println("A minimalis lepesszam (BFS): " + tav[gx][gy]);
		utvonal(gx, gy); //el fogom menteni egy allomanyban az utvonalat, a vegponttol indulok

	}

	public void utvonal(int x, int y) {
		int x1, y1;
		kimenet.add(matr[gx][gy]);
		while (x != sx || y != sy) { //amig el nem jutok a startig, minden pontnak az elozoen keresztul haladok
			//System.out.println("(" + x + ", " + y + ")");
			x1 = matr[x][y].getElozoX();
			y1 = matr[x][y].getElozoY();
			x = x1;
			y = y1;
			kimenet.add(matr[x][y]);
		}

		String ss;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("kimenetBFS.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Pont ppp : kimenet) {
			ss = Integer.toString(ppp.getX()) + " " + Integer.toString(ppp.getY()) + " " + Double.toString(ppp.getZ())
					+ "\n";
			writer.write(ss);
		}
		writer.close();
	}

	public void initTav() {
		for (int i = 0; i < 520; i++)
			for (int j = 0; j < 520; j++)
				tav[i][j] = 0;
	}

}
