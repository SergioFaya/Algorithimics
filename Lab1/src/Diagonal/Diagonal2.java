package Diagonal;

import Vector.Vector1;

/**
 * This program serves to measure times automatically increasing 
 * the size of the problem (n) and also using a time scale determined
 * by nTimes, which is taken from the argument arg[1]
 * It also gets as an execution argument (arg[0]) the operation on which 
 * we will focus the measurement (options 0,1,2 respectively)
 * @author viceg
 */
public class Diagonal2 {
	static int [][]a;

	@SuppressWarnings("unused")
	public static void main(String arg []) {
		int nTimes = Integer.parseInt(arg[1]); //nTimes
		int option = Integer.parseInt(arg[0]); //selected option
		long t1=0,t2 =0;
		int s1 = 0;
		int matrix[][];
		
		for (int n=3; n<=1000; n*=2){//n is incremented * 2
			matrix= new int[n][n];
		   if (option==0){ //fill in the matrix
			   t1 = System.currentTimeMillis();
			   for (int repetition=1; repetition<=nTimes; repetition++){    	
				     Diagonal1.fillIn(matrix);
			   }
			   t2 = System.currentTimeMillis();
		   } //if
		   else if (option==1) { //sum of the diagonal (one way)
			   t1 = System.currentTimeMillis();
			   for (int repetition=1; repetition<=nTimes; repetition++){
				   Diagonal1.fillIn(matrix);
				   s1 = Diagonal1.sum1Diagonal(matrix);
			   }
			   t2 = System.currentTimeMillis();
		   } //else if
		   else if (option==2) { //sum of the diagonal (another way)
			   t1 = System.currentTimeMillis();
			   for (int repetition=1; repetition<=nTimes; repetition++){    	
				   Diagonal1.fillIn(matrix);
				   s1 = Diagonal1.sum1Diagonal(matrix);
			   }
			   t2 = System.currentTimeMillis();
		   } //else if
		   else System.out.println("INCORRECT OPTION"); 
		  
		  System.out.println("SIZE = "+n+" ** OPTION = "+option+" ** "+"TIME = "+(t2-t1)+"ms ** "+ " SUM = " + s1 + " ** nTimes = " + nTimes);
		}//FOR
	} //main

} //class
