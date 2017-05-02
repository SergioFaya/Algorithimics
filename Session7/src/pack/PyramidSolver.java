package pack;

import java.io.IOException;
import java.util.logging.FileHandler;

import topics.branchandbound.util.BranchAndBound;

public class PyramidSolver extends BranchAndBound{
	
	private MyFileHandler handler = new MyFileHandler();;
	private int size ;
	private int[][] pyramid,colors;
	
	public PyramidSolver(String path) throws IOException {
		size = handler.readFile(path);
		colors = handler.getColors();
		pyramid = handler.getPyramid();
		
		rootNode = new Pyramid(pyramid, colors, size);
	}
	public static void main(String[] args) {
		try {
			PyramidSolver solver = new PyramidSolver("files/case1.txt");
			solver.branchAndBound(solver.getRootNode());
			solver.printSolutionTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
