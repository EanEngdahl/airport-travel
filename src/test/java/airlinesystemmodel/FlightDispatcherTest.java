package airlinesystemmodel;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class FlightDispatcherTest {

	@Test
	public void testFlightDispatchService() {
		FlightDispatcher tester = new FlightDispatcher();
		char _aircraftSize = 'l';
		int _maxSeats[] = new int[] {150, 100, 100, 50};
		int _seatsFilled[] = new int[] {73, 42, 10, 5};
		BigDecimal _seatCost[] = {new BigDecimal("250"), new BigDecimal("350"), 
				new BigDecimal("450"), new BigDecimal("650")};
		String _source = "1";
		String _dest = "2";
		int _distanceTravelled = 2500;
		
		Flight testDispatch = tester.flightDispatchService(_aircraftSize, _maxSeats, 
				_seatsFilled, _seatCost, _source, _dest, _distanceTravelled);
		assertEquals("Plane size should be large", 'l', testDispatch.getAircraftSize());
		assertEquals("Max number of seats should be 400", 400, 
				testDispatch.getAircraftAssigned().getMaxAircraftSeats());
		assertEquals("Plane seats filled should be the same as entered", _seatsFilled, 
				testDispatch.getSeatsFilledPerSection());
		assertEquals("Plane seat cost should be the same as entered", _seatCost, 
				testDispatch.getSeatCostPerSection());
		assertEquals("Plane source should be 1", _source, testDispatch.getSource());
		assertEquals("Plane destination should be 2", _dest, testDispatch.getDest());
		assertEquals("Plane travel distance should be 2500", _distanceTravelled, 
				testDispatch.getDistanceTravelled());
	}

}
