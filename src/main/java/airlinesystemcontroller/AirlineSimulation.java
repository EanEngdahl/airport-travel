package airlinesystemcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airlinesystemmodel.FlightList;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Properties;

public class AirlineSimulation {

	private FlightList listOfFlights = new FlightList();
	private AirportGraph graphOfAirports = new AirportGraph();
	private Properties modelProperties;
	private BigDecimal totalCost;
	private BigDecimal totalRevenue;
	private BigDecimal totalProfit;

	private Logger resultsLogger = LoggerFactory.getLogger("resultsLogger");
	private Logger consoleLogger = LoggerFactory.getLogger("consoleLogger");
	private Logger debugLogger = LoggerFactory.getLogger("debugLogger");
	
	public void processGraph(AirportGraph graphOfAirports_, String graphFileName_) throws Exception{
		ReadGraphFromPSV _graphInput = new ReadGraphFromPSV();
		try {
		_graphInput.readFileInputIntoGraph(graphOfAirports_, graphFileName_);
		debugLogger.debug("Graph successfully read");
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
	
	public BigDecimal[] findTotalRCP(FlightList listOfFlights_) throws Exception{
		FlightRCPManager _flightProfitManager = new FlightRCPManager(modelProperties);
		BigDecimal[] _totalProfit;
		
		try {		
			_totalProfit = _flightProfitManager.findTotalRCPOfFlightList(listOfFlights_);
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
			BigDecimal arrayOfRCP[];
			arrayOfRCP = findTotalRCP(listOfFlights);
			totalRevenue = arrayOfRCP[0];
			totalCost = arrayOfRCP[1];
			totalProfit = arrayOfRCP[2];
			NumberFormat _numberFormatter = NumberFormat.getInstance();
			resultsLogger.info("Total Profit = $" + _numberFormatter.format(arrayOfRCP[2]));
			consoleLogger.info("Flights successfully created\n");
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
			resultsLogger.error(e_.getMessage());
		}
	}
	
	public void runFromDataFile(String propertiesFileName_, String dataFileName_) {
		ReadModelDataIntoState _readData = new ReadModelDataIntoState();
		try {
			modelProperties = processProperties(modelProperties, propertiesFileName_);
		} 
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		BigDecimal arrayOfRCP[];
		try {
			_readData.readFileInputIntoFlightList(listOfFlights, dataFileName_,
					modelProperties, graphOfAirports);
			arrayOfRCP = findTotalRCP(listOfFlights);
			totalRevenue = arrayOfRCP[0];
			totalCost = arrayOfRCP[1];
			totalProfit = arrayOfRCP[2];
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
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
