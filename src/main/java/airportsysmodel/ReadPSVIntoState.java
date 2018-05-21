package airportsysmodel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPSVIntoState {
	
	private static final String DELIM = "|";
	
	/*Read information from the input file then sends that information properly
	 * formatted to be added to the FlightList */
	public void ReadFileInputIntoFlightList(FlightList listOfFlights_) {
		Logger debugLogger = LoggerFactory.getLogger("debugLogger");
		debugLogger.debug("Reading input file");
		String _source;
		String _dest;
		int _distanceTravelled;
		char _aircraftSize;
		int _maxSeatsPerSection[] = new int [4];
		int _seatsFilledPerSection[] = new int [4];
		BigDecimal _seatCostPerSection[] = new BigDecimal [4];
		String _fileToRead = "/sample-data.txt";

		
		
		
		try (InputStream _is = ReadPSVIntoState.class.getResourceAsStream(_fileToRead)) {
			InputStreamReader _sr = new InputStreamReader(_is);
			BufferedReader reader = new BufferedReader(_sr);
			StringTokenizer tokenizer;
			String line = null;
			while ((line = reader.readLine()) != null) {
				if ("".equals(line)) {
					continue;
				}
				tokenizer = new StringTokenizer(line, DELIM);
				_source = tokenizer.nextToken();
				_dest = tokenizer.nextToken();
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
						_dest, _distanceTravelled);
			}
			debugLogger.debug("Successfully read file");
			reader.close();
		}
		catch (Exception e_) {
			debugLogger.error("Could not open file " + _fileToRead);
		}
	}
	
	public int setDistanceTravelled(String distanceTravelled_) {
		return Integer.parseInt(distanceTravelled_);
	}
	
	public char setAircraftSize(String aircraftSize_) {
		
		return aircraftSize_.charAt(0);
	}
	
	public int setMaxSeatsPerSection(String maxSeatsInSection_) {
		return Integer.parseInt(maxSeatsInSection_);
	}
	
	public int setSeatsFilledPerSection(String seatsFilledInSection_) {
		return Integer.parseInt(seatsFilledInSection_);
	}
	
	public BigDecimal setSeatCostPerSection(String seatCostInSection_) {
		BigDecimal _decimalReturn = new BigDecimal(seatCostInSection_);
		return _decimalReturn;
	}
}
