/*
 * ReadGraphFromPSV Class
 * 		Reads a file input line by line with | as delimiters
 * 		this information is then converted to needed data types
 * 		and adds the vertices (if not already present) and 
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

	/*
	 * Reads a given file where each line contains two vertices (airports) that have an edge
	 * between them and a weight (distance) between them
	 * 
	 * @param graphOfAirports_
	 * 		AirportGraph type object that will have any new vertices and edges added to the graph
	 * @param graphFileName_
	 * 		String of the file to attempt to open and read for the graph data
	 * @return
	 * 		N/A
	 */
	public void readFileInputIntoGraph(AirportGraph graphOfAirports_, String graphFileName_) 
			throws IOException, NullPointerException, Exception{
		Logger _debugLogger = LoggerFactory.getLogger("debugLogger");
		_debugLogger.debug("Reading graph input file");
		
		String _source;
		String _destination;
		double _distanceTravelled;
		int _counter = 1;
		String fileToRead_ = graphFileName_;
		
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
				_source = tokenizer.nextToken().toUpperCase();
				_destination = tokenizer.nextToken().toUpperCase();
				_distanceTravelled = setDistanceTravelled(tokenizer.nextToken());
				_sourceAirport = new Airport(_source);
				_destinationAirport = new Airport(_destination);
				graphOfAirports_.addAirport(_sourceAirport);
				graphOfAirports_.addAirport(_destinationAirport);
				if (!graphOfAirports_.createEdge(_source, _destination, _distanceTravelled)) {
					_debugLogger.debug("Ignored at line " + Integer.toString(_counter) + " in file");
				}
			}
			_debugLogger.debug("Successfully read graph file");
			reader.close();
		}
		catch (IOException e_) {
			throw new IOException("IOException: could not read graph.");
		}
		catch (NullPointerException e_) {
			throw new NullPointerException("NullPointerException: Graph file error,"
					+ " could not find file on path- " + graphFileName_);
		}
		catch (Exception e_) {
			throw new Exception("Unexpected error occured while reading graph file");
		}
	}
	
	/*
	 * Finds the distance travelled by converting a passed String to a double
	 * 
	 * @param distanceTravelled_
	 * 		String to be converted into a double
	 * @return
	 * 		double that represented the distance travelled by a flight
	 */
	public double setDistanceTravelled(String distanceTravelled_) {
		return Double.parseDouble(distanceTravelled_);
	}
}
