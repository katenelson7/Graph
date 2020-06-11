package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import java.util.Map;
import java.util.HashMap;

/**
 * This class tests the functionality of your PageRank algorithm on a
 * directed AdjacencyMatrixGraph. The general framework of a test case is:
 * - Name the test method descriptively,
 * - Mention what is being tested (it is ok to have slightly verbose method names here)
 * 
 * Some tips to keep in mind when writing test cases: 
 * - All pages' ranks should total to 1.
 * - It will be easier to start out by writing test cases on smaller graphs.
 *
 */
public class MyPageRankTest {

	// This is your margin of error for testing
	double _epsilon = 0.03;

	/**
     * A simple test with four pages. Each page only has one
	 * outgoing link to a different page, resulting in a square 
	 * shape or cycle when visualized. The pages' total ranks is 1.
     */
	
	
	@Test
	public void testFourEqualRanks() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");

		

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(c,d,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(d,a,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 0.25
		double expectedRankA = 0.25;
		double expectedRankB = 0.25;
		double expectedRankC = 0.25;
		double expectedRankD = 0.25;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon 
		assertEquals(output.get(a), expectedRankA, _epsilon);
		assertEquals(output.get(b), expectedRankB, _epsilon);
		assertEquals(output.get(c), expectedRankC, _epsilon);
		assertEquals(output.get(d), expectedRankD, _epsilon);

	}
	
	
	
	
	
	

	/**
     	 * A simple test with three pages. Note that vertex A's 
	 * rank is not 0 even though it has no incoming edges, 
	 * demonstrating the effects of the damping factor and handling sinks.
     	 */
	@Test
	public void simpleTestOne() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 3);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// These are precomputed values
		double expectedRankA = 0.186;
		double expectedRankB = 0.342;
		double expectedRankC = 0.471;

		assertEquals(total, 1, _epsilon);
		assertEquals(output.get(a), expectedRankA, _epsilon);
		assertEquals(output.get(b), expectedRankB, _epsilon);
		assertEquals(output.get(c), expectedRankC, _epsilon);

	}
	

	/**
	  * TODO: Add your own tests here. Instead of checking for specific rank values, 
	  * make test cases comparing the relative ranks of pages (e.g. using an assertThat statement)!
	  */

	@Test
	public void singleNode() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");


		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 1);
		
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// These are precomputed values
		double expectedRankA = 1;


		//assertEquals(total, 1, _epsilon);
		assertEquals(output.get(a), expectedRankA, _epsilon);

	}
	
	/**
	 * more complex test with 4 nodes and 5 edges 
	 * 
	 */
	@Test
	public void complexTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");
		CS16Vertex<String> e = adjMatrix.insertVertex("D");
		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(c,b,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(b,e,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(c,e,null);
		CS16Edge<String> e4 = adjMatrix.insertEdge(c,a,null);
		CS16Edge<String> e5 = adjMatrix.insertEdge(d,c,null);
		CS16Edge<String> e6 = adjMatrix.insertEdge(c,d,null);
		CS16Edge<String> e7 = adjMatrix.insertEdge(d,e,null);
		CS16Edge<String> e8 = adjMatrix.insertEdge(e,d,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 5);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		

		assertEquals(total, 1, _epsilon);
		assertTrue(output.get(e)>output.get(a));
		assertTrue(output.get(e)>output.get(c));
		assertTrue(output.get(b)>output.get(a));
		assertTrue(output.get(d)>output.get(c));
		assertTrue(output.get(e)>output.get(b));
	}
	
	
	
	
	
	
	
	
	
}
