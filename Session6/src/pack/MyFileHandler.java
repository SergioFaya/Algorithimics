package pack;

import java.io.*;

public class MyFileHandler {
	public static String[][] readFile(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String[][] piramid;
		String size = reader.readLine();
		int sizeint = Integer.parseInt(size.trim());
		piramid = new String[sizeint][sizeint];
		int linenumber=0;
		while(reader.ready()){
			String line = reader.readLine();
			String[] pieces = line.trim().split(" ");
			int length= pieces.length;
			for (int i = 0; i < pieces.length; i++) {
				piramid[linenumber][i] = pieces[i];
			}
		linenumber++;
		}
		reader.close();
		return piramid;
	}
	
	
	
}
