package tron;

import java.util.Random;

public class Tromino {

	public final static int DEFAULT_SIZE = 8;
	private int[][] matrix;
	private int size;
	public int xpos;
	public int ypos;
	private int currentNum;
	
	public int getXpos() {
		return xpos;
	}
	
	public int getYpos() {
		return ypos;
	}
	
	public Tromino(){
		matrix = new int [DEFAULT_SIZE][DEFAULT_SIZE];
		size = DEFAULT_SIZE;
		currentNum = 1;
		setRandomPiece(size);
	}
	
	public Tromino(int size){
		matrix = new int [size][size];
		currentNum = 1;
		setRandomPiece(size);
	}
	
	public Tromino(int dim, int xpiece, int ypiece ){
		matrix = new int [dim][dim];
		size = dim;
		currentNum = 1;
		matrix[xpiece][ypiece] = -1;
		xpos = xpiece;
		ypos = ypiece;
	}
	

	
	private void setRandomPiece(int size){
		Random rand = new Random();
		int x = rand.nextInt(size);
		int y = rand.nextInt(size);
		xpos = x;
		ypos = y;
		matrix[x][y] = -1;
	}
	
	public boolean[] findCuadrant(int size,int xpos, int ypos){
		boolean up = false;
		boolean left= false;
		if(xpos < size/2){
			// left
			left = true;
		}else{
			// right
			left = false;
		}
		
		if(ypos<size/2){
			// up
			up = true;
		}else{
			// down
			up = false;
		}
		boolean[] array = new boolean[] {up,left};
		return array;
	}
	
	public void solveLog(int size, boolean up, boolean left){
			//base case	
			if(up && left){
				matrix[size/2][size/2] = currentNum;
				matrix[size/2][size/2-1] = currentNum;
				matrix[size/2-1][size/2] = currentNum;
//				boolean a[] = findCuadrant(size/2,size/2, size/2);
//				boolean b[] = findCuadrant(size/2,size/2, size/2 - 1);
//				boolean c[] = findCuadrant(size/2,size/2 - 1, size/2);
				currentNum++;
				if(size!=2){
				solveLog(size/2,true, true);
				solveLog(size/2,true, false);
				solveLog(size/2,false, true);
				}
			}
			else if( up == false && left == false){// down right
				matrix[size/2-1][size/2] = currentNum;
				matrix[size/2][size/2-1] = currentNum;
				matrix[size/2-1][size/2-1] = currentNum;
//				boolean a[] = findCuadrant(size/2,size/2-1, size/2);
//				boolean b[] = findCuadrant(size/2,size/2, size/2-1);
//				boolean c[] = findCuadrant(size/2,size/2-1, size/2);
				currentNum++;
				if(size!=2){
				solveLog(size/2,false, true);
				solveLog(size/2,true, false);
				solveLog(size/2,false, false);
				}
				
			}
			else if( up == false && left == true){//up right
				matrix[size/2][size/2] = currentNum;
				matrix[size/2][size/2-1] = currentNum;
				matrix[size/2-1][size/2-1] = currentNum;
//				boolean a[] = findCuadrant(size/2,size/2, size/2);
//				boolean b[] = findCuadrant(size/2,size/2-1, size/2);
//				boolean c[] = findCuadrant(size/2,size/2 - 1, size/2-1);
				currentNum++;
				if(size!=2){
				solveLog(size/2,true, true);
				solveLog(size/2,true, false);
				solveLog(size/2,false, false);
				}
			}
			else{//down left
				matrix[size/2-1][size/2] = currentNum;
				matrix[size/2][size/2] = currentNum;
				matrix[size/2-1][size/2-1] = currentNum;
//				boolean a[] = findCuadrant(size/2,size/2, size/2);
//				boolean b[] = findCuadrant(size/2,size/2, size/2 - 1);
//				boolean c[] = findCuadrant(size/2,size/2 - 1, size/2 -1 );
				currentNum++;
				if(size!=2){
				solveLog(size/2,false, true);
				solveLog(size/2,true, true);
				solveLog(size/2,false, false);
				}
			}
	}
	
public void solve(int size, int topx, int topy) {
		//Base Case
		if (size == 2) {
			for (int i=0; i<size; i++) 
				for (int j=0; j<size; j++)
					if (matrix[topx+i][topy+j] == 0)
						matrix[topx+i][topy+j] = currentNum;
					currentNum++;
		}
		// Recursive Case
		else {
			//Get the position of 1,1 piece
			int savex=topx, savey=topy;
			for (int x=topx; x<topx+size; x++) 
				for (int y=topy; y<topy+size; y++)
					if (matrix[x][y] != 0) {
						savex = x;
						savey = y;
					}
			//Upper left quadrant.		
			if (savex < topx + size/2 && savey < topy + size/2) {
				solve(size/2, topx, topy);
				matrix[topx+size/2][topy+size/2-1] = currentNum;
				matrix[topx+size/2][topy+size/2] = currentNum;
				matrix[topx+size/2-1][topy+size/2] = currentNum;
				currentNum++;
				solve(size/2, topx, topy+size/2);
				solve(size/2, topx+size/2, topy);
				solve(size/2, topx+size/2, topy+size/2);
				
			}
			//Upper right quadrant
			else if (savex < topx + size/2 && savey >= topy + size/2) {
				solve(size/2, topx, topy+size/2);
				matrix[topx+size/2][topy+size/2-1] = currentNum;
				matrix[topx+size/2][topy+size/2] = currentNum;
				matrix[topx+size/2-1][topy+size/2-1] = currentNum;
				currentNum++;
				solve(size/2, topx, topy);
				solve(size/2, topx+size/2, topy);
				solve(size/2, topx+size/2, topy+size/2);
				
			}
			//Bottom left quadrant
			else if (savex >= topx + size/2 && savey < topy + size/2) {
				solve(size/2, topx+size/2, topy);
				matrix[topx+size/2-1][topy+size/2] = currentNum;
				matrix[topx+size/2][topy+size/2] = currentNum;
				matrix[topx+size/2-1][topy+size/2-1] = currentNum;
				currentNum++;
				solve(size/2, topx, topy);
				solve(size/2, topx, topy+size/2);
				solve(size/2, topx+size/2, topy+size/2);
			}
			//Bottom right quadrant
			else {
				solve(size/2, topx+size/2, topy+size/2);
				matrix[topx+size/2-1][topy+size/2] = currentNum;
				matrix[topx+size/2][topy+size/2-1] = currentNum;
				matrix[topx+size/2-1][topy+size/2-1] = currentNum;
				currentNum++;
				solve(size/2, topx+size/2, topy);
				solve(size/2, topx, topy+size/2);
				solve(size/2, topx, topy);
			}	
		}	
	} 
	
	@Override
	public String toString() {
		String str = new String();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				str += matrix[i][j] +"\t";
			}
			str+="\n";
		}	
		return str;
	}
	
	public void print(){
		System.out.println(toString());
	}
	
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		int x = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);

		long t1,t2;
		Tromino tromino = new Tromino(size, x, y);
		t1 = System.currentTimeMillis();
		tromino.solve(size, 0, 0);
		t2 = System.currentTimeMillis();
		long time = t2 - t1 ;
		System.out.println("Time -- "+ time);
		tromino.print();
	}
	
	
}
