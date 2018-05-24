package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

import java.math.BigDecimal;
import java.util.Properties;

public class CommandHandler {

	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	public CommandHandler(String propertiesFileName_) {
		consoleLogger.info("Calculating flight results...");
		consoleLogger.debug("Command Handler");
		
		FlightList _listOfFlights = new FlightList();
		AirportGraph _graphOfAirports = new AirportGraph();
		ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
		ReadModelDataIntoState _flightInput = new ReadModelDataIntoState();
		GenerateModelData _dataCreator = new GenerateModelData();
		RuntimePropertyController _propertyCreator = new RuntimePropertyController();
		Properties _modelProperty;
		
		try {
			_graphInput.ReadFileInputIntoGraph(_graphOfAirports);
			_graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error("Graph file reading error.");
		}
		try {
			_modelProperty = _propertyCreator.loadRuntimeProperties(propertiesFileName_);
			consoleLogger.info("Made it past model loading");
			_dataCreator.generateCurrentStateModel(_modelProperty, _graphOfAirports, _listOfFlights, _flightInput);
			
			FlightRCPManager _flightProfitManager = new FlightRCPManager();
			BigDecimal _totalProfit = _flightProfitManager.findTotalProfitOfFlightList(_listOfFlights);
			resultsLogger.info("Total Profit = $" + _totalProfit.toString());
			consoleLogger.info("Total Profit = $" + _totalProfit.toString());
		}
		catch (Exception e_) {
			consoleLogger.error("Information file reading error, no profits calculated.");
			resultsLogger.error("No profits calculated.");
		}
		/*
		try {
			_flightData = _dataCreator.generateRandomFlight(_modelProperty, _graphOfAirports);
			_flightInput.ReadSingleFlightIntoFlightList(_listOfFlights, _flightData);
			FlightRCPManager _flightProfitManager = new FlightRCPManager();
			BigDecimal _totalProfit = _flightProfitManager.findTotalProfitOfFlightList(_listOfFlights);
			resultsLogger.info("Total Profit = $" + _totalProfit.toString());
			consoleLogger.info("Total Profit = $" + _totalProfit.toString());
		}
		catch (Exception e_) {
			consoleLogger.error("Information file reading error, no profits calculated.");
			resultsLogger.error("No profits calculated.");
		}
		*/
	}
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(CommandHandler.class);
		String _propertiesFileName = "default.properties";
		//String _propertiesFileName = args[0];
        try {
            new CommandHandler(_propertiesFileName);
        }
        catch (Exception e_) {
        	logger.error("IOException" + e_.getMessage());
        }        
    }
}
