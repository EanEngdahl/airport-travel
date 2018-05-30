package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

import java.math.BigDecimal;
import java.util.Properties;

public class CommandHandler {

	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	private Logger debugLogger = LoggerFactory.getLogger("debugLogger");
	
	public void processGraph(AirportGraph graphOfAirports_, String graphFileName_) throws Exception{
		ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
		try {
		_graphInput.readFileInputIntoGraph(graphOfAirports_, graphFileName_);
		debugLogger.debug("Graph successfully read.");
		}
		catch(Exception e_) {
			throw new Exception(e_.getMessage());
		}
	}
	
	public Properties processProperties(Properties modelProperty_, String propertiesFileName_) throws Exception{
		RuntimePropertyController _propertyCreator = new RuntimePropertyController();
		try {
			modelProperty_ = _propertyCreator.loadRuntimeProperties(propertiesFileName_);
			debugLogger.debug("Created property model from file");
			return modelProperty_;
		}
		catch (Exception e_) {
			throw new Exception("Error, cannot create properties");
		}
	}
	
	public void generateData(Properties modelProperty_, AirportGraph graphOfAirports_,
			FlightList listOfFlights_) throws Exception{
		ReadModelDataIntoState _flightInput = new ReadModelDataIntoState();
		GenerateModelData _dataCreator = new GenerateModelData();
		try {
			_dataCreator.generateCurrentStateModel(modelProperty_, graphOfAirports_,
					listOfFlights_, _flightInput);
			debugLogger.debug("Generated data");
		}
		catch (Exception e_) {
			throw new Exception("Error, cannot generate data");
		}
	}
	
	public BigDecimal findTotalProfit(FlightList listOfFlights_) throws Exception{
		FlightRCPManager _flightProfitManager = new FlightRCPManager();
		BigDecimal _totalProfit = new BigDecimal(0);
		
		try {		
			_totalProfit = _flightProfitManager.findTotalProfitOfFlightList(listOfFlights_);
			return _totalProfit;
		}
		catch (Exception e_) {
			throw new Exception("Error, cannot find total profit");
		}
	
	}
	public void runSimulation(String propertiesFileName_, String graphFileName_,
			FlightList listOfFlights_, AirportGraph graphOfAirports_, 
			Properties modelProperties_) {
		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("NoUserInput");
				
		try {
			processGraph(graphOfAirports_, graphFileName_);
			graphOfAirports_.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}

		try {
			modelProperties_ = processProperties(modelProperties_, propertiesFileName_);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		
		try {
			generateData(modelProperties_, graphOfAirports_, listOfFlights_);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		
		try {		
			BigDecimal _totalProfit = new BigDecimal("0");
			_totalProfit = findTotalProfit(_totalProfit, listOfFlights_);
			resultsLogger.info("Total Profit = $" + _totalProfit.toString());
			consoleLogger.info("Total Profit = $" + _totalProfit.toString());	
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
			resultsLogger.error(e_.getMessage());
		}
	}
	
	public CommandHandler() {
	}

	/*
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
        	CommandHandler _ch = new CommandHandler();
        	_ch.NoUserInput(_propertiesFileName, _graphFileName);
        }
        catch (Exception e_) {
        	logger.error("IOException" + e_.getMessage());
        }        
    }
    */
}
