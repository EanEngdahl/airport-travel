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
	
	public void ProcessGraph(AirportGraph graphOfAirports_, String graphFileName_) throws Exception{
		ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
		try {
		_graphInput.ReadFileInputIntoGraph(graphOfAirports_, graphFileName_);
		debugLogger.debug("Graph successfully read.");
		}
		catch(Exception e_) {
			throw new Exception(e_.getMessage());
		}
	}
	
	public Properties ProcessProperties(Properties modelProperty_, String propertiesFileName_) throws Exception{
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
	
	public void GenerateData(Properties modelProperty_, AirportGraph graphOfAirports_,
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
	
	public BigDecimal FindTotalProfit(BigDecimal totalProfit_, FlightList listOfFlights_) throws Exception{
		FlightRCPManager _flightProfitManager = new FlightRCPManager();
		
		try {		
			totalProfit_ = _flightProfitManager.findTotalProfitOfFlightList(listOfFlights_);
			return totalProfit_;
		}
		catch (Exception e_) {
			throw new Exception("Error, cannot find total profit");
		}
	
	}
	public void NoUserInput(String propertiesFileName_, String graphFileName_) {
		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("NoUserInput");
		
		FlightList _listOfFlights = new FlightList();
		AirportGraph _graphOfAirports = new AirportGraph();
		Properties _modelProperty = new Properties();
				
		try {
			ProcessGraph(_graphOfAirports, graphFileName_);
			_graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}

		try {
			_modelProperty = ProcessProperties(_modelProperty, propertiesFileName_);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		
		try {
			GenerateData(_modelProperty, _graphOfAirports, _listOfFlights);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		
		try {		
			BigDecimal _totalProfit = new BigDecimal("0");
			_totalProfit = FindTotalProfit(_totalProfit, _listOfFlights);
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
