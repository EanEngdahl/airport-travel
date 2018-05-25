package airlinesystemcontroller;

import static org.junit.Assert.*;

import org.junit.Test;
import java.math.BigDecimal;
import java.util.Properties;

import airlinesystemmodel.Flight;
import airlinesystemcontroller.RuntimePropertyController;

public class FlightRCPManagerTest {
	
	private final static int MAX_SEATS[] = {10, 10, 10, 10};
	private final static int SEATS_FILLED[] = {10, 10, 10, 10};
	private final static BigDecimal SEAT_COST[] =  {new BigDecimal(10), new BigDecimal(15), new BigDecimal(20),
			new BigDecimal(25)};

	RuntimePropertyController propManager = new RuntimePropertyController();
	Properties testProps = propManager.createRuntimeProperties("/default.properties");
	FlightBuilder fd = new FlightBuilder();
	Flight testFlight = fd.flightDispatchService('l', MAX_SEATS, SEATS_FILLED, SEAT_COST, "1", "2", 100, testProps);
	FlightRCPManager testRcp = new FlightRCPManager();
	
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
		
		assertEquals("The value of cost does not match", expectedCost.doubleValue(), costTest.doubleValue(), .01);
	}

	@Test
	public void testFindProfit() {
		BigDecimal profitTest = new BigDecimal(0);
		BigDecimal expectedProfit = new BigDecimal(-2400);
		
		profitTest = testRcp.findProfit(testFlight);
		
		assertEquals("The value of profit does not match", expectedProfit.doubleValue(), profitTest.doubleValue(), .01);
	}

}
