package pack;

public class PyramidGenerator {

	private int pyramidSize;
	private int blanks;
	private String[][] pyramid;
	PyramidSolver solver = new PyramidSolver();
	
	public PyramidGenerator(int pyramidSize,int blanks) {
		this.blanks=blanks;
		this.pyramidSize = pyramidSize;
	}
	
	private void createPyramid() {
		for (int i = 0; i < pyramidSize; i++) {
			for (int j = 0; j < pyramidSize; j++) {
				pyramid[i][j] = "*";
			}
		}
	}
}
