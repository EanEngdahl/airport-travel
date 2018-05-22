package airlinesystemcontroller;

import java.math.BigDecimal;

import airlinesystemmodel.AircraftPilot;
import airlinesystemmodel.Airport;
import airlinesystemmodel.Flight;

public class FlightBuilder {
	
	public Flight flightDispatchService(char aircraftSize_, int maxSeatsPerSection_[], 
			int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
			String source_, String distance_, int distanceTravelled_ ) {
	
		AircraftPilot _pilot;
		AircraftPilot _coPilot;

		PilotBuilder assignPilots = new PilotBuilder();

		_pilot = assignPilots.assignPilotToAircraft(aircraftSize_);
		_coPilot = assignPilots.assignPilotToAircraft(aircraftSize_);	
		Airport source = new Airport(source_);
		Airport destination = new Airport(distance_);
		
		Flight _newFlightFromData = new Flight(aircraftSize_, maxSeatsPerSection_, 
				seatsFilledPerSection_, seatCostPerSection_, source, destination, 
				distanceTravelled_, _pilot, _coPilot);
	
		return _newFlightFromData;
		/* TODO: newFlightFromData will go to a database/file storing all the flights for 
		 * querying etc.
		 */
	}
}