package pack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class PyramidSolver {

	private int[][] pyramid, colors, solution;
	private int size;
	private boolean wasFound;
	
	private ArrayList arrayRandom =  new ArrayList();
	
	public PyramidSolver(int[][] pyramid,int[][] colors){
		this.wasFound = false;
		this.pyramid = pyramid;
		this.colors = colors;
		this.size = pyramid.length;
		this.solution = new int[size][size];		
		fillRandomArray();
	}

	private void fillRandomArray() {
		for (int i = 1; i < 10; i++) {
			arrayRandom.add(i);
		}
		Collections.shuffle(arrayRandom);
	}
	
	
	public PyramidSolver(String path) {
		this.wasFound = false;
		fillRandomArray();
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
		//print with a pyramid shape
		for (int i = 0; i < pyramid.length; i++) {
			//print tabs
			for (int j = 0; j < pyramid.length-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < pyramid.length; j++) {
				if (pyramid[i][j] != -1)
					System.out.print(pyramid[i][j] + " ");
			}
			System.out.println();
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
	/*
	 * RANDOM BACKTRACKING IS USED SPECIALLY FOR THE GENERATOR TO GET A DIFFERENT
	 *  SOLUTION EVERY TIME (if it exists)
	 */
	public void randomBacktracking(int x, int y) {
		if (x == -1) { // final solution
			wasFound = true;
			System.out.println("SOLUTION FOUND: ");
			print();
			//Save here the matrix because later it will be destroyed by adding 0s
			//solution is saved here to use it in the generator
			for (int i = 0; i < colors.length; i++) {
				for (int j = 0; j < colors.length; j++) {
					solution[i][j] = pyramid[i][j];			
				}
			}
			
		} else
			for (Object o : arrayRandom) {
				if(wasFound) break;
				int k = (int)o;
				if (!wasFound && check1(x, y, k)
						&& check2(x, y, k) 
						&& check3(x, y, k)
						&& checkColors(x, y, k)) {
					pyramid[x][y] = k; 
					int[] zero = new int[2];
					zero = findZero(x, y);
					randomBacktracking(zero[0], zero[1]);
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
		long t1,t2,t3;
		for (int i = 1; i < 18; i++) {
			System.out.println("case  " + i);
			PyramidSolver solver = new PyramidSolver("files/case" + i + ".txt");
			solver.print();
			System.out.println();
			int[] zero = new int[2];
			zero = solver.findZero(solver.size - 1, -1);
			t1 = System.currentTimeMillis();
			solver.randomBacktracking(zero[0], zero[1]);
			t2 = System.currentTimeMillis();
			System.out.println();
			t3 = t2-t1;
			System.out.println("Elapsed Time "+t3);

		}
		//Test of my generated pyramid
		PyramidSolver solver = new PyramidSolver("files/generatedPyramid.txt");
		solver.print();
		solver.solve();
		
	}
	
	public int[][] solve(){//method to be called from pyramid generator
		int[] zeros = new int[2];
		zeros = findZero(size - 1, -1);
		randomBacktracking(zeros[0], zeros[1]);
		return solution;
	}

}
