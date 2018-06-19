/**
 * ConsoleViewController class
 *		Controllers user display and input
 *		options available
 */

package org.airlinesystem.controllers;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.airlinesystem.controllers.logging.FullLogging;
import org.airlinesystem.helpers.AirlineSimulationBuilder;
import org.airlinesystem.model.AirlineSimulation;
import org.airlinesystem.model.AirlineSystemFileConstants;
import org.airlinesystem.view.*;
import org.airlinesystem.exceptions.*;




public class ConsoleViewController {

	private FullLogging viewControllerLog = FullLogging.getInstance();

	/*
	 *  Console menu options
	 */
	enum MenuOptions {
		QUIT_PROGRAM(0),
		INPUT_CUSTOM_FILES(1),
		RUN_SIMULATION(2),
		SHOW_RESULTS(3),
		FIND_AVG_RCP_BETWEEN_AIRPORTS(4),
		READ_FROM_DATA_FILE(5),
		DISPLAY_GRAPH(6);
		
		int selection;
		
		MenuOptions(int selection) {
			this.selection = selection;
		}
		
		int getSelection() {
			return selection;
		}
	}

	/**
	 * Main menu controller for user, handles all main menu input
	 * 
	 * @param consoleLogger_
	 * 		logger that will be used for error displays
	 * @param fileNameList_
	 * 		String array that contains list of file names to be used
	 * @param sim_
	 * 		AirlineSimulation object used to run the simulation
	 * @return
	 * 		N/A
	 */
	public void menuController(String [] fileNameList_,
			AirlineSimulation simulation_, AirlineSimulationBuilder simulator_) {


		Path _pathToFile = Paths.get(fileNameList_[0]);
		File _propertiesFile = _pathToFile.toFile();
		_pathToFile = Paths.get(fileNameList_[1]);
		File _graphFile = _pathToFile.toFile();
		_pathToFile = Paths.get(fileNameList_[2]);
		File _dataFile = _pathToFile.toFile();
		MenuOptions _selection;
		String[] _airportNames;
		BigDecimal _averageProfit;
		ConsoleView _consoleOut = new ConsoleView();
		FlightRCPController _flightRCPManager;
		Boolean _hasSimBeenRun = false;
		
		Scanner _input = new Scanner(System.in);
		
		do {
			_selection = MenuOptions.valueOf(String.valueOf(_consoleOut.showMainMenu(_input)));
			
			switch(_selection) {
				case INPUT_CUSTOM_FILES:
					fileNameList_ = _consoleOut.promptUserForFileNames(_input);
					if(fileNameList_[0] != null && !(fileNameList_[0].isEmpty())) {
						_pathToFile = Paths.get(fileNameList_[0]);
						_propertiesFile = _pathToFile.toFile();
					} else {
						_propertiesFile = new File(System.getProperty("user.dir") + 
								AirlineSystemFileConstants.AIRLINESYSTEM_DEFAULT_PROPERTIES);
					}					
					if(fileNameList_[1] != null && !(fileNameList_[1].isEmpty())) {
						_pathToFile = Paths.get(fileNameList_[1]);
						_graphFile = _pathToFile.toFile();
					} else {
						_graphFile = new File(System.getProperty("user.dir") + 
								AirlineSystemFileConstants.AIRLINESYSTEM_DEFAULT_GRAPH);
					}
					
					if(fileNameList_[2] != null && !(fileNameList_[2].isEmpty())) {
						_pathToFile = Paths.get(fileNameList_[2]);
						_dataFile = _pathToFile.toFile();
					}
					else {
						_dataFile = new File(System.getProperty("user.dir") + 
								AirlineSystemFileConstants.AIRLINESYSTEM_DEFAULT_DATA);
					}
						_dataFile = _pathToFile.toFile();

					_hasSimBeenRun = false;
					break;
				case RUN_SIMULATION:
					simulation_.getListOfFlights().clear();
					simulation_.getGraphOfAirports().clearGraph();
					simulator_.runSimulation(_propertiesFile, _graphFile, simulation_);
					if(simulation_.getListOfFlights().size() != 0) {
					_hasSimBeenRun = true;
					}
					break;
				case SHOW_RESULTS:
					if(_hasSimBeenRun) {
					_consoleOut.resultsView(simulation_.getTotalProfit(), simulation_.getTotalCost(), 
							simulation_.getTotalRevenue(), simulation_.getListOfFlights().size(), 
							simulation_.getTotalProfit().divide(new BigDecimal(simulation_.getListOfFlights().size()),
									2, RoundingMode.FLOOR));
					}
					else {
						viewControllerLog.menuError("No simulation run, unable to show results\n");
					}
					break;
				case FIND_AVG_RCP_BETWEEN_AIRPORTS:
					if(_hasSimBeenRun) {
						_flightRCPManager = new FlightRCPController();
						_airportNames = _consoleOut.findAverageBetweenAirports(_input);
						if(simulation_.getGraphOfAirports().areAirportsConnected(_airportNames[0], _airportNames[1])) {
							try {
								_averageProfit = _flightRCPManager.findAverageProfitPerEdge(simulation_.getListOfFlights(),
										simulation_.getGraphOfAirports(), _airportNames[0], _airportNames[1]);
								_consoleOut.displayAverageBetweenAirports(_averageProfit);
							} catch(AirlineSystemException _e) {
								viewControllerLog.menuError("There are no flights between the two airports\n");
							}
						}
						else if(!(simulation_.getGraphOfAirports().getGraphOfAirports().containsVertex(_airportNames[0]))
								|| !(simulation_.getGraphOfAirports().getGraphOfAirports().containsVertex(_airportNames[1]))) {
							viewControllerLog.menuError("Airport input not present in graph, cannot find average\n");
						}
						else {
							viewControllerLog.menuError("Airports are not connected, cannot find average\n");
						}
					} 
					else {
						viewControllerLog.menuError("No simulation run, unable to find profit\n");
					}
					break;
				case READ_FROM_DATA_FILE:
					simulation_.getListOfFlights().clear();
					simulation_.getGraphOfAirports().clearGraph();
					try {
						simulator_.runFromDataFile(_propertiesFile, _dataFile, simulation_);
						_hasSimBeenRun = true;
					}
					catch (Exception e_) {
						_hasSimBeenRun = false;
						viewControllerLog.menuError("\nError reading data, cannot run simulation\n");
					}
					break;
				case DISPLAY_GRAPH:
					if(_hasSimBeenRun) {
						simulation_.getGraphOfAirports().printGraph();
					}
					else {
						viewControllerLog.menuError("No simulation run, unable to display graph\n");
					}
					break;
				case QUIT_PROGRAM:
					return;		
			}
		} while(!_selection.equals(MenuOptions.QUIT_PROGRAM));
		_input.close();
	}
}
