package airlinesystemcontroller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.Airport;


public class AirportGraphTest {

	//private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	AirportGraph _graphOfAirports = new AirportGraph();
	
	@Before
	public void initialize() {
		_graphOfAirports.addAirport(new Airport("A"));
		_graphOfAirports.addAirport(new Airport("B"));
		_graphOfAirports.addAirport(new Airport("D"));
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
	}
	
	public void testAirportFunctions() {
		assertTrue("Airport A is in graph",_graphOfAirports.isAirportInGraph("A"));
		assertFalse("Airport C is not in graph",_graphOfAirports.isAirportInGraph("C"));
		_graphOfAirports.addAirport(new Airport("C"));
		assertTrue("Airport C is now in graph after adding it",_graphOfAirports.isAirportInGraph("C"));
		
	}

}
