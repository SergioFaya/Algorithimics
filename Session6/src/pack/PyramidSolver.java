package pack;

import java.io.IOException;
import java.util.function.Predicate;

public class PyramidSolver {
<<<<<<< HEAD
	private int[][] pyramid,colors;
	private int size;
	private boolean wasFound = false;
=======

	int [][] pyramid;
	boolean wasFound = false;
	public static Predicate<int,int> check1= (x,y)-> pyramid[x+1][y] + pyramid[x+1][y+1] == pyramid[x][y]?true:false;
>>>>>>> a9d310b36366bc5e41e145107e31a029558161bd
	
	public PyramidSolver(String path){
		try {
			MyFileHandler handler = new MyFileHandler();
			size = handler.readFile(path);
			pyramid = handler.getPyramid();
			colors= handler.getColors();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Wrong path or file name");
		}
	}
	
<<<<<<< HEAD
	boolean checkColors(int i, int j, int k)
	{
		if(colors[i][j]==-2)
		{
			for(int x=0;x<size;x++)
			{
				for(int y=0;y<size;y++)
				{
					if(colors[x][y]==-2)
					{
						pyramid[x][y]=k;
						return true;
					}
				}
			}
		}
		else if(colors[i][j]==-3)
		{
			for(int x=0;x<size;x++)
			{
				for(int y=0;y<size;y++)
				{
					if(colors[x][y]==-3)
					{
						pyramid[x][y]=k;
						return true;
					}
				}
			}
		}
		else if(colors[i][j]==-4)
		{
			for(int x=0;x<size;x++)
			{
				for(int y=0;y<size;y++)
				{
					if(colors[x][y]==-4)
					{
						pyramid[x][y]=k;
						return true;
					}
				}
			}
		}
		else
		{
			return true;
		}
		return false;
	}
	
	private boolean iterate(int x, int y,int k,int colorNum){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(colors[x][y]== colorNum){
					pyramid[x][y] = k;
					return true;
				}
			}
		}
		return false;
	}
	
	public void print(){
=======
	public void print() {
>>>>>>> a9d310b36366bc5e41e145107e31a029558161bd
		for (int i = 0; i < pyramid.length; i++) {
			for (int j = 0; j < pyramid.length; j++) {
				if(pyramid[i][j]!=-1)
					System.out.print(pyramid[i][j]+"\t");
			}
			System.out.println();
		}
	}	
	
	boolean checkSumOrRest1(int i, int j, int k)
	{
		if(i==size-1)
		{
			return true;
		}
		else
		{
			if(k==pyramid[i+1][j]+pyramid[i+1][j+1])
			{
				return true;
			}
			else if(k==Math.abs(pyramid[i+1][j]-pyramid[i+1][j+1]))
			{
				return true;
			}
		}
		return false;
	}
	
	
<<<<<<< HEAD

	boolean checkSumOrRest2(int i, int j, int k)
	{
		if(i==0)
		{
			return true;
		}
		else
		{
			if(j>0)
			{
				if(pyramid[i-1][j-1]!=0)
				{
					if(Math.abs(pyramid[i][j-1]-pyramid[i-1][j-1])==k)
					{
						return true;
					}
					
					else if(pyramid[i][j-1]+pyramid[i-1][j-1]==k)
					{
						return true;
					}
				}
				else
				{
					return true;
				}
			}
			else
			{
				return true;
			}
		}	
		return false;
	}
	
	boolean checkSumOrRest3(int i, int j, int k)
	{
		if(i==0)
		{
			return true;
		}
		else
		{
			if(j<i)
			{
				if(pyramid[i-1][j]!=0 && pyramid[i][j+1]!=0)
				{
					if(Math.abs(pyramid[i][j+1]-pyramid[i-1][j])==k)
					{
						return true;
					}
					else if(pyramid[i][j+1]+pyramid[i-1][j]==k)
					{
						return true;
					}
				}
				else 
				{
					return true;
				}
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	
	void backtracking(int x, int y) {
		if (x==-1) {  //we found a solution
			wasFound=true;
			System.out.println("SOLUTION FOUND: ");
			print();
		}
		else
			for (int k=1; k<=9; k++) { //from 1 to n
				if (!wasFound && checkSumOrRest1(x,y,k) && checkSumOrRest2(x,y,k) && checkSumOrRest3(x,y,k) && checkColors(x,y,k)) { 
					pyramid[x][y] = k; //mark
					int[]zero = new int[2];
					zero = findZero(x,y);
					backtracking(zero[0], zero[1]);
					pyramid[x][y] = 0; //unmark
				}
			}
	}
	
	int[] findZero(int x, int y) { //look for the next position with a 0 (ie., that it is empty)
		int[]nul = new int[2];
		boolean b = true;
		do {
			y = y+1; //we advance one position to the right
			if (y==size) { //if we finish the columns...
				x=x-1; y=0; //we go to the next row
			}
			/* here, we have 2 possibilities:
			 * 1- we don't find an empty cell (x==n) => the backtracking will finish in the next iteration
			 * 2- we find an empty cell => we continue the process */
			b=(x==-1)||pyramid[x][y]==0;
		}
		while (!b);
		nul[0]=x; 
		nul[1]=y;
		return nul;
	}
	
	
	public static void main(String[] args) throws IOException {
		for (int i = 1; i < 16; i++) {
			System.out.println("case  "+i);
			PyramidSolver solver = new PyramidSolver("files/case"+i+".txt");
			solver.print();
			System.out.println();
			int[]zero = new int[2];
			zero = solver.findZero(solver.size-1, -1); //we start here to look for the next available (empty) position starting from 0,0
			solver.backtracking(zero[0], zero[1]);
			System.out.println();
			
			
		}
	}
	
}
=======
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
>>>>>>> a9d310b36366bc5e41e145107e31a029558161bd
