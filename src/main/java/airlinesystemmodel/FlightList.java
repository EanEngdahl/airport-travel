package airlinesystemmodel;

import java.util.ArrayList;
import java.util.Properties;
import java.math.BigDecimal;

import airlinesystemcontroller.FlightBuilder;

public class FlightList extends ArrayList<Flight> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4575157870451051348L;

	public FlightList() {}
	
	public void addFlightToList(char aircraftSize_, int maxSeatsPerSection_[], 
		int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
		String source_, String destination_, double distanceTravelled_, 
		Properties modelProperties_) {

			Flight _addedFlight;
			FlightBuilder _createFlight = new FlightBuilder();

			_addedFlight = _createFlight.flightDispatchService(aircraftSize_, maxSeatsPerSection_, 
					seatsFilledPerSection_, seatCostPerSection_, 
					source_, destination_, distanceTravelled_, modelProperties_);

			addFlightToList(_addedFlight);
	}
	
	public void addFlightToList(Flight flight_) {
		add(flight_);
	}
}