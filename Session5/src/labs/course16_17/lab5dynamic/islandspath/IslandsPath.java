package labs.course16_17.lab5dynamic.islandspath;

import java.io.IOException;
import java.*;

public class IslandsPath {

	private int matrix[][];//read matrix
	private int optimal[][];//optimal solution after applying the algorithm
	private int paths[][];//paths to follow after the algorithm
	
	public IslandsPath(String path) throws IOException {
		matrix = MyFileHandler.readFile(path);
		optimal = new int[matrix.length][matrix.length];
		paths = new int[matrix.length][matrix.length];
	}
	

	public int sumCostsMatrix() {
		int c=0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				c+= matrix[i][j];
			}
		}
		return c;
	}

	public void calculateMaximumWidths() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				paths[i][j]=-1;
			}
		}
		int min;
		for (int k = 0; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
 				for (int j = 0; j < matrix.length; j++) {
 					if(i!=j){
						if((min =Math.min(matrix[i][k],matrix[k][j])) > matrix[i][j]){
 							matrix[i][j] = min;
 							paths[i][j] = k;
						}
					}
				}
			}
		}		
	}

	public void printMatrix(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(i==j){
					System.out.print("*\t");
				}
				else{
					System.out.print(matrix[i][j]+"\t");
				}
			}
			System.out.println();
		}
	}
	
	public void printCostsMatrix() {
		printMatrix();
	}

	public void printPaths() {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				if(i!=j){
					System.out.println(i+" -- "+pathBetween(i, j)+j);
				}
			}
		}
	}
	
	public String pathBetween(int i, int j){
		String aux = "";
		int k = paths[i][j];
		if(k>=0){
			aux+=pathBetween(i,k);
			aux+=" "+k+" -- ";
			aux+=pathBetween(k, j);
		}
		return aux;
	}		
}
