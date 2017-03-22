package labs.course16_17.lab5dynamic.islandspath;

import java.io.IOException;

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
		// TODO Auto-generated method stub
		return 0;
	}

	public void calculateMaximumWidths() {
		// TODO Auto-generated method stub
		
	}

	public void printMatrix(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[i][j]== Integer.MIN_VALUE){
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
		// TODO Auto-generated method stub
		
	}

	public void printPaths() {
		// TODO Auto-generated method stub
		
	}
	
	public void method(){
		for (int k = 0; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					
				}
			}
		}
	}
		
}
