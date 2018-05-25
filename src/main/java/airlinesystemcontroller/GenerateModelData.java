package airlinesystemcontroller;

import java.util.Random;
import java.util.Properties;
import java.util.Set;
import org.jgrapht.graph.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

/*
 *  Generates the data for the airport system based on the configuration file and 
 *  airport graph provided. This is intended to simulate the data from a basic
 *  POS system.
 */
public class GenerateModelData {

	private Random rand = new Random();
	
	private static final char LARGE = 'l';
    private static final char MEDIUM = 'm';
    private static final char SMALL = 's';
	
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

		try {
			int _econBasic;
			int _econPlus;
			int _business;
			int _first;
		
			switch(airplaneSize_) {
				case SMALL:
					_maxSeats = modelProperties_.getProperty("SMALL_PLANE_SEAT_MAX_PER_SECTION").split("\\|");
					

					if(!_maxSeats[0].equals("0")) {
						_econBasic = rand.nextInt(Integer.parseInt(_maxSeats[0]));
					} else {
						_econBasic = 0;
					}
					if(!_maxSeats[1].equals("0")) {
						_econPlus = rand.nextInt(Integer.parseInt(_maxSeats[1]));
					} else {
						_econPlus = 0;
					}
					if(!_maxSeats[2].equals("0")) {
						_business = rand.nextInt(Integer.parseInt(_maxSeats[2]));	
					} else {
						_business = 0;
					}
					if(!_maxSeats[3].equals("0")) {
						_first = rand.nextInt(Integer.parseInt(_maxSeats[3]));
					} else {
						_first = 0;
					}
	
					genString_ = String.format("%d|%d|%d|%d", _econBasic, _econPlus, _business, _first);
					break;
	
				case MEDIUM:
					_maxSeats = modelProperties_.getProperty("MEDIUM_PLANE_SEAT_MAX_PER_SECTION").split("\\|");
	
					if(!_maxSeats[0].equals("0")) {
						_econBasic = rand.nextInt(Integer.parseInt(_maxSeats[0]));
					} else {
						_econBasic = 0;
					}
					if(!_maxSeats[1].equals("0")) {
						_econPlus = rand.nextInt(Integer.parseInt(_maxSeats[1]));
					} else {
						_econPlus = 0;
					}
					if(!_maxSeats[2].equals("0")) {
						_business = rand.nextInt(Integer.parseInt(_maxSeats[2]));	
					} else {
						_business = 0;
					}
					if(!_maxSeats[3].equals("0")) {
						_first = rand.nextInt(Integer.parseInt(_maxSeats[3]));
					} else {
						_first = 0;
					}
	
					genString_ = String.format("%d|%d|%d|%d", _econBasic, _econPlus, _business, _first);
					break;
	
				case LARGE:
					_maxSeats = modelProperties_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION").split("\\|");
	
					if(!_maxSeats[0].equals("0")) {
						_econBasic = rand.nextInt(Integer.parseInt(_maxSeats[0]));
					} else {
						_econBasic = 0;
					}
					if(!_maxSeats[1].equals("0")) {
						_econPlus = rand.nextInt(Integer.parseInt(_maxSeats[1]));
					} else {
						_econPlus = 0;
					}
					if(!_maxSeats[2].equals("0")) {
						_business = rand.nextInt(Integer.parseInt(_maxSeats[2]));	
					} else {
						_business = 0;
					}
					if(!_maxSeats[3].equals("0")) {
						_first = rand.nextInt(Integer.parseInt(_maxSeats[3]));
					} else {
						_first = 0;
					}
	
					genString_ = String.format("%d|%d|%d|%d", _econBasic, _econPlus, _business, _first);
					break;
				}
		} catch(Exception e) {
			e.printStackTrace();
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
			_maxSeatsPerSection = modelProperties_.getProperty("SMALL_PLANE_SEAT_MAX_PER_SECTION");
			_seatPricePerSection = modelProperties_.getProperty("SMALL_PLANE_SEAT_PRICE");

		} else if (_distance < Double.parseDouble(modelProperties_.getProperty("MEDIUM_PLANE_MAX_RANGE"))) {
			_airplaneSize = 'm';
			_maxSeatsPerSection = modelProperties_.getProperty("MEDIUM_PLANE_SEAT_MAX_PER_SECTION");
			_seatPricePerSection = modelProperties_.getProperty("MEDIUM_PLANE_SEAT_PRICE");
		} else {
			_airplaneSize = 'l';
			_maxSeatsPerSection = modelProperties_.getProperty("LARGE_PLANE_SEAT_MAX_PER_SECTION");
			_seatPricePerSection = modelProperties_.getProperty("LARGE_PLANE_SEAT_PRICE");
		}

		String _seatsFilledPerSection = generateRandomSeatsFilled(modelProperties_, _airplaneSize);
		
		_flight = String.format("%s|%s|%f|%c|%s|%s|%s", _source, _dest, 
				_distance, _airplaneSize, _maxSeatsPerSection, 
				_seatsFilledPerSection, _seatPricePerSection);
		
		Logger _debugLogger = LoggerFactory.getLogger("debugLogger");
		_debugLogger.debug("Model gen string output: {}", _flight);		
		
		return _flight;
	}
	
	public void generateCurrentStateModel(Properties modelProperties_, AirportGraph airportGraph_,
			FlightList listOfFlights_, ReadModelDataIntoState flightInput_) {

		int _flightsNeeded = Integer.parseInt(modelProperties_.getProperty("NUMBER_OF_FLIGHTS"));
		String _flightData;

		for (int i = 0; i < _flightsNeeded; i++)
		{
			_flightData = generateRandomFlight(modelProperties_, airportGraph_);
			flightInput_.ReadSingleFlightIntoFlightList(listOfFlights_, _flightData, modelProperties_);
		}
	}
	
}