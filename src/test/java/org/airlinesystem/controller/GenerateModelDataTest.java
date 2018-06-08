package org.airlinesystem.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;

import java.util.Properties;

import org.airlinesystem.model.FlightList;
import org.airlinesystem.model.AirportGraph;


public class GenerateModelDataTest {

	private static AirportGraph airportGraph;
	private static GenerateModelData gen;
	private static Properties props;

	@BeforeClass
	public static void initialize() {

		props = new RuntimePropertyController().loadRuntimeProperties("/default.properties");
		gen = new GenerateModelData();
		airportGraph = new AirportGraph();
		
		ReadModelDataIntoState _in = new ReadModelDataIntoState();
		try {
		_in.readFileInputIntoFlightList(new FlightList(), "/test-model-data", props, 
				airportGraph);
		} catch(Exception e) {
		}

	}
	
	@Test
	public void testGenerateRandomSeatsFilled() {
		String[] _seatsFilled = gen.generateRandomSeatsFilled(props, 'm').split("\\|");
		String[] _maxSeats = props.getProperty("MEDIUM_PLANE_SEAT_MAX_PER_SECTION").split("\\|");
		
		assertTrue("The seats filled for econ basic is between zero and max.", 
				0 < Integer.parseInt(_seatsFilled[0]) && Integer.parseInt(_seatsFilled[0]) < Integer.parseInt(_maxSeats[0]));
		
		assertTrue("The seats filled for first class is between zero and max.", 
				0 < Integer.parseInt(_seatsFilled[3]) && Integer.parseInt(_seatsFilled[3]) < Integer.parseInt(_maxSeats[3]));
		
	}

}
