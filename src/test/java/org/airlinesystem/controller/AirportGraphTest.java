package org.airlinesystem.controller;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import org.airlinesystem.controller.AirportGraph;
import org.airlinesystem.model.Airport;
import org.jgrapht.graph.DefaultEdge;


public class AirportGraphTest {

	//private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	AirportGraph _graphOfAirports = new AirportGraph();
	
	@BeforeClass
	public void initialize() {
		_graphOfAirports.addAirport(new Airport("A"));
		_graphOfAirports.addAirport(new Airport("B"));
		_graphOfAirports.addAirport(new Airport("D"));
		_graphOfAirports.addAirport(new Airport("E"));
	}
	
	@Test
	public void testEdgeFunctions() {
		assertTrue("Creating edge from A to B gives true", _graphOfAirports.createEdge("A", "B", 7));
		assertTrue("There is an edge from A to B", _graphOfAirports.areAirportsConnected("A", "B"));
		assertEquals("Distance from A to B is 7.0", 7.0, _graphOfAirports.getDistance("A", "B"), 0.01);
		assertEquals("Distance from A to D is 0.0", _graphOfAirports.getDistance("A", "D"), 0.0, 0.01);
		_graphOfAirports.removeEdge("A", "B");
		assertFalse("There is no edge from A to B", _graphOfAirports.areAirportsConnected("A", "B"));
		assertEquals("After removing edge, distance between A and B is 0.0", _graphOfAirports.getDistance("A", "B"), 0.0, 0.01);
		
		assertTrue("There should be an edge added from A to D", _graphOfAirports.createEdge("D", "A", 37));
		assertFalse("There should be no new edge allowed between A and D", 
				_graphOfAirports.createEdge("A", "D", 12));
		assertEquals("Distance between A and D should be 37.0", _graphOfAirports.getDistance("A", "D"),
				37.0, 0.01);
		_graphOfAirports.removeAirport("D");
		assertFalse("D should no longer be in the graph", _graphOfAirports.isAirportInGraph("D"));
		assertFalse("D and A should not be connected", _graphOfAirports.areAirportsConnected("D", "A"));
		assertFalse("Should not be able to make an edge with the same airport as source and destination",
				_graphOfAirports.createEdge("A", "A", 5));
		assertFalse("Should not create an otherwise valid edge if weight is negative",
				_graphOfAirports.createEdge("A", "B", -2));
	}
	
	@Test
	public void testAirportFunctions() {
		assertTrue("Airport A is in graph",_graphOfAirports.isAirportInGraph("A"));
		assertFalse("Airport C is not in graph",_graphOfAirports.isAirportInGraph("C"));
		_graphOfAirports.addAirport(new Airport("C"));
		assertTrue("Airport C is now in graph after adding it",_graphOfAirports.isAirportInGraph("C"));
		
		assertFalse("Airport should not be connected to itself", 
				_graphOfAirports.areAirportsConnected("A", "A"));
		assertEquals("Hashmap should return airport with correct name field", "A", 
				_graphOfAirports.getAirport("A").getName());
		
	}
	
	@Test
	public void testGetSortedListOfEdges() {
		_graphOfAirports.createEdge("A", "B", 5);
		_graphOfAirports.createEdge("A", "D", 3);
		_graphOfAirports.createEdge("B", "D", 4);
		_graphOfAirports.createEdge("A", "E", 1);
		_graphOfAirports.createEdge("D", "E", 2);
		
		ArrayList<DefaultEdge>	_sortedEdges;
		
		_sortedEdges = _graphOfAirports.getSortedListOfEdges();
		
		assertTrue("Index 0 less than index 1",
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(0)) <=
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(1)));
		assertTrue("Index 1 less than index 2",
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(1)) <=
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(2)));
		assertTrue("Index 2 less than index 3",
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(2)) <=
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(3)));
		assertTrue("Index 3 less than index 4",
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(3)) <=
				_graphOfAirports.getGraphOfAirports().getEdgeWeight(_sortedEdges.get(4)));
	}
}
