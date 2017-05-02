package pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyFileHandler {
	private int[][] pyramid, pyramidColors;
	
	public int[][] getColors(){
		return pyramidColors;
	}
	
	public int[][] getPyramid(){
		return pyramid;
	}
	
	public int readFile(String path) throws IOException{
		//nHoles is equal to the numeber of * and half of the colors(as once a color have been found the other color must be equal)
		int nHoles = 0;
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String size = reader.readLine();
		int sizeint = Integer.parseInt(size.trim());
		pyramid = new int[sizeint][sizeint];
		pyramidColors= new int[sizeint][sizeint];
		
		for (int i = 0; i < sizeint; i++) {
			for (int j = 0; j < sizeint; j++) {
				pyramid[i][j]= -1;
				pyramidColors[i][j]= -1;
			}
		}
		int linenumber=0;
		while(reader.ready()){
			String line = reader.readLine();
			String[] pieces = line.trim().split(" ");
			int length= pieces.length;
			for (int i = 0; i < pieces.length; i++) {
				if(pieces[i].equals("R")){
					pyramid[linenumber][i] = 0;
					pyramidColors[linenumber][i] = -2;
				}else if(pieces[i].equals("B")){
					pyramid[linenumber][i] = 0;
					pyramidColors[linenumber][i] = -3;
				}else if(pieces[i].equals("Y")){
					pyramid[linenumber][i] = 0;
					pyramidColors[linenumber][i] = -4;
				}else if(pieces[i].equals("*")){
					pyramid[linenumber][i] = 0;
					pyramidColors[linenumber][i] = -1;
					//Asterisks count double 
				}else if(pieces[i] != null){
					pyramid[linenumber][i] = Integer.parseInt(pieces[i]);
					pyramidColors[linenumber][i] = Integer.parseInt(pieces[i]);
				}
			}
		linenumber++;
		}
		reader.close();
		return sizeint;
	}
	
	
	
}
