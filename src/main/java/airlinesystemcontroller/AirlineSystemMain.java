package airlinesystemcontroller;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirlineSystemMain {

	public static void main(String[] args_) {
		String _propertiesFileName = "/default.properties";
		String _graphFileName = "/default-graph";
		boolean _menuFlag = false;
		
		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");
		
		CommandLineParser _parser = new DefaultParser();
		HelpFormatter _formatter = new HelpFormatter();
		AirlineSimulation _simulation = new AirlineSimulation();
		ConsoleViewController _consoleOut = new ConsoleViewController();

		
		/*
		 *  Handles the parsing of command line arguments passed to the main
		 */
		Options _options = new Options();
		
		_options.addOption("p", "properties", true, "Properties file");
		_options.addOption("g", "graph", true, "Graph file");
		_options.addOption("m", "menu", false, "Load terminal menu");
		_options.addOption("h", "help", false, "Outputs the help descriptions");
		
		try {
			CommandLine _cl = _parser.parse(_options, args_);
			
			if(_cl.hasOption("h")) {
				_formatter.printHelp("airporttravel", _options, true);
				return;
			}
		
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

		/*
		 *  Decides whether or not a console menu was requested
		 */
		if(!_menuFlag) {
			_simulation.runSimulation(_propertiesFileName, _graphFileName);
			_consoleLogger.info("Total Profit = $" + _simulation.getTotalProfit().toString());
			return;
		}
		
		/*
		 *  Runs the console menu if it is applicable
		 */
		String _fileNameList[] = {_propertiesFileName, _graphFileName};
		_consoleOut.menuController(_consoleLogger, _fileNameList, _simulation);	
	}
}
