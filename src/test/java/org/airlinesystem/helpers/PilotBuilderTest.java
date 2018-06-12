package org.airlinesystem.helpers;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Properties;
import java.math.BigDecimal;

import org.airlinesystem.controllers.RuntimePropertyController;
import org.airlinesystem.helpers.PilotBuilder;
import org.airlinesystem.model.AircraftPilot;

public class PilotBuilderTest {

	private static RuntimePropertyController propManager;
	private static Properties testProps;
	private static PilotBuilder testPilotBuilder;
	
	@BeforeClass
	public static void initialize() {
		propManager = new RuntimePropertyController();
		testProps = propManager.loadDefaultProperties();
		testPilotBuilder = new PilotBuilder(testProps);
	}
	
	@Test
	public void testAssignPilotToAircraft() {	
		char _aircraftSize = 'l';
		AircraftPilot testPilot = testPilotBuilder.assignPilotToAircraft(_aircraftSize);
		assertEquals("Pilot for large aircraft should be senior level",
				2, testPilot.getSeniority());
		assertEquals("Pilot for large aircraft should have senior pay", 
				new BigDecimal (testProps.getProperty("SENIOR_PILOT_PAY")), testPilot.getCostPerFlight());
		
		_aircraftSize = 'm';
		testPilot = testPilotBuilder.assignPilotToAircraft(_aircraftSize);
		assertEquals("Pilot for medium aircraft should be mid level",
				1, testPilot.getSeniority());
		assertEquals("Pilot for medium aircraft should have mid level pay",
				new BigDecimal (testProps.getProperty("MIDLEVEL_PILOT_PAY")), testPilot.getCostPerFlight());
		
		_aircraftSize = 's';
		testPilot = testPilotBuilder.assignPilotToAircraft(_aircraftSize);
		assertEquals("Pilot for small aircraft should be junior level",
				0, testPilot.getSeniority());
		assertEquals("Pilot for small aircraft should have junior pay",
				new BigDecimal(testProps.getProperty("JUNIOR_PILOT_PAY")), testPilot.getCostPerFlight());
	}
}
