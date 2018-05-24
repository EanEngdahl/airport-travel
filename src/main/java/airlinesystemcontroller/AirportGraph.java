/*
 * AirportGraph class
 *		Creates an undirected weighted graph that allows 
 *		changes to the graph such as adding airports (vertexes),
 *		adding flights (edges), removing airports or flights.
 *		and finding information about the graph
 */

package airlinesystemcontroller;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;

import airlinesystemmodel.Airport;


public class AirportGraph {
	
	Graph<String, DefaultEdge> graphOfAirports;
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");

	/*
	 * Constructor, initializes graph
	 */
	public AirportGraph() {
		graphOfAirports = new SimpleWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
	}
	
	/*
	 * Add a new airport by creating a new vertex on the graph
	 * based on the airport object input
	 */
	public void addAirport(Airport airport_) {
		graphOfAirports.addVertex(airport_.getName());
	}
	
	/*
	 *  TODO: Try creating temp DefualtEdge
	 */
	public boolean createEdge(String source_, String destination_, double distance_) {		
		if (source_.equals(destination_) || areAirportsConnected(source_, destination_)
				|| distance_ <= 0) {
			consoleLogger.debug("Invalid graph input found, input ignored.");
			return false;
		}
		graphOfAirports.addEdge(source_, destination_);
		graphOfAirports.setEdgeWeight(graphOfAirports.getEdge(source_, destination_), distance_);
		return true;
	}
	
	/*
	 * Return distance between two airports in the graph
	 * only if they are connected, otherwise return 0
	 */
	public double getDistance(String source_, String destination_) {
		if (areAirportsConnected(source_, destination_)) {
			return graphOfAirports.getEdgeWeight(graphOfAirports.getEdge(source_, destination_));
		}
		return 0;
	}

	/*
	 * Remove a connection between two airports
	 * only if there exists a connection
	 */
	public void removeEdge(String source_, String destination_) {
		graphOfAirports.removeEdge(source_, destination_);
	}

	/*
	 * Remove an airport from the graph and all connections
	 * only if it exists
	 */
	public void removeAirport(String airport_) {
		graphOfAirports.removeVertex(airport_);
	}

	/*
	 * Returns true if airport is found in graph as vertex,
	 * otherwise returns false
	 */
	public boolean isAirportInGraph(String airport_) {
		return graphOfAirports.containsVertex(airport_);
	}
	
	/*
	 * Returns true if two airports are connected,
	 * otherwise returns false
	 */
	public boolean areAirportsConnected(String source_, String destination_) {
		return graphOfAirports.containsEdge(source_, destination_);
	}
	
	/*
	 * Returns airport object taken from hash map
	 * that is based on the name
	 */
	public Airport getAirport(String airportName_, HashMap<String, Airport> nameMap_) {
		return nameMap_.get(airportName_);
	}
	//TODO temporary method using logging to console for testing graph, remove later
	public void printGraph() {
		consoleLogger.info(graphOfAirports.toString());
		Iterator<String> _vertexItr = graphOfAirports.vertexSet().iterator();
		Iterator<DefaultEdge> _edgeItr;
		String _vertex;
		String _destinationVertex;
		DefaultEdge _edgeTracker;
		while(_vertexItr.hasNext()) {
			_vertex = _vertexItr.next();
			consoleLogger.info("Vertex: " + _vertex);
			_edgeItr = graphOfAirports.edgesOf(_vertex).iterator();
			while (_edgeItr.hasNext()) {
				_edgeTracker = _edgeItr.next();
				_destinationVertex = graphOfAirports.getEdgeTarget(_edgeTracker);
				if(_destinationVertex.equals(_vertex)) {
					_destinationVertex = graphOfAirports.getEdgeSource(_edgeTracker);
				}
				consoleLogger.info("-> " + _destinationVertex + "(" 
				+ graphOfAirports.getEdgeWeight(_edgeTracker) + ")");
			}
		}
		/* Some old testing
		System.out.println("A and B connected: " + areAirportsConnected("A", "B") 
		+ "\nB and A connected: " + areAirportsConnected("B", "A")
		+ "\nD and A connected: " + areAirportsConnected("D", "A")
		+ "\nA and D connected: " + areAirportsConnected("A", "D")
		+ "\nD and B connected: " + areAirportsConnected("D", "B")
		+ "\nB and D connected: " + areAirportsConnected("B", "D")
		+ "\nD and C connected: " + areAirportsConnected("D", "C")
		+ "\nC and D connected: " + areAirportsConnected("C", "D")
		+ "\nD and D connected: " + areAirportsConnected("D", "D"));
		*/
	}
}