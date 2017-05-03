package pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class PyramidSolver {

	private int[][] pyramid, colors, solution;
	private int size;
	private boolean wasFound;
	
	public PyramidSolver(String path) {
		this.wasFound = false;
		try {
			MyFileHandler handler = new MyFileHandler();
			size = handler.readFile(path);
			this.pyramid = handler.getPyramid();
			this.colors = handler.getColors();
			this.solution = new int[size][size];
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Wrong path or file name");
		}
	}

	public boolean checkColors(int i, int j, int k) {
		//we look for a copy of the color and in case they are the same we mark the
		//position returning true, false in case the colors doesn't match and if there is no color return true
		if (colors[i][j] < -1) {
			int colornumber = colors[i][j];
			for (int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) {
					if (colors[x][y] == colornumber) {
						pyramid[x][y] = k;
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
	
	public void print() {
		turn();		
		disgustingPrint();
	}

	private void disgustingPrint() {
		System.out.print(
				pyramid[0][0]+"\n\t"+pyramid[0][1]+"\n"+
				pyramid[1][0]+"\t\t"+pyramid[0][2]+"\n \t"+pyramid[1][1]+ "\t\t"+ pyramid[0][3]+ "\n"+
				pyramid[2][0]+"\t\t"+ pyramid[1][2]+"\n \t"+pyramid[2][1]+"\n"+pyramid[3][0] );
		System.out.println();
	}
	//it is the same as the other turn in myfilehandler but with this pyramids
	private void turn() {
		int[][] pyramidAux = new int[size][size];
		int[][] colorsAux = new int[size][size];
		
		for (int i = 0; i < colorsAux.length; i++) {
			for (int j = 0; j < colorsAux.length; j++) {
				pyramidAux[i][j] = pyramid[size - i -1][j];
				colorsAux[i][j] = colors[size - i -1][j];
			}
		}
		
		for (int i = 0; i < colorsAux.length; i++) {
			for (int j = 0; j < colorsAux.length; j++) {
				pyramid[i][j] = pyramidAux[i][j];
				colors[i][j] = colorsAux[i][j];
			}
		}
		
	}

	private boolean check1(int i, int j, int k) {
		if (i == size - 1) {
			return true;
		} else {
			if (k == pyramid[i + 1][j] + pyramid[i + 1][j + 1]) {
				return true;
			} else if (k == Math.abs(pyramid[i + 1][j] - pyramid[i + 1][j + 1])) {
				return true;
			}
			return false;
		}
	}
	private boolean check2(int i, int j, int k) {
		if (i == 0) {
			return true;
		} else {
			if (j > 0 && pyramid[i - 1][j - 1] != 0) {
				if (Math.abs(pyramid[i][j - 1] - pyramid[i - 1][j - 1]) == k) {
					return true;
				} else if (pyramid[i][j - 1] + pyramid[i - 1][j - 1] == k) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
	}

	private boolean check3(int i, int j, int k) {
		if (i == 0) {
			return true;
		} else {
			if (j < i && pyramid[i - 1][j] != 0 && pyramid[i][j + 1] != 0) {
				if (Math.abs(pyramid[i][j + 1] - pyramid[i - 1][j]) == k) {
					return true;
				} else if (pyramid[i][j + 1] + pyramid[i - 1][j] == k) {
					return true;
				} else {
					return false;
				}
			} 
		}
		return true;
	}

	/*
	 * REGULAR BACKTRACKING METHOD FOR THE PYRAMID TEST 1,THEN 2 AND SO ON. 	
	 */
	public void backtracking(int x, int y) {
		if (x == -1) { 
			wasFound = true;
			System.out.println("SOLUTION FOUND: ");
			print();
		} else
			for (int k = 1; k <= 9; k++) { //values that are form 1 to 9
				if (!wasFound && check1(x, y, k)
						&& check2(x, y, k) 
						&& check3(x, y, k)
						&& checkColors(x, y, k)) {
					pyramid[x][y] = k; // mark
					int[] zero = new int[2];
					zero = findZero(x, y);
					backtracking(zero[0], zero[1]);
					pyramid[x][y] = 0; 
				}
			}
	}
	
	private int[] findZero(int x, int y) { //look for the next position with a 0 (ie., that it is empty)
		int[] nul = new int[2];
		boolean b = true;
		do {
			y = y + 1; 
			if (y == size) { 
				x = x - 1;
				y = 0; 
			}
			b = (x == -1) || pyramid[x][y] == 0;
		} while (!b);
		nul[0] = x;
		nul[1] = y;
		return nul;
	}

	public static void main(String[] args) throws IOException {
		//Test of my generated pyramid
		PyramidSolver solver = new PyramidSolver("files/EXAM.txt");
		solver.solve();
		
	}
	
	public int[][] solve(){//method to be called from pyramid generator
		int[] zeros = new int[2];
		zeros = findZero(size - 1, -1);
		backtracking(zeros[0], zeros[1]);
		return solution;
	}

}
