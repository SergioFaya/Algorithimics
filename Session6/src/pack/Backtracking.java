package pack;
import java.io.*;


public class Backtracking
{
	
	int [][] pyramid;
	int [][] pyramid2;
	private int size;
	boolean wasFound;
	
	public static void main(String []args)
	{
		Backtracking a = new Backtracking();
		a.readFile();
		a.wasFound=false;
		a.print();
		int[]zero = new int[2];
		zero = a.findZero(a.size-1, -1); //we start here to look for the next available (empty) position starting from 0,0
		
		a.backtracking(zero[0], zero[1]);
		
		System.out.println();
		
	}
	
	
	public void readFile() 
	{
		String linea = "";
		try {
			BufferedReader file = new BufferedReader(new FileReader("files/case4.txt"));
			size = Integer.parseInt(file.readLine());
			pyramid = new int [size][size];
			pyramid2 = new int [size][size];
			int i=0;
			while (file.ready()) {
				linea = file.readLine();
				String[] trozos = linea.split(" ");
					for(int j=0;j<trozos.length;j++)
					{
						if(trozos[j].equals("R"))
						{
							pyramid[i][j]=-1;
							pyramid2[i][j]=-2;
						}
						else if(trozos[j].equals("B"))
						{
							pyramid[i][j]=-1;
							pyramid2[i][j]=-3;
						}
						else if(trozos[j].equals("Y"))
						{
							pyramid[i][j]=-1;
							pyramid2[i][j]=-4;
						}
						else if(trozos[j].equals("*"))
						{
							pyramid[i][j]=-1;
							pyramid2[i][j]=-1;
						}
						else if(trozos[j]!=null)
						{
							pyramid[i][j]=Integer.parseInt(trozos[j]);
							pyramid2[i][j]=Integer.parseInt(trozos[j]);
						}
					}
					i++;
			}
			file.close();
			for(int k=0;k<size;k++)
				for(int j=0;j<size;j++)
				{
					if(pyramid[k][j]==0)
					{
						pyramid[k][j]=-1;
						pyramid2[k][j]=-1;
					}
					else if(pyramid2[k][j]==-1)
					{
						pyramid[k][j]=0;
						pyramid2[k][j]=0;
					}
					else if(pyramid2[k][j]==-2)
					{
						pyramid[k][j]=0;
					}
					else if(pyramid2[k][j]==-3)
					{
						pyramid[k][j]=0;
					}
					else if(pyramid2[k][j]==-4)
					{
						pyramid[k][j]=0;
					}
					
				}
		}
		
		
		catch (FileNotFoundException fnfe) {
			new RuntimeException("File not found");
		}
		catch (IOException ioe) {
			new RuntimeException("Error I/O");
		}
	}
	
	void print()
	{
		for(int i=0;i<pyramid.length;i++)
		{
			for(int j=0;j<pyramid[i].length;j++)
			{
				if(pyramid[i][j]!=-1)
					System.out.print(pyramid[i][j]);
			}
			System.out.println();
		}
	}
	
	boolean checkColor(int i, int j, int k)
	{
		if(pyramid2[i][j]==-2)
		{
			for(int x=0;x<size;x++)
			{
				for(int y=0;y<size;y++)
				{
					if(pyramid2[x][y]==-2)
					{
						pyramid[x][y]=k;
						return true;
					}
				}
			}
		}
		else if(pyramid2[i][j]==-3)
		{
			for(int x=0;x<size;x++)
			{
				for(int y=0;y<size;y++)
				{
					if(pyramid2[x][y]==-3)
					{
						pyramid[x][y]=k;
						return true;
					}
				}
			}
		}
		else if(pyramid2[i][j]==-4)
		{
			for(int x=0;x<size;x++)
			{
				for(int y=0;y<size;y++)
				{
					if(pyramid2[x][y]==-4)
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
				if (!wasFound && checkSumOrRest1(x,y,k) && checkSumOrRest2(x,y,k) && checkSumOrRest3(x,y,k) && checkColor(x,y,k)) { 
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
}
