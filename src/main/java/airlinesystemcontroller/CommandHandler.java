package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

import java.math.BigDecimal;
import java.util.Properties;

public class CommandHandler {

	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	
	public CommandHandler(String propertiesFileName_, String graphFileName_) {
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
			_graphInput.ReadFileInputIntoGraph(_graphOfAirports, graphFileName_);
			consoleLogger.debug("Graph successfully read.");
			_graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}

		try {
			_modelProperty = _propertyCreator.loadRuntimeProperties(propertiesFileName_);
			consoleLogger.debug("Created property model from file");
			_dataCreator.generateCurrentStateModel(_modelProperty, _graphOfAirports,
					_listOfFlights, _flightInput);
			consoleLogger.debug("Generated data");			
			FlightRCPManager _flightProfitManager = new FlightRCPManager();
			BigDecimal _totalProfit = _flightProfitManager.findTotalProfitOfFlightList(_listOfFlights);
			resultsLogger.info("Total Profit = $" + _totalProfit.toString());
			consoleLogger.info("Total Profit = $" + _totalProfit.toString());
		}
		catch (Exception e_) {
			consoleLogger.error("Unable to generate data, no profits calculated.");
			resultsLogger.error("No profits calculated.");
		}
	}
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(CommandHandler.class);
		String _propertiesFileName = "default.properties";
		String _graphFileName = "airports";
		
		// Check if there were filenames passed and then prefer those files
		if(args.length > 0) {
			_propertiesFileName = args[0];
		}
		if (args.length > 1) {
			_graphFileName = args[1];
		}
        try {
            new CommandHandler(_propertiesFileName, _graphFileName);
        }
        catch (Exception e_) {
        	logger.error("IOException" + e_.getMessage());
        }        
    }
}
