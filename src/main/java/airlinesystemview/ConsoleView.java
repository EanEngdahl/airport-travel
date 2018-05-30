package airlinesystemview;

import org.slf4j.Logger;
import java.util.Scanner;
import java.math.BigDecimal;


public class ConsoleView {
	
	public String[] promptUserForFilenames(Logger output_) {
		String _fileNames[] = new String [2];
		int _selection;

		try(Scanner _input = new Scanner(System.in)) {
			do {
				output_.info("Input custom file paths. If none are input, use defaults.");
				output_.info("1. Enter custom properties file path\n"
						+ "2. Enter custom graph file path\n"
						+ "3. Run program");
			
				_selection = _input.nextInt();
				
				switch(_selection) {
					case 1:
						output_.info("File path: ");
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

			} while(_selection != 3);

		} catch(Exception e_) {
			output_.error("Prompt input error");
		}
		
		return _fileNames;
	}	

	public int showMainMenu(Logger output_) {
		int _selection;
		
		output_.info("MAIN MENU:");
		output_.info("1. Input custom files"
				+	 "2. Run simulation"
				+	 "3. Show results"
				+	 "0. Quit");

		try(Scanner _input = new Scanner(System.in)) {
		
			for(;;) {
				_selection = _input.nextInt();

				switch(_selection) {
						case 0:
							return 0;
						case 1:
							return 1;
						case 2:
							return 2;
						case 3:
							return 3;
						default:
							output_.info("Input a valid option");
							break;
				}
			}
		} catch(Exception e_) {
			output_.error("Menu input error");
			return 0;
		}
	}
	
	public void resultsView(Logger output_, BigDecimal profit_, BigDecimal cost_, 
			BigDecimal revenue_, int totalFlights_) {
		output_.info("*****Flight Results*****");
		output_.info("Total number of flights: " + Integer.toString(totalFlights_)
				+ "Total revenue: " + revenue_.toString()
				+ "Total cost: " + cost_.toString()
				+ "Total Profit: " + profit_.toString());
	}
}
