package org.airlinesystem.model;

import java.util.ArrayList;
import java.util.Properties;
import java.math.BigDecimal;
import java.util.HashMap;

import org.airlinesystem.model.AirportGraph;
import org.airlinesystem.controller.FlightBuilder;
import org.jgrapht.graph.DefaultEdge;

public class FlightList extends ArrayList<Flight> {

	private HashMap<DefaultEdge, FlightList> mapEdgeToFlights;
	private static final long serialVersionUID = 4575157870451051348L;

	public FlightList() {
		mapEdgeToFlights = new HashMap<DefaultEdge, FlightList>();
	}
	
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
		
		DefaultEdge _testEdge = airportGraph_.getEdge(source_, destination_);

		if(mapEdgeToFlights.containsKey(_testEdge)) {
			mapEdgeToFlights.get(_testEdge).addFlightToList(addedFlight_);

		} else {
			FlightList _list = new FlightList();
			_list.addFlightToList(addedFlight_);
			mapEdgeToFlights.put(_testEdge, _list);
		}
	}
	
	public HashMap<DefaultEdge, FlightList> getFlightMap() {
		return mapEdgeToFlights;
	}
	
	@Override
	public void clear() {
		mapEdgeToFlights.clear(); 
		super.clear();
	}
	
}