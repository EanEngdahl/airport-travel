package airlinesystemcontroller;

import airlinesystemview.*;
import airlinesystemmodel.*;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class AirlineSystemMain {

	public static void main(String[] args_) {
	
		String _propertiesFileName = "default.properties";
		String _graphFileName = "default-graph";
		String [] _fileNameList = new String [2];
		boolean _menuFlag = false;
		int _selection = 0;
		
		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");
		
		ConsoleView _consoleOut = new ConsoleView();
		CommandLineParser _parser = new DefaultParser();
		AirlineSimulation _commandHandler = new AirlineSimulation();
		
		Options _options = new Options();
		
		_options.addOption("p", "properties", true, "Properties file");
		_options.addOption("g", "graph", true, "Graph file");
		_options.addOption("m", "menu", false, "Load terminal menu");
		
		FlightList _listOfFlights = new FlightList();
		AirportGraph _airportGraph = new AirportGraph();
		Properties _modelProperties;
		
		try {
			CommandLine _cl = _parser.parse(_options, args_);
		
			if(_cl.hasOption('p')) {
				_propertiesFileName = _cl.getOptionValue('p');
			}
		
			if(_cl.hasOption('g')) {
				_graphFileName = _cl.getOptionValue('g');
			}
			
			if(_cl.hasOption('m')) {
				_menuFlag = true;
			}
			
		} catch(ParseException _e) {
			_e.getMessage();
		}

		if(!_menuFlag) {
			_commandHandler.runSimulation(_propertiesFileName, _graphFileName, 
					_listOfFlights, _airportGraph, _modelProperties);
			return;
		}
		
		
		do {
			_selection = _consoleOut.showMainMenu(_consoleLogger);
			
			switch(_selection) {
				case 1:
					_fileNameList = _consoleOut.promptUserForFilenames(_consoleLogger);
					_propertiesFileName = _fileNameList[0];
					_graphFileName = _fileNameList[1];
					break;
				case 2:
					_commandHandler.runSimulation(_propertiesFileName, _graphFileName, 
							_listOfFlights, _airportGraph, _modelProperties);
					break;
				case 3:
					_consoleOut.resultsView(_commandHandler.findTotalProfit(_listOfFlights), cost_, revenue_, totalFlights_);
			}
			
		} while(_selection != 0);
		
	}

}
