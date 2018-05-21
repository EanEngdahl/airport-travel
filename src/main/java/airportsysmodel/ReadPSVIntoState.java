package airportsysmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadPSVIntoState {
	
	private static final String DELIM = "|";
	
	public void ReadFileInputIntoFlightList(FlightList listOfFlights_) {
		
		String _source;
		String _dest;
		int _distanceTravelled;
		char _aircraftSize;
		int _maxSeatsPerSection[] = new int [4];
		int _seatsFilledPerSection[] = new int [4];
		BigDecimal _seatCostPerSection[] = new BigDecimal [4];

		try (FileReader fileReader = new FileReader("sample-data")) {
			BufferedReader reader = new BufferedReader(fileReader);
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
		}
		catch (Exception e_) {
			//TODO logging
		}	
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
	
	public int setDistanceTravelled(String distanceTravelled_) {
		return Integer.parseInt(distanceTravelled_);
	}
}
