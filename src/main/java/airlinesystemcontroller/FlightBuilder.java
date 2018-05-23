package airlinesystemcontroller;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import airlinesystemmodel.AircraftPilot;
import airlinesystemmodel.Airport;
import airlinesystemmodel.Flight;
import airlinesystemmodel.Aircraft;

public class FlightBuilder {
	
	public Flight flightDispatchService(char aircraftSize_, int maxSeatsPerSection_[], 
			int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
			String source_, String distance_, int distanceTravelled_ ) {
	
		AircraftPilot _pilot;
		AircraftPilot _coPilot;
		Aircraft _aircraftAssigned;
		
		PilotBuilder assignPilots = new PilotBuilder();

		_pilot = assignPilots.assignPilotToAircraft(aircraftSize_);
		_coPilot = assignPilots.assignPilotToAircraft(aircraftSize_);	
		_aircraftAssigned = new Aircraft(aircraftSize_, getTotalNumOfPassengers(seatsFilledPerSection_),
				seatsFilledPerSection_, seatCostPerSection_, getMaxAircraftSeats(maxSeatsPerSection_));
		Airport source = new Airport(source_);
		Airport destination = new Airport(distance_);
		
		Flight _newFlightFromData = new Flight(aircraftSize_, maxSeatsPerSection_, 
				seatsFilledPerSection_, seatCostPerSection_, source, destination, 
				distanceTravelled_, _pilot, _coPilot, _aircraftAssigned);
	
		return _newFlightFromData;
		/* TODO: newFlightFromData will go to a database/file storing all the flights for 
		 * querying etc.
		 */
	}
	
	public int getTotalNumOfPassengers(int seatsFilledPerSection_[]) {
		return IntStream.of(seatsFilledPerSection_).sum();
	}
	
	public int getMaxAircraftSeats(int maxSeatsPerSection_[]) {
		return IntStream.of(maxSeatsPerSection_).sum();
				
	}
}
