package org.airlinesystem.controllers;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Properties;

import org.airlinesystem.controllers.FlightRCPController;
import org.airlinesystem.controllers.RuntimePropertyController;
import org.airlinesystem.helpers.FlightBuilder;
import org.airlinesystem.model.Flight;
import org.airlinesystem.model.AircraftSize;

public class FlightRCPControllerTest {
	
	private final static int MAX_SEATS[] = {10, 10, 10, 10};
	private final static int SEATS_FILLED[] = {10, 10, 10, 10};
	private final static BigDecimal SEAT_COST[] =  {new BigDecimal(10), 
			new BigDecimal(15), new BigDecimal(20), new BigDecimal(25)};

	private static RuntimePropertyController propManager;
	private static Properties testProps;
	private static FlightBuilder fd;
	private static Flight testFlight;
	private static FlightRCPController testRcp;
	
	@BeforeClass
	public static void initialize() {
		propManager = new RuntimePropertyController();
		testProps = propManager.loadDefaultProperties();
		fd = new FlightBuilder();
		testFlight = fd.flightDispatchService(AircraftSize.L, MAX_SEATS, SEATS_FILLED, 
				SEAT_COST, "1", "2", 100, testProps);
		testRcp = new FlightRCPController(testProps);
	}
	
	@Test
	public void testFindRevenue() {
		BigDecimal revenueTest = new BigDecimal(0);
		BigDecimal expectedRevenue = new BigDecimal(700);
		
		revenueTest = testRcp.findRevenue(testFlight);
		
		assertEquals("The value of revenue does not match", expectedRevenue, revenueTest);
	}

	@Test
	public void testFindCost() {
		BigDecimal costTest = new BigDecimal(0);
		BigDecimal expectedCost = new BigDecimal(3100);
		
		costTest = testRcp.findCost(testFlight);
		
		assertEquals("The value of cost does not match", expectedCost.doubleValue(),
				costTest.doubleValue(), .01);
	}

	@Test
	public void testFindProfit() {
		BigDecimal profitTest = new BigDecimal(0);
		BigDecimal expectedProfit = new BigDecimal(-2400);
		
		profitTest = testRcp.findProfit(testFlight);
		
		assertEquals("The value of profit does not match", expectedProfit.doubleValue(), 
				profitTest.doubleValue(), .01);
	}
}
