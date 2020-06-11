package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.lang.Math;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.PageRank;

/**
 * In this class you will implement one of many different versions
 * of the PageRank algorithm. This algorithm will only work on
 * directed graphs. Keep in mind that there are many different ways
 * to handle sinks.
 *
 * Make sure you review the help slides and handout for details on
 * the PageRank algorithm.
 *
 */
public class MyPageRank<V> implements PageRank<V> {
	private Graph<V> _g;
	private Map<CS16Vertex<V>, Double> _vertsToRanks;	
	private List<CS16Vertex<V>> _vertices;
	private List<CS16Vertex<V>> _sinks;
	private Double[] _prevRanks;
	private Double[] _currRanks;
	private int _iterations;	
	private static final double _dampingFactor = 0.85;
	private static final int _maxIterations = 100;
	private static final double _error = 0.01;
	

	/**
	 * TODO: Feel free to add in anything else necessary to store the information
	 * needed to calculate the rank. Maybe make something to keep track of sinks,
	 * your ranks, and your outgoing edges?
	 */

	/**
	 * The main method that does the calculations! You'll want to call the methods
	 * that initialize your variables here. You'll also want to decide on a
	 * type of loop - for loop, do while, or while loop - for your calculations.
	 *
	 * @return A Map of every Vertex to its corresponding rank
	 *
	 */
	@Override
	public Map<CS16Vertex<V>, Double> calcPageRank(Graph<V> g) {
		// TODO: initialize private instance variables
		_g = g;
		_vertsToRanks = new HashMap<CS16Vertex<V>, Double>();		
		Iterator<CS16Vertex<V>> it = _g.vertices();
		//use the iterrator to put the vertices into an arraylist so the order stays constant
		_vertices = new ArrayList<CS16Vertex<V>>();
		while (it.hasNext()) {
    		CS16Vertex<V> v = it.next();
    		_vertices.add(v);	
    	}				
		_sinks = new ArrayList<CS16Vertex<V>>();		
		//add vertices that have no outgoing edges to the _sinks arraylist
		for (int i=0; i<_vertices.size(); i++) {
			CS16Vertex<V> v = _vertices.get(i);		
			ArrayList<CS16Edge<V>> outEdges= this.getOutgoingEdges(v);		
			if (outEdges.size()==0) {
				_sinks.add(v);				
			}
		}		
		_currRanks = new Double[_vertices.size()];
		_prevRanks = new Double[_vertices.size()];		
		//this.removeBlacklist();
		//create an edge between every sink and every other node 
		this.handleSinks();
		//initialize current ranks to 1/number of vertices in the graph
		for (int i=0; i<_vertices.size(); i++) {
			_currRanks[i] = 1.0/_vertices.size();
		}
		//goes through loop once and then while the conditions below are true
		do {
			//set the previous ranks to the current ranks
			for (int i=0; i<_vertices.size(); i++) {
				_prevRanks[i] = _currRanks[i];
			}
			//for each vertex
			for (int i=0; i<_vertices.size(); i++) {
				//clear the current ranks
				_currRanks[i] = 0.0;
				//get the incoming edges 
				ArrayList<CS16Edge<V>> inEdges = this.getIncomingEdges(_vertices.get(i));				
				//for each incoming edge
				for (int j=0; j<inEdges.size(); j++) {
					//get the opposite vertex 
					CS16Vertex<V> u = _g.opposite(_vertices.get(i),inEdges.get(j));
					//get the index for u
					int index = _vertices.indexOf(u);
					//add up the page rank from that neighbor
					_currRanks[i] = _currRanks[i] + ((_dampingFactor)*(_prevRanks[index]/this.getOutgoingEdges(u).size()));
				}
				//adds PR that every vertex gets from every other vertex
				_currRanks[i] = _currRanks[i] + (1-_dampingFactor)/(_vertices.size());
			}
			_iterations++;			
		//continue the while loop until PR has converged or 100 iterations has been reached		
		} while ((this.hasConverged()==false) && (_iterations<_maxIterations));
		//put the vertices and their respective ranks into the hashmap that will be returned 
		for (int i=0; i<_vertices.size(); i++) {
			_vertsToRanks.put(_vertices.get(i),_currRanks[i]);
		}	
		return _vertsToRanks;
	}

	/**
	 * Method used to account for sink pages (those with no outgoing
	 * edges). There are multiple ways you can implement this, check
	 * the lecture and help slides!
	 */
	private void handleSinks() {
		// TODO: Fill this in
		//for each sink
		for (int i=0; i<_sinks.size(); i++) {
			//for each vertex
			for (int j=0; j<_vertices.size(); j++) {
				//insert an edge between every sink and every other vertex including itself
				_g.insertEdge(_sinks.get(i), _vertices.get(j), null);
			}
		}
	}

	/**
	 * helper method that checks if the PageRank has converged. Returns true only if PR has converged
	 * @return boolean 
	 */
	private boolean hasConverged() {
		//for each vertex
		for (int i=0; i<_vertices.size(); i++) {
			//if the difference between the current rank and the previous rank is greater than the 0.1
			if (Math.abs(_prevRanks[i] - _currRanks[i]) >= _error) {
				return false;
			}
		}
		//if makes it through the whole loop then every difference between prev and curr is smaller than 0.1, so PR has converged
		return true;
	}
	
	/**
	 * This helper method takes in a vertex and returns an arraylist of the vertex's outgoing edges
	 * @param v vertex in the graph
	 * @return arraylist of outgoing edges for that vertex
	 */
	private ArrayList<CS16Edge<V>> getOutgoingEdges(CS16Vertex<V> v) {
		Iterator<CS16Edge<V>> it = _g.outgoingEdges(v);		
		ArrayList<CS16Edge<V>> outEdges= new ArrayList<CS16Edge<V>>();
		//use the iterator to loop through the outgoing edges
		while (it.hasNext()) {
    		CS16Edge<V> e = it.next();
    		outEdges.add(e);	
    	}
		return outEdges;
	}
	
	/**
	 * This helper method takes in a vertex and returns an arraylist of the vertex's incoming edges
	 * @param v vertex in the graph
	 * @return arraylist of incoming edges for that vertex
	 */
	private ArrayList<CS16Edge<V>> getIncomingEdges(CS16Vertex<V> v) {
		Iterator<CS16Edge<V>> it = _g.incomingEdges(v);
		ArrayList<CS16Edge<V>> inEdges= new ArrayList<CS16Edge<V>>();
		while (it.hasNext()) {
    		CS16Edge<V> e = it.next();
    		inEdges.add(e);	
    	}
		return inEdges;
	}
	
	/**
	 * this method is for the Extra Credit. It removes the incoming edges from each page that's in the list.
	 * The call for this method is currenlty commented out in the calcPageRank body because I wasn't sure 
	 * if we were supposed to include it. 
	 * 
	 */
	private void removeBlacklist() {	
		Set<String> blackSet = PageRank.blacklist;
		Iterator<String> it = blackSet.iterator();
		ArrayList<String> list = new ArrayList<String>();
		//use the iterator of the blacklist set to add the pages to an arraylist so the pages order won't change 
		while(it.hasNext()){
			String page = it.next();
			list.add(page);			
		}
		ArrayList<CS16Vertex<V>> vertices = new ArrayList<CS16Vertex<V>>();	    
		//for each page in the blacklist of pages
		for (int i=0; i<list.size(); i++) {
			//for each vertex
			for (int j=0; j<_vertices.size(); j++) {
				String str1 = list.get(i);
				String str2 = _vertices.get(j).getVertexName();
				//if the vertex has the same name as one of the pages in the list of blacklisted pages, add it to the vertices arraylist
				if (str1.equals(str2)) {
					vertices.add(_vertices.get(j));
				}
			}
		}		
		//for each vertex that's blacklisted, remove it's incoming edges
		for (int i=0; i<vertices.size(); i++) {
			ArrayList<CS16Edge<V>> inEdges = this.getIncomingEdges(vertices.get(i));
			for (int j=0; j<inEdges.size(); j++) {
				_g.removeEdge(inEdges.get(j));
			}				
		}	    	     
	}

}
