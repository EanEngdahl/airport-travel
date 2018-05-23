package airlinesystemcontroller;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.util.HashMap;
import java.util.Iterator;

import airlinesystemmodel.Airport;


public class AirportGraph {
	
	Graph<String, DefaultEdge> graphOfAirports;
	
	public AirportGraph() {
		graphOfAirports = new SimpleWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
	}
	
	public void addAirport(Airport airport_) {
		graphOfAirports.addVertex(airport_.getName());
	}
	/*
	 *  TODO: Try creating temp DefualtEdge
	 */
	public void createEdge(String source_, String destination_, double distance_) {
		graphOfAirports.addEdge(source_, destination_);
		graphOfAirports.setEdgeWeight(graphOfAirports.getEdge(source_, destination_), distance_);
	}
	
	public double getDistance(String source_, String destination_) {
		return graphOfAirports.getEdgeWeight(graphOfAirports.getEdge(source_, destination_));
	}

	public void removeEdge(String source_, String destination_) {
		graphOfAirports.removeEdge(source_, destination_);
	}

	public void removeAirport(String airport_) {
		graphOfAirports.removeVertex(airport_);
	}

	public boolean isAirportInGraph(String airport_) {
		return graphOfAirports.containsVertex(airport_);
	}
	
	public boolean areAirportsConnected(String source_, String destination_) {
		return graphOfAirports.containsEdge(source_, destination_);
	}
	
	public Airport getAirport(String airportName_, HashMap<String, Airport> nameMap_) {
		return nameMap_.get(airportName_);
	}
	//TODO temporary method using system out for testing graph, remove later
	public void printGraph() {
		System.out.println(graphOfAirports.toString());
		Iterator<String> _vertexItr = graphOfAirports.vertexSet().iterator();
		Iterator<DefaultEdge> _edgeItr;
		String _vertex;
		String _destinationVertex;
		DefaultEdge _edgeTracker;
		while(_vertexItr.hasNext()) {
			_vertex = _vertexItr.next();
			System.out.print("Vertex: " + _vertex);
			_edgeItr = graphOfAirports.edgesOf(_vertex).iterator();
			while (_edgeItr.hasNext()) {
				_edgeTracker = _edgeItr.next();
				_destinationVertex = graphOfAirports.getEdgeTarget(_edgeTracker);
				if(_destinationVertex.equals(_vertex)) {
					_destinationVertex = graphOfAirports.getEdgeSource(_edgeTracker);
				}
				System.out.print("-> " + _destinationVertex + "(" + graphOfAirports.getEdgeWeight(_edgeTracker) + ")");
			}
			System.out.println();
		}
		System.out.println("A and B connected: " + areAirportsConnected("A", "B") 
		+ "\nB and A connected: " + areAirportsConnected("B", "A")
		+ "\nD and A connected: " + areAirportsConnected("D", "A")
		+ "\nA and D connected: " + areAirportsConnected("A", "D")
		+ "\nD and B connected: " + areAirportsConnected("D", "B")
		+ "\nB and D connected: " + areAirportsConnected("B", "D")
		+ "\nD and C connected: " + areAirportsConnected("D", "C")
		+ "\nC and D connected: " + areAirportsConnected("C", "D")
		+ "\nD and D connected: " + areAirportsConnected("D", "D"));
	}
}