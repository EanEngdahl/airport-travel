package airlinesystemcontroller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AirportGraphTest {

	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	AirportGraph _graphOfAirports = new AirportGraph();
	ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
	
	@Before
	public void initialize() {
		try {
			_graphInput.ReadFileInputIntoGraph(_graphOfAirports);
			_graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error("Graph file reading error.");
		}
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
