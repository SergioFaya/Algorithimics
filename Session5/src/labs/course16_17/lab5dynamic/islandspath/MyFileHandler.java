package labs.course16_17.lab5dynamic.islandspath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public  class MyFileHandler {

	
	public static int[][] readFile(String path) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(path));
		
		int nislands = Integer.parseInt(reader.readLine().trim());
		int matrix [][] = new int[nislands][nislands];
		while (reader.ready()){
			String line = reader.readLine();
			String[] lineSplitted = line.split(" ");
			int from = Integer.parseInt(lineSplitted[0]);
			int to = Integer.parseInt(lineSplitted[2]);
			String direction = lineSplitted[1];
			int width = Integer.parseInt(lineSplitted[3]);
			
			if (direction.equals("--")){
				matrix[from][to] = width;
				matrix[to][from] = width;
			}
			else{
				matrix[from][to] = width;
			}
		}
		
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][i] = Integer.MIN_VALUE;
		}
		reader.close();
		return matrix;
	}
}
