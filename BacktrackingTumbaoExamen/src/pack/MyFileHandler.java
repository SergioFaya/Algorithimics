package pack;

import java.io.*;

public class MyFileHandler {
	private int[][] pyramid, pyramidColors;
	private int sizeint;
	public int[][] getColors(){
		return pyramidColors;
	}
	
	public int[][] getPyramid(){
		return pyramid;
	}
	
	public int readFile(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String size = reader.readLine();
		sizeint = Integer.parseInt(size.trim());
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
				}else if(pieces[i] != null){
					pyramid[linenumber][i] = Integer.parseInt(pieces[i]);
					pyramidColors[linenumber][i] = Integer.parseInt(pieces[i]);
				}
			}
		linenumber++;
		}
		reader.close();
		turn();
		return sizeint;
	}

	private void turn() {
		int[][] pyramidAux = new int[sizeint][sizeint];
		int[][] colorsAux = new int[sizeint][sizeint];
		
		for (int i = 0; i < colorsAux.length; i++) {
			for (int j = 0; j < colorsAux.length; j++) {
				pyramidAux[i][j] = pyramid[sizeint - i -1][j];
				colorsAux[i][j] = pyramidColors[sizeint - i -1][j];
			}
		}
		
		for (int i = 0; i < colorsAux.length; i++) {
			for (int j = 0; j < colorsAux.length; j++) {
				pyramid[i][j] = pyramidAux[i][j];
				pyramidColors[i][j] = colorsAux[i][j];
			}
		}
		
	}
	
	
	
}
