package airlinesystemcontroller;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.Properties;

import org.junit.Test;

import airlinesystemmodel.AircraftPilot;
import airlinesystemcontroller.RuntimePropertyController;


public class PilotBuilderTest {

	@Test
	public void testAssignPilotToAircraft() {
		RuntimePropertyController propManager = new RuntimePropertyController();
		Properties testProps = propManager.createRuntimeProperties("/default.properties");
		PilotBuilder tester = new PilotBuilder(testProps);
	
		
		char _aircraftSize = 'l';
		AircraftPilot testPilot = tester.assignPilotToAircraft(_aircraftSize);
		assertEquals("Pilot for large aircraft should be senior level",
				testPilot.getSeniority(), 2);
		assertEquals("Pilot for large aircraft should have $800 pay",
				testPilot.getCostPerFlight(), new BigDecimal("800.00"));
		_aircraftSize = 'm';
		testPilot = tester.assignPilotToAircraft(_aircraftSize);
		assertEquals("Pilot for medium aircraft should be mid level",
				testPilot.getSeniority(), 1);
		assertEquals("Pilot for medium aircraft should have $600 pay",
				testPilot.getCostPerFlight(), new BigDecimal("600.00"));
		_aircraftSize = 's';
		testPilot = tester.assignPilotToAircraft(_aircraftSize);
		assertEquals("Pilot for small aircraft should be junior level",
				testPilot.getSeniority(), 0);
		assertEquals("Pilot for small aircraft should have $400 pay",
				testPilot.getCostPerFlight(), new BigDecimal("400.00"));
	}
}
