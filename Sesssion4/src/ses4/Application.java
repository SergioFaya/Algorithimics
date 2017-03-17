package ses4;

public class Application {

	public static void main(String[] args) throws Exception {
		Product[] products1 = Shelves.readFromFiles("files/productos1.txt");
		System.out.println("PREODUCTS 1");
		System.out.println("Unordered shelves: "+Shelves.calculateAverageAccesTime(products1));
		Shelves.orderByHeuristic(products1);
		System.out.println("Heuristicly sorted shelves: "+Shelves.calculateAverageAccesTime(products1));
		Shelves.orderByLength(products1);
		System.out.println("Lengthly sorted shelves: "+Shelves.calculateAverageAccesTime(products1));
		Shelves.orderByRequest(products1);
		System.out.println("Requestly sorted shelves: "+Shelves.calculateAverageAccesTime(products1));  
		System.out.println();
		System.out.println("PREODUCTS 2");
		Product[] products2 = Shelves.readFromFiles("files/productos2.txt");
		System.out.println("Unordered shelves: "+Shelves.calculateAverageAccesTime(products2));
		Shelves.orderByHeuristic(products2);
		System.out.println("Heuristicly sorted shelves: "+Shelves.calculateAverageAccesTime(products2));
		Shelves.orderByLength(products2);
		System.out.println("Lengthly sorted shelves: "+Shelves.calculateAverageAccesTime(products2));
		Shelves.orderByRequest(products2);
		System.out.println("Requestly sorted shelves: "+Shelves.calculateAverageAccesTime(products2));
		
	}
}
