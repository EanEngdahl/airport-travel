package org.airlinesystem.controller;

import org.airlinesystem.model.FlightList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	/*
	 * Attempts to process a graph file given a name and graph to process it into
	 * by call on the class for reading graphs
	 * 
	 * @param graphOfAirports_
	 * 		AirportGraph type object that will have any new vertices and edges added to the graph
	 * @param graphFileName_
	 * 		String of the file to attempt to open and read for the graph data
	 * @return
	 * 		N/A
	 */
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
	
	/*
	 * Attempts to process properties by using the class for runtime properties to either
	 * open a custom properties file or the default file
	 * 
	 * @param propertiesFileName_
	 * 		String of the file to attempt to open and read for the properties data
	 * @return
	 * 		N/A
	 */
	public void processProperties(String propertiesFileName_) throws Exception{
		RuntimePropertyController _propertyCreator = new RuntimePropertyController();
		try {
			modelProperties = _propertyCreator.loadRuntimeProperties(propertiesFileName_);
			debugLogger.debug("Created property model from file");
		}
		catch (Exception e_) {
			throw new Exception("Error, cannot create properties");
		}
	}
	
	/*
	 * Generates data to fill a flightList with the number of flights taken from properties
	 * 
	 * @param modelProperties_
	 * 		Properties used as guidelines for the amount and variety of data to generate
	 * @param graphOfAirports_
	 * 		AirportGraph used to select connected airports for flights
	 * @param listOfFlights_
	 * 		FlightList that will be filled with generated flights 
	 * @return
	 * 		N/A
	 */
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
	
	/*
	 * Attempts to find the total revenue, cost, and profit of an entire flight list
	 * 
	 * @param listOfFlights
	 * 		FlightList containing all the flights to be included in calculations
	 * @return
	 * 		BigDecimal array holding calculated total revenue, cost and profit of flights
	 */
	public BigDecimal[] findTotalRCP(FlightList listOfFlights_) throws Exception{
		FlightRCPManager _flightProfitManager = new FlightRCPManager(modelProperties);
		BigDecimal[] _totalRCP;
		
		try {		
			_totalRCP = _flightProfitManager.findTotalRCPOfFlightList(listOfFlights_);
			return _totalRCP;
		}
		catch (Exception e_) {
			throw new Exception("Error, cannot find total profit");
		}
	
	}
	
	/*
	 * Attempts to simulate by processing graph and properties then generating data
	 * and finding results from that data
	 * 
	 * @param propertiesFileName_
	 * 		String of the file to attempt to open and read for the properties data
	 * @param graphFileName_
	 * 		String of the file to attempt to open and read for the graph data
	 * @return
	 * 		N/A
	 */
	public void runSimulation(String propertiesFileName_, String graphFileName_) {
		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("runSimulation");
				
		try {
			processGraph(graphOfAirports, graphFileName_);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}
		try {
			processProperties(propertiesFileName_);
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
			resultsLogger.info("Total Profit = ${}", _numberFormatter.format(arrayOfRCP[2]));
			consoleLogger.info("Flights successfully created\n");
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
			resultsLogger.error(e_.getMessage());
		}
	}
	
	/*
	 * Attempts to find results by using a data file already filled with flight
	 * information to find results
	 * 
	 * @param propertiesFileName_
	 * 		String of the file to attempt to open and read for the properties data
	 * @param dataFileName_
	 * 		String of the file to attempt to open and read for the flight data
	 * @return
	 * 		N/A
	 */
	public void runFromDataFile(String propertiesFileName_, String dataFileName_) 
		throws Exception{
		ReadModelDataIntoState _readData = new ReadModelDataIntoState();
		try {
			processProperties(propertiesFileName_);
		} 
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
			throw new Exception();
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
			throw new Exception();
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
