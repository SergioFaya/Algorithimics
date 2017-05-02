package pack;

import topics.branchandbound.util.*;
import java.util.ArrayList;
import java.util.UUID;

public class Pyramid extends Node{

	private int[][] pyramid, colors;
	private int size;
	private int heuristicValue = 0;
	private boolean wasFound;
	
	public Pyramid(int[][] pyramid, int [][] pyramidWithColors, int size)
	{
		this.pyramid = pyramid;
		this.colors = pyramidWithColors;
		this.size = size;
	}

	public Pyramid(int[][] pyramid, int [][] colors, int size, int depth, UUID parentID)
	{
		this.pyramid = pyramid;
		this.colors = colors;
		this.size = size;
		this.depth = depth;
		this.parentID = parentID;
		calculateHeuristicValue();
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < pyramid.length; i++) {
			for (int j = 0; j < pyramid.length - i; j++){
				builder.append(" ");
			}
			for (int k = 0; k <= i; k++){
				if(pyramid[i][k] == 0){
					if (colors[i][k] == -2)
						builder.append("R ");
					else if (colors[i][k] == -3)
						builder.append("B ");
					else if (colors[i][k] == -4)
						builder.append("Y ");
					else
						builder.append("* ");
				}else	builder.append(pyramid[i][k] + " ");
			}		
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public void print() {
		System.out.println(toString());

	}
	
	private int[][] findPartialSolution(int k) 
	{
		int[] zeros = findZero(size-1, -1);
		int x = zeros[0];
		int y = zeros[1];
		
		if (check1(x, y, k) && check2(x, y, k) && check3(x, y, k) && checkColors(x, y, k)){
			int [][] aux = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					aux[i][j] = pyramid[i][j];
				}
			}
			aux[x][y] = k;
			
			return aux;
		}
		return null;
	}

	public boolean checkColors(int i, int j, int k) {
		//we look for a copy of the color and in case they are the same we mark the
		//position returning true, false in case the colors doesn't match and if there is no color return true
		if (colors[i][j] < 0) {
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

	@Override
	public void calculateHeuristicValue() {
		/*
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors.length; j++) {
				if(colors[i][j] == -1 ){
					heuristicValue+=2;
				}else if(colors[i][j] <-1){
					heuristicValue++;
				}
			}
		}
		*/
		for (int i = 0; i < pyramid.length; i++) {
			for (int j = 0; j < pyramid.length; j++) {
				if (pyramid[i][j] == 0)
					heuristicValue++;
			}
		}
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> results = new ArrayList<Node>();
		int[][] testPyramid;
		Pyramid newPyramid = new Pyramid(pyramid, colors, size, depth, this.getID());
		results.add(newPyramid);
		for (int k = 1; k <= 9; k++) {
			testPyramid = findPartialSolution(k);
			if (testPyramid != null){
				Pyramid aux = new Pyramid(testPyramid, colors, size, depth + 1, this.getID());
				results.add(aux);
			}
		}
		return results;
	}

	@Override
	public boolean isSolution() {
		return heuristicValue==0;
	}

}
