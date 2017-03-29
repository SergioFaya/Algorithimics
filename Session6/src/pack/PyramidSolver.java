package pack;

import java.io.IOException;

public class PyramidSolver {

	private class MyColor{
		char color;
		int x;
		int y;
		
		public MyColor(char color, int x, int y) {
			this.color = color;
			this.x = x;
			this.y = y;
		}
	}
	
	String[][] pyramid;
	public PyramidSolver(String path){
		try {
			pyramid = MyFileHandler.readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Wrong path or file name");
		}
	}
	
	public void print() throws IOException{
		for (int i = 0; i < pyramid.length; i++) {
			for (int j = 0; j < pyramid.length; j++) {
				System.out.print(pyramid[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void solve(){
		for (int i = 0; i < pyramid.length; i++) {
			for (int j = 0; j < pyramid.length; j++) {
				if(pyramid[i][j] == "*"){
					
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
	}
}
