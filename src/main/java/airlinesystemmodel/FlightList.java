package airlinesystemmodel;

import java.util.ArrayList;
import java.math.BigDecimal;

public class FlightList extends ArrayList<Flight> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4575157870451051348L;

	public FlightList() {}
	
	public void addFlightToList(char aircraftSize_, int maxSeatsPerSection_[], 
		int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
		String source_, String dest_, int distanceTravelled_ ) {

			Flight _addedFlight;
			FlightDispatcher _createFlight = new FlightDispatcher();

			_addedFlight = _createFlight.flightDispatchService(aircraftSize_, maxSeatsPerSection_, 
					seatsFilledPerSection_, seatCostPerSection_, 
					source_, dest_, distanceTravelled_ );

			addFlightToList(_addedFlight);
	}
	
	public void addFlightToList(Flight flight_) {
		add(flight_);
	}
}