package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

import java.math.BigDecimal;

public class CommandHandler {

	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	public CommandHandler(String fname_) {
		consoleLogger.info("Calculating flight results...");
		consoleLogger.debug("Command Handler");
		
		FlightList _listOfFlights = new FlightList();
		AirportGraph _graphOfAirports = new AirportGraph();
		ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
		ReadPSVIntoState _informationInput = new ReadPSVIntoState();
		
		try {
			_graphInput.ReadFileInputIntoGraph(_graphOfAirports);
			_graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error("Graph file reading error.");
		}
		try {
			_informationInput.ReadFileInputIntoFlightList(_listOfFlights);
			FlightRCPManager _flightProfitManager = new FlightRCPManager();
			BigDecimal _totalProfit = _flightProfitManager.findTotalProfitOfFlightList(_listOfFlights);
			resultsLogger.info("Total Profit = $" + _totalProfit.toString());
			consoleLogger.info("Total Profit = $" + _totalProfit.toString());
		}
		catch (Exception e_) {
			consoleLogger.error("Information file reading error, no profits calculated.");
		}
	}
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(CommandHandler.class);
        try {
            new CommandHandler("/default.csv");
        }
        catch (Exception e_) {
        	logger.error("IOException" + e_.getMessage());
        }        
    }
}
