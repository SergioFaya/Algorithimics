package labs.course15_16.lab2;

public class Bench {

	static int []v;
	
	public static void main (String arg [] ){
	  System.out.println ("WE ARE GOING TO TEST THAT IT WORKS");
	 
	  System.out.println ("QUADRATIC TIMES USING A BAD PIVOT");	
	  long t1,t2;
	  
	  for (int n=100;n<10000;n*=2) {
	    v = new int[n] ;
	    Vector.sorted(v);

	    t1 = System.currentTimeMillis();
	    for (int nVeces=1;nVeces<=1000;nVeces++){
	    	Bubble1.bubble(v);
	    }
	    	
	    t2 = System.currentTimeMillis();
	  
	    System.out.println("n="+n+"**TIME="+(t2-t1)+" MICROSECONDS"); 
	  } 
	 
	}
}
