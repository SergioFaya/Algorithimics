package labs.course15_16.lab2;

/* This program can be used to sort n elements with 
 * the best algorithm. It is the QUICKSORT */
public class QuicksortCentralElement
	{
	static int []v;
	
	public static void main (String arg [] ){
	  int n= Integer.parseInt (arg[0]);  //size of the problem
	  v = new int [n];
	
	  Vector.sorted(v);
	  System.out.println("VECTOR TO BE SORTED:");
	  Vector.write(v);	
	  quicksort(v);
	  System.out.println("SORTED VECTOR:");
	  Vector.write(v);
	
	  Vector.inverselySorted (v);
	  System.out.println("VECTOR TO BE SORTED:");
	  Vector.write(v);	
	  quicksort(v);
	  System.out.println("SORTED VECTOR:");
	  Vector.write(v);
	  
	  Vector.random(v, 1000000);
	  System.out.println("VECTOR TO BE SORTED:");
	  Vector.write(v);	
	  quicksort(v);
	  System.out.println("SORTED VECTOR:");
	  Vector.write(v);
	} 
	
	private static void quickSort(int elements[], int left, int right){
		if(right<=left && left>=right){
			
		}else{
			int pivot = elements[left];
			int i = left+1;
			
			for (int j = left+1; j <= right; j++) {
				if(pivot > elements[j]){
					Util.interchange(elements, i, j);
					i++;
				}
			}
			
			elements[left] = elements[i-1];
			elements[i-1] = pivot;
			
			quickSort
			(elements, left, i-2);
			quickSort(elements, i, right);
		}
	}
	
	public static void quicksort(int[] elements) {
		quickSort(elements, 0, elements.length-1);
	}
} 
