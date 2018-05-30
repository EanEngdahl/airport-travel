package airlinesystemmodel;

import java.util.ArrayList;
import java.util.Properties;
import java.math.BigDecimal;
import java.util.HashMap;

import org.jgrapht.graph.DefaultEdge;

import airlinesystemcontroller.FlightBuilder;
import airlinesystemcontroller.AirportGraph;

public class FlightList extends ArrayList<Flight> {

	private HashMap<DefaultEdge, Flight> mapEdgeToFlights;
	private static final long serialVersionUID = 4575157870451051348L;

	public FlightList() {}
	
	public void addFlightToList(char aircraftSize_, int maxSeatsPerSection_[], 
		int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
		String source_, String destination_, double distanceTravelled_, 
		Properties modelProperties_, AirportGraph airportGraph_) {

			Flight _addedFlight;
			FlightBuilder _createFlight = new FlightBuilder();

			_addedFlight = _createFlight.flightDispatchService(aircraftSize_, maxSeatsPerSection_, 
					seatsFilledPerSection_, seatCostPerSection_, 
					source_, destination_, distanceTravelled_, modelProperties_);

			addFlightToList(_addedFlight);
			mapFlight(airportGraph_, _addedFlight, source_, destination_);
	}
	
	public void addFlightToList(Flight flight_) {
		add(flight_);
	}
	
	public void mapFlight(AirportGraph airportGraph_, Flight addedFlight_, 
			String source_, String destination_) {
		mapEdgeToFlights.put(airportGraph_.getGraphOfAirports().getEdge(source_, destination_),
				addedFlight_);
	}
}