package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

import java.math.BigDecimal;
import java.util.Properties;

public class AirlineSimulation {

	private FlightList listOfFlights = new FlightList();
	private AirportGraph graphOfAirports = new AirportGraph();
	private Properties modelProperties;

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
	
	public void generateData(Properties modelProperties_, AirportGraph graphOfAirports_,
			FlightList listOfFlights_) throws Exception{
		ReadModelDataIntoState _flightInput = new ReadModelDataIntoState();
		GenerateModelData _dataCreator = new GenerateModelData();
		try {
			_dataCreator.generateCurrentStateModel(modelProperties_, graphOfAirports_,
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
	
	public void runSimulation(String propertiesFileName_, String graphFileName_) {
		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("runSimulation");
				
		try {
			processGraph(graphOfAirports, graphFileName_);
			graphOfAirports.printGraph();
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}

		try {
			modelProperties = processProperties(modelProperties, propertiesFileName_);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		
		try {
			generateData(modelProperties, graphOfAirports, listOfFlights);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		
		try {		
			BigDecimal _totalProfit = new BigDecimal("0");
			_totalProfit = findTotalProfit(listOfFlights);
			resultsLogger.info("Total Profit = $" + _totalProfit.toString());
			consoleLogger.info("Total Profit = $" + _totalProfit.toString());	
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
			resultsLogger.error(e_.getMessage());
		}
	}
	
	public FlightList getListOfFlights() {
		return listOfFlights;
	}

	public AirportGraph getGraphOfAirports() {
		return graphOfAirports;
	}

	public Properties getModelProperties() {
		return modelProperties;
	}
	
	public AirlineSimulation() {
	}
}