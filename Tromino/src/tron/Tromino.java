package tron;

import java.util.Random;

public class Tromino {

	public final static int DEFAULT_SIZE = 2;
	private int[][] matrix;
	private int size;
	private int xpos;
	private int ypos;
	
	public Tromino(){
		matrix = new int [DEFAULT_SIZE][DEFAULT_SIZE];
		size = DEFAULT_SIZE;
		fill();
		setRandomPiece();
	}
	public Tromino(int dim, int xpiece, int ypiece ){
		matrix = new int [dim][dim];
		size = dim;
		fill();
		matrix[xpiece][ypiece] = -1;
		xpos = xpiece;
		ypos = ypiece;
	}
	
	
	private void fill(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] =0;
			}
		}
	}
	
	private void setRandomPiece(){
		Random rand = new Random();
		int x = rand.nextInt(size);
		int y = rand.nextInt(size);
		xpos = x;
		ypos = y;
		matrix[x][y] = -1;
	}
	
	public void findCuadrant(){
		boolean up = false;
		boolean left= false;
		//base case 
		if(xpos <= size/2){
			up = true;
			//cuadrant top
			if(ypos<=size/2){
				//top left
				left = true;
			}else{
				//top right
				left = false;
			}
		}else{
			//bottom cuadrant
			up = false;
		}
		
		if(up && left){
			matrix[size/2][size/2] = 1;
			matrix[size/2][size/2-1] = 1;
			matrix[size/2-1][size/2] = 1;
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
		Tromino tromino = new Tromino();
		tromino.print();
		tromino.findCuadrant();
		tromino.print();
		
	}
	
	
}
