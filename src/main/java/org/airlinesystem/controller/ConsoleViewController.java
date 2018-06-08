/*
 * ConsoleViewController class
 *		Controllers user display and input
 *		options available
 */

package org.airlinesystem.controller;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.airlinesystem.model.AirlineSimulation;
import org.airlinesystem.view.*;
import org.slf4j.Logger;

public class ConsoleViewController {

	/*
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
	public void menuController(Logger consoleLogger_, String [] fileNameList_,
			AirlineSimulation simulation_, AirlineSimulationBuilder simulator_, String dataFile_) {

		int _selection;
		String _propertiesFileName = fileNameList_[0];
		String _graphFileName = fileNameList_[1];
		String[] _airportNames;
		BigDecimal _averageProfit;
		ConsoleView _consoleOut = new ConsoleView();
		FlightRCPManager _flightRCPManager;
		Boolean _hasSimBeenRun = false;
		
		Scanner _input = new Scanner(System.in);
		
		do {
			_selection = _consoleOut.showMainMenu(consoleLogger_, _input);
			
			switch(_selection) {
				case 1:
					fileNameList_ = _consoleOut.promptUserForFileNames(consoleLogger_, _input);
					if(fileNameList_[0] != null && !(fileNameList_[0].isEmpty())) {
						_propertiesFileName = fileNameList_[0];
					}
					if(fileNameList_[1] != null && !(fileNameList_[1].isEmpty())) {
						_graphFileName = fileNameList_[1];
					}
					_hasSimBeenRun = false;
					break;
				case 2:
					simulation_.getListOfFlights().clear();
					simulation_.getGraphOfAirports().clearGraph();
					simulator_.runSimulation(_propertiesFileName, _graphFileName, simulation_);
					if(simulation_.getListOfFlights().size() != 0) {
					_hasSimBeenRun = true;
					}
					break;
				case 3:
					if(_hasSimBeenRun) {
					_consoleOut.resultsView(consoleLogger_, simulation_.getTotalProfit(), simulation_.getTotalCost(), 
							simulation_.getTotalRevenue(), simulation_.getListOfFlights().size(), 
							simulation_.getTotalProfit().divide(new BigDecimal(simulation_.getListOfFlights().size()),
									2, RoundingMode.FLOOR));
					}
					else {
						consoleLogger_.error("No simulation run, unable to show results\n");
					}
					break;
				case 4:
					if(_hasSimBeenRun) {
						_flightRCPManager = new FlightRCPManager(simulation_.getSimulationProperties());
						_airportNames = _consoleOut.findAverageBetweenAirports(_input);
						if(simulation_.getGraphOfAirports().areAirportsConnected(_airportNames[0], _airportNames[1])) {
							try {
								_averageProfit = _flightRCPManager.findAverageRCPPerEdge(simulation_.getListOfFlights(),
										simulation_.getGraphOfAirports(), _airportNames[0], _airportNames[1]);
								_consoleOut.displayAverageBetweenAirports(_averageProfit);
							} catch(NullPointerException _e) {
								consoleLogger_.error("There are no flights between the two airports\n");
							}
						}
						else if(!(simulation_.getGraphOfAirports().containsVertex(_airportNames[0]))
								|| !(simulation_.getGraphOfAirports().containsVertex(_airportNames[1]))) {
							consoleLogger_.error("Airport input not present in graph, cannot find average\n");
						}
						else {
							consoleLogger_.error("Airports are not connected, cannot find average\n");
						}
					} 
					else {
						consoleLogger_.error("No simulation run, unable to find profit\n");
					}
					break;
				case 5:
					simulation_.getListOfFlights().clear();
					simulation_.getGraphOfAirports().clearGraph();
					dataFile_ = _consoleOut.promptForDataFile(_input);
					if(dataFile_ == (null) || dataFile_.equals("")) {
						dataFile_ = "/default-data";
						consoleLogger_.error("Invalid entry, reverting to default-data file\n");
					}
					try {
						simulator_.runFromDataFile(_propertiesFileName, dataFile_, simulation_);
						_hasSimBeenRun = true;
					}
					catch (Exception e_) {
						_hasSimBeenRun = false;
						consoleLogger_.error("Error reading data, cannot run simulation\n");
					}
					break;
				case 6:
					if(_hasSimBeenRun) {
						simulation_.getGraphOfAirports().printGraph();
					}
					else {
						consoleLogger_.error("No simulation run, unable to display graph\n");
					}
					break;
				case 0:
					return;		
			}
		} while(_selection != 0);
		_input.close();
	}
}
