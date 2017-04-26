package pack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PyramidGenerator {

	private int pyramidSize;
	private int nblanks;
	private int[][] pyramid;
	private int[][] colors;
	private int[][] solution;
	private int ncolors;
	private PyramidSolver solver;
	private int x1, x2, x3, y1, y2, y3;
	private int xa, xb, xc, ya, yb, yc;//The color coordinates are saved for testing purposes

	public PyramidGenerator(int pyramidSize, int nblanks, int ncolors) {
		this.nblanks = nblanks;
		this.pyramidSize = pyramidSize;
		this.ncolors = ncolors;
		this.pyramid = new int[pyramidSize][pyramidSize];
		this.colors = new int[pyramidSize][pyramidSize];
		initializeMatrix();
		createPyramid();
		solver = new PyramidSolver(pyramid, colors);
		solution = solver.solve();
		putBlanks();
	}

	private void initializeMatrix() {
		for (int i = 0; i < pyramidSize; i++) {
			for (int j = 0; j < pyramidSize; j++) {
				pyramid[i][j] = -1;
				colors[i][j] = -1;
			}
		}

	}

	private void createPyramid() {
		int size = 1;
		for (int i = 0; i < pyramidSize; i++) {
			for (int j = 0; j < size; j++) {
				pyramid[i][j] = 0;
				colors[i][j] = 0;
			}
			size++;
		}
		Random random = new Random();
		if(ncolors == 0){
			System.out.println("NO COLORS ADDED");
		}
		if(ncolors >= 1){// RED
			x1 = random.nextInt(pyramidSize);
			y1 = random.nextInt(x1 + 1);
			colors[x1][y1] = -2;
			do {
				xa = random.nextInt(pyramidSize);
				ya = random.nextInt(xa + 1);
			} while (colors[x1][y1] == colors[xa][ya]);
			colors[xa][ya] = -2;
		}
		if(ncolors>=2){// RED and BLUE
			do {
				x2 = random.nextInt(pyramidSize);
				y2 = random.nextInt(x2+1);
			} while (colors[x1][y1] == colors[x2][y2] || colors[xa][ya] == colors[x2][y2]);
			colors[x2][y2] = -3;
			do{
				xb = random.nextInt(pyramidSize);
				yb = random.nextInt(xb+ 1);
			}while(colors[x1][y1] == colors[xb][yb] || colors[xa][ya] == colors[xb][yb] ||
					colors[x2][y2]== colors[xb][yb]);
			colors[xb][yb] = -3;
		}
		if(ncolors ==3){// RED, BLUE and YELLOW
			do {
				x3 = random.nextInt(pyramidSize);
				y3 = random.nextInt(x3+1);
			} while (colors[x1][y1] == colors[x3][y3] || colors[x2][y2] == colors[x3][y3] ||
					colors[xa][ya] == colors[x3][y3] || colors[xb][yb] == colors[x3][y3]);
			colors[x3][y3] = -4;
			do{
				xc = random.nextInt(pyramidSize);
				yc = random.nextInt(xc+ 1);
			}while(colors[x1][y1] == colors[xc][yc] || colors[x2][y2] == colors[xc][yc] ||
					colors[xa][ya] == colors[xc][yc] || colors[xb][yb] == colors[xc][yc] ||
					colors[x3][y3] == colors[xc][yc]
					);
			colors[xc][yc] = -4;
		}
		if(ncolors >3){
			System.err.println("Cannot be more than 3 colours in the pyramid");
			System.exit(0);
		}
	}

	private void print(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			// print tabs
			for (int j = 0; j < m.length - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < m.length; j++) {
				if(m[i][j]!=-1)	
					if (m[i][j] == 0)
						System.out.print("* ");
					else if (colors[i][j] == -2)
						System.out.print("R ");
					else if (colors[i][j] == -3)
						System.out.print("B ");
					else if (colors[i][j] == -4)
						System.out.print("Y ");
					else
						System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void printAll(){
		System.out.println("Pyramid at the end of backtracking");
		solver.print();
		System.out.println("Colors pyramid");
		print(colors);
		System.out.println("Final pyramid that will be passed to the file");
		print(solution);
	}

	private void putBlanks(){
		Random random = new Random();
		while(nblanks >0){
			int x = random.nextInt(pyramidSize);
			int y = random.nextInt(x+1);
			if(colors[x][y] == 0 && solution[x][y]!=0){
				solution[x][y]=0;
				nblanks--;
			}
				
		}
	}
	
	private void createFile(String path)  {
		StringBuilder builder = new StringBuilder();
		builder.append(pyramidSize+"\n");
		int size = 1;
		for (int i = 0; i < pyramidSize; i++) {
			for (int j = 0; j < size; j++) {
				if (solution[i][j] == 0)
					builder.append("* ");
				else if (colors[i][j] == -2)
					builder.append("R ");
				else if (colors[i][j] == -3)
					builder.append("B ");
				else if (colors[i][j] == -4)
					builder.append("Y ");
				else
					builder.append(solution[i][j]+" ");
			}
			size++;
			builder.append("\n");
		}
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));	
			writer.write(builder.toString());
			writer.close();
		}catch(IOException e){
			System.err.println("Something went wrong with files");
		}
	}
	
	public static void main(String[] args) {
		PyramidGenerator gen = new PyramidGenerator(5, 3, 1);
		gen.printAll();
		gen.createFile("files/generatedPyramid.txt");
	}

	
}
