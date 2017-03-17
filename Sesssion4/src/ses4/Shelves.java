package ses4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Shelves {


	public static Product[] readFromFiles(String filename) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		int size = Integer.parseInt(reader.readLine().trim());
		Product[] result = new Product[size];
		String l;
		//number of elements
		int i =0;
		while((l= reader.readLine())!= null){
			String[] linesplitted = l.split(" ");
			result[i]= (new Product(Integer.parseInt(linesplitted[0]),
					Integer.parseInt(linesplitted[1]),
					Float.parseFloat(linesplitted[2])));
			i++;
		}
		
		reader.close();
		return result;
	}
	
	public static double calculateAverageAccesTime(Product[] products){
		double time = 0.0;
		int iterator = 0;
		for (int i = 0; i < products.length; i++) {
			if (iterator>0){
				int length = 0;
				for (int j = iterator-1; j >=0 ; j--) {
					length += products[j].getLength();
				}
				time += (length + products[i].getLength()/2) * products[i].getRequest();
			}
			else{
				time += products[i].getLength()/2 * products[i].getRequest();
			}
			iterator++;
		}
		return time;
	}
	
	//They are similar to merge sort
	public static void orderByLength(Product[] products)
	{
		Arrays.sort(products, (p1, p2) -> p1.getLength() - p2.getLength());
	}
	
	public static void orderByRequest(Product[] products)
	{
		Arrays.sort(products, (p1, p2) -> Float.compare(p2.getRequest(), p1.getRequest()));
	}
	
	public static void orderByHeuristic(Product[] products)
	{
		Arrays.sort(products, (p1, p2) -> Float.compare(p2.getRequest() / p2.getLength(), p1.getRequest() / p1.getLength()));
	}
	
	
	
	
}
