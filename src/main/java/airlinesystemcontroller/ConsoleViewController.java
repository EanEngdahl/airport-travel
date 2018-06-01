package airlinesystemcontroller;

import airlinesystemview.*;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;

public class ConsoleViewController {

	public void menuController(Logger consoleLogger_, String [] fileNameList_, AirlineSimulation sim_) {
		int _selection;
		String _propertiesFileName = fileNameList_[0];
		String _graphFileName = fileNameList_[1];
		String _dataFile;
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
					fileNameList_ = _consoleOut.promptUserForFilenames(consoleLogger_, _input);
					if(fileNameList_[0] != null && !(fileNameList_[0].isEmpty())) {
						_propertiesFileName = fileNameList_[0];
					}
					if(fileNameList_[1] != null && !(fileNameList_[1].isEmpty())) {
						_graphFileName = fileNameList_[1];
					}
					_hasSimBeenRun = false;
					break;
				case 2:
					sim_.getListOfFlights().clear();
					sim_.getGraphOfAirports().clearGraph();
					sim_.runSimulation(_propertiesFileName, _graphFileName);
					if(sim_.getListOfFlights().size() != 0) {
					_hasSimBeenRun = true;
					}
					break;
				case 3:
					if(_hasSimBeenRun) {
					_consoleOut.resultsView(consoleLogger_, sim_.getTotalProfit(), sim_.getTotalCost(), 
							sim_.getTotalRevenue(), sim_.getListOfFlights().size(), 
							sim_.getTotalProfit().divide(new BigDecimal(sim_.getListOfFlights().size()),
									2, RoundingMode.FLOOR));
					}
					else {
						consoleLogger_.error("No simulation run, unable to show results\n");
					}
					break;
				case 4:
					if(_hasSimBeenRun) {
						_flightRCPManager = new FlightRCPManager(sim_.getModelProperties());
						_airportNames = _consoleOut.findAverageBetweenAirports(_input);
						if(sim_.getGraphOfAirports().areAirportsConnected(_airportNames[0], _airportNames[1])) {
							try {
								_averageProfit = _flightRCPManager.findAverageRCPPerEdge(sim_.getListOfFlights(),
										sim_.getGraphOfAirports(), _airportNames[0], _airportNames[1]);
								_consoleOut.displayAverageBetweenAirports(_averageProfit);
							} catch(NullPointerException _e) {
								consoleLogger_.error("There are no flights between the two airports\n");
							}
						}
						else if(!(sim_.getGraphOfAirports().getGraphOfAirports().containsVertex(_airportNames[0]))
								|| !(sim_.getGraphOfAirports().getGraphOfAirports().containsVertex(_airportNames[1]))) {
							consoleLogger_.error("Airport input not present in graph, cannot find average\n");
						}
						else {
							consoleLogger_.error("Airports are not connected, cannot find average\n");
						}
					} else {
						consoleLogger_.error("No simulation run, unable to find profit\n");
					}
					break;
				case 5:
					sim_.getListOfFlights().clear();
					sim_.getGraphOfAirports().clearGraph();
					_dataFile = _consoleOut.promptForDataFile(_input);
					if(_dataFile == (null) || _dataFile.equals("")) {
						_dataFile = "/default-data";
						consoleLogger_.error("Invalid entry, using default data file\n");
					}
					try {
						sim_.runFromDataFile(_propertiesFileName, _dataFile);
						_hasSimBeenRun = true;
					}
					catch (Exception e_) {
						_hasSimBeenRun = false;
						consoleLogger_.error("Error reading data, cannot run simulation\n");
					}
					break;
				case 6:
					if(_hasSimBeenRun) {
						sim_.getGraphOfAirports().printGraph();
					}
					else {
						consoleLogger_.error("No simulation run, unable to display graph\n");
					}
					break;
				case 0:
					return;		
			}
		} while(_selection != 0);
	}
}
