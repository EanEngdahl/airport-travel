package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.Flight;
import airlinesystemmodel.FlightList;

import java.math.BigDecimal;
import airlinesystemmodel.AirportGraph;

public class CommandHandler {

	public CommandHandler(String fname_) {
		Logger debugLogger = LoggerFactory.getLogger("debugLogger");
		Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
		Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("Command Handler");
		FlightList _listOfFlights = new FlightList();
		ReadPSVIntoState _input = new ReadPSVIntoState();
		
		try {
			_input.ReadFileInputIntoFlightList(_listOfFlights);
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
			debugLogger.debug("Error reading file");
			consoleLogger.info("File reading error, no profits calculated.");
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
        //TODO temporary test for graph, remove later
        AirportGraph testGraph = new AirportGraph(5);
        testGraph.addEdge(1, 2, 10);
        testGraph.addEdge(2, 3, 7);
        testGraph.addEdge(3, 4, 4);
        testGraph.addEdge(4, 2, 13);
        testGraph.addEdge(4, 1, 8);
        testGraph.printAdjacencyList();
        testGraph.removeEdge(1, 2);    
        testGraph.printAdjacencyList();
    }
}
