package tron;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class TimeBench {

	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
//		int xpiece = Integer.parseInt(args[1]);
//		int ypiece = Integer.parseInt(args[2]);
		long t1,t2;
		while(true){
			try{
				Tromino tromino = new Tromino(size);
				t1 = System.currentTimeMillis();
				tromino.solve(size, 0, 0);
				t2 = System.currentTimeMillis();
				long time = t2 - t1 ;
				System.out.println("Time -- "+ time+ " -- Size -- "+ size);
			}catch(OutOfMemoryError e){
				System.out.println("End of the loop");
			}finally {
				size*=2;
			}
		}

	}

}
