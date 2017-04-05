package pack;

import java.io.*;

public class MyFileHandler {
	public static int[][] readFile(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		int[][] piramid;
		String size = reader.readLine();
		int sizeint = Integer.parseInt(size.trim());
		piramid = new int[sizeint][sizeint];
		int linenumber=0;
		while(reader.ready()){
			String line = reader.readLine();
			String[] pieces = line.trim().split(" ");
			int length= pieces.length;
			for (int i = 0; i < pieces.length; i++) {
				piramid[linenumber][i] = Integer.parseInt(pieces[i]);
			}
		linenumber++;
		}
		reader.close();
		return piramid;
	}
	
	
	
}
