package airlinesystemcontroller;

import java.util.Random;
import java.util.Properties;
import java.util.Set;
import org.jgrapht.graph.*;

import airlinesystemmodel.FlightList;

/*
 *  Generates the data for the airport system based on the configuration file and 
 *  airport graph provided. This is intended to simulate the data from a basic
 *  POS system.
 */
public class GenerateModelData {

	private Random rand = new Random();
	
	public DefaultEdge getRandomEdge(Set<DefaultEdge> edgeSet_) {
		int _bound = edgeSet_.size();
		int _stop;
		int _counter = 0;
		DefaultEdge _returnEdge = new DefaultEdge();
		
		_stop = rand.nextInt(_bound);
	
		for(DefaultEdge _e : edgeSet_) {
			if(_counter == _stop) {
				_returnEdge = _e;
				break;
			}
			_counter++;
		}
		
		return _returnEdge;
	}

	public String generateRandomSeatsFilled(Properties modelProperties_, char airplaneSize_) {
		
		String genString_ = null;
		String[] _maxSeats;
		
		switch(airplaneSize_) {
			case 's':
				_maxSeats = modelProperties_.getProperty("SMALL_PLANE_SEAT_MAX_PER_SECTION").split("|");
				genString_ = String.format("%d|%d|%d|%d",
						rand.nextInt(Integer.parseInt(_maxSeats[0])),  
						rand.nextInt(Integer.parseInt(_maxSeats[1])),
						rand.nextInt(Integer.parseInt(_maxSeats[2])),
						rand.nextInt(Integer.parseInt(_maxSeats[3])));
				break;
			case 'm':
				_maxSeats = modelProperties_.getProperty("MEDIUM_PLANE_SEAT_MAX_PER_SECTION").split("|");
				genString_ = String.format("%d|%d|%d|%d",
						rand.nextInt(Integer.parseInt(_maxSeats[0])),  
						rand.nextInt(Integer.parseInt(_maxSeats[1])),
						rand.nextInt(Integer.parseInt(_maxSeats[2])),
						rand.nextInt(Integer.parseInt(_maxSeats[3])));
				break;
			case 'l':
				_maxSeats = modelProperties_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION").split("|");
				genString_ = String.format("%d|%d|%d|%d",
						rand.nextInt(Integer.parseInt(_maxSeats[0])),  
						rand.nextInt(Integer.parseInt(_maxSeats[1])),
						rand.nextInt(Integer.parseInt(_maxSeats[2])),
						rand.nextInt(Integer.parseInt(_maxSeats[3])));
				break;
		}
		
		return genString_;
	}
	
	public String generateRandomFlight(Properties modelProperties_, AirportGraph airportGraph_) {
	
		String _flight;
		char _airplaneSize;
		String _maxSeatsPerSection;
		String _seatPricePerSection;
		
		DefaultEdge _randomEdge = getRandomEdge(airportGraph_.graphOfAirports.edgeSet());
		String _source = airportGraph_.graphOfAirports.getEdgeSource(_randomEdge);
		String _dest = airportGraph_.graphOfAirports.getEdgeTarget(_randomEdge);
	
		double _distance = airportGraph_.graphOfAirports.getEdgeWeight(_randomEdge);
	
		if(_distance < Double.parseDouble(modelProperties_.getProperty("SMALL_PLANE_MAX_RANGE"))) {
			_airplaneSize = 's';
			_maxSeatsPerSection = modelProperties_.getProperty("SMALL_PLANE_MAX_SEATS");
			_seatPricePerSection = modelProperties_.getProperty("SMALL_PLANE_SEAT_PRICE");

		} else if (_distance < Double.parseDouble(modelProperties_.getProperty("MEDIUM_PLANE_MAX_RANGE"))) {
			_airplaneSize = 'm';
			_maxSeatsPerSection = modelProperties_.getProperty("MEDIUM_PLANE_MAX_SEATS");
			_seatPricePerSection = modelProperties_.getProperty("MEDIUM_PLANE_SEAT_PRICE");
		} else {
			_airplaneSize = 'l';
			_maxSeatsPerSection = modelProperties_.getProperty("LARGE_PLANE_MAX_SEATS");
			_seatPricePerSection = modelProperties_.getProperty("LARGE_PLANE_SEAT_PRICE");
		}
	
		String _seatsFilledPerSection = generateRandomSeatsFilled(modelProperties_, _airplaneSize);
		
		_flight = String.format("%s|%s|%f|%c|%s|%s|%s", _source, _dest, 
				_distance, _airplaneSize, _maxSeatsPerSection, 
				_seatsFilledPerSection, _seatPricePerSection);
		
		return _flight;
	}
	
	public void generateCurrentStateModel(Properties modelProperties_,
			AirportGraph airportGraph_, FlightList listOfFlights_, ReadModelDataIntoState flightInput_) {
		int _flightsNeeded = Integer.parseInt(modelProperties_.getProperty("NUMBER_OF_FLIGHTS"));
		String _flightData;
		for (int i = 0; i < _flightsNeeded; i++)
		{
			_flightData = generateRandomFlight(modelProperties_, airportGraph_);
			flightInput_.ReadSingleFlightIntoFlightList(listOfFlights_, _flightData);
		}
	}
	
}