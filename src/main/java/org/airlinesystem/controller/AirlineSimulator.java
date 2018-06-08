/*
 * AirlineSimulation class
 *		Runs a full simulation with calculations and
 *		has a graph of the airports with a list of flights
 */

package org.airlinesystem.controller;

import org.airlinesystem.model.AirlineSimulation;
import org.airlinesystem.model.FlightList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Properties;

public class AirlineSimulator {

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
	public void processGraph(AirportGraph graphOfAirports_, String graphFileName_) throws Exception {
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
	public BigDecimal[] findTotalRCP(FlightList listOfFlights_, Properties modelProperties_) throws Exception {

		FlightRCPManager _flightProfitManager = new FlightRCPManager(modelProperties_);
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
	public void runSimulation(String propertiesFileName_, String graphFileName_,  AirlineSimulation simulation_) {

		RuntimePropertyController _propertyController = new RuntimePropertyController();
		Properties _modelProperties = _propertyController.loadRuntimeProperties(propertiesFileName_);
		simulation_.setSimulationProperties(_modelProperties);

		consoleLogger.info("Calculating flight results...");
		debugLogger.debug("runSimulation");
				
		try {
			processGraph(simulation_.getGraphOfAirports(), graphFileName_);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
		}

		try {
			generateData(_modelProperties, simulation_.getGraphOfAirports(), simulation_.getListOfFlights());
			BigDecimal arrayOfRCP[];
			arrayOfRCP = findTotalRCP(simulation_.getListOfFlights(), _modelProperties);
			simulation_.setTotalRevenue(arrayOfRCP[0]);
			simulation_.setTotalCost(arrayOfRCP[1]);
			simulation_.setTotalProfit(arrayOfRCP[2]);

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
	public void runFromDataFile(String propertiesFileName_, String dataFileName_, AirlineSimulation simulation_) 
		throws Exception {

		ReadModelDataIntoState _readData = new ReadModelDataIntoState();
		RuntimePropertyController _propertyController = new RuntimePropertyController();
		Properties _modelProperties = _propertyController.loadRuntimeProperties(propertiesFileName_);

		BigDecimal _arrayOfRCP[];

		try {
			_readData.readFileInputIntoFlightList(simulation_.getListOfFlights(), dataFileName_,
					_modelProperties, simulation_.getGraphOfAirports());
			_arrayOfRCP = findTotalRCP(simulation_.getListOfFlights(), _modelProperties);
			simulation_.setTotalRevenue(_arrayOfRCP[0]);
			simulation_.setTotalCost(_arrayOfRCP[1]);
			simulation_.setTotalProfit(_arrayOfRCP[2]);
		}
		catch (Exception e_) {
			consoleLogger.error(e_.getMessage());
			throw new Exception();
		}
	}
	
}
