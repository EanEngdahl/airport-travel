/*
 * FlightBuilder class
 *		Creates a new flight using given data by adding 
 *		pilots, aircraft, and airports that will be stored
 *		in the flight itself
 */

package airlinesystemcontroller;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import airlinesystemmodel.AircraftPilot;
import airlinesystemmodel.Airport;
import airlinesystemmodel.Flight;
import airlinesystemmodel.Aircraft;

public class FlightBuilder {
	
    /*
     * Create new flight based off requirements passed in
     * 
     * @param aircraftSize_
     * 		character that represents the size of plane that will be
     * 		passed in and used to determine the pilot assigned
     * @param maxSeatsPerSection_
     * 		integer array used to determine total number of seats on
     * 		the flight for each section
     * @param seatsFilledPerSection_
     * 		integer array used to determine total number of passengers
     * 		and the number of filled seats in each section for the flight
     * @param seatCostPerSection_
     * 		BigDecimal type array that determines the cost of a single seat
     * 		in each section
     * @param source_
     * 		String that represents the first node on the graph for the flight
     * @param destination_
     * 		String that represents the second node on the graph for the flight
     * @param distanceTravelled_
     * 		
     * @return
     * 		a new pilot to be assigned to the flight
     */
	public Flight flightDispatchService(char aircraftSize_, int maxSeatsPerSection_[], 
			int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
			String source_, String destination_, double distanceTravelled_) {
	
		AircraftPilot _pilot;
		AircraftPilot _coPilot;
		Aircraft _aircraftAssigned;
		
		PilotBuilder assignPilots = new PilotBuilder();

		_pilot = assignPilots.assignPilotToAircraft(aircraftSize_);
		_coPilot = assignPilots.assignPilotToAircraft(aircraftSize_);	
		_aircraftAssigned = new Aircraft(aircraftSize_, getTotalNumOfPassengers(seatsFilledPerSection_),
				seatsFilledPerSection_, seatCostPerSection_, getMaxAircraftSeats(maxSeatsPerSection_));
		Airport source = new Airport(source_);
		Airport destination = new Airport(destination_);
		
		Flight _newFlightFromData = new Flight(aircraftSize_, maxSeatsPerSection_, 
				seatsFilledPerSection_, seatCostPerSection_, source, destination, 
				distanceTravelled_, _pilot, _coPilot, _aircraftAssigned);
		setFlightRCPData(_newFlightFromData);
		
		return _newFlightFromData;
		/* TODO: newFlightFromData will go to a database/file storing all the flights for 
		 * querying etc.
		 */
	}
	
	public void setFlightRCPData(Flight flightToSet_) {
		FlightRCPManager _revenueCostProfitFinder = new FlightRCPManager();
		BigDecimal[] _flightRCPArray = _revenueCostProfitFinder.getRCPAsArray(flightToSet_);
		flightToSet_.setRevenue(_flightRCPArray[0]);
		flightToSet_.setCost(_flightRCPArray[1]);
		flightToSet_.setProfit(_flightRCPArray[2]);
	}
	
	public int getTotalNumOfPassengers(int seatsFilledPerSection_[]) {
		return IntStream.of(seatsFilledPerSection_).sum();
	}
	
	public int getMaxAircraftSeats(int maxSeatsPerSection_[]) {
		return IntStream.of(maxSeatsPerSection_).sum();
				
	}
}
