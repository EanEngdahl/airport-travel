package org.airlinesystem.model;

import java.util.ArrayList;
import java.util.Properties;
import java.math.BigDecimal;
import java.util.HashMap;

import org.airlinesystem.graphdb.impl.AirportGraph;
import org.airlinesystem.helpers.FlightBuilder;
import org.jgrapht.graph.DefaultEdge;

public class FlightList extends ArrayList<Flight> {

	private HashMap<DefaultEdge, FlightList> mapEdgeToFlights;
	private static final long serialVersionUID = 4575157870451051348L;

	public FlightList() {
		mapEdgeToFlights = new HashMap<DefaultEdge, FlightList>();
	}
	
	public void addFlightToList(AircraftSize aircraftSize_, int maxSeatsPerSection_[], 
		int seatsFilledPerSection_[], BigDecimal seatCostPerSection_[], 
		String source_, String destination_, double distanceTravelled_, 
		Properties modelProperties_, AirportGraph airportGraph_) {
		
		Flight _addedFlight;
		FlightBuilder _createFlight = new FlightBuilder();

		_addedFlight = _createFlight.flightDispatchService(aircraftSize_, maxSeatsPerSection_, 
				seatsFilledPerSection_, seatCostPerSection_, 
				source_, destination_, distanceTravelled_, modelProperties_);

		addFlightToList(_addedFlight, airportGraph_);
	}

	public void addFlightToList(Flight flight_, AirportGraph graph_) {
		add(flight_);
		mapFlight(graph_, flight_, flight_.getSource().getName(), flight_.getDestination().getName());
	}
	
	public void mapFlight(AirportGraph airportGraph_, Flight addedFlight_, 
			String source_, String destination_) {
		
		DefaultEdge _testEdge = airportGraph_.getGraphOfAirports().getEdge(source_, destination_);

		if(mapEdgeToFlights.containsKey(_testEdge)) {
			mapEdgeToFlights.get(_testEdge).add(addedFlight_);

		} else {
			FlightList _list = new FlightList();
			_list.add(addedFlight_);
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