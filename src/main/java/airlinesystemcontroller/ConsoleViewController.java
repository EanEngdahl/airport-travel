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
		String[] _airportNames;
		BigDecimal _averageProfit;
		ConsoleView _consoleOut = new ConsoleView();
		FlightRCPManager _flightRCPManager;
		
		Scanner _input = new Scanner(System.in);
		
		do {
			_selection = _consoleOut.showMainMenu(consoleLogger_, _input);
			
			switch(_selection) {
				case 1:
					fileNameList_ = _consoleOut.promptUserForFilenames(consoleLogger_, _input);
					_propertiesFileName = fileNameList_[0];
					_graphFileName = fileNameList_[1];
					break;
				case 2:
					sim_.getListOfFlights().clear();
					sim_.runSimulation(_propertiesFileName, _graphFileName);
					break;
				case 3:
					if(sim_.getListOfFlights().size() > 0) {
					_consoleOut.resultsView(consoleLogger_, sim_.getTotalProfit(), sim_.getTotalCost(), 
							sim_.getTotalRevenue(), sim_.getListOfFlights().size(), 
							sim_.getTotalCost().divide(new BigDecimal(sim_.getListOfFlights().size()),
									2, RoundingMode.FLOOR));
					}
					else {
						consoleLogger_.error("No simulation run, unable to show results");
					}
					break;
				case 4:
					_flightRCPManager = new FlightRCPManager(sim_.getModelProperties());
					_airportNames = _consoleOut.findAverageBetweenAirports(_input);
					if(sim_.getGraphOfAirports().areAirportsConnected(_airportNames[0], _airportNames[1])) {
						try {
							_averageProfit = _flightRCPManager.findAverageRCPPerEdge(sim_.getListOfFlights(),
									sim_.getGraphOfAirports(), _airportNames[0], _airportNames[1]);
							_consoleOut.displayAverageBetweenAirports(_averageProfit);
						} catch(NullPointerException _e) {
							consoleLogger_.error("There are no flights between the two airports");
						}
					}
					else {
						consoleLogger_.error("Airports are not connected, cannot find average");
					}
					break;
				case 0:
					return;		
			}
		} while(_selection != 0);
	}
}
