package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.Flight;
import airlinesystemmodel.FlightList;

import java.math.BigDecimal;

public class CommandHandler {

	private Logger debugLogger = LoggerFactory.getLogger("debugLogger");
	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	public CommandHandler(String fname_) {
		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("Command Handler");
		
		FlightList _listOfFlights = new FlightList();
		AirportGraph _graphOfAirports = new AirportGraph();
		ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
		ReadPSVIntoState _informationInput = new ReadPSVIntoState();
		
		try {
			_graphInput.ReadFileInputIntoGraph(_graphOfAirports);
			_graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			debugLogger.debug("Error reading graph file");
			consoleLogger.error("Graph file reading error.");
		}
		try {
			_informationInput.ReadFileInputIntoFlightList(_listOfFlights);
			BigDecimal _profitSum = new BigDecimal("0");
			BigDecimal _singleFlightProfit = new BigDecimal("0");
			FlightRCPManager flightProfitManager = new FlightRCPManager();
			for (Flight i : _listOfFlights) {
				_singleFlightProfit = flightProfitManager.findProfit(i);
				_profitSum = _profitSum.add(_singleFlightProfit);
				resultsLogger.info("Individual flight profit = $" + _singleFlightProfit.toString());
			}
			resultsLogger.info("Total Profit = $" + _profitSum.toString());
			consoleLogger.info("Total Profit = $" + _profitSum.toString());
			
		}
		catch (Exception e_) {
			debugLogger.debug("Error reading information file");
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
