package pack;

import java.io.IOException;
import java.util.function.Predicate;

public class PyramidSolver {

	int [][] pyramid;
	boolean wasFound = false;
	public static Predicate<int,int> check1= (x,y)-> pyramid[x+1][y] + pyramid[x+1][y+1] == pyramid[x][y]?true:false;
	
	public PyramidSolver(String path){
		try {
			pyramid = MyFileHandler.readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Wrong path or file name");
		}
	}
	
	public void print() {
		for (int i = 0; i < pyramid.length; i++) {
			for (int j = 0; j < pyramid.length; j++) {
				System.out.print(pyramid[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	
	public void backtracking(int x, int y) {
		int n = pyramid.length;
		if (x==n) { //we found a solution (AND FINISH)
			wasFound = true;
			System.out.println("SOLUTION FOUND:");
			print();
		}
		else
			for (int k=1; k<=n; k++) { //from 1 to n
				if(!wasFound && check1) {
					pyramid[x][y] = k; //mark
					int[]zero = new int[2];
					backtracking(zero[0], zero[1]);
					
					pyramid[x][y] = 0; //unmark
		   }
		 }
	}
	
	
}
