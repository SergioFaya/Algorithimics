package labs.course16_17.lab5dynamic.islandspath;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;


/**
 * JUnit Test for Islands Path
 */
public class IslandsPathTest {

	@Test
	public void test1() throws IOException {
		IslandsPath problem = new IslandsPath("files/islands01.txt");
		problem.printMatrix();
		executeFromFile(problem);
		assertTrue(problem.sumCostsMatrix() == 16);
	}
	
	@Test
	public void test2() throws IOException {
		IslandsPath problem = new IslandsPath("files/islands02.txt");
		executeFromFile(problem);
		assertTrue(problem.sumCostsMatrix() == 49);
	}
	
	@Test
	public void test3() throws IOException {
		IslandsPath problem = new IslandsPath("files/islands03.txt");
		executeFromFile(problem);
		assertTrue(problem.sumCostsMatrix() == 10);
	}
	
	private void executeFromFile(IslandsPath problem) {
		System.out.println("Widths");
		System.out.println(problem);
		problem.calculateMaximumWidths();
		System.out.println("Matrix of the maximum widths");
		problem.printCostsMatrix();
		System.out.println("Islands through which each road passes");
		problem.printPaths();
	}
		
}
