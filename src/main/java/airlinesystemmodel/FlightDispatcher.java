package airlinesystemmodel;

import java.math.BigDecimal;

public class FlightDispatcher {
	
	public Flight flightDispatchService(char aircraftSize_, int maxSeatsPerSection_[], 
			int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
			String source_, String dest_, int distanceTravelled_ ) {
	
		AircraftPilot _pilot;
		AircraftPilot _coPilot;

		PilotDispatcher assignPilots = new PilotDispatcher();

		_pilot = assignPilots.assignPilotToAircraft(aircraftSize_);
		_coPilot = assignPilots.assignPilotToAircraft(aircraftSize_);	

		Flight _newFlightFromData = new Flight(aircraftSize_, maxSeatsPerSection_, 
				seatsFilledPerSection_, seatCostPerSection_, source_, dest_, 
				distanceTravelled_, _pilot, _coPilot);
	
		return _newFlightFromData;
		/* TODO: newFlightFromData will go to a database/file storing all the flights for 
		 * querying etc.
		 */
	}
}
