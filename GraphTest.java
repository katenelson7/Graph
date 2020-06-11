package graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static support.graph.Constants.MAX_VERTICES;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.DirectionException;
import support.graph.Graph;
import support.graph.InvalidEdgeException;
import support.graph.InvalidVertexException;
import support.graph.NoSuchEdgeException;
import support.graph.NoSuchVertexException;

/**
 * This class tests the functionality of a Graph based on a 'String' type
 * parameter for the vertices. Edge elements are Integers. The general framework
 * of a test case is: - Name the test method descriptively, mentioning what is
 * being tested (it is ok to have slightly verbose method names here) - Set-up
 * the program state (ex: instantiate a heap and insert K,V pairs into it) - Use
 * assertions to validate that the program is in the state you expect it to be
 * See header comments over tests for what each test does
 * 
 * Before each test is run, you can assume that the '_graph' variable is reset to
 * a new instance of the a Graph<String> that you can simply use 'as is'
 * via the methods under the 'DO NOT MODIFY ANYTHING BELOW THIS LINE' line.
 * Of course, please do not modify anything below that, or the above 
 * assumptions may be broken.
 */
@RunWith(Parameterized.class)


public class GraphTest {
    
    // Undirected Graph instance variable
    private Graph<String> _graph;
    // Directed Graph instance variable
    private Graph<String> _dirGraph;
    private String _graphClassName;
	
    /**
     * A simple test for insertVertex, that adds 3 vertices and then checks to
     * make sure they were added by accessing them through the vertices
     * iterator.
     */
    @Test(timeout = 10000)
    public void testInsertVertex() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }
    
    
    
    /**
     * my own test for insert vertex. I wasn't sure if the given tests are also run
     */
    @Test(timeout = 10000)
    public void myTestInsertVertex() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }
    
    

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testInsertVertexDirected() {
        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }
    
    
    
    
 // Same test as my own simple test, but with a directed graph
    @Test(timeout = 10000)
    public void myTestInsertVertexDirected() {
        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
    }


    /**
     * A simple test for insertEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that both edges were in fact added using the edge
     * iterator as well as checks to make sure their from and to vertices were
     * set correctly.
     */
    @Test(timeout = 10000)
    public void testInsertEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }
    
    /**
     * A simple test for insertEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that both edges were in fact added using the edge
     * iterator as well as checks to make sure their from and to vertices were
     * set correctly.
     */
    @Test(timeout = 10000)
    public void myTestInsertEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }


    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testInsertEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }
    
    
    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void myTestInsertEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
        
    }
    
    
    /**
	 * Checks that an InvalidVertexException is thrown if we try and insert edges between vertices that 
	 * are null.
	 */
	@Test(expected=InvalidVertexException.class)
	public void testInsertEdgeThrowsInvalidVertexException() {
		 CS16Vertex<String> A = _dirGraph.insertVertex("A");
	     CS16Vertex<String> B = _dirGraph.insertVertex("B");
	     CS16Edge<String> ab = _dirGraph.insertEdge(null, B, 1);
	     CS16Edge<String> bc = _dirGraph.insertEdge(B, null, 2);
	}
	
    
  
	/**
	 * Checks that removeVertex works on a directed graph with 3 nodes.
	 */
    @Test(timeout = 10000)
    public void removeVertexTest() {
        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 2);
        
        _dirGraph.removeVertex(B);
        
        // use the vertex iterator to get a list of the vertices in the actual graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(2));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(false));
        assertThat(actualVertices.contains(C), is(true));
       
        
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> its = _dirGraph.edges();
        while (its.hasNext()) {
            actualEdges.add(its.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(1));
        assertThat(actualEdges.contains(ab), is(false));
        assertThat(actualEdges.contains(bc), is(false));
        assertThat(actualEdges.contains(ca), is(true));
        
        
    }
    
    /**
 	 * Checks that an InvalidVertexException is thrown if we pass in null to removeVertex 
 	 */
 	@Test(expected=InvalidVertexException.class)
 	public void testRemoveVertexThrowsInvalidVertexException() {
 		 CS16Vertex<String> A = _dirGraph.insertVertex("A");
 		 _dirGraph.removeVertex(null);
 	}

    
 	/**
	 * Checks that removeVertex works on a undirected graph with 4 nodes.
	 */
    @Test(timeout = 10000)
    public void removeVertexTestUndirected() {
        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        CS16Vertex<String> D = _graph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> ac = _graph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _graph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _graph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _graph.insertEdge(C, D, 2);
        
        _graph.removeVertex(A);
        
        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(false));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
       
        
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> its = _graph.edges();
        while (its.hasNext()) {
            actualEdges.add(its.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(3));
        assertThat(actualEdges.contains(ab), is(false));
        assertThat(actualEdges.contains(ac), is(false));
        assertThat(actualEdges.contains(ad), is(false));
        assertThat(actualEdges.contains(bd), is(true));
        assertThat(actualEdges.contains(bc), is(true));
        assertThat(actualEdges.contains(cd), is(true));
      
        
    }
    
    
    /**
   	 * Checks that an InvalidEdgeException is thrown if we pass in null to removeEdge 
   	 */
   	@Test(expected=InvalidEdgeException.class)
   	public void testRmoveEdgeThrowsInvalidVertexException() {
   		 CS16Vertex<String> A = _dirGraph.insertVertex("A");
   		 CS16Vertex<String> B = _dirGraph.insertVertex("B");
   		 CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
   		 _dirGraph.removeEdge(null);
   	}

  
    
   	
   	/**
   	 *tests remove edge functionality for a directed graph. Ensures you can remove a directed edge
   	 * and the one going the other way remains in the adjacency matrix, as it should 
   	 */
    
    @Test(timeout = 10000)
    public void removeDirectedEdge() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> ba = _dirGraph.insertEdge(B, A, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(ba), is(true));
        
        _dirGraph.removeEdge(ab);
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _dirGraph.edges();
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges2.size(), is(1));
        assertThat(actualEdges2.contains(ab), is(false));
        assertThat(actualEdges2.contains(ba), is(true));
        
        
    }
    
    /**
   	 * Checks that an InvalidVertexException is thrown if we pass in null to connectingEdge 
   	 */
   	@Test(expected=InvalidVertexException.class)
   	public void testConnectingEdgeThrowsInvalidVertexException() {
   		 CS16Vertex<String> A = _dirGraph.insertVertex("A");
   		 CS16Vertex<String> B = _dirGraph.insertVertex("B");
   		 CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
   		 _dirGraph.connectingEdge(null,B);
   		 _dirGraph.connectingEdge(A,null);
   		 
   	}

    /**
   	 * Checks that an NoSuchEdgeException is thrown if there 
   	 * isn't an edge that connects the vertices in the correct direction
   	 */
   	@Test(expected=NoSuchEdgeException.class)
   	public void testConnectingEdgeThrowsNoSuchEdgeException() {
   		 CS16Vertex<String> A = _dirGraph.insertVertex("A");
   		 CS16Vertex<String> B = _dirGraph.insertVertex("B");
   		 CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
   		 _dirGraph.connectingEdge(B,A); 
   		 		 
   	}

   	/**
   	 * Checks connectedEdge functionality work as expected for a simple undirected graph with 2 nodes 
   	 */
    @Test(timeout = 10000)
    public void connectingEdgesUndirected() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
  		assertTrue(_graph.connectingEdge(A,B)==ab); 
  		assertTrue(_graph.connectingEdge(B,A)==ab); 
  		
       
    }
   	
   	
    /**
   	 * Checks that an InvalidVertexException is thrown if we pass in null to incomingEdges 
   	 */
   	@Test(expected=InvalidVertexException.class)
   	public void testIncoingEdgesThrowsInvalidVertexException() {
   		 _dirGraph.incomingEdges(null); 
   	}
   	
   	
    // checks incomingEdges is functional for undirected graph with 4 nodes 
    @Test(timeout = 10000)
    public void incomingEdgesUndirected() {

        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        CS16Vertex<String> D = _graph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> ac = _graph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _graph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _graph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _graph.insertEdge(C, D, 2);
        
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.incomingEdges(A);
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(3));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(ac), is(true));
        assertThat(actualEdges.contains(ad), is(true));
        assertThat(actualEdges.contains(bd), is(false));
        assertThat(actualEdges.contains(bc), is(false));
        assertThat(actualEdges.contains(cd), is(false));
        
        
    }
    

   	
   	
    // checks incomingEdges is functional for directed graph with four nodes 
    @Test(timeout = 10000)
    public void incomingEdgesDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ac = _dirGraph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _dirGraph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _dirGraph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _dirGraph.insertEdge(C, D, 2);
        
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.incomingEdges(A);
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(0));
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _dirGraph.incomingEdges(B);
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }
       
        assertThat(actualEdges2.size(), is(1));
        assertThat(actualEdges2.contains(ab), is(true));
        assertThat(actualEdges2.contains(ac), is(false));
        assertThat(actualEdges2.contains(ad), is(false));
        assertThat(actualEdges2.contains(bd), is(false));
        assertThat(actualEdges2.contains(bc), is(false));
        assertThat(actualEdges2.contains(cd), is(false));
  
    }
    
    /**
   	 * Checks that an InvalidVertexException is thrown if we pass in null to outgoingEdges 
   	 */
   	@Test(expected=InvalidVertexException.class)
   	public void testOutgoingEdgesThrowsInvalidVertexException() {
   		 _dirGraph.outgoingEdges(null); 
   	}
   	
   	
    // checks OutgoingEdges is functional for undirected graph 
    @Test(timeout = 10000)
    public void OutgoingEdgesUndirected() {

        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        CS16Vertex<String> D = _graph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> ac = _graph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _graph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _graph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _graph.insertEdge(C, D, 2);
        
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.outgoingEdges(A);
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(3));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(ac), is(true));
        assertThat(actualEdges.contains(ad), is(true));
        assertThat(actualEdges.contains(bd), is(false));
        assertThat(actualEdges.contains(bc), is(false));
        assertThat(actualEdges.contains(cd), is(false));
        
        
    }
    
    
    
    
    
    // checks outgoingEdges is functional for directed graph with four nodes 
    @Test(timeout = 10000)
    public void outgoingEdgesDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ac = _dirGraph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _dirGraph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _dirGraph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _dirGraph.insertEdge(C, D, 2);
        
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.outgoingEdges(A);
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(3));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(ac), is(true));
        assertThat(actualEdges.contains(ad), is(true));
        assertThat(actualEdges.contains(bd), is(false));
        assertThat(actualEdges.contains(bc), is(false));
        assertThat(actualEdges.contains(cd), is(false));
        
        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it2 = _dirGraph.outgoingEdges(B);
        while (it2.hasNext()) {
            actualEdges2.add(it2.next());
        }
       
        assertThat(actualEdges2.size(), is(2));
        assertThat(actualEdges2.contains(ab), is(false));
        assertThat(actualEdges2.contains(ac), is(false));
        assertThat(actualEdges2.contains(ad), is(false));
        assertThat(actualEdges2.contains(bd), is(true));
        assertThat(actualEdges2.contains(bc), is(true));
        assertThat(actualEdges2.contains(cd), is(false));

    }
    
    /**
   	 * Checks that an InvalidVertexException is thrown if we pass in null to numOutgoingEdges 
   	 */
   	@Test(expected=InvalidVertexException.class)
   	public void testnumOutgoingEdgesThrowsInvalidVertexException() {
   		 _dirGraph.numOutgoingEdges(null); 
   	}
   	
   	/**
   	 * Checks that an DirectionException is thrown if we try to call numOutgoingEdges on a directed graph  
   	 */
   	@Test(expected=DirectionException.class)
   	public void testnumOutgoingEdgesThrowsDirectionException() {
   		CS16Vertex<String> A = _graph.insertVertex("A");
   		_graph.numOutgoingEdges(A); 
   	}
   	

    // checks numOutgoingEdges is functional for directed graph with four nodes 
    @Test(timeout = 10000)
    public void numoutgoingEdgesDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ac = _dirGraph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _dirGraph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _dirGraph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _dirGraph.insertEdge(C, D, 2);
        
        assertThat(_dirGraph.numOutgoingEdges(A), is(3));
        assertThat(_dirGraph.numOutgoingEdges(B), is(2));
        assertThat(_dirGraph.numOutgoingEdges(C), is(1));
        assertThat(_dirGraph.numOutgoingEdges(D), is(0));

    }
    
   	/**
   	 * Checks that an InvalidVertexException is thrown if we pass in a null vertex to opposite 
   	 */
   	@Test(expected=InvalidVertexException.class)
   	public void testOppositeThrowsInvalidVertexException() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
   		_graph.opposite(null,ab);
   	}
   	
  	/**
   	 * Checks that an InvalidEdgeException is thrown if we pass in a null edge to opposite 
   	 */
   	@Test(expected=InvalidEdgeException.class)
   	public void testOppositeThrowsInvalidEdgeException() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
   		_graph.opposite(A,null);
   	}
   	
	/**
   	 * Checks that an NoSuchVertexEcvpetion is thrown if we pass in an edge that isn't incident to the vertex
   	 */
   	@Test(expected=NoSuchVertexException.class)
   	public void testOppositeThrowsNoSuchVertexException() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
   		_graph.opposite(C,ab);
   	}
   	
   	
    // checks opposite is functional for directed graph with four nodes 
    @Test(timeout = 10000)
    public void testOppositeDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ac = _dirGraph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _dirGraph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _dirGraph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _dirGraph.insertEdge(C, D, 2);
        
        assertThat(_dirGraph.opposite(A,ac), is(C));
        assertThat(_dirGraph.opposite(C,ac), is(A));

    }
    
 	
  	/**
   	 * Checks that an InvalidEdgeException is thrown if we pass in a null edge to endvertices 
   	 */
   	@Test(expected=InvalidEdgeException.class)
   	public void testendVerticesThrowsInvalidEdgeException() {
   		_graph.endVertices(null);
   		_dirGraph.endVertices(null);
   		
   	}
    
    // checks endvertices is functional for directed graph with four nodes
    @Test(timeout = 10000)
    public void testendVerticesDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ac = _dirGraph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _dirGraph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _dirGraph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _dirGraph.insertEdge(C, D, 2);
        
        List<CS16Vertex<String>> list = _dirGraph.endVertices(ac);
        assertThat(list.contains(A), is(true));
        assertThat(list.contains(C), is(true));
        assertThat(list.contains(D), is(false));
     

    }
    
    
 	/**
   	 * Checks that an InvalidVertexException is thrown if we pass in a null vertex to are adjacent 
   	 */
   	@Test(expected=InvalidVertexException.class)
   	public void testareAdjacentThrowsInvalidVertexException() {
   		CS16Vertex<String> A = _dirGraph.insertVertex("A");
   		_dirGraph.areAdjacent(null,A);
   		_dirGraph.areAdjacent(A,null);	
   	}
   	
   	
   	
   	
    // checks are adjacent is functional for directed graph with four nodes
    @Test(timeout = 10000)
    public void testareAdjacentDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        // add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ac = _dirGraph.insertEdge(A, C, 2);
        CS16Edge<String> ad = _dirGraph.insertEdge(A, D, 2);
        CS16Edge<String> bd = _dirGraph.insertEdge(B, D, 2);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> cd = _dirGraph.insertEdge(C, D, 2);
        
   
        assertThat(_dirGraph.areAdjacent(A,B), is(true));
        assertThat(_dirGraph.areAdjacent(B,A), is(false));
        assertThat(_dirGraph.areAdjacent(B,D), is(true));
        assertThat(_dirGraph.areAdjacent(D,B), is(false));

    }
    
    
    // checks clear is functional and the instance variables are reassigned successfully
    @Test(timeout = 10000)
    public void testClear() {

    	 CS16Vertex<String> A = _dirGraph.insertVertex("A");
         CS16Vertex<String> B = _dirGraph.insertVertex("B");
         CS16Vertex<String> C = _dirGraph.insertVertex("C");

         // add edges
         CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
         CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
         CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 2);
         
         _dirGraph.clear();
         // use the vertex iterator to get a list of the vertices in the actual
         // graph
         List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
         Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
         while (it.hasNext()) {
             actualVertices.add(it.next());
         }

         // assert that the graph state is consistent with what you expect
         assertThat(actualVertices.size(), is(0));

         
         // use the edge iterator to get a list of the edges in the actual graph.
         List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
         Iterator<CS16Edge<String>> its = _dirGraph.edges();
         while (its.hasNext()) {
             actualEdges.add(its.next());
         }

         // assert that the graph state is consistent with what you expect.
         assertThat(actualEdges.size(), is(0));         

    }
    
    
    // checks numVerticew is functional for directed graph 
    @Test(timeout = 10000)
    public void testgetnumVertices() {
    	assertThat(_dirGraph.getNumVertices(),is(0));
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        assertThat(_dirGraph.getNumVertices(),is(1));
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        assertThat(_dirGraph.getNumVertices(),is(2));
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        assertThat(_dirGraph.getNumVertices(),is(3));
        CS16Vertex<String> D = _dirGraph.insertVertex("D");
        assertThat(_dirGraph.getNumVertices(),is(4));
        _dirGraph.removeVertex(A);
        assertThat(_dirGraph.getNumVertices(),is(3));
        
    }
    
    /**
     *Tests that directed edges are removed properly. Removing a directed edge does not get rid of it's counterpart that goes the 
     * the other direction
     */
    
    
    @Test(timeout = 10000)
    public void testDirectedEdges() {
    	
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 2);
        CS16Edge<String> ba = _dirGraph.insertEdge(B, A, 1);
        
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> its = _dirGraph.edges();
        while (its.hasNext()) {
            actualEdges.add(its.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));         
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(ba), is(true));
        
        _dirGraph.removeEdge(ab);
        
        List<CS16Edge<String>> actualEdges2 = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> its2 = _dirGraph.edges();
        while (its2.hasNext()) {
            actualEdges2.add(its2.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges2.size(), is(1));         
        assertThat(actualEdges2.contains(ab), is(false));
        assertThat(actualEdges2.contains(ba), is(true));

        
    }
    
    // 
    @Test(timeout = 10000)
    public void testEdgetoself() {
    	
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Edge<String> aa = _dirGraph.insertEdge(A, A, 2);
       
        
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> its = _dirGraph.edges();
        while (its.hasNext()) {
            actualEdges.add(its.next());
        }
        
        assertThat(actualEdges.contains(aa),is(true));
        assertThat(_dirGraph.endVertices(aa).size(),is(2));
        assertThat(_dirGraph.endVertices(aa).get(0), is (A));
        assertThat(_dirGraph.endVertices(aa).get(1), is (A));
        
        
    }
    
    
    /*
     * List of graphs for testing!
     */
    @Parameters(name = "with graph: {0}")
    public static Collection<String> graphs() {
        List<String> names = new ArrayList<>();
        names.add("graph.AdjacencyMatrixGraph");
        return names;
    }
    
    /*
     * ####################################################
     * 
     * DO NOT MODIFY ANYTHING BELOW THIS LINE
     * 
     * ####################################################
     */
	
	
    @SuppressWarnings("unchecked")
    @Before
	public void makeGraph() {
        Class<?> graphClass = null;
        try {
            graphClass = Class.forName(_graphClassName);
            Constructor<?> constructor = graphClass.getConstructors()[0];
            _graph = (Graph<String>) constructor.newInstance(false);
	    _dirGraph = (Graph<String>) constructor.newInstance(true);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            System.err.println("Exception while instantiating Graph class in GraphTest.");
            e.printStackTrace();
        }
	}
	
    public GraphTest(String graphClassName) {
	    this._graphClassName = graphClassName;
	}
}
