package ses4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileHandler {

	
	
	public ArrayList<Product> Read(String filename) throws Exception {
		ArrayList<Product> result = new ArrayList<Product>();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String l;
		result.add(Integer.parseInt(reader.readLine().trim()));//number of elements
		while((l= reader.readLine())!= null){
			String[] linesplitted = l.split(" ");
			result.add(new Product(Integer.parseInt(linesplitted[0]), Integer.parseInt(linesplitted[1]), Float.parseFloat(linesplitted[2])));
		}
		
			
		return result;
	}
}
