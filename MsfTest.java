package graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.MinSpanForest;

/**
 * This class tests the functionality of your MSF algorithms on an AdjacencyMatrixGraph
 * with a 'String' type parameter for the vertices. Edge elements are Integers.
 * The general framework of a test case is: - Name the test method descriptively,
 * mentioning what is  being tested (it is ok to have slightly verbose method names here)
 * Set-up the program state (ex: instantiate a heap and insert K,V pairs into it) - Use
 * assertions to validate that the program is in the state you expect it to be
 * See header comments over tests for what each test does
 * 
 * Before each test is run, you can assume that the '_graph' variable is reset to
 * a new instance of the a Graph<String> that you can simply use 'as is', as
 * well as the '_msf' variable.
 *
 * Of course, please do not modify anything below the 'DO NOT MODIFY ANYTHING BELOW THIS LINE'
 * line, or the above assumptions may be broken.
 */
@RunWith(Parameterized.class)
public class MsfTest {

    private String _msfClassName;
    private MinSpanForest<String> _msf;
    private Graph<String> _graph;
    
    
    /**
     * simple test that tests the functionality of the algorithm on a graph with 3 nodes 
     * 
     */
    @Test
    public void simpleTest() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 1);
        CS16Edge<String> ca = _graph.insertEdge(A, C, 10);
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(2));
        assertThat(MSF.contains(ab), is(true));
        assertThat(MSF.contains(bc), is(true));
        assertThat(MSF.contains(ca), is(false));
    }
    
    /**
     * My own simple test for a graph with 3 nodes 
     * 
     */
    @Test
    public void mySimpleTest() {
    	 CS16Vertex<String> A = _graph.insertVertex("A");
         CS16Vertex<String> B = _graph.insertVertex("B");
         CS16Vertex<String> C = _graph.insertVertex("C");

         CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
         CS16Edge<String> bc = _graph.insertEdge(B, C, 1);
         CS16Edge<String> ca = _graph.insertEdge(A, C, 10);
         Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

         assertThat(MSF.size(), is(2));
         assertThat(MSF.contains(ab), is(true));
         assertThat(MSF.contains(bc), is(true));
         assertThat(MSF.contains(ca), is(false));
    	
    }
    
    
    /**
     * test on a more complicated graph with 5 nodes and 9 edges 
     * 
     */
    @Test
    public void complexTest() {
    	 CS16Vertex<String> A = _graph.insertVertex("A");
         CS16Vertex<String> B = _graph.insertVertex("B");
         CS16Vertex<String> C = _graph.insertVertex("C");
         CS16Vertex<String> D = _graph.insertVertex("D");
         CS16Vertex<String> E = _graph.insertVertex("E");

         CS16Edge<String> ab = _graph.insertEdge(A, B, 15);
         CS16Edge<String> ae = _graph.insertEdge(A, E, 1);
         CS16Edge<String> ac = _graph.insertEdge(A, C, 9);
         CS16Edge<String> be = _graph.insertEdge(B, E, 18);
         CS16Edge<String> ec = _graph.insertEdge(E, C, 4);
         CS16Edge<String> bd = _graph.insertEdge(B, D, 6);
         CS16Edge<String> ed = _graph.insertEdge(E, D, 11);
         CS16Edge<String> cd = _graph.insertEdge(D, C, 23);
   
         Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

         assertThat(MSF.size(), is(4));
         assertThat(MSF.contains(ab), is(false));
         assertThat(MSF.contains(ae), is(true));
         assertThat(MSF.contains(ac), is(false));
         assertThat(MSF.contains(be), is(false));
         assertThat(MSF.contains(ec), is(true));
         assertThat(MSF.contains(bd), is(true));
         assertThat(MSF.contains(ed), is(true));
         assertThat(MSF.contains(cd), is(false));
    	
    }
    
    
    /**
     * tests that algorithm works when there's one connected piece with other random nodes that are unconnected to each other and
     * unconnected to the main connected piece of the graph
     */
    @Test
    public void unConnectedTest() {
    	 CS16Vertex<String> A = _graph.insertVertex("A");
         CS16Vertex<String> B = _graph.insertVertex("B");
         CS16Vertex<String> C = _graph.insertVertex("C");
         CS16Vertex<String> D = _graph.insertVertex("D");
         CS16Vertex<String> E = _graph.insertVertex("E");
         CS16Vertex<String> F = _graph.insertVertex("F");
         CS16Vertex<String> G = _graph.insertVertex("G");

         CS16Edge<String> ab = _graph.insertEdge(A, B, 15);
         CS16Edge<String> ae = _graph.insertEdge(A, E, 1);
         CS16Edge<String> ac = _graph.insertEdge(A, C, 9);
         CS16Edge<String> be = _graph.insertEdge(B, E, 18);
         CS16Edge<String> ec = _graph.insertEdge(E, C, 4);
         CS16Edge<String> bd = _graph.insertEdge(B, D, 6);
         CS16Edge<String> ed = _graph.insertEdge(E, D, 11);
         CS16Edge<String> cd = _graph.insertEdge(D, C, 23);
   
         Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

         assertThat(MSF.size(), is(4));
         assertThat(MSF.contains(ab), is(false));
         assertThat(MSF.contains(ae), is(true));
         assertThat(MSF.contains(ac), is(false));
         assertThat(MSF.contains(be), is(false));
         assertThat(MSF.contains(ec), is(true));
         assertThat(MSF.contains(bd), is(true));
         assertThat(MSF.contains(ed), is(true));
         assertThat(MSF.contains(cd), is(false));
    	
    }
    
    
    
    
    
    
    //tests functionality for two separate connected graphs 
    @Test
    public void unConnectedTest2() {
    	 CS16Vertex<String> A = _graph.insertVertex("A");
         CS16Vertex<String> B = _graph.insertVertex("B");
         CS16Vertex<String> C = _graph.insertVertex("C");
         CS16Vertex<String> D = _graph.insertVertex("D");
         CS16Vertex<String> E = _graph.insertVertex("E");
         
         
         CS16Vertex<String> F = _graph.insertVertex("F");
         CS16Vertex<String> G = _graph.insertVertex("G");
         CS16Vertex<String> H = _graph.insertVertex("H");

         CS16Edge<String> ab = _graph.insertEdge(A, B, 15);
         CS16Edge<String> ae = _graph.insertEdge(A, E, 1);
         CS16Edge<String> ac = _graph.insertEdge(A, C, 9);
         CS16Edge<String> be = _graph.insertEdge(B, E, 18);
         CS16Edge<String> ec = _graph.insertEdge(E, C, 4);
         CS16Edge<String> bd = _graph.insertEdge(B, D, 6);
         CS16Edge<String> ed = _graph.insertEdge(E, D, 11);
         CS16Edge<String> cd = _graph.insertEdge(D, C, 23);
         CS16Edge<String> fg = _graph.insertEdge(F, G, 21);
         CS16Edge<String> fh = _graph.insertEdge(F, H, 9);
         CS16Edge<String> hg = _graph.insertEdge(H, G, 3);
   
         Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

         assertThat(MSF.size(), is(6));
         assertThat(MSF.contains(ab), is(false));
         assertThat(MSF.contains(ae), is(true));
         assertThat(MSF.contains(ac), is(false));
         assertThat(MSF.contains(be), is(false));
         assertThat(MSF.contains(ec), is(true));
         assertThat(MSF.contains(bd), is(true));
         assertThat(MSF.contains(ed), is(true));
         assertThat(MSF.contains(cd), is(false));
         
         assertThat(MSF.contains(fg), is(false));
         assertThat(MSF.contains(fh), is(true));
         assertThat(MSF.contains(hg), is(true));
    }
    
    
    
    /**
     * test that you can run the algorithm, add more things to your and then run the algorithm again
     * 
     */
    @Test
    public void addMoreTest() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        

        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 1);
        CS16Edge<String> ca = _graph.insertEdge(A, C, 10);
        Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);

        assertThat(MSF.size(), is(2));
        assertThat(MSF.contains(ab), is(true));
        assertThat(MSF.contains(bc), is(true));
        assertThat(MSF.contains(ca), is(false));
        
        CS16Vertex<String> D = _graph.insertVertex("D");
        CS16Edge<String> ad = _graph.insertEdge(A, D, 5);
    
        Collection<CS16Edge<String>> MSF2 = _msf.genMinSpanForest(_graph, null);
        
        assertThat(MSF2.size(), is(3));
        assertThat(MSF2.contains(ab), is(true));
        assertThat(MSF2.contains(bc), is(true));
        assertThat(MSF2.contains(ca), is(false));
        assertThat(MSF2.contains(ad), is(true));
        
        CS16Vertex<String> E = _graph.insertVertex("E");
        
        CS16Edge<String> cd = _graph.insertEdge(C, D, 3);
        CS16Edge<String> de = _graph.insertEdge(E, D, 2);
        
        Collection<CS16Edge<String>> MSF3 = _msf.genMinSpanForest(_graph, null);
        
        assertThat(MSF3.size(), is(4));
        assertThat(MSF3.contains(ab), is(true));
        assertThat(MSF3.contains(bc), is(true));
        assertThat(MSF3.contains(ca), is(false));
        assertThat(MSF3.contains(ad), is(false));
        assertThat(MSF3.contains(cd), is(true));
        assertThat(MSF3.contains(de), is(true));

        
    }
    
    
    
    /** 
     * tessts that algorithm works for a single node
     * 
     */
    @Test
    public void singleNodeTest() {
    	CS16Vertex<String> A = _graph.insertVertex("A");
    	Collection<CS16Edge<String>> MSF = _msf.genMinSpanForest(_graph, null);
        assertThat(MSF.size(), is(0));
    }
    
    
    
    
    
    
    /*
     * This is the method that, using junit magic, provides the list of MSF algorithms
     * that should be created and be tested via the methods above.
     * By default, all of the above tests will be run on MyPrimJarnik algorithm implementations.
     * If you're interested in testing the methods on just one of the
     * algorithms, comment out the one you don't want in the method below!
     */
    @Parameters(name = "with msf algo: {0}")
    public static Collection<String> msts() {
        List<String> algoNames = new ArrayList<>();
        algoNames.add("graph.MyPrimJarnik");
        return algoNames;
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
    public void setup() {
        Class<?> msfClass = null;
        try {
            msfClass = Class.forName(_msfClassName);
            Constructor<?> constructor = msfClass.getConstructors()[0];
            _msf = (MinSpanForest<String>) constructor.newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException
                | IllegalArgumentException e) {
            System.err.println("Exception while instantiating msf class " + _msfClassName + " from test.");
            e.printStackTrace();
        }
        _graph = new AdjacencyMatrixGraph<>(false);
    }

    public MsfTest(String msfClassName) {
        _msfClassName = msfClassName;
    }
}
