package airlinesystemcontroller;

import airlinesystemview.*;

import org.slf4j.Logger;

public class ConsoleViewController {

	public void menuController(Logger consoleLogger_, String [] fileNameList_, AirlineSimulation sim_) {
		int _selection;
		String _propertiesFileName = fileNameList_[0];
		String _graphFileName = fileNameList_[1];
		ConsoleView _consoleOut = new ConsoleView();
		
		do {
			_selection = _consoleOut.showMainMenu(consoleLogger_);
			
			switch(_selection) {
				case 1:
					fileNameList_ = _consoleOut.promptUserForFilenames(consoleLogger_);
					_propertiesFileName = fileNameList_[0];
					_graphFileName = fileNameList_[1];
					break;
				case 2:
					sim_.runSimulation(_propertiesFileName, _graphFileName);
					break;
				case 3:
					break;
				case 0:
					return;		
			}
		} while(_selection != 0);
	}
}
