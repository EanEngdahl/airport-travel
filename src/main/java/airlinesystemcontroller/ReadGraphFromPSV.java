/*
 * ReadGraphFromPSV Class
 * 		Reads a file input line by line with | as delimiters
 * 		this information is then converted to needed data types
 * 		and adds the vertexes (if not already present) and 
 * 		edge (if allowed)
 */

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

	public void ReadFileInputIntoGraph(AirportGraph graphOfAirports_, String fileToRead_) 
			throws IOException, NullPointerException, Exception{
		Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
		consoleLogger.debug("Reading graph input file");
		
		String _source;
		String _destination;
		double _distanceTravelled;
		int _counter = 1;
		
		try (InputStream _is = ReadGraphFromPSV.class.getResourceAsStream(fileToRead_)) {
			InputStreamReader _sr = new InputStreamReader(_is);
			BufferedReader reader = new BufferedReader(_sr);
			reader.readLine();
			StringTokenizer tokenizer;
			String line = null;
			Airport _sourceAirport;
			Airport _destinationAirport;
			while ((line = reader.readLine()) != null) {
				_counter++;
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
				if (!graphOfAirports_.createEdge(_source, _destination, _distanceTravelled)) {
					consoleLogger.debug("Ignored at line " + Integer.toString(_counter) + " in file");
				}
			}
			consoleLogger.debug("Successfully read graph file");
			reader.close();
		}
		catch (IOException e_) {
			throw new IOException("IOException: could not read graph.");
		}
		catch (NullPointerException e_) {
			throw new NullPointerException("NullPointerException: Graph file error,"
					+ " could not find file- " + fileToRead_);
		}
		catch (Exception e_) {
			throw new Exception("Unexpected error occured while reading graph file");
		}
	}
	
	public double setDistanceTravelled(String distanceTravelled_) {
		return Double.parseDouble(distanceTravelled_);
	}
}
