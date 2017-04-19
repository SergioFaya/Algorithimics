/* SUDOKU
 * Solves a Sudoku (9x9 that is in a file)
 * The name of the file (without extension) is arg[0]
 * Calculates all the possible solutions */

package sudoku;

import java.io.*;
import java.util.StringTokenizer; 

public class SudokuAll {	
	static int n; //size of the Sudoku
	static int[][]board; //board for the Sudoku puzzle
	static int counter; //solution counter
	
	public static void main(String arg[]) {
		n = 9; //size
		String name = arg[0]; //name of the file
		name = name + ".txt";
		
		board = new int[n][n];
		readFile(name);
		
		System.out.println("THE SUDOKU TO BE SOLVED IS:");
		writeBoard();
			
		counter = 0;
		int[]zero = new int[2];
		zero = findZero(0, -1); //we start here to look for the next available (empty) position starting from 0,0
		
		backtracking(zero[0], zero[1]);
		
		if(counter==0) 
			System.out.println("THERE IS NO SOLUTION");
	}
	
	static void readFile(String name) { //method for loading a Sudoku from a file
		String line = "";
		StringTokenizer words; 
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(name));        
			for (int x=0; x<n; x++) { //n lines
				line = reader.readLine();
				words = new StringTokenizer(line); //each of the numbers
				for (int y=0;y<n;y++)
					board[x][y] = Integer.parseInt(words.nextToken());
			}
		}
		catch(Exception e) { 
			System.out.println("FILE NOT FOUND");
		}
		try {
			if (reader != null) reader.close();
		}
		catch (IOException e)
		{}
	} 
	
	static void backtracking(int x, int y) {
		if (x==n) {  //we found a solution
			counter++;
			System.out.println("SOLUTION FOUND, NUMBER: " + counter);
			writeBoard();
		}
		else
			for (int k=1; k<=n; k++) { //from 1 to n
				if (row(x,k) && column(y,k) && region(x,y,k)) { 
					board[x][y] = k; //mark
					
					int[]zero = new int[2];
					zero = findZero(x,y);
					backtracking(zero[0], zero[1]);
					
					board[x][y] = 0; //unmark
				}
			}
	}
	
	static void writeBoard() {
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++)
				System.out.printf("%3d", board[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	static int[] findZero(int x, int y) { //look for the next position with a 0 (ie., that it is empty)
		int[]nul = new int[2];
		boolean b = true;
		do {
			y = y+1; //we advance one position to the right
			if (y==n) { //if we finish the columns...
				x=x+1; y=0; //we go to the next row
			}
			/* here, we have 2 possibilities:
			 * 1- we don't find an empty cell (x==n) => the backtracking will finish in the next iteration
			 * 2- we find an empty cell => we continue the process */
			b=(x==n)||board[x][y]==0; 
		}
		while (!b);
		nul[0]=x; 
		nul[1]=y;
		return nul;
	}
	
	//to check if the row x is valid for the number k
	static boolean row(int x, int k) {
		boolean b = true;
		for (int i=0; i<n; i++)
			if (board[x][i] == k) b=false;
		return b;
	}
	
	//to check if the column y is valid for the number k
	static boolean column(int y, int k) {
		boolean b = true;
		for (int i=0; i<n; i++)
			if (board[i][y] == k) b=false;
		return b;
	}
	
	//to check if the region is valid for the number k
	static boolean region(int x, int y, int k) {
		boolean b = true;
		int startingX = x-x%3; //if for example x=7 => startingX = 6
		int startingY = y-y%3; //if for example y=1 => startingY = 0 
		//that is, we only check the region from [6,0] to (9, 3)
		for (int i=startingX; i<startingX+3; i++) //each region has a size of 3x3 (working with a 9x9 Sudoku)
			for (int j=startingY; j<startingY+3; j++)
				if (board[i][j] == k) b=false;
		return b;
	} 

}