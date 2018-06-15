/**
 * ConsoleViewController class
 *		Controllers user display and input
 *		options available
 */

package org.airlinesystem.controllers;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.File;

import org.airlinesystem.controllers.logging.FullLogging;
import org.airlinesystem.helpers.AirlineSimulationBuilder;
import org.airlinesystem.model.AirlineSimulation;
import org.airlinesystem.view.*;
import org.airlinesystem.exceptions.*;

public class ConsoleViewController {

	private FullLogging viewControllerLog = FullLogging.getInstance();

	/**
	 * Main menu controller for user, handles all main menu input
	 * 
	 * @param consoleLogger_
	 * 		logger that will be used for error displays
	 * @param fileNameList_
	 * 		String array that contains list of file names to be used
	 * @param sim_
	 * 		AirlineSimulation object used to run the simulation
	 * @param dataFile_
	 * 		String of data file to be used
	 * @return
	 * 		N/A
	 */
	public void menuController(String [] fileNameList_,
			AirlineSimulation simulation_, AirlineSimulationBuilder simulator_, File dataFile_) {

		int _selection;
		File _propertiesFile = new File(fileNameList_[0]);
		File _graphFile = new File(fileNameList_[1]);
		String[] _airportNames;
		BigDecimal _averageProfit;
		ConsoleView _consoleOut = new ConsoleView();
		FlightRCPController _flightRCPManager;
		Boolean _hasSimBeenRun = false;
		
		Scanner _input = new Scanner(System.in);
		
		do {
			_selection = _consoleOut.showMainMenu(_input);
			
			switch(_selection) {
				case 1:
					fileNameList_ = _consoleOut.promptUserForFileNames(_input);
					if(fileNameList_[0] != null && !(fileNameList_[0].isEmpty())) {
						_propertiesFile = new File(fileNameList_[0]);
					}
					if(fileNameList_[1] != null && !(fileNameList_[1].isEmpty())) {
						_graphFile = new File(fileNameList_[1]);
					}
					_hasSimBeenRun = false;
					break;
				case 2:
					simulation_.getListOfFlights().clear();
					simulation_.getGraphOfAirports().clearGraph();
					simulator_.runSimulation(_propertiesFile, _graphFile, simulation_);
					if(simulation_.getListOfFlights().size() != 0) {
					_hasSimBeenRun = true;
					}
					break;
				case 3:
					if(_hasSimBeenRun) {
					_consoleOut.resultsView(simulation_.getTotalProfit(), simulation_.getTotalCost(), 
							simulation_.getTotalRevenue(), simulation_.getListOfFlights().size(), 
							simulation_.getTotalProfit().divide(new BigDecimal(simulation_.getListOfFlights().size()),
									2, RoundingMode.FLOOR));
					}
					else {
						viewControllerLog.consoleError("No simulation run, unable to show results");
					}
					break;
				case 4:
					if(_hasSimBeenRun) {
						_flightRCPManager = new FlightRCPController();
						_airportNames = _consoleOut.findAverageBetweenAirports(_input);
						if(simulation_.getGraphOfAirports().areAirportsConnected(_airportNames[0], _airportNames[1])) {
							try {
								_averageProfit = _flightRCPManager.findAverageProfitPerEdge(simulation_.getListOfFlights(),
										simulation_.getGraphOfAirports(), _airportNames[0], _airportNames[1]);
								_consoleOut.displayAverageBetweenAirports(_averageProfit);
							} catch(AirlineSystemException _e) {
								viewControllerLog.consoleError("There are no flights between the two airports");
							}
						}
						else if(!(simulation_.getGraphOfAirports().getGraphOfAirports().containsVertex(_airportNames[0]))
								|| !(simulation_.getGraphOfAirports().getGraphOfAirports().containsVertex(_airportNames[1]))) {
							viewControllerLog.consoleError("Airport input not present in graph, cannot find average");
						}
						else {
							viewControllerLog.consoleError("Airports are not connected, cannot find average");
						}
					} 
					else {
						viewControllerLog.consoleError("No simulation run, unable to find profit");
					}
					break;
				case 5:
					simulation_.getListOfFlights().clear();
					simulation_.getGraphOfAirports().clearGraph();
					String _dataFileName = _consoleOut.promptForDataFile(_input);
					if(_dataFileName == (null) || _dataFileName.equals("")) {
						dataFile_ = new File(System.getProperty("user.dir") + "/airlinesystem-defaults/default-data");
						viewControllerLog.consoleError("Invalid entry, reverting to default-data file");
					}
					else {
						dataFile_ = new File(_dataFileName);
					}
					try {
						simulator_.runFromDataFile(_propertiesFile, dataFile_, simulation_);
						_hasSimBeenRun = true;
					}
					catch (Exception e_) {
						_hasSimBeenRun = false;
						viewControllerLog.consoleError("Error reading data, cannot run simulation");
					}
					break;
				case 6:
					if(_hasSimBeenRun) {
						simulation_.getGraphOfAirports().printGraph();
					}
					else {
						viewControllerLog.consoleError("No simulation run, unable to display graph");
					}
					break;
				case 0:
					return;		
			}
		} while(_selection != 0);
		_input.close();
	}
}
