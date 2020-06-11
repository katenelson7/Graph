package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import net.datastructures.Entry;
import support.graph.CS16AdaptableHeapPriorityQueue;
import support.graph.CS16Edge;
import support.graph.CS16GraphVisualizer;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.MinSpanForest;

/**
 * In this class you will implement a slightly modified version
 * of the Prim-Jarnik algorithm for generating Minimum Spanning trees.
 * The original version of this algorithm will only generate the 
 * minimum spanning tree of the connected vertices in a graph, given
 * a starting vertex. Like Kruskal's, this algorithm can be modified to 
 * produce a minimum spanning forest with very little effort.
 *
 * See the handout for details on Prim-Jarnik's algorithm.
 * Like Kruskal's algorithm this algorithm makes extensive use of 
 * the decorator pattern, so make sure you know it.
 */
public class MyPrimJarnik<V> implements MinSpanForest<V> {
	

    /** 
     * This method implements Prim-Jarnik's algorithm and extends 
     * it slightly to account for disconnected graphs. You must return 
     * the collection of edges of the Minimum Spanning Forest (MSF) for 
     * the given graph, g.
     * 
     * This algorithm must run in O((|E| + |V|)log(|V|)) time
     * @param g Your graph
     * @param v Only used if you implement the optional animation.
     * @return returns a data structure that contains the edges of your MSF that implements java.util.Collection
     */
    @Override
    public Collection<CS16Edge<V>> genMinSpanForest(Graph<V> g, CS16GraphVisualizer<V> visualizer) {
    	//instantiate decorators 
    	MyDecorator<CS16Vertex<V>,Integer> costDecorator = new MyDecorator<CS16Vertex<V>,Integer>();
    	MyDecorator<CS16Vertex<V>,CS16Vertex<V>> prevDecorator = new MyDecorator<CS16Vertex<V>,CS16Vertex<V>>();
    	MyDecorator<CS16Vertex<V>,Boolean> visitedDecorator = new MyDecorator<CS16Vertex<V>,Boolean>();
    	MyDecorator<CS16Vertex<V>,Entry<Integer,CS16Vertex<V>>> entryDecorator = new MyDecorator<CS16Vertex<V>,Entry<Integer,CS16Vertex<V>>>();
    	Iterator<CS16Vertex<V>> vertices = g.vertices();
    	//use the iterator for the vertices to loop through the vertices 
    	while (vertices.hasNext()) {
    		///initialize values for various decorations
    		CS16Vertex<V> v = vertices.next();
    		costDecorator.setDecoration(v,Integer.MAX_VALUE);
    		prevDecorator.setDecoration(v,null);
    		visitedDecorator.setDecoration(v,false);
    		entryDecorator.setDecoration(v,null);    		
    	}
    	//since the iterator has no more nexts since it loop through all of the vertices, reset the iterator
    	vertices = g.vertices();
    	//get the next vertex, which will be a random vertex, and set it as the source
    	CS16Vertex<V> source = vertices.next();
    	//set it's cost decoration to 0
    	costDecorator.replaceDecoration(source, 0);    	
    	ArrayList<CS16Edge<V>> MST = new ArrayList<CS16Edge<V>>();
       	CS16AdaptableHeapPriorityQueue<Integer,CS16Vertex<V>> PQ = new CS16AdaptableHeapPriorityQueue<Integer,CS16Vertex<V>>();    	
    	//put all vertices into PQ
    	vertices = g.vertices();
    	while (vertices.hasNext()) {
    		CS16Vertex<V> v = vertices.next();
    		Entry<Integer,CS16Vertex<V>> entry = PQ.insert(costDecorator.getDecoration(v),v);
    		//update the entry decoration for all vertices 
    		entryDecorator.setDecoration(v,entry);
    	}  
    	//while the PQ is not empty
    	while (PQ.isEmpty()==false) {    		
    		//get the entry from the PQ that has the minimum key
    		Entry<Integer,CS16Vertex<V>> entry = PQ.removeMin();    	
    		//get the value from the entry, which is the vertex
    		CS16Vertex<V> v = entry.getValue();
    		//update the visited decoration to mark the vertex as visited after it's popped off the PQ
    		visitedDecorator.replaceDecoration(v,true);   		
    		//If the previous decoration for this vertex isn't null, add it to the MST
    		if (prevDecorator.getDecoration(v)!=null) {
    			MST.add(g.connectingEdge(v,prevDecorator.getDecoration(v)));
    		}    		
    		ArrayList<CS16Edge<V>> incidentEdges = this.getIncidentEdges(g, v, visitedDecorator);
    		//for each incident edge that v has 
    		for (int i=0; i<incidentEdges.size();i++) {
    			//get the vertex opposite to v
    			CS16Vertex<V> u = g.opposite(v,incidentEdges.get(i));
    			//if the cost of u > the weight for the incident edge connected v to u
    			if (costDecorator.getDecoration(u) > incidentEdges.get(i).element()) {
    				//replace teh cost of u with the weight of the edge connecting v and u
    				costDecorator.replaceDecoration(u, incidentEdges.get(i).element());
    				//set the previous decoration for u to be v 
    				prevDecorator.replaceDecoration(u,v);
    				//update the key for u's entry in the PQ
    				PQ.replaceKey(entryDecorator.getDecoration(u), costDecorator.getDecoration(u));   				
    			}
    		}  		
    	}
    	return MST;       
      }
    

    /**
     * This method is used to get the the edges that are incident to v where the vertex opposite of v is still in the PQ.
     * @param g Your graph
     * @param v A vertex in your graph 
     * @param visitedDecorator that's instantiated in the main method
     * @return an ArrayList that includes the incident edges 
     */
	public ArrayList<CS16Edge<V>> getIncidentEdges(Graph<V> g, CS16Vertex<V> v, MyDecorator<CS16Vertex<V>,Boolean> visitedDecorator) {
    	ArrayList<CS16Edge<V>> incidentEdges = new ArrayList<CS16Edge<V>>();
    	Iterator<CS16Edge<V>> edges = g.incomingEdges(v);
    	//use the iterator to loop through the incoming edges
    	while (edges.hasNext()) {
    		CS16Edge<V> e = edges.next();
    		//get the vertex opposite v
    		CS16Vertex<V> oppVertex = g.opposite(v,e);
    		//if the visited decorator for the opposite vertex is false, so it's still in the PQ
    		if (visitedDecorator.getDecoration(oppVertex)==false) {
    			//add it to the incident edges array
    			incidentEdges.add(e);
    		}
    	}
		return incidentEdges;
	}


}

