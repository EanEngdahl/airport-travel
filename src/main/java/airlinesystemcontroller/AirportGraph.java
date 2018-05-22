package airlinesystemcontroller;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import java.util.HashMap;

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

	
}