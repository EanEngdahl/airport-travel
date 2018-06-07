/*
 * ReadModelDataIntoState
 * 		Reads data into a given flight list either from
 * 		an entire file or a single string
 */

package org.airlinesystem.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.StringTokenizer;

import org.airlinesystem.model.FlightList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ReadModelDataIntoState {
	
	private static final String DELIM = "|";
	
	/*
	 * Read information from the input file then sends that information properly
	 * formatted to be added to the FlightList line by line
	 * 
	 * @param listOfFlights_
	 * 		FlightList type object that is the list that will have the flights
	 * 		added to it
	 * @param fileToRead_
	 * 		String that tells what file should be attempted to be opened and read
	 * @param modelProperties_
	 * 		Properties type object that will be passed to the FlightList so that
	 * 		it can be used by the flightBuilder
	 * @return
	 * 		N/A
	 */
	public void readFileInputIntoFlightList(FlightList listOfFlights_, 
			String fileToRead_, Properties modelProperties_, AirportGraph airportGraph_)
			throws IOException, NullPointerException, Exception {
		Logger _debugLogger = LoggerFactory.getLogger("debugLogger");
		_debugLogger.debug("Reading input file");
		
		ReadGraphFromPSV _addEdgeToGraph = new ReadGraphFromPSV();
		String _source;
		String _destination;
		double _distanceTravelled;
		char _aircraftSize;
		int _maxSeatsPerSection[] = new int [4];
		int _seatsFilledPerSection[] = new int [4];
		BigDecimal _seatCostPerSection[] = new BigDecimal [4];
		
		try (InputStream _is = ReadModelDataIntoState.class.getResourceAsStream(fileToRead_)) {
			InputStreamReader _sr = new InputStreamReader(_is);
			BufferedReader reader = new BufferedReader(_sr);
			reader.readLine();
			StringTokenizer tokenizer;
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("\\s", "");
				if ("".equals(line)) {
					continue;
				}
				
				tokenizer = new StringTokenizer(line, DELIM);
				_source = tokenizer.nextToken();
				_destination = tokenizer.nextToken();
				_distanceTravelled = setDistanceTravelled(tokenizer.nextToken());
				_aircraftSize = setAircraftSize(tokenizer.nextToken());
				
				for (int i = 0; i < 4; i++) {
					_maxSeatsPerSection[i] = setMaxSeatsPerSection(tokenizer.nextToken());
				}
				for (int i = 0; i < 4; i++) {
					_seatsFilledPerSection[i] = setSeatsFilledPerSection(tokenizer.nextToken());
				}
				for (int i = 0; i < 4; i++) {
					_seatCostPerSection[i] = setSeatCostPerSection(tokenizer.nextToken());
				}
				_addEdgeToGraph.readEdgeIntoGraph(airportGraph_, _source, _destination, _distanceTravelled);
				listOfFlights_.addFlightToList(_aircraftSize, _maxSeatsPerSection,
						_seatsFilledPerSection, _seatCostPerSection, _source,
						_destination, _distanceTravelled, modelProperties_, airportGraph_);
			}
			_debugLogger.debug("Successfully read file");
			reader.close();
		}
		catch (IOException e_) {
			throw new IOException("IOException: could not read data");
		}
		catch (NullPointerException e_) {
			throw new NullPointerException("NullPointerException: data file error,"
					+ " could not find file- " + fileToRead_);
		}
		catch (Exception e_) {
			throw new Exception("Unexpected error occured while reading data file");
		}
	}
	
	/*
	 * Read information from a String then formats it and sends it to a FlightList
	 * 
	 * @param listOfFlights_
	 * 		FlightList type object that is the list that will have the flights
	 * 		added to it
	 * @param flightInformation_
	 * 		String that contains the information needed for a flight
	 * @param modelProperties_
	 * 		Properties type object that will be passed to the FlightList so that
	 * 		it can be used by the flightBuilder
	 * @return
	 * 		N/A
	 * */
	public void readSingleFlightIntoFlightList(FlightList listOfFlights_, 
			String flightInformation_, Properties modelProperties_, AirportGraph airportGraph_) {
		
		flightInformation_ = flightInformation_.replaceAll("\\s", "");
		String _source;
		String _destination;
		double _distanceTravelled;
		char _aircraftSize;
		int _maxSeatsPerSection[] = new int [4];
		int _seatsFilledPerSection[] = new int [4];
		BigDecimal _seatCostPerSection[] = new BigDecimal [4];
		StringTokenizer tokenizer = new StringTokenizer(flightInformation_, DELIM);
		
		_source = tokenizer.nextToken();
		_destination = tokenizer.nextToken();
		_distanceTravelled = setDistanceTravelled(tokenizer.nextToken());
		_aircraftSize = setAircraftSize(tokenizer.nextToken());
		
		for (int i = 0; i < 4; i++) {
			_maxSeatsPerSection[i] = setMaxSeatsPerSection(tokenizer.nextToken());
		}
		for (int i = 0; i < 4; i++) {
			_seatsFilledPerSection[i] = setSeatsFilledPerSection(tokenizer.nextToken());
		}
		for (int i = 0; i < 4; i++) {
			_seatCostPerSection[i] = setSeatCostPerSection(tokenizer.nextToken());
		}
		listOfFlights_.addFlightToList(_aircraftSize, _maxSeatsPerSection,
				_seatsFilledPerSection, _seatCostPerSection, _source,
				_destination, _distanceTravelled, modelProperties_, airportGraph_);
	}
	
	/*
	 * Finds the distance travelled by converting a passed String to a double
	 * 
	 * @param distanceTravelled_
	 * 		String to be converted into a double
	 * @return
	 * 		double that represented the distance travelled by a flight
	 * */
	public double setDistanceTravelled(String distanceTravelled_) {
		return Double.parseDouble(distanceTravelled_);
	}
	
	/*
	 * Finds the aircraftSize that a flight will use by taking the first
	 * character of a passed String and returning it
	 * 
	 * @param aircraftSize_
	 * 		String to be converted into a char
	 * @return
	 * 		character that represented the size of the aircraft to use for the
	 * 		flight (s, m, l)
	 */
	public char setAircraftSize(String aircraftSize_) {
		
		return aircraftSize_.charAt(0);
	}
	
	/*
	 * Finds the max seats in a section by converting a passed String to an integer
	 * 
	 * @param maxSeatsInSection_
	 * 		String to be converted into an integer
	 * @return
	 * 		integer that represented the max number of seats in a section
	 * 		for the flight
	 */
	public int setMaxSeatsPerSection(String maxSeatsInSection_) {
		return Integer.parseInt(maxSeatsInSection_);
	}
	
	/*
	 * Finds the number of seats filled in a section by converting 
	 * a passed String to an integer
	 * 
	 * @param seatsFilledInSection_
	 * 		String to be converted into an integer
	 * @return
	 * 		integer that represented the number of seats filled
	 * 		in a section for the flight
	 */
	public int setSeatsFilledPerSection(String seatsFilledInSection_) {
		return Integer.parseInt(seatsFilledInSection_);
	}
	
	/*
	 * Finds the cost of an individual seat in a section by converting 
	 * a passed String to a BigDecimal
	 * 
	 * @param seatCostInSection_
	 * 		String to be converted into a BigDecimal
	 * @return
	 * 		BigDecimal type that represented the cost for a seat
	 * 		in a section for the flight
	 */
	public BigDecimal setSeatCostPerSection(String seatCostInSection_) {
		BigDecimal _decimalReturn = new BigDecimal(seatCostInSection_);
		return _decimalReturn;
	}
}
