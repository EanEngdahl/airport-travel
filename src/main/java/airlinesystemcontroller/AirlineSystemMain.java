package airlinesystemcontroller;

import airlinesystemview.*;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirlineSystemMain {

	public static void main(String[] args_) {
	
		String _propertiesFileName = "default.properties";
		String _graphFileName = "default-graph";
		boolean _menuFlag = false;
		int _selection = 0;
		
		Logger _consoleLogger = LoggerFactory.getLogger("consoleLogger");
		
		ConsoleView _consoleOut = new ConsoleView();
		CommandLineParser _parser = new DefaultParser();
		CommandHandler _commandHandler = new CommandHandler();
		
		Options _options = new Options();
		
		_options.addOption("p", "properties", true, "Properties file");
		_options.addOption("g", "graph", true, "Graph file");
		_options.addOption("m", "menu", false, "Load terminal menu");
		
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
			_commandHandler.NoUserInput(_propertiesFileName, _graphFileName);
			return;
		}
		
		do {
			_selection = _consoleOut.showMainMenu(_consoleLogger);
			// TODO: Finish handling menu options
		} while(_selection != 4);
		
	}

}
