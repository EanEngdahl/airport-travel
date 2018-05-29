package airlinesystemview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;


public class ConsoleView {
	
	public String[] promptUserForFilenames(Logger output_) {
		String _fileNames[] = new String [2];
		int _command;

		try(Scanner _input = new Scanner(System.in)) {
			do {
				output_.info("Input custom file paths. If none are input, use defaults.");
				output_.info("1. Enter custom properties file path\n"
						+ "2. Enter custom graph file path\n"
						+ "3. Run program");
			
				_command = _input.nextInt();
				
				switch(_command) {
					case 1:
						output_.info("Filepath: ");
						_fileNames[0] = _input.nextLine();
						break;
					case 2:
						output_.info("File path: ");
						_fileNames[1] = _input.nextLine();
						break;
					case 3:
						output_.info("Running program");
						break;
					default:
						output_.info("Input a valid option");
						break; 
				}

			} while(_command != 3);

		} catch(Exception e) {
		}
		
		return _fileNames;
	}	

	public int showMainMenu(Logger output_) {
		output_.info("MAIN MENU:");
		return 0;
	}
}
