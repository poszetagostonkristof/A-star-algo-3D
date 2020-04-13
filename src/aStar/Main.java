package aStar;


import java.io.File;


public class Main {

	public static void main(String[] args) {
		File f = new File("surface.txt");
		Beolv beOlv = new Beolv(f);
		
		
		Pont[][] matr = new Pont[520][520];
		matr = beOlv.getMatr(); //atadva az osszes pont
		
		MinLepes mini = new MinLepes(matr);
		mini.bfs();
		
		Ast ast = new Ast(matr);
		ast.aStarAlg();
		
		AstEnergiaval astE = new AstEnergiaval(matr);
		astE.aStarAlg();
		
	}

}
