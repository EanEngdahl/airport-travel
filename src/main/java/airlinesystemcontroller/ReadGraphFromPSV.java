package airlinesystemcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.Airport;

public class ReadGraphFromPSV {

	private static final String DELIM = "|";
	
	public void ReadFileInputIntoGraph(AirportGraph graphOfAirports_) throws IOException{
		Logger debugLogger = LoggerFactory.getLogger("debugLogger");
		debugLogger.debug("Reading graph input file");
		
		String _source;
		String _destination;
		double _distanceTravelled;
		String _fileToRead = "/airports";
		
		try (InputStream _is = ReadGraphFromPSV.class.getResourceAsStream(_fileToRead)) {
			InputStreamReader _sr = new InputStreamReader(_is);
			BufferedReader reader = new BufferedReader(_sr);
			reader.readLine();
			StringTokenizer tokenizer;
			String line = null;
			Airport _sourceAirport;
			Airport _destinationAirport;
			while ((line = reader.readLine()) != null) {
				if ("".equals(line)) {
					continue;
				}
				tokenizer = new StringTokenizer(line, DELIM);
				_source = tokenizer.nextToken();
				_destination = tokenizer.nextToken();
				_distanceTravelled = setDistanceTravelled(tokenizer.nextToken());
				_sourceAirport = new Airport(_source);
				_destinationAirport = new Airport(_destination);
				graphOfAirports_.addAirport(_sourceAirport);
				graphOfAirports_.addAirport(_destinationAirport);
				graphOfAirports_.createEdge(_source, _destination, _distanceTravelled);
			}
			debugLogger.debug("Successfully read graph file");
			reader.close();
		}
	}
	
	public double setDistanceTravelled(String distanceTravelled_) {
		return Double.parseDouble(distanceTravelled_);
	}
}
